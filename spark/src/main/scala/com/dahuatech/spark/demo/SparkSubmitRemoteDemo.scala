package com.dahuatech.spark.demo

import com.dahuatech.spark.utils.SparkUtil
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.{SPARK_BRANCH, rdd}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}
import org.apache.spark.util.LongAccumulator
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

object SparkSubmitRemoteDemo {
  private val log: Logger = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    val sparkSession: SparkSession = SparkUtil.getSparkSession()
    import sparkSession.implicits._

    val df: DataFrame = sparkSession.sql(
      """
        |select * from test
        |""".stripMargin)

    df.show(100, false)

    import org.apache.spark.sql.functions._
    df.select($"name", 'name, df("name"), df.col("name"))
      .withColumn("newCol", lit("alan"))
      .withColumnRenamed("age", "newAge")
      .show()

  }
}