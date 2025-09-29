package com.dahuatech.spark.demo

import com.dahuatech.spark.utils.SparkUtil
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.{SPARK_BRANCH, rdd}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, SaveMode, SparkSession}
import org.apache.spark.util.LongAccumulator
import org.slf4j.{Logger, LoggerFactory}

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Random
import scala.collection.immutable

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

  case class Demo(id: String, name: String, age: Long, city: String, province: String, dt: String)

  def main(args: Array[String]): Unit = {

    val sparkSession: SparkSession = SparkUtil.getSparkSession()
    import sparkSession.implicits._

    val dt = LocalDate.now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    val df = sparkSession.range(0L, 10000)
      .map(id => Demo(id.toString, generateRandomString(32), id, generateRandomString(32), generateRandomString(32), dt))
      .toDF("id", "name", "age", "city", "province", "dt")

    df.repartition(20).write
      .format("jdbc")
      .option("url", "jdbc:mysql://hadoop101:3306/demo?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8")
      .option("dbtable", "test")
      .option("user", "root")
      .option("password", "root")
      .option("driver", "com.mysql.jdbc.Driver")
      .option("batchsize", "500")                  // 每批次插入多少行，默认 1000
      .option("isolationLevel", "READ_COMMITTED")  // 事务隔离级别
      .mode(SaveMode.Overwrite)                    // 追加写入
      .save()

  }

  /**
   * 生成指定长度的随机字符串 非BASE64字符串 因为BASE64字符串"="只会出现在末尾
   *
   * @param length
   * @return
   */
  def generateRandomString(length: Int): String = {
    val chars: immutable.IndexedSeq[Char] = ('a' to 'z') ++ ('A' to 'Z') ++ ('0' to '9') ++ Seq('/', '+')
    val random = new Random
    (1 to length).map(_ => chars(random.nextInt(chars.length))).mkString
  }
}