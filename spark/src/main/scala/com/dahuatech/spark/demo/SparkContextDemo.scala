package com.dahuatech.spark.demo

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession
import org.apache.spark.Partitioner
import org.apache.spark.storage.StorageLevel
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{Dependency, SparkConf, SparkContext, TaskContext}

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
  val sparkConf: SparkConf = new SparkConf().setMaster("local[*]")
    .set("spark.sql.autoBroadcastThreshold", "-1")
    // .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    // .registerKryoClasses(Array(classOf[Person]))
    .setAppName(getClass.getName)

  private val sparkSession: SparkSession = SparkSession.builder().config(sparkConf).getOrCreate()
  // private val sparkContext: SparkContext = new SparkContext(sparkConf)

  def showPartition[T](rdd: RDD[T]): Unit = {
    println("start --<< " + rdd.getNumPartitions + " >>-- start")
    rdd.foreachPartition(
      iter => println("partitionId: " + TaskContext.getPartitionId() + " <--> " + iter.mkString(","))
    )
    println("end --<< " + rdd.getNumPartitions + " >>-- end")
    println()
  }

  case class MyPartitioner(partitionNum: Int) extends Partitioner {
    override def numPartitions: Int = partitionNum

    override def getPartition(key: Any): Int = key.toString.toInt % 2
  }

  def main(args: Array[String]): Unit = {
    sparkSession.sparkContext.setCheckpointDir("./")
    val originRDD: RDD[Int] = sparkSession.sparkContext.makeRDD(Seq(1, 2, 3, 4, 5, 6), 2)


    // val originRDD: RDD[(String, Int)] = sparkSession.sparkContext.makeRDD(Seq(("a", 1), ("b", 2), ("a", 3), ("b", 4), ("c", 6), ("c", 5)), 2)
    // showPartition(originRDD)

    // val mapRDD: RDD[Int] = originRDD.map(x => {
    //   println(s"x = ${x}")
    //   x
    // })
    //
    // mapRDD.checkpoint()
    // mapRDD.collect()

    // val broadcastInt: Broadcast[Int] = sparkSession.sparkContext.broadcast(1)

    // val sum: LongAccumulator = sparkSession.sparkContext.longAccumulator("sum")
    //
    // originRDD.foreach(
    //   x => {
    //     sum.add(x)
    //   }
    // )
    //
    // println(sum.sum)
    // println(sum.count)
    // println(sum.avg)
    // println(sum.isZero)
    // sum.reset()
    // println(sum.isZero)
    // println(s"sum = ${sum.value}")

    // val partitionByRDD: RDD[(String, Int)] = originRDD.map((ab: (String, Int)) => (ab._2, ab._1)).partitionBy(MyPartitioner(2)).map((ab: (Int, String)) => (ab._2, ab._1))
    // showPartition(partitionByRDD)


    // val mapRDD: RDD[Int] = originRDD.map(x => {
    //   println(s"x = ${x + 1}")
    //   x
    // })
    //
    // mapRDD.collect()
    // mapRDD.collect()

    // l rdd1: RDD[Int] = originRDD.map(x => x)
    // val rdd2: RDD[(Int, Iterable[Int])] = rdd1.groupBy(x => x % 2)
    // val rdd3: RDD[(Int, Iterable[Int])] = rdd2.filter(x => x._1 % 2 == 0)
    //
    // println(rdd3.toDebugString)
    // va

    // val p = new Person()
    // originRDD.foreach(
    //   x => {
    //     p.age += x
    //   }
    // )
    //
    // println()
    // println(p.age)

    // val person = new Person()
    //
    // originRDD.foreach(x => {
    //   person.age += x
    //   println(TaskContext.getPartitionId() + " --> " + person.age)
    // })
    //
    // println(person.age)


    // val originRDD1: RDD[(String, Int)] = sparkSession.sparkContext.makeRDD(Seq(("a", 1), ("b", 2), ("a", 3), ("b", 4), ("c", 6), ("c", 5)), 2)

    // val map: collection.Map[Int, Long] = originRDD.countByValue()
    // println(map)
    //
    // val map1: collection.Map[String, Long] = originRDD1.countByKey()
    // println(map1)

    // val sum: Int = originRDD.fold(10)(_ + _)
    // println(sum)

    // println(originRDD.takeOrdered(2)(Ordering[Int].reverse).toBuffer)

    // val take: Array[Int] = originRDD.take(3)
    // println(take.toBuffer)

    // val first: Int = originRDD.first()
    // println(first)

    // val count: Long = originRDD.count()
    // println(count)

    // val array: Array[Int] = originRDD.collect()
    // originRDD.foreach(x => println(x))

    // val sum: Int = originRDD.reduce(_ + _)
    // println(sum)

    // val originRDD2: RDD[(String, Int)] = sparkSession.sparkContext.makeRDD(Seq(("b", 3), ("b", 3), ("c", 4)), 2)
    // showPartition(originRDD2)
    //
    // val sortByKeyRDD: RDD[(String, Int)] = originRDD1.sortByKey(true)
    // showPartition(sortByKeyRDD)

    // val cogroupRDD: RDD[(String, (Iterable[Int], Iterable[Int]))] = originRDD1.cogroup(originRDD2)
    // showPartition(cogroupRDD)

    // val leftOuterJoin: RDD[(String, (Int, Option[Int]))] = originRDD1.leftOuterJoin(originRDD2)
    // showPartition(leftOuterJoin)

    // val joinRDD: RDD[(String, (Int, Int))] = originRDD1.join(originRDD2)
    // showPartition(joinRDD)

    // val combineByKeyRDD: RDD[(String, (Int, Int))] = originRDD.combineByKey(
    //   (a: Int) => (a, 1),
    //   (a: (Int, Int), b: Int) => (a._1 + b, a._2 + 1),
    //   (a: (Int, Int), b: (Int, Int)) => (a._1 + b._1, a._2 + b._2)
    // )
    // showPartition(combineByKeyRDD)

    // val foldByKeyRDD: RDD[(String, Int)] = originRDD.foldByKey(20)(_ + _)
    // showPartition(foldByKeyRDD)

    // val aggregateByKeyRDD: RDD[(String, Int)] = originRDD.aggregateByKey(0)(
    //   (a: Int, b: Int) => math.max(a, b),
    //   (a: Int, b: Int) => a + b
    // )
    // showPartition(aggregateByKeyRDD)

    // val groupByKeyRDD: RDD[(Int, Iterable[String])] = originRDD.groupByKey()
    // showPartition(groupByKeyRDD)
    //
    // val groupByRDD: RDD[(Int, Iterable[(Int, String)])] = originRDD.groupBy(_._1)
    // showPartition(groupByRDD)
    //
    // val reduceByKeyRDD: RDD[(String, Int)] = originRDD.reduceByKey((a, b) => a + b)
    // showPartition(reduceByKeyRDD)

    // val partitionByRDD: RDD[(Int, String)] = originRDD.partitionBy(new HashPartitioner(4))
    // showPartition(partitionByRDD)

    // val originRDD1: RDD[Int] = sparkSession.sparkContext.makeRDD(Seq(1, 2, 3, 4), 2)
    // showPartition(originRDD1)
    // val originRDD2: RDD[Int] = sparkSession.sparkContext.makeRDD(Seq(1, 2 ,3 ,4), 2)
    // showPartition(originRDD2)

    // val intersectionRDD: RDD[Int] = originRDD1.intersection(originRDD2)
    // showPartition(intersectionRDD)
    //
    // val unionRDD: RDD[Int] = originRDD1.union(originRDD2)
    // showPartition(unionRDD)
    //
    // val subtractRDD: RDD[Int] = originRDD1.subtract(originRDD2)
    // showPartition(subtractRDD)

    // val zipRDD: RDD[(Int, Int)] = originRDD1.zip(originRDD2)
    // showPartition(zipRDD)

    // val prop = new Properties()
    // for (i <- 0 to 1000000) {
    //   prop.setProperty("alan" + i, i + "")
    // }
    //
    //
    // originRDD.map(
    //   x => {
    //     println(prop.getProperty("alan0"))
    //     prop.setProperty("jack", "1")
    //   }
    // ).collect()
    //
    // println(prop)
    // Thread.sleep(100000L)

    // val sortByRDD: RDD[Int] = originRDD.sortBy(x => x, false)
    //
    // sortByRDD.foreachPartition(
    //   iter => {
    //     println(iter.mkString(","))
    //   }
    // )
    //
    // sortByRDD.foreach(println)
    //
    // Thread.sleep(1000000L)

    // val partitioner: Option[Partitioner] = originRDD.partitioner
    // println(partitioner.getOrElse("none"))
    //
    // val mapRDD: RDD[(Int, Int)] = originRDD.map(x => (x, 1)).repartition(1).partitionBy(new HashPartitioner(10))
    // println(mapRDD.partitioner.getOrElse("none1"))
    //
    // println(originRDD.dependencies)


    // val textFileRDD: RDD[String] = sparkSession.sparkContext.textFile("D:\\dev\\idea\\project\\demo\\spark\\src\\main\\resources\\log4j.properties")
    // showPartition(textFileRDD)

    // showPartition(textFileRDD)

    // println(originRDD.dependencies)
    //
    //
    // val mapRDD: RDD[Int] = originRDD.map(x => x + 1)
    //
    // println(mapRDD.dependencies)
    //
    // val coalesceRDD: RDD[Int] = mapRDD.coalesce(1, true)
    //
    // println(coalesceRDD.dependencies)
    //
    // val repartitionRDD: RDD[Int] = coalesceRDD.repartition(2)
    //
    // println(repartitionRDD.dependencies)
    // println(repartitionRDD.toDebugString)

    // showPartition(originRDD)
    //
    // val reduceByKeyRDD: RDD[(Int, Int)] = originRDD.map(x => (x, 1)).reduceByKey((x, _) => x)
    // showPartition(reduceByKeyRDD)
    //
    // val distinctRDD: RDD[Int] = originRDD.distinct()
    // showPartition(distinctRDD)

    // println(originRDD.getNumPartitions)
    //
    // originRDD.foreachPartition(
    //   iter => println(TaskContext.getPartitionId() + " <-> " + iter.mkString(","))
    // )
    //
    // val groupRDD: RDD[(Int, Iterable[Int])] = originRDD.groupBy(x => x % 4)
    //
    // println(groupRDD.getNumPartitions)
    //
    // groupRDD.foreachPartition(
    //   (iter: Iterator[(Int, Iterable[Int])]) => {
    //     println(TaskContext.getPartitionId() + " <-> " + iter.mkString(","))
    //   }
    // )

    // originRdd.mapPartitionsWithIndex(
    //   (partitionNum: Int, iter: Iterator[Int]) => {
    //     if (partitionNum == 0) {
    //       iter
    //     } else {
    //       Nil.iterator
    //     }
    //   }
    // ).collect().foreach(println)

    // val mapRDD1: RDD[Int] = originRdd.map(
    //   x => {
    //     println("map1: " + TaskContext.getPartitionId() + " ---> " + x)
    //     x
    //   }
    // )
    //
    // val mapRDD2: RDD[Int] = mapRDD1.map(
    //   x => {
    //     println("map2: " + TaskContext.getPartitionId() + " ---> " + x)
    //     x
    //   }
    // )
    //
    // mapRDD2.collect()

    // val rdd: RDD[Int] = sparkSession.sparkContext.makeRDD(Seq(1, 2, 3, 4), 2)
    // println(rdd.getNumPartitions)
    // rdd.foreachPartition {
    //   iter: Iterator[Int] => {
    //     val partitionId: Int = TaskContext.getPartitionId()
    //     println("partitionId: " + partitionId + " --> " + iter.mkString(","))
    //   }
    // }
    // rdd.foreach(println)
    // rdd.collect().foreach(println)

    // val rdd: RDD[String] = sparkSession.sparkContext.textFile("D:\\dev\\idea\\project\\demo\\spark\\src\\main\\resources\\log4j.properties")
    // println(rdd.getNumPartitions)
    // rdd.foreachPartition {
    //   iter: Iterator[String] => {
    //     while(iter.hasNext) {
    //       val str: String = iter.next()
    //       println(str)
    //     }
    //
    //     println("alan########################")
    //   }
    // }

    // val rdd: RDD[(Int, Int)] = sparkContext.makeRDD(Array((1, 2), 3 -> 4))
    // val df: DataFrame = rdd.toDF("a", "b", "c")
    // df.show()
    // df.createOrReplaceGlobalTempView("demo")
    // sparkSession.sql(
    //   """
    //     |select * from global_temp.demo
    //     |""".stripMargin).show()k
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
