package com.dahuatech.spark.demo

import com.dahuatech.spark.utils.SparkUtil
import com.dahuatech.spark.utils.SparkUtil.{getLocalSparkContent, showPartition}
import org.apache.spark.{HashPartitioner, Partitioner, SparkContext, TaskContext}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.slf4j.{Logger, LoggerFactory}

import scala.collection.{LinearSeq, mutable}
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.spark.demo</p>
 * <p>className: SparkLocalDemo</p>
 * <p>date: 6/22/2024</p>
 *
 * @author qinjiawei(alan)
 * @since JDK8.0
 * @version 1.0.0
 */

object SparkLocalDemo {
  private val logger: Logger = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {
    val sparkContext: SparkContext = getLocalSparkContent()

    val originRDD: RDD[Long] = sparkContext.range(1, 5, 1, 2)

    originRDD.cache()
    println(originRDD.dependencies)

    val originListRDD: RDD[List[Long]] = originRDD.map(x => List(x, x))

    val originKeyValueRDD: RDD[(Long, Long)] = originRDD.map(x => (x, x))


  }
}
