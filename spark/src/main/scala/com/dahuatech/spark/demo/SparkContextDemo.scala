package com.dahuatech.spark.demo

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

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
  val sparkConf: SparkConf = new SparkConf().setAppName(getClass.getName)
  // .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
  // .registerKryoClasses(Array(classOf[FileWriter]))

  private val sparkContext: SparkContext = new SparkContext(sparkConf)

  private val sparkSession: SparkSession = SparkSession.builder().config(sparkConf).enableHiveSupport().getOrCreate()

  import sparkSession.implicits._

  def main(args: Array[String]): Unit = {
    val rdd: RDD[(Int, Int)] = sparkContext.makeRDD(Array((1, 2), 3 -> 4))
    val df: DataFrame = rdd.toDF("a", "b")
    df.show()
    // df.createOrReplaceGlobalTempView("demo")
    // sparkSession.sql(
    //   """
    //     |select * from global_temp.demo
    //     |""".stripMargin).show()
    // sparkSession.newSession()
    // sparkSession.newSession().sql(
    //   """
    //     |select * from global_temp.demo
    //     |""".stripMargin).show()
    // df.select($"a" + 10, 'b).show()
    //
    // val ds: Dataset[(Int, Int)] = rdd.toDS()
  }
}
