package com.dahuatech.spark.utils

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext, TaskContext}
import org.slf4j.{Logger, LoggerFactory}

import java.util.Random
import scala.collection.immutable

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
  private val logger: Logger = LoggerFactory.getLogger(this.getClass)

  def getLocalSparkSession(): SparkSession = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName(getClass.getName)
    SparkSession.builder().config(sparkConf).getOrCreate()
  }

  def getSparkSession(): SparkSession = {
    val sparkConf: SparkConf = new SparkConf().setAppName(getClass.getName)
    SparkSession.builder().config(sparkConf).enableHiveSupport().getOrCreate()
  }

  def getLocalSparkContent(): SparkContext = {
    val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName(getClass.getName)
    new SparkContext(sparkConf)
  }

  def showPartition[T](rdd: RDD[T]): Unit = {
    println("start --<< " + rdd.getNumPartitions + " >>-- start")
    rdd.foreachPartition(
      iter => {
        println("partitionId: " + TaskContext.getPartitionId() + " <--> " + iter.mkString(","))
      }
    )
    println("end --<< " + rdd.getNumPartitions + " >>-- end")
    println()
  }

  def generateRandomString(length: Int): String = {
    val chars: immutable.IndexedSeq[Char] = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9') ++ Seq('/', '+')
    val random = new Random
    (1 to length).map(_ => chars(random.nextInt(chars.length))).mkString
  }
}
