package com.dahuatech.spark.demo

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.spark</p>
 * <p>className: package</p>
 * <p>date: 2023/4/23</p>
 *
 * @author qinjiawei(alan)
 * @since JDK8.0
 * @version 1.0.0
 */
package object spark {
  val sparkConf: SparkConf = new SparkConf()
    .setMaster("local[*]")
    .setAppName(getClass.getName)

  // val sparkSession: SparkSession = SparkSession.builder()
  //   .config(sparkConf)
  //   .enableHiveSupport()
  //   .getOrCreate()

  val ssc: StreamingContext = new StreamingContext(sparkConf, Seconds(3))
}
