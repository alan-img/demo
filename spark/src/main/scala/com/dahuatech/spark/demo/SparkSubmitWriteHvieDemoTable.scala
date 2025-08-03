package com.dahuatech.spark.demo

import com.dahuatech.spark.utils.SparkUtil
import org.apache.spark.sql.{SaveMode, SparkSession}
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

object SparkSubmitWriteHvieDemoTable {
  private val log: Logger = LoggerFactory.getLogger(this.getClass)

  case class Demo(id: String, name: String, age: Long, city: String, province: String, dt: String)

  def main(args: Array[String]): Unit = {

    val sparkSession: SparkSession = SparkUtil.getSparkSession()
    import sparkSession.implicits._

    val dt = LocalDate.now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    val df = sparkSession.range(0L, args(0).toLong)
      .map(id => Demo(id.toString, generateRandomString(32), id, generateRandomString(32), generateRandomString(32), dt))
      .toDF("id", "name", "age", "city", "province", "dt")

    df.write.mode(SaveMode.Overwrite).insertInto("demo")

    val dt_minus_7 = LocalDate.now.minusDays(7).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))

    val partitionList = sparkSession.sql(
      """
        |show partitions demo
        |""".stripMargin).collect().map(row => row.getString(0).split("=")(1))

    partitionList.foreach {
      partition =>
        println(s"partition: ${partition}")
        if (partition < dt_minus_7) {
          sparkSession.sql(s"alter table demo drop partition(dt = '${partition}')")
        }
    }

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