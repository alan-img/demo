package com.dahuatech.kafka.demo

import org.apache.kafka.clients.producer._
import org.slf4j.{Logger, LoggerFactory}

import java.util.{Objects, Random}
import java.util.concurrent.TimeUnit
import scala.collection.immutable

object DemoProducer {
  private val logger: Logger = LoggerFactory.getLogger(getClass)
  private val topicName: String = "first"
  val kafkaProducer: KafkaProducer[String, String] = new KafkaProducer[String, String](KafkaParamConfig.getProducerProperties())

  def generateRandomString(length: Int): String = {
    val chars: immutable.IndexedSeq[Char] = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9') ++ Seq('/', '+')
    val random = new Random
    (1 to length).map(_ => chars(random.nextInt(chars.length))).mkString
  }

  // /**
  //  * 异步精确发送一次
  //  */
  // def asyncExactlyOnceSendRecord(): Unit = {
  //   kafkaProducer.initTransactions()
  //   kafkaProducer.beginTransaction()
  //
  //   try {
  //     for (i <- 0 until 10) {
  //       kafkaProducer.send(
  //         new ProducerRecord[String, String](topicName, "key_" + i, generateRandomString(10))
  //       )
  //     }
  //     kafkaProducer.commitTransaction() // 提交事务
  //   } catch {
  //     case exp: Exception => {
  //       logger.error("kafka producer send record failed", exp)
  //       kafkaProducer.abortTransaction() // 发送异常 终止事务
  //     }
  //   } finally {
  //     kafkaProducer.close()
  //   }
  // }
  //
  // /**
  //  * 同步精确发送一次 不推荐使用同步 同步发送性能太差了
  //  */
  // def syncExactlyOnceSendRecord(): Unit = {
  //   kafkaProducer.initTransactions()
  //   kafkaProducer.beginTransaction()
  //
  //   try {
  //     for (_ <- 0 until 10) {
  //       kafkaProducer.send(
  //         new ProducerRecord[String, String](topicName, generateRandomString(10), generateRandomString(512))
  //       )
  //     }
  //     kafkaProducer.commitTransaction() // 提交事务
  //   } catch {
  //     case exp: Exception => {
  //       logger.error("kafka producer send record failed", exp)
  //       kafkaProducer.abortTransaction() // 发送异常 终止事务
  //     }
  //   } finally {
  //     kafkaProducer.close()
  //   }
  // }

  def main(args: Array[String]): Unit = {
    while (true) {
      val random: Int = new Random().nextInt(3)
      kafkaProducer.send(
        new ProducerRecord[String, String](topicName, random, random + "", generateRandomString(20)),
        new Callback {
          override def onCompletion(metadata: RecordMetadata, exception: Exception): Unit = {
            if (Objects.isNull(exception)) {
              println("success")
            } else {
              println("failure")
            }
          }
        }
      )

      println("alan")
    }

    kafkaProducer.close()
  }
}
