package com.dahuatech.kafka.demo

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

object SparkDemoConsumer {

  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName(getClass.getName)
    val ssc: StreamingContext = new StreamingContext(sparkConf, Seconds(3))

    val kafkaParams: Map[String, Object] = Map[String, String](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> KafkaParamConfig.BOOTSTRAP_SERVERS_CONFIG,
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer].getName,
      (ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName),
      (ConsumerConfig.GROUP_ID_CONFIG, KafkaParamConfig.GROUP_ID_CONFIG)
    )

    val kafkaStreaming: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream(
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](Array("first", "second"), kafkaParams)
    )

    kafkaStreaming.map(kv => kv.value()).print

    ssc.start()
    ssc.awaitTermination()

  }

}
