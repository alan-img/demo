package com.dahuatech.flink.demo

import com.dahuatech.flink.bean.RandomGenerateData
import com.dahuatech.flink.util.KafkaUtils
import org.apache.flink.api.common.RuntimeExecutionMode
import org.apache.flink.api.common.eventtime._
import org.apache.flink.api.common.functions.{MapFunction, Partitioner, RichMapFunction}
import org.apache.flink.api.common.serialization.{SimpleStringEncoder, SimpleStringSchema}
import org.apache.flink.api.common.state.ValueStateDescriptor
import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.api.scala.{AggregateDataSet, DataSet, ExecutionEnvironment, GroupedDataSet}
import org.apache.flink.configuration.{Configuration, RestOptions}
import org.apache.flink.connector.jdbc.{JdbcConnectionOptions, JdbcSink, JdbcStatementBuilder}
import org.apache.flink.core.fs.Path
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.DefaultRollingPolicy
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}
import org.apache.flink.streaming.api.functions.source.{RichSourceFunction, SourceFunction}
import org.apache.flink.streaming.api.functions.{KeyedProcessFunction, ProcessFunction}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.function.{ProcessAllWindowFunction, ProcessWindowFunction}
import org.apache.flink.streaming.api.windowing.assigners.{SlidingEventTimeWindows, TumblingEventTimeWindows, TumblingProcessingTimeWindows}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer, FlinkKafkaProducer}
import org.apache.flink.table.api.Table
import org.apache.flink.table.api.bridge.scala.StreamTableEnvironment
import org.apache.flink.util.Collector
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.LoggerFactory

import java.sql.PreparedStatement
import java.time.Duration
import java.util.Properties
import java.util.concurrent.TimeUnit
import scala.collection.mutable
import scala.sys.env
import scala.util.Random

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.flink.demo</p>
 * <p>className: Demo</p>
 * <p>date: 5/16/2024</p>
 *
 * @author qinjiawei(alan)
 * @since JDK8.0
 * @version 1.0.0
 */

object DemoTest {
  val log = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]): Unit = {

    val conf = new Configuration()
    conf.setString(RestOptions.BIND_PORT, "8080")

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf)



    env.execute()

  }

  def wordCountDemo(): Unit = {
    val conf = new Configuration()
    conf.setString(RestOptions.BIND_PORT, "8080")

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val ds: DataStream[String] = env.socketTextStream("hadoop102", 9999)
    // val ds: DataStream[String] = env.readTextFile("hdfs://hadoop101:8020/input/derby.log")
    // val ds: DataStream[String] = env.readTextFile("D:\\dev\\3.idea-2022.1.1\\project\\demo\\flink\\src\\main\\resources\\word.txt")
    val flatMap: DataStream[String] = ds.flatMap(_.split(","))
    val map: DataStream[(String, Int)] = flatMap.map((_, 1))
    val keyedStream: KeyedStream[(String, Int), String] = map.keyBy(_._1)
    val sum: DataStream[(String, Int)] = keyedStream.sum(1)
    sum.print()
    env.execute()
  }

}