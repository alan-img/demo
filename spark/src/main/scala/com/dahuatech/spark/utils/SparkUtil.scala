package com.dahuatech.spark.utils

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext, TaskContext}

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.spark.utils</p>
 * <p>className: SparkUtil</p>
 * <p>date: 2023/4/3</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */
object SparkUtil {
  def getLocalSparkSession(): SparkSession = SparkSession.builder().config(new SparkConf().setMaster("local[*]").setAppName(getClass.getName)).getOrCreate()

  def getSparkSession(): SparkSession = {
    val sparkConf: SparkConf = new SparkConf().setAppName(getClass.getName)
    SparkSession.builder().config(sparkConf).enableHiveSupport().getOrCreate()
    // .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    // .registerKryoClasses(Array(classOf[FileWriter]))
  }

  def showPartition[T](rdd: RDD[T]): Unit = {
    println("start --<< " + rdd.getNumPartitions + " >>-- start")
    rdd.foreachPartition(
      iter => println("partitionId: " + TaskContext.getPartitionId() + " <--> " + iter.mkString(","))
    )
    println("end --<< " + rdd.getNumPartitions + " >>-- end")
    println()
  }
}
