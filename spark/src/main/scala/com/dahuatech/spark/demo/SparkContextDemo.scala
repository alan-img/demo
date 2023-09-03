package com.dahuatech.spark.demo

import com.dahuatech.spark.utils.SparkUtil
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.hive.test.TestHive.sparkContext
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
  val logger: Logger = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]): Unit = {
    val sparkSession: SparkSession = SparkUtil.getSparkSession()
    val sparkContext: SparkContext = sparkSession.sparkContext
    import sparkSession.implicits._

    for (i <- 0 until 20) {
      logger.info(s"driver log test ${i}...")
    }
    val rdd: RDD[Long] = sparkContext.range(0, 1000000, 1)
    val value: RDD[Long] = rdd.map {
      x =>
        println(x)
        logger.info(s"driver log test...")
        x + 1
    }
    value.collect()
  }
}
