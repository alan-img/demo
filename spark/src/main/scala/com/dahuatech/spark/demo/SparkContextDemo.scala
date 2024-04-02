package com.dahuatech.spark.demo

import com.dahuatech.spark.utils.SparkUtil
import com.dahuatech.spark.utils.SparkUtil.showPartition
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.Partitioner
import org.apache.spark.sql.hive.test.TestHive.sparkContext
import org.apache.spark.storage.StorageLevel
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{Dependency, SparkConf, SparkContext, TaskContext}
import org.slf4j.{Logger, LoggerFactory}

import java.lang
import java.util.concurrent.TimeUnit
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

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
  private val logger: Logger = LoggerFactory.getLogger(this.getClass)
  private val sparkSession: SparkSession = SparkUtil.getSparkSession()

  def main(args: Array[String]): Unit = {
    logger.info("start distribute calculate...")
    val originRDD: RDD[java.lang.Long] = sparkSession.sparkContext.makeRDD(Seq.range(0L, 10L).map(_.asInstanceOf[lang.Long]), 5)
    val originListRDD: RDD[List[lang.Long]] = originRDD.map(x => List(x, x))
    val originKeyValueRDD: RDD[(lang.Long, lang.Long)] = originRDD.map(x => (x, x))
    showPartition(originRDD)

    TimeUnit.MINUTES.sleep(5)
  }
}