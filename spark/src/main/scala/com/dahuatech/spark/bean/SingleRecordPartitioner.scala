package com.dahuatech.spark.bean

import org.apache.spark.Partitioner


case class SingleRecordPartitioner(partitionNum: Int) extends Partitioner {
  override def numPartitions: Int = partitionNum

  override def getPartition(key: Any): Int = key.toString.toInt
}