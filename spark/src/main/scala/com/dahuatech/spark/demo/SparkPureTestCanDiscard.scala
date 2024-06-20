package com.dahuatech.spark.demo

import com.dahuatech.spark.utils.SparkUtil
import org.apache.spark.rdd
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}
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

object SparkPureTestCanDiscard {
  private val logger: Logger = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    val sparkSession: SparkSession = SparkUtil.getSparkSession()
    import sparkSession.implicits._

    // val originRDD: RDD[Long] = sparkSession.sparkContext.range(0, 5000000, 1, 3)
    //
    // originRDD.map(x => (x + "1234123412341234123412341234123412341234", x, "10232"))
    //   .toDF("name", "age", "dt").write.format("hive").mode(SaveMode.Overwrite).saveAsTable("default.stu2")

    println(sparkSession.read.table("default.stu2").rdd.getNumPartitions)
  }
}