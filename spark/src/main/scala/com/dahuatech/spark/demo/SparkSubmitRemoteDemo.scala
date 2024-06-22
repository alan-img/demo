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

object SparkSubmitRemoteDemo {
  private val logger: Logger = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    val sparkSession: SparkSession = SparkUtil.getSparkSession()
    import sparkSession.implicits._

    val originRDD: RDD[Long] = sparkSession.sparkContext.range(0, 2456558, 1, 1)

    // originRDD.map(x => (SparkUtil.generateRandomString(50), x, "2024"))
    //   .toDF("name", "age", "dt").write.partitionBy("dt").mode(SaveMode.Overwrite).saveAsTable("adv")
    //
    // sparkSession.sparkContext.range(0, 2933203, 1, 1).map(x => (SparkUtil.generateRandomString(50), x, "2024"))
    //   .toDF("name", "age", "dt").write.partitionBy("dt").mode(SaveMode.Append).saveAsTable("adv")
    //
    // sparkSession.sparkContext.range(0, 1500000, 1, 1).map(x => (SparkUtil.generateRandomString(50), x, "2024"))
    //   .toDF("name", "age", "dt").write.partitionBy("dt").mode(SaveMode.Append).saveAsTable("adv")

    println(sparkSession.read.table("adv").rdd.getNumPartitions)
  }
}