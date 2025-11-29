package com.dahuatech.spark.demo

import com.dahuatech.spark.utils.SparkUtil
import org.apache.spark.sql.{Dataset, Row}
import org.apache.spark.sql.functions.{col, from_json}
import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.types.{StringType, StructType}
import scalaj.http.Http

object SparkStreamingWriteDorisTableDemo {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkUtil.getLocalSparkSession()
    import sparkSession.implicits._

    // 定义 Schema
    val schema = new StructType()
      .add("create_time", StringType)
      .add("city", StringType)
      .add("weather", StringType)
      .add("temperature", StringType)

    // 读取 Kafka
    val kafkaDF = sparkSession.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "hadoop101:9092,hadoop102:9092,hadoop103:9092")
      .option("subscribe", "weather_data")
      .option("startingOffsets", "earliest")
      .load()

    // 解析 JSON
    val parsedDF = kafkaDF
      .selectExpr("CAST(value AS STRING) AS json")
      .select(from_json(col("json"), schema).as("data"))
      .select("data.*")

    val dorisOptions = Map(
      "doris.table.identifier" -> "demo.weather_data",
      "doris.fenodes" -> "hadoop108:8030",
      "user" -> "root",
      "password" -> "",
      "sink.batch.size" -> "2000",
      "sink.max-retries" -> "3"
    )

    // ★ foreachBatch：每批写入 Doris 该方式不推荐，推荐使用doris streaming load方式
    val query = parsedDF.writeStream
      .trigger(Trigger.ProcessingTime("100 milliseconds")) // 5毫秒秒出发一次
      .option("checkpointLocation", "D:\\pct\\idea\\spark\\src\\main\\checkpoint")
      // 写入到doris库
       .format("doris")
       .options(dorisOptions)
       .start()

      // 打印到控制台
//      .format("console")
//      .option("truncate", "false")
//      .start()

    query.awaitTermination()
  }


}
