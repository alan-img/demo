package com.dahuatech.spark.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}

import scala.collection.mutable

object KafkaParamConfig {

  val BOOTSTRAP_SERVERS_CONFIG = "hadoop101:9092,hadoop102:9092,hadoop103:9092"
  val BUFFER_MEMORY_CONFIG = "33554432" // 32MB
  val BATCH_SIZE_CONFIG = "16384" // 16KB
  val LINGER_MS_CONFIG = "1" // 1ms
  val ACKS_CONFIG = "-1" // 和设置为all相等
  val RETRIES_CONFIG = "3" // 默认是int的最大值

  /**
   * 获取生产者properties
   *
   * @return
   */
  def getProducerProperties(): mutable.Map[String, String] = {
    val map: mutable.Map[String, String] = mutable.Map[String, String]()
    map += ((ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaParamConfig.BOOTSTRAP_SERVERS_CONFIG))
    map += ((ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName))
    map += ((ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName))
    map += ((ProducerConfig.BUFFER_MEMORY_CONFIG, KafkaParamConfig.BUFFER_MEMORY_CONFIG))
    map += ((ProducerConfig.BATCH_SIZE_CONFIG, KafkaParamConfig.BATCH_SIZE_CONFIG))
    map += ((ProducerConfig.LINGER_MS_CONFIG, KafkaParamConfig.LINGER_MS_CONFIG))
    map += ((ProducerConfig.ACKS_CONFIG, KafkaParamConfig.ACKS_CONFIG))
    map += ((ProducerConfig.RETRIES_CONFIG, KafkaParamConfig.RETRIES_CONFIG))

    map
  }

  val GROUP_ID_CONFIG = "spark.kafka.consumer.group"
  val ENABLE_AUTO_COMMIT_CONFIG = "false" // 自动提交kafka消费者组offset
  val AUTO_COMMIT_INTERVAL_MS_CONFIG = "5000" // 默认5000ms
  val AUTO_OFFSET_RESET_CONFIG = "earliest" // 偏移量无效后自动将偏移量移动到最早 默认latest最新
  val MAX_POLL_INTERVAL_MS_CONFIG = "10800000" // 消费者多久不提交偏移量则认为消费者异常 驱逐出消费者组 默认5分钟

  /**
   * 获取消费者properties
   * @return
   */
  def getConsumerProperties(): mutable.Map[String, String] = {
    val map: mutable.Map[String, String] = mutable.Map[String, String]()
    map += ((ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaParamConfig.BOOTSTRAP_SERVERS_CONFIG))
    map += ((ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName))
    map += ((ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName))
    map += ((ConsumerConfig.GROUP_ID_CONFIG, KafkaParamConfig.GROUP_ID_CONFIG))
    map += ((ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, KafkaParamConfig.ENABLE_AUTO_COMMIT_CONFIG))
    map += ((ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, KafkaParamConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG))
    map += ((ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, KafkaParamConfig.AUTO_OFFSET_RESET_CONFIG))
    map += ((ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, KafkaParamConfig.MAX_POLL_INTERVAL_MS_CONFIG))

    map
  }
}
