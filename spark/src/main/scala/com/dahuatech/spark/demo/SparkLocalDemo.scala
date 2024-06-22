package com.dahuatech.spark.demo

import com.dahuatech.spark.utils.SparkUtil
import com.dahuatech.spark.utils.SparkUtil.getLocalSparkContent
import org.apache.spark.{SparkContext, TaskContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.slf4j.{Logger, LoggerFactory}

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.spark.demo</p>
 * <p>className: SparkLocalDemo</p>
 * <p>date: 6/22/2024</p>
 *
 * @author qinjiawei(alan)
 * @since JDK8.0
 * @version 1.0.0
 */

object SparkLocalDemo {
  private val logger: Logger = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    val sparkContext: SparkContext = getLocalSparkContent()

    val rdd: RDD[String] = sparkContext.textFile("D:\\dev\\idea\\project\\demo\\spark\\data")
    println(rdd.getNumPartitions)

  }
}
