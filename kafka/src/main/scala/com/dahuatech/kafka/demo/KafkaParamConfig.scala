package com.dahuatech.kafka.demo

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.{StringDeserializer, StringSerializer}

import java.util.Properties

object KafkaParamConfig {

  val BOOTSTRAP_SERVERS_CONFIG = "10.38.21.66:9092"
  val GROUP_ID_CONFIG = "kafka-consumer-group"
  val BUFFER_MEMORY_CONFIG = "33554432" // 32MB
  val BATCH_SIZE_CONFIG = "16384" // 16KB
  val LINGER_MS_CONFIG = "5" // 默认0ms
  val COMPRESSION_TYPE_CONFIG = "snappy"
  val ACKS_CONFIG = "all" // 和设置为-1相等 默认1
  val RETRIES_CONFIG = "3" // 默认是int的最大值
  val ENABLE_IDEMPOTENCE_CONFIG = "true" // 开启幂等性 默认就是true表示开启 这里显式的写出来
  val TRANSACTIONAL_ID_CONFIG = "transactional_id_config"
  val ENABLE_AUTO_COMMIT_CONFIG = "true" // 自动提交kafka消费者offset 默认就是true
  val AUTO_COMMIT_INTERVAL_MS_CONFIG = "5000" // 默认5000 ms
  val MAX_POLL_INTERVAL_MS_CONFIG = "300000" // 默认300000

  /**
   * 获取生产者properties
   * @return
   */
  def getProducerProperties(): Properties = {
    val prop: Properties = new Properties()
    prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaParamConfig.BOOTSTRAP_SERVERS_CONFIG)
    prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
    prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
    // prop.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, classOf[CustomizePartitioner].getName)
    prop.put(ProducerConfig.BUFFER_MEMORY_CONFIG, KafkaParamConfig.BUFFER_MEMORY_CONFIG)
    prop.put(ProducerConfig.BATCH_SIZE_CONFIG, KafkaParamConfig.BATCH_SIZE_CONFIG)
    prop.put(ProducerConfig.LINGER_MS_CONFIG, KafkaParamConfig.LINGER_MS_CONFIG)
    prop.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, KafkaParamConfig.COMPRESSION_TYPE_CONFIG)
    prop.put(ProducerConfig.ACKS_CONFIG, KafkaParamConfig.ACKS_CONFIG)
    prop.put(ProducerConfig.RETRIES_CONFIG, KafkaParamConfig.RETRIES_CONFIG)
    // prop.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, KafkaParamConfig.ENABLE_IDEMPOTENCE_CONFIG)
    // prop.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, KafkaParamConfig.TRANSACTIONAL_ID_CONFIG)

    prop
  }

  /**
   * 获取消费者properties
   * @return
   */
  def getConsumerProperties(): Properties = {
    val prop: Properties = new Properties()
    prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaParamConfig.BOOTSTRAP_SERVERS_CONFIG)
    prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
    prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
    prop.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaParamConfig.GROUP_ID_CONFIG)
    prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, KafkaParamConfig.ENABLE_AUTO_COMMIT_CONFIG)
    prop.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, KafkaParamConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG)
    prop.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, KafkaParamConfig.MAX_POLL_INTERVAL_MS_CONFIG)

    prop
  }
}
