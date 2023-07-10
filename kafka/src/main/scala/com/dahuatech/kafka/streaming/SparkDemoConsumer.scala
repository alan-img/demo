package com.dahuatech.kafka.streaming

import com.dahuatech.kafka.demo.KafkaParamConfig
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.{Partitioner, SparkConf}
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.kafka.streaming</p>
 * <p>className: SparkDemoConsumer</p>
 * <p>date: 2023/6/20</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */
object SparkDemoConsumer {

  def main(args: Array[String]): Unit = {

    val sparkConf: SparkConf = new SparkConf()
      .setMaster("local[*]")
      .set("spark.streaming.kafka.maxRatePerPartition", "1")
      .setAppName(getClass.getName)
      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
    val ssc: StreamingContext = new StreamingContext(sparkConf, Seconds(2))

    val kafkaParams: Map[String, Object] = Map[String, String](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> KafkaParamConfig.BOOTSTRAP_SERVERS_CONFIG,
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer].getName,
      (ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName),
      (ConsumerConfig.GROUP_ID_CONFIG, KafkaParamConfig.GROUP_ID_CONFIG),
      (ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false"),
      (ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "6")
    )

    // kafkaDStreaming -> class org.apache.spark.streaming.kafka010.DirectKafkaInputDStream
    val kafkaDStreaming: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream(
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](Array("first"), kafkaParams)
    )

    // 用以提交偏移量
    val canCommitOffsets: CanCommitOffsets = kafkaDStreaming.asInstanceOf[CanCommitOffsets]

    val transformDStream: DStream[(Long, String)] = kafkaDStreaming.transform(
      // rdd -> class org.apache.spark.streaming.kafka010.KafkaRDD
      (rdd, _) => {
        // rdd -> 分区数和topic分区数默认相同
        println(rdd.toJavaRDD().getNumPartitions)
        // 提交已经消费数据的偏移量
        canCommitOffsets.commitAsync(rdd.asInstanceOf[HasOffsetRanges].offsetRanges)
        // 转换数据以供返回
        rdd.zipWithIndex().map { case (consumerRecord, index) => (index, consumerRecord.value()) }
      }
    )

    // 虽然名为名foreachRDD 但实际上针对每个batch是只有一个RDD
    transformDStream.foreachRDD {
      rdd: RDD[(Long, String)] => println(rdd.count())
    }

    val groupDStream: DStream[(Long, Iterable[String])] = transformDStream.groupByKeyAndWindow(Seconds(4), Seconds(4), new Partitioner {
      override def numPartitions: Int = 4

      override def getPartition(key: Any): Int = key.toString.hashCode % 4
    })

    // 虽然名为名foreachRDD 但实际上针对每个batch是只有一个RDD
    groupDStream.foreachRDD {
      (rdd, time) => {
        println(s"time: $time")
        println(rdd.toJavaRDD().getNumPartitions)
        println(rdd.count())
        rdd.foreach {case (key, value) => println(key, value.mkString(","))}
      }
    }

    ssc.start()
    ssc.awaitTermination()

  }

}
