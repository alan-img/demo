package com.dahuatech.spark.demo

import com.alibaba.fastjson.{JSON, JSONArray, JSONObject}
import com.dahuatech.spark.config.KafkaParamConfig
import com.dahuatech.spark.utils.DateTimeUtil
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Durations, StreamingContext, Time}
import org.apache.spark.{Partitioner, SparkConf, SparkContext, TaskContext}
import org.slf4j.{Logger, LoggerFactory}

import java.time.LocalDate
import java.time.format.DateTimeFormatter


/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.spark.demo</p>
 * <p>className: SparkStreamDemo</p>
 * <p>date: 2023/6/15</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */

object SparkSubmitStreamDemo {
  /**
   * 提交任务命令示例
   * spark-submit
   * --master yarn
   * --deploy-mode cluster
   * --class com.dahuatech.spark.demo.SparkSQLDemo
   * --conf spark.driver.extraJavaOptions=-Dlog4j.configuration=log4j.properties
   * --conf spark.executor.extraJavaOptions=-Dlog4j.configuration=log4j.properties
   * --files log4j.properties
   * spark-jar-with-dependencies.jar
   * @param args
   */

  def main(args: Array[String]): Unit = {
    val configMap = parameterTool(args)

    val sparkConf: SparkConf = new SparkConf().setAppName(getClass.getName)
      .set("spark.streaming.kafka.maxRatePerPartition", s"${configMap.getOrElse("ratePerPartition", "500")}")
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .registerKryoClasses(Array(classOf[ConsumerRecord[String, String]]))

    val streamingContext: StreamingContext = new StreamingContext(
      SparkContext.getOrCreate(sparkConf),
      Durations.seconds(configMap.getOrElse("batchTime", "60").toLong)
    )
    val sparkSession = SparkSession.builder()
      .config(sparkConf)
      .config("hive.exec.dynamic.partition", "true")
      .config("hive.exec.dynamic.partition.mode", "nonstrict")
      .config("spark.sql.sources.partitionOverwriteMode", "dynamic")
      .enableHiveSupport()
      .getOrCreate()
    import sparkSession.implicits._

    val inputDStream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream(
      streamingContext,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe(Array("weather_data"), KafkaParamConfig.getConsumerProperties())
    )

    val canCommitOffsets: CanCommitOffsets = inputDStream.asInstanceOf[CanCommitOffsets]
    var hasOffsetRanges: HasOffsetRanges = null

    val dStream: DStream[WeatherInfo] = inputDStream.transform((kafkaRDD: RDD[ConsumerRecord[String, String]], batchTime: Time) => {
        hasOffsetRanges = kafkaRDD.asInstanceOf[HasOffsetRanges]
        kafkaRDD.map(kv => kv.value()).map(json => {
          /**
           * {
           * "city": "Shanghai",
           * "weather": "阴",
           * "temperature": "30",
           * "create_time": "2025-08-13 00:53:19"
           * }
           */
          val obj = JSON.parseObject(json)
          val city = obj.getString("city")
          val weather = obj.getString("weather")
          val temperature = obj.getLong("temperature")
          val create_time = obj.getString("create_time").slice(0, 10)

          WeatherInfo(city, weather, temperature, create_time)
        })
      })

    dStream.foreachRDD((batchRDD: RDD[WeatherInfo], batchTime: Time) => {
      batchRDD.toDF().write.mode(SaveMode.Append).partitionBy("create_time").saveAsTable("weather_data")

      val dt_minus_7 = LocalDate.now.minusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
      val partitionList = sparkSession.sql(
        """
          |show partitions weather_data
          |""".stripMargin).collect().map(row => row.getString(0).split("=")(1))
      partitionList.foreach {
        partition =>
          println(s"partition: ${partition}")
          if (partition < dt_minus_7) {
            sparkSession.sql(s"alter table weather_data drop partition(create_time = '${partition}')")
          }
      }

      canCommitOffsets.commitAsync(hasOffsetRanges.offsetRanges)
    })

    streamingContext.start()
    streamingContext.awaitTermination()
  }

  def parameterTool(args: Array[String]): Map[String, String] = {
    args.map(x => x.split("=")).map(x => (x(0), x(1))).toMap
  }

  case class WeatherInfo(city: String, weather: String, temperature: Long, create_time: String)
}
