package com.dahuatech.kafka.demo

import org.apache.kafka.clients.producer.Partitioner
import org.apache.kafka.common.Cluster
import org.slf4j.{Logger, LoggerFactory}

import java.util

class CustomizePartitioner extends Partitioner {
  private val logger: Logger = LoggerFactory.getLogger(getClass)

  override def partition(topic: String, key: Any, keyBytes: Array[Byte], value: Any, valueBytes: Array[Byte], cluster: Cluster): Int = {
    val str: String = key.asInstanceOf[String]
    if (str.substring(3).toInt < 50) 0 else 1
  }

  override def close(): Unit = {}

  override def configure(configs: util.Map[String, _]): Unit = {}
}
