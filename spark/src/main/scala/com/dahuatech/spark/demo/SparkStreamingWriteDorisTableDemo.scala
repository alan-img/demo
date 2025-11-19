package com.dahuatech.spark.demo

import com.dahuatech.spark.utils.SparkUtil
import org.apache.spark.sql.functions.{col, from_json}
import org.apache.spark.sql.types.{StringType, StructType}

object SparkStreamingWriteDorisTableDemo {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkUtil.getLocalSparkSession()
    import sparkSession.implicits._

    // 定义 JSON schema
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
      .option("startingOffsets", "latest")
      .load()

    // Kafka 中的 value 是 binary，需要转成 string 再解析 JSON
    val parsedDF = kafkaDF
      .selectExpr("CAST(value AS STRING)")
      .select(from_json(col("value"), schema).as("data"))
      .select("data.*")  // 展开结构体字段

    // 输出到控制台
    val query = parsedDF.writeStream
      .format("console")
      .option("truncate", "false")
      .trigger(org.apache.spark.sql.streaming.Trigger.ProcessingTime("5 seconds")) // 5秒触发
      .start()

    query.awaitTermination()

  }


}
