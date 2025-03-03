package com.dahuatech.flink.util

import org.apache.kafka.common.serialization.StringDeserializer

import java.util.Properties

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.flink.util</p>
 * <p>className: KafkaUtils</p>
 * <p>date: 2025/2/20</p>
 *
 * @author qinjiawei(Administrator)
 * @since JDK8.0
 * @version 1.0.0
 */

object KafkaUtils {

  val TOPIC_FIRST = "first"

  def getKafkaConsumerProperties(): Properties = {
    val properties: Properties = new Properties()
    properties.setProperty("bootstrap.servers", "hadoop101:9092,hadoop102:9092,hadoop103:9092")
    properties.setProperty("group.id", "flink.kafka.consumer.group")
    properties.setProperty("key.deserializer", classOf[StringDeserializer].getName)
    properties.setProperty("value.deserializer", classOf[StringDeserializer].getName)
    properties.setProperty("auto.offset.reset", "latest")
    properties
  }

}
