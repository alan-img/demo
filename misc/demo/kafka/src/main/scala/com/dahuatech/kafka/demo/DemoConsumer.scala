package com.dahuatech.kafka.demo

import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecords, KafkaConsumer}
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.{Logger, LoggerFactory}

import java.time.Duration
import java.util.Properties
import java.{lang, util}
import scala.collection.JavaConverters._

object DemoConsumer {

  private val logger: Logger = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]): Unit = {

    val prop: Properties = new Properties()
    prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaParamConfig.BOOTSTRAP_SERVERS_CONFIG)
    prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
    prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
    prop.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaParamConfig.GROUP_ID_CONFIG)
    prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, KafkaParamConfig.ENABLE_AUTO_COMMIT_CONFIG)
    prop.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, KafkaParamConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG)

    val kafkaConsumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](prop)

//    1.消费者消费指定topic 消费所有分区数据
    val topics: util.ArrayList[String] = new util.ArrayList[String]()
    topics.add("first")
    kafkaConsumer.subscribe(topics)

//    2.消费者消费topic的指定分区数据
//    val topicPartitions: util.ArrayList[TopicPartition] = new util.ArrayList[TopicPartition]()
//    topicPartitions.add(new TopicPartition("first", 0))
//    kafkaConsumer.assign(topicPartitions)

//    循环获取TopicPartition 知道获取到为止 保证获取到所有TopicPartition
    var topicPartitions: util.Set[TopicPartition] = kafkaConsumer.assignment()
    while (topicPartitions.size() == 0) {
      kafkaConsumer.poll(Duration.ofSeconds(1))
      topicPartitions = kafkaConsumer.assignment()
    }

//    3.指定offset消费
//    topicPartitions.asScala.foreach{
//      topicPartition => kafkaConsumer.seek(topicPartition, 10L)
//    }

//    4.将消费者组中的offset至于最新
//    kafkaConsumer.seekToEnd(topicPartitions)

//    5.将消费者组中的offset至于最老
//    kafkaConsumer.seekToBeginning(topicPartitions)

//    6.指定时间消费 核心逻辑将时间戳转换为offset
//    val topicPartitionLongHashMap: util.HashMap[TopicPartition, lang.Long] = new util.HashMap[TopicPartition, lang.Long]()
//    topicPartitions.asScala.foreach{
//      topicPartition => topicPartitionLongHashMap.put(topicPartition, System.currentTimeMillis() - 1 * 24 * 3600 * 1000)
//    }
//    val partitionToTimestamp: util.Map[TopicPartition, OffsetAndTimestamp] = kafkaConsumer.offsetsForTimes(topicPartitionLongHashMap)
//    循环给每个partition指定偏移量
//    topicPartitions.asScala.foreach{
//      topicPartition => kafkaConsumer.seek(topicPartition, partitionToTimestamp.get(topicPartition).offset())
//    }

//    7.查看每个分区最大offset
    val partitionToOffset: util.Map[TopicPartition, lang.Long] = kafkaConsumer.endOffsets(topicPartitions)
    partitionToOffset.asScala.foreach {
      case (topicPartition, offset) => println(topicPartition, offset)
    }

//    8.查看每个分区最小offset
//    val partitionToOffset: util.Map[TopicPartition, lang.Long] = kafkaConsumer.beginningOffsets(topicPartitions)
//    partitionToOffset.asScala.foreach {
//      case (topicPartition, offset) => println(topicPartition, offset)
//    }

    try {
      while (true) {
        val consumerRecords: ConsumerRecords[String, String] = kafkaConsumer.poll(Duration.ofSeconds(1))
        consumerRecords.asScala.foreach{
          consumerRecords => println(consumerRecords)
        }
//        kafkaConsumer.commitAsync() // 手动异步提交offset 生产中常用异步提交
      }
    } catch {
      case exp: Exception => println(s"happen exception: ${exp.getMessage}")
    } finally {
      kafkaConsumer.close() // 最终关闭kafka消费者
    }

  }

}
