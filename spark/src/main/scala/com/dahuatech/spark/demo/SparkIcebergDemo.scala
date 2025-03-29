package com.dahuatech.spark.demo

import com.dahuatech.spark.utils.SparkUtil
import org.apache.spark.sql.SparkSession
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

object SparkIcebergDemo {
  private val log: Logger = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    val sparkSession: SparkSession = SparkSession.builder().master("local[*]").appName(this.getClass.getSimpleName)
      .config("spark.sql.catalog.spark_hive_catalog", "org.apache.iceberg.spark.SparkCatalog")
      .config("spark.sql.catalog.spark_hive_catalog.type", "hive")
      .config("spark.sql.catalog.spark_hive_catalog.uri", "thrift://hadoop101:9083")
      .config("iceberg.engine.hive.enabled", "true")
      .config("spark.sql.catalog.spark_hadoop_catalog", "org.apache.iceberg.spark.SparkCatalog")
      .config("spark.sql.catalog.spark_hadoop_catalog.type", "hadoop")
      .config("spark.sql.catalog.spark_hadoop_catalog.warehouse", "hdfs://hadoop101:8020/user/hive/warehouse/spark_hadoop_catalog")
      .getOrCreate()

    // sparkSession.read
    //   .format("iceberg")
    //   .load("hdfs://hadoop101:8020/user/hive/warehouse/spark_hadoop_catalog/test/test3")
    //   .show()

    sparkSession.read
      .option("snapshot-id", 8049766748014798254L)
      .format("iceberg")
      .load("hdfs://hadoop101:8020/user/hive/warehouse/spark_hadoop_catalog/test/test3")
      .show()

  }
}