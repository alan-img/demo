package com.dahuatech.spark.demo

import com.dahuatech.spark.utils.SparkUtil
import com.dahuatech.spark.utils.SparkUtil._
import org.apache.spark.Partitioner
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
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

object SparkContextDemo {
  private val logger: Logger = LoggerFactory.getLogger(this.getClass)
  private val sparkSession: SparkSession = SparkUtil.getLocalSparkSession()

  def main(args: Array[String]): Unit = {
    val originRDD: RDD[Long] = sparkSession.sparkContext.range(0, 10, 1, 5)
    val originListRDD: RDD[List[Long]] = originRDD.map(x => List(x, x))
    val originKeyValueRDD: RDD[(Long, Long)] = originRDD.map(x => (x, x))

    
  }
}