package com.dahuatech.spark.demo

import com.dahuatech.spark.utils.SparkUtil
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.slf4j.{Logger, LoggerFactory}

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.spark.demo</p>
 * <p>className: SparkContextDemo</p>
 * <p>date: 2023/6/19</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */

object SparkSQLDemo {
  private val logger: Logger = LoggerFactory.getLogger(this.getClass)
  private val sparkSession: SparkSession = SparkUtil.getSparkSession()

  def main(args: Array[String]): Unit = {
    val df: DataFrame = sparkSession.read.table("default.stu")
    println(df.rdd.getNumPartitions)
    df.rdd.foreachPartition(iter => {
      println(iter.size)
      iter
    })
  }
}