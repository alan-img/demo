package com.dahuatech.spark.demo

import com.alibaba.fastjson.{JSON, JSONArray, JSONObject}
import com.dahuatech.spark.config.KafkaParamConfig
import com.dahuatech.spark.utils.{DateTimeUtil, SparkUtil}
import org.apache.kafka.clients.consumer.{ConsumerRecord, ConsumerRecords}
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Durations, StreamingContext, Time}
import org.apache.spark.{Partitioner, SparkConf, SparkContext, TaskContext}
import org.json4s.scalap.scalasig.ScalaSigEntryParsers.index
import org.slf4j.{Logger, LoggerFactory}


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

object SparkStreamDemo {
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

  private val logger: Logger = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]): Unit = {
    val sparkConf: SparkConf = new SparkConf().setAppName(getClass.getName)
      .setMaster("local[*]")
      .set("spark.streaming.kafka.maxRatePerPartition", "5")
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .registerKryoClasses(Array(classOf[ConsumerRecord[String, String]]))
    val streamingContext = new StreamingContext(SparkContext.getOrCreate(sparkConf), Durations.seconds(10))

    val inputDStream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream(
      streamingContext,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe(Array("first"), KafkaParamConfig.getConsumerProperties())
    )

    val canCommitOffsets: CanCommitOffsets = inputDStream.asInstanceOf[CanCommitOffsets]
    var hasOffsetRanges: HasOffsetRanges = null

    val dStream: DStream[(Long, ConsumerRecord[String, String])] =
      inputDStream.transform((kafkaRDD: RDD[ConsumerRecord[String, String]], batchTime: Time) => {
        hasOffsetRanges = kafkaRDD.asInstanceOf[HasOffsetRanges]

        kafkaRDD.mapPartitionsWithIndex((index, iter) => {
          new Iterator[(Long, ConsumerRecord[String, String])] {
            var bodyNum = 0L

            override def hasNext: Boolean = {
              val hasNext: Boolean = iter.hasNext
              if (!hasNext) {
                println(s"The ${index}-th partition total process ${bodyNum} pieces of data")
              }
              hasNext
            }

            override def next(): (Long, ConsumerRecord[String, String]) = {
              val consumerRecord: ConsumerRecord[String, String] = iter.next()
              val jsonObject: JSONObject = JSON.parseObject(consumerRecord.value())
              val jsonArray: JSONArray = jsonObject.getJSONArray("bodiesInfo")
              bodyNum += jsonArray.size()
              (index, consumerRecord)
            }
          }
        })
      }).cache()

    dStream.foreachRDD((batchRDD: RDD[(Long, ConsumerRecord[String, String])], batchTime: Time) => {
      batchRDD.foreachPartition(iter => {
        println(s"The ${DateTimeUtil.toLocalDateTimeString(DateTimeUtil.toLocalDateTime(batchTime.milliseconds), "yyyy-MM-dd-HH-mm-ss")} ${TaskContext.getPartitionId()}-th partition process ${iter.size} pieces of data")
      })
    })

    val groupByKeyAndWindow: DStream[(Long, Iterable[ConsumerRecord[String, String]])] = dStream.groupByKeyAndWindow(
      Durations.seconds(10 * 2),
      Durations.seconds(10 * 2),
      new Partitioner {
        override def numPartitions: Int = 3
        override def getPartition(key: Any): Int = key.toString.toInt.hashCode()
      }
    )

    groupByKeyAndWindow.foreachRDD((batchRDD: RDD[(Long, Iterable[ConsumerRecord[String, String]])], batchTime: Time) => {
      batchRDD.foreachPartition((iter: Iterator[(Long, Iterable[ConsumerRecord[String, String]])]) => {
        iter.foreach(iter => {
          println(s"index: ${iter._1}, iter: ${iter._2.size}")
        })
      })

      canCommitOffsets.commitAsync(hasOffsetRanges.offsetRanges)
      inputDStream.getClass.getDeclaredMethod("commitAll").invoke(inputDStream)
    })

    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
