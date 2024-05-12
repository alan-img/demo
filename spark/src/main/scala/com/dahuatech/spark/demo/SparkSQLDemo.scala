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
  /**
   * 提交任务命令示例
   * spark-submit
   * --master yarn
   * --deploy-mode cluster
   * --class com.dahuatech.spark.demo.SparkSQLDemo
   * --conf spark.driver.extraJavaOptions=-Dlog4j.configuration=log4j.properties
   * --conf spark.executor.extraJavaOptions=-Dlog4j.configuration=log4j.properties
   * --files log4j.properties
   * spark-jar-with-dependencies.jar
   * @param args
   */

  private val logger: Logger = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    val sparkSession: SparkSession = SparkUtil.getSparkSession()

    val df: DataFrame = sparkSession.read.table("default.stu")
    println(df.rdd.getNumPartitions)
    df.rdd.foreachPartition(iter => {
      println(iter.size)
      iter
    })
  }
}