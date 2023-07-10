package com.dahuatech.kafka.demo

object KafkaParamConfig {

  val BOOTSTRAP_SERVERS_CONFIG = "hadoop101:9092,hadoop102:9092,hadoop103:9092"
  val GROUP_ID_CONFIG = "kafka-consumer-group"
  val BUFFER_MEMORY_CONFIG = "33554432" // 32MB
  val BATCH_SIZE_CONFIG = "16384" // 16KB
  val LINGER_MS_CONFIG = "1" // 1ms
  val COMPRESSION_TYPE_CONFIG = "snappy"
  val ACKS_CONFIG = "-1" // 和设置为all相等
  val RETRIES_CONFIG = "3" // 默认是int的最大值
  val ENABLE_IDEMPOTENCE_CONFIG = "true" // 开启幂等性 默认就是true表示开启 这里显式的写出来
  val TRANSACTIONAL_ID_CONFIG = "transactional_id_config"
  val ENABLE_AUTO_COMMIT_CONFIG = "true" // 自动提交kafka消费者offset
  val AUTO_COMMIT_INTERVAL_MS_CONFIG = "5000" // 默认5000 单位: ms
}
