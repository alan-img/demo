package com.dahuatech.flink.demo

import com.dahuatech.flink.demo.Demo.getKafkaProperties
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.api.common.state.{MapState, MapStateDescriptor, ValueState, ValueStateDescriptor}
import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.api.scala.typeutils.Types
import org.apache.flink.configuration.{Configuration, RestOptions}
import org.apache.flink.streaming.api.functions.KeyedProcessFunction
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer, FlinkKafkaProducer}
import org.apache.flink.util.Collector
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.LoggerFactory

import java.util.Properties

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

object SubmitDemo {
  val log = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]): Unit = {
    val parameterTool: ParameterTool = ParameterTool.fromArgs(args)
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val properties: Properties = getKafkaProperties()

    val kafkaDataStream: DataStream[String] = env.addSource(
      new FlinkKafkaConsumer[String](parameterTool.get("topic.name", "weather_info"), new SimpleStringSchema(),
      properties)
    )

    kafkaDataStream.addSink(
      new FlinkKafkaProducer[String]("hadoop101:9092,hadoop102:9092,hadoop103:9092", "weather_data", new SimpleStringSchema())
    )

    env.execute()
  }

  def getKafkaProperties(): Properties = {
    val properties: Properties = new Properties()
    properties.setProperty("bootstrap.servers", "hadoop101:9092,hadoop102:9092,hadoop103:9092")
    properties.setProperty("group.id", "flink.kafka.consumer.group")
    properties.setProperty("key.deserializer", classOf[StringDeserializer].getName)
    properties.setProperty("value.deserializer", classOf[StringDeserializer].getName)
    properties.setProperty("auto.offset.reset", "earliest")
    properties
  }

}