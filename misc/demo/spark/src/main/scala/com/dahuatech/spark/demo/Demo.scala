package com.dahuatech.spark.demo

import com.dahuatech.spark.demo.spark.ssc
import org.apache.spark.rdd.RDD

import scala.collection.mutable

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.spark</p>
 * <p>className: Demo</p>
 * <p>date: 2023/4/23</p>
 *
 * @author qinjiawei(alan)
 * @since JDK8.0
 * @version 1.0.0
 */
object Demo {
  def main(args: Array[String]): Unit = {
    val rddQueue = new mutable.Queue[RDD[Int]]()
    val inputStream = ssc.queueStream(rddQueue,oneAtATime = false)
    val mappedStream = inputStream.map(x => {
      println(Thread.currentThread().getName)
      (x, 1)
    })
    val reducedStream = mappedStream.reduceByKey(_ + _)
    reducedStream.print()
    ssc.start()

    println(Thread.currentThread().getName)

    for (i <- 1 to 5) {
      rddQueue += ssc.sparkContext.makeRDD(1 to 3, 1)
      Thread.sleep(2000)
    }

    ssc.awaitTermination()

    println(quicksort(List()))
  }

  def quicksort(xs: List[Int]): List[Int] = xs match {
    case List() => List()
    case x :: xs1 =>
      val (left, right) = xs1.partition(_ < x)
      quicksort(left) ++ (x :: quicksort(right))
  }
}
