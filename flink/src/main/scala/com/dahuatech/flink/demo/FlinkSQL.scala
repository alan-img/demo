package com.dahuatech.flink.demo

import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.configuration.{Configuration, RestOptions}
import org.apache.flink.streaming.api.functions.ProcessFunction
import org.apache.flink.streaming.api.functions.source.{RichParallelSourceFunction, RichSourceFunction, SourceFunction}
import org.apache.flink.streaming.api.scala.{DataStream, OutputTag, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.assigners.{GlobalWindows, TumblingProcessingTimeWindows}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.util.Collector
import org.slf4j.LoggerFactory

import java.util.concurrent.TimeUnit
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.Random
import scala.util.control.Breaks
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.table.api.Expressions.$
import org.apache.flink.table.api.Table
import org.apache.flink.table.api.bridge.scala.StreamTableEnvironment


/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.flink.demo</p>
 * <p>className: FlinkSQL</p>
 * <p>date: 2025/3/5</p>
 *
 * @author qinjiawei(Administrator)
 * @since JDK8.0
 * @version 1.0.0
 */

object FlinkSQL {

  val log = LoggerFactory.getLogger(getClass)

  case class Event(name: String, age: Int, var ts: Long)

  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val tableEnv: StreamTableEnvironment = StreamTableEnvironment.create(env)



    env.execute()

  }

  // 开窗聚合
  def demo5(): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val ds = env.addSource(new SourceFunction[Event] {
      override def run(ctx: SourceFunction.SourceContext[Event]): Unit = {
        val list: List[Event] = List(Event("Alice", 10, 1000L),
          Event("Bob", 20, 1000L),
          Event("Alice", 30, 5 * 1000L),
          Event("Cary", 40, 60 * 1000L),
          Event("Bob", 50, 90 * 1000L),
          Event("Alice", 60, 105 * 1000L))
        while (true) {
          val event: Event = list(Random.nextInt(6))
          event.ts = System.currentTimeMillis()
          ctx.collect(event)
          TimeUnit.SECONDS.sleep(1)
        }
      }

      override def cancel(): Unit = {}
    }).assignAscendingTimestamps(_.ts)
    val tableEnv: StreamTableEnvironment = StreamTableEnvironment.create(env)

    tableEnv.createTemporaryView("tb", ds, $("name"), $("age"), $("ts").rowtime())
    tableEnv.toChangelogStream(tableEnv.sqlQuery(
      """
        |select *, max(age) over (order by ts rows between 5 preceding and current row) from tb
        |""".stripMargin)).print()

    env.execute()
  }

  // 分组累计窗口聚合
  def demo4(): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val ds = env.addSource(new SourceFunction[Event] {
      override def run(ctx: SourceFunction.SourceContext[Event]): Unit = {
        val list: List[Event] = List(Event("Alice", 10, 1000L),
          Event("Bob", 20, 1000L),
          Event("Alice", 30, 5 * 1000L),
          Event("Cary", 40, 60 * 1000L),
          Event("Bob", 50, 90 * 1000L),
          Event("Alice", 60, 105 * 1000L))
        while (true) {
          val event: Event = list(Random.nextInt(6))
          event.ts = System.currentTimeMillis()
          ctx.collect(event)
          TimeUnit.SECONDS.sleep(1)
        }
      }

      override def cancel(): Unit = {}
    }).assignAscendingTimestamps(_.ts)
    val tableEnv: StreamTableEnvironment = StreamTableEnvironment.create(env)

    tableEnv.createTemporaryView("tb", ds, $("name"), $("age"), $("ts").rowtime())
    tableEnv.toChangelogStream(tableEnv.sqlQuery(
      """
        |select name, count(age) as cnt, window_start, window_end from table(cumulate(table tb, descriptor(ts), interval '3' second, interval '1' hour)) group by name, window_start, window_end
        |""".stripMargin)).print()

    env.execute()
  }

  // 分组滚动窗口聚合
  def demo3(): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val ds = env.addSource(new SourceFunction[Event] {
      override def run(ctx: SourceFunction.SourceContext[Event]): Unit = {
        val list: List[Event] = List(Event("Alice", 10, 1000L),
          Event("Bob", 20, 1000L),
          Event("Alice", 30, 5 * 1000L),
          Event("Cary", 40, 60 * 1000L),
          Event("Bob", 50, 90 * 1000L),
          Event("Alice", 60, 105 * 1000L))
        while (true) {
          val event: Event = list(Random.nextInt(6))
          event.ts = System.currentTimeMillis()
          ctx.collect(event)
          TimeUnit.SECONDS.sleep(1)
        }
      }

      override def cancel(): Unit = {}
    }).assignAscendingTimestamps(_.ts)
    val tableEnv: StreamTableEnvironment = StreamTableEnvironment.create(env)

    tableEnv.createTemporaryView("tb", ds, $("name"), $("age"), $("ts").rowtime())
    tableEnv.toChangelogStream(tableEnv.sqlQuery(
      """
        |select name, count(age) as cnt, window_start, window_end from table(tumble(table tb, descriptor(ts), interval '5' second)) group by name, window_start, window_end
        |""".stripMargin)).print()

    env.execute()
  }

  // 分组聚合
  def demo2(): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val ds = env.addSource(new SourceFunction[Event] {
      override def run(ctx: SourceFunction.SourceContext[Event]): Unit = {
        val list: List[Event] = List(Event("Alice", 10, 1000L),
          Event("Bob", 20, 1000L),
          Event("Alice", 30, 5 * 1000L),
          Event("Cary", 40, 60 * 1000L),
          Event("Bob", 50, 90 * 1000L),
          Event("Alice", 60, 105 * 1000L))
        while (true) {
          ctx.collect(list(Random.nextInt(6)))
          TimeUnit.SECONDS.sleep(1)
        }
      }

      override def cancel(): Unit = ???
    })
    val tableEnv: StreamTableEnvironment = StreamTableEnvironment.create(env)
    tableEnv.createTemporaryView("tb", ds, $("name"), $("age"), $("ts"))
    tableEnv.toDataStream(tableEnv.sqlQuery("select name, age, ts from tb")).print()
    tableEnv.toChangelogStream(tableEnv.sqlQuery("select name, count(age) as cnt from tb group by name")).print()

    env.execute()
  }

  // 快速上手
  def demo1(): Unit = {
    val conf = new Configuration()
    conf.setString(RestOptions.BIND_PORT, "8080")
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf)
    env.setParallelism(3)
    val (maxParallelism, defaultParallelism): (Int, Int) = FlinkUtils.calculateMaxAndDefaultParallelism(env)
    val ds: DataStream[Event] = env.fromElements(Event("alan", 23, 100000L), Event("jack", 24, 200000L), Event("adam", 25, 300000L))

    // 获取表执行环境
    val tableEnv: StreamTableEnvironment = StreamTableEnvironment.create(env)
    // 从DataStream转换为表
    val table: Table = tableEnv.fromDataStream(ds)
    // 纯SQL方式
    val table1: Table = tableEnv.sqlQuery(s"select name, age from ${table}")
    // Table API方式
    val table2: Table = table.select($("name"))
    tableEnv.toDataStream(table2).print()

    env.execute()
  }

}
