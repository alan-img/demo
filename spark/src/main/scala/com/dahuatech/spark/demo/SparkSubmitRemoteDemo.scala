package com.dahuatech.spark.demo

import com.dahuatech.spark.utils.SparkUtil
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.{SPARK_BRANCH, rdd}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.functions._
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

    val df = sparkSession.read
      .format("jdbc")
      .option("url", "jdbc:mysql://hadoop101:3306/demo?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8")
      //      .option("dbtable", "(SELECT name, age FROM test) tmp") // 可写子查询，需加别名
      .option("dbtable", "test") // 或者直接使用表名
      .option("user", "root")
      .option("password", "root")
      .option("driver", "com.mysql.jdbc.Driver")
      // --- 大表分区并行读取 ---
      .option("partitionColumn", "age")       // 分区字段（整型/时间戳）
      .option("lowerBound", "1")             // 最小值
      .option("upperBound", "1000000")       // 最大值
      .option("numPartitions", "20")         // 并行连接数
      // --- 优化参数 ---
      .option("fetchsize", "1000")           // 批次抓取行数
      .option("queryTimeout", "300")         // 查询超时（秒）
      .load()

    println(df.count)

  }
}