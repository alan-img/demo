package com.dahuatech.spark.demo

import com.dahuatech.spark.config.KafkaParamConfig
import com.dahuatech.spark.utils.SparkUtil
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{InputDStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Durations, StreamingContext}

import scala.collection.JavaConversions._
import scala.collection.immutable.{NumericRange, Stack}
import scala.collection.mutable

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
  def main(args: Array[String]): Unit = {

    val streamingContext = new StreamingContext(SparkUtil.sparkConf, Durations.seconds(3))

    val kafkaDStream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream(
      streamingContext,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](
        Array("first"),
        KafkaParamConfig.getConsumerProperties().toMap[String, String]
      )
    )

    kafkaDStream.map {
      consumerRecord => println(consumerRecord.value())
    }.print()
  }
}
