package com.dahuatech.spark.utils

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

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
  val sparkConf: SparkConf = new SparkConf().setMaster("local[*]").setAppName(getClass.getName)

  def getSparkContext(): SparkContext = new SparkContext(sparkConf)

  def getSparkSession(): SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
}
