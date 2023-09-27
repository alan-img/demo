package com.dahuatech.spark.demo

import com.dahuatech.spark.config.KafkaParamConfig
import com.dahuatech.spark.utils.SparkUtil
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.spark.TaskContext
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.catalyst.expressions.Second
import org.apache.spark.streaming.dstream.{DStream, InputDStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Durations, Seconds, StreamingContext, Time}

import scala.collection.JavaConversions._
import scala.collection.immutable.{NumericRange, Stack}
import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.spark.demo</p>
 * <p>className: SparkStreamDemo</p>
 * <p>date: 2023/6/15</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */

object SparkStreamDemo {
  def main(args: Array[String]): Unit = {
    val streamingContext = new StreamingContext(SparkUtil.getLocalSparkSession().sparkContext, Durations.seconds(3))

    val receiverInputDStream: ReceiverInputDStream[String] = streamingContext.socketTextStream("10.12.162.2", 9999)

    val dStream: DStream[String] = receiverInputDStream.transform {
      (rdd: RDD[String], time: Time) => {
        println(rdd.getNumPartitions)
        rdd
      }
    }

    val stream: DStream[(String, ArrayBuffer[String])] = dStream.map((x: String) => (x, ArrayBuffer(x)))

    // stream.foreachRDD {
    //   rdd: RDD[(String, ArrayBuffer[String])] => {
    //     rdd.foreachPartition {
    //       kv: Iterator[(String, ArrayBuffer[String])] => println("compute....")
    //     }
    //   }
    // }

    val group: DStream[(String, Iterable[ArrayBuffer[String]])] = stream.groupByKeyAndWindow(Seconds(6), Seconds(6))

    group.print()


    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
