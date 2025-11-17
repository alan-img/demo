package com.dahuatech.spark.demo

import com.dahuatech.spark.utils.SparkUtil

object SparkReadDorisTableDemo {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkUtil.getLocalSparkSession()
    import sparkSession.implicits._

    val stu = sparkSession.read
      .format("doris")
      .option("doris.table.identifier", "demo.example_tbl_duplicate")
      .option("doris.fenodes", "hadoop108:8030")
      .option("user", "root")
      .option("password", "")
      .load()

    stu.createOrReplaceTempView("stu")

    sparkSession.sql(
      """
        |select * from stu
        |""".stripMargin).show

  }

}
