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
import com.dahuatech.spark.utils.SparkUtil.showPartition


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
    val streamingContext = new StreamingContext(SparkUtil.getLocalSparkSession().sparkContext, Durations.seconds(5))
    val receiverInputDStream: ReceiverInputDStream[String] = streamingContext.socketTextStream("hadoop104", 9999)

    val stream: DStream[(String, String)] = receiverInputDStream.map((x: String) => (x, x))
    stream.foreachRDD((rdd: RDD[(String, String)]) => {
      println("alan")
    })

    val group: DStream[(String, Iterable[String])] = stream.groupByKeyAndWindow(Seconds(10), Seconds(10))
    group.foreachRDD((rdd: RDD[(String, Iterable[String])]) => {
      println("jack")
    })


    streamingContext.start()
    streamingContext.awaitTermination()
  }
}
