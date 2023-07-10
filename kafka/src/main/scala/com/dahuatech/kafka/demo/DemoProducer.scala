package com.dahuatech.kafka.demo

import org.apache.kafka.clients.producer._
import org.slf4j.{Logger, LoggerFactory}

object DemoProducer {

  private val logger: Logger = LoggerFactory.getLogger(getClass)
  private val topicName = "first"

  def main(args: Array[String]): Unit = {

    val kafkaProducer: KafkaProducer[String, String] = new KafkaProducer[String, String](KafkaParamConfig.getProducerProperties())

    // kafkaProducer.initTransactions()
    // kafkaProducer.beginTransaction()

    try {
      for (i <- 0 until Integer.MAX_VALUE) {
        kafkaProducer.send(
          new ProducerRecord[String, String](topicName, "key" + i, "value" + i)
        )
      }
      // kafkaProducer.commitTransaction() // 提交事务
    } catch {
      // case exp: Exception => kafkaProducer.abortTransaction() // 发送异常 终止事务
      case exp: Exception => exp.printStackTrace()
    } finally {
      kafkaProducer.close()
    }
  }

}
