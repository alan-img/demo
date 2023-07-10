package com.dahuatech.kafka.demo

import org.apache.kafka.clients.producer._
import org.apache.kafka.common.serialization.StringSerializer
import org.slf4j.{Logger, LoggerFactory}

import java.util.{Objects, Properties}

object DemoProducer {

  private val logger: Logger = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]): Unit = {

    val prop: Properties = new Properties()
    prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaParamConfig.BOOTSTRAP_SERVERS_CONFIG)
    prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
    prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer].getName)
//    prop.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, classOf[CustomizePartitioner].getName)
    prop.put(ProducerConfig.BUFFER_MEMORY_CONFIG, KafkaParamConfig.BUFFER_MEMORY_CONFIG)
    prop.put(ProducerConfig.BATCH_SIZE_CONFIG, KafkaParamConfig.BATCH_SIZE_CONFIG)
    prop.put(ProducerConfig.LINGER_MS_CONFIG, KafkaParamConfig.LINGER_MS_CONFIG)
    prop.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, KafkaParamConfig.COMPRESSION_TYPE_CONFIG)
    prop.put(ProducerConfig.ACKS_CONFIG, KafkaParamConfig.ACKS_CONFIG)
    prop.put(ProducerConfig.RETRIES_CONFIG, KafkaParamConfig.RETRIES_CONFIG)
    prop.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, KafkaParamConfig.ENABLE_IDEMPOTENCE_CONFIG)
    prop.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, KafkaParamConfig.TRANSACTIONAL_ID_CONFIG)

    val kafkaProducer: KafkaProducer[String, String] = new KafkaProducer[String, String](prop)

    kafkaProducer.initTransactions()
    kafkaProducer.beginTransaction()

    try {
      for (i <- 1 to 10) {
        kafkaProducer.send(
          new ProducerRecord[String, String]("first", "key" + i, "value" + i),
          new Callback {
            override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
              if (Objects.isNull(exception)) {
                println(s"topic: ${metadata.topic()}, partition: ${metadata.partition()}, offset: ${metadata.offset()}, timestamp: ${metadata.timestamp()}, hashtimestamp: ${metadata.hasTimestamp}, serializedKeySize: ${metadata.serializedKeySize()}, serializedValueSize: ${metadata.serializedValueSize()}")
              } else {
                println(s"happen exception: ${exception.getMessage}")
              }
            }
          }
        )
      }

      kafkaProducer.commitTransaction() // 提交事务
    } catch {
      case exp: Exception => kafkaProducer.abortTransaction() // 发送异常 终止事务
    } finally {
      kafkaProducer.close()
    }

  }

}
