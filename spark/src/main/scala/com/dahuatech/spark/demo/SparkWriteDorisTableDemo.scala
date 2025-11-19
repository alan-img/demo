package com.dahuatech.spark.demo

import com.dahuatech.spark.utils.SparkUtil

object SparkWriteDorisTableDemo {

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkUtil.getLocalSparkSession()
    import sparkSession.implicits._

    // 构造一个 DataFrame 示例
    val df = Seq(
      (1, "Tom", 20),
      (2, "Jerry", 30)
    ).toDF("id", "name", "age")

    df.write
      .format("doris")
      .option("doris.fenodes", "hadoop108:8030")   // FE 地址
      .option("doris.query.port", "9030")
      .option("doris.table.identifier", "demo.stu") // database.table
      .option("user", "root")
      .option("password", "")
      .option("doris.write.field.delimiter", ",")
      .option("doris.write.batch.size", "1024")
      .option("doris.write.flush.interval.ms", "2000")
      .mode("overwrite")   // append / overwrite
      .save()

  }


}
