package com.dahuatech.kafka.demo

import org.apache.kafka.clients.consumer.{ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.TopicPartition
import org.slf4j.{Logger, LoggerFactory}

import java.time.Duration
import java.util
import scala.collection.JavaConversions._
import scala.util.control.Breaks

object DemoConsumer {

  private val logger: Logger = LoggerFactory.getLogger(getClass)
  private val topicName = "first"
  private val partitionNum = 4

  def main(args: Array[String]): Unit = {

    val kafkaConsumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](KafkaParamConfig.getConsumerProperties())

    // 1.消费者消费指定topic 消费所有分区数据
    // kafkaConsumer.subscribe(util.Arrays.asList(topicName))
    // // 建议必须加上 不然无法获取topicPartitionSet集合 后续操作均无法完成
    // while (kafkaConsumer.assignment().size() == 0) kafkaConsumer.poll(Duration.ZERO)

    // 2.消费者消费topic的指定分区数据
    val topicPartitionList = new util.ArrayList[TopicPartition]()
    for (partitionIndex <- 0 until partitionNum) topicPartitionList.add(new TopicPartition(topicName, partitionIndex))
    kafkaConsumer.assign(topicPartitionList)

    // 获取所有TopicPartition 便于对分区数据进行设置
    val topicPartitionSet: util.Set[TopicPartition] = kafkaConsumer.assignment()
    println(topicPartitionSet)

    // 3.指定offset消费
    // topicPartitionSet.foreach {
    //   topicPartition => kafkaConsumer.seek(topicPartition, 0L)
    // }

    // 4.将消费者组中的offset至于最新
    // kafkaConsumer.seekToEnd(topicPartitionSet)

    // 5.将消费者组中的offset至于最老
    // kafkaConsumer.seekToBeginning(topicPartitionSet)

    // 6.指定时间消费 核心逻辑将时间戳转换为offset
    // val topicPartitionToLongMap: util.HashMap[TopicPartition, java.lang.Long] = new util.HashMap[TopicPartition, java.lang.Long]()
    // topicPartitionSet.foreach{
    //   topicPartition => topicPartitionToLongMap.put(topicPartition, System.currentTimeMillis() - 2 * 3600 * 1000)
    // }
    // val partitionToTimeMap: util.Map[TopicPartition, OffsetAndTimestamp] = kafkaConsumer.offsetsForTimes(topicPartitionToLongMap)
    // // 循环给每个partition指定偏移量
    // topicPartitionSet.foreach{
    //   topicPartition =>
    //     kafkaConsumer.seek(topicPartition, partitionToTimeMap.get(topicPartition).offset())
    // }

    // 7.查看每个分区最大offset
    //  val partitionToOffset: util.Map[TopicPartition, java.lang.Long] = kafkaConsumer.endOffsets(topicPartitionSet)
    //  partitionToOffset.foreach {
    //    case (topicPartition, offset) => println(topicPartition, offset)
    //  }

    // 8.查看每个分区最小offset
    // val partitionToOffset: util.Map[TopicPartition, java.lang.Long] = kafkaConsumer.beginningOffsets(topicPartitionSet)
    // partitionToOffset.foreach {
    //   case (topicPartition, offset) => println(topicPartition, offset)
    // }

    try {
      while (true) {
        val consumerRecords: ConsumerRecords[String, String] = kafkaConsumer.poll(Duration.ofMillis(100L))
        consumerRecords.foreach {
          consumerRecords => {
            println(s"topic: ${consumerRecords.topic()}, partition: ${consumerRecords.partition()}, key: ${consumerRecords.key()}, value: ${consumerRecords.value()}")
          }
        }
      }
    } catch {
      case exp: Exception => exp.printStackTrace()
    } finally {
      kafkaConsumer.close()
    }
  }
}
