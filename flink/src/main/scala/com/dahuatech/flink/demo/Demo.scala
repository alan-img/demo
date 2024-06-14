package com.dahuatech.flink.demo

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala._
import org.slf4j.LoggerFactory

import scala.sys.env

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

object Demo {
  val log = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]): Unit = {

    unboundedStream(args)

  }

  def unboundedStream(args: Array[String]): Unit = {
    val parameterTool: ParameterTool = ParameterTool.fromArgs(args)
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val dataStream: DataStream[String] = env.socketTextStream(parameterTool.get("host", "hadoop101"), parameterTool.getInt("port", 9999))
    val keyedStream: KeyedStream[(String, Int), String] = dataStream.flatMap(_.split(" ")).map(word => (word, 1)).keyBy(_._1)
    val sum: DataStream[(String, Int)] = keyedStream.sum(1)
    sum.print()
    env.execute()
  }

  def boundStream(): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val dataStream: DataStream[String] = env.readTextFile("D:\\dev\\idea\\project\\demo\\flink\\src\\main\\resources\\word.txt")
    val keyedStream: KeyedStream[(String, Int), String] = dataStream.flatMap(_.split(" ")).map(word => (word, 1)).keyBy(_._1)
    val sum: DataStream[(String, Int)] = keyedStream.sum(1)
    sum.print()
    env.execute()
  }

}
