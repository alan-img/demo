package com.dahuatech.flink.demo

import org.apache.flink.api.common.eventtime.{BoundedOutOfOrdernessWatermarks, SerializableTimestampAssigner, TimestampAssigner, TimestampAssignerSupplier, Watermark, WatermarkGenerator, WatermarkGeneratorSupplier, WatermarkOutput, WatermarkStrategy}
import org.apache.flink.api.common.functions.{AggregateFunction, FilterFunction, FlatMapFunction, IterationRuntimeContext, MapFunction, Partitioner, ReduceFunction, RichMapFunction, RuntimeContext}
import org.apache.flink.api.common.serialization.{SimpleStringEncoder, SimpleStringSchema}
import org.apache.flink.api.common.state.{AggregatingState, AggregatingStateDescriptor, ListState, ListStateDescriptor, ReducingState, ReducingStateDescriptor, ValueState, ValueStateDescriptor}
import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.configuration.{Configuration, RestOptions}
import org.apache.flink.connector.jdbc.{JdbcConnectionOptions, JdbcSink, JdbcStatementBuilder}
import org.apache.flink.core.fs.Path
import org.apache.flink.streaming.api.functions.{KeyedProcessFunction, ProcessFunction}
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.DefaultRollingPolicy
import org.apache.flink.streaming.api.functions.source.{ParallelSourceFunction, RichParallelSourceFunction, SourceFunction}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.api.scala.function.{ProcessAllWindowFunction, ProcessWindowFunction}
import org.apache.flink.streaming.api.windowing.assigners.{SlidingEventTimeWindows, TumblingEventTimeWindows, TumblingProcessingTimeWindows}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.triggers.CountTrigger
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer, FlinkKafkaProducer}
import org.apache.flink.table.api.Table
import org.apache.flink.table.api.bridge.scala.StreamTableEnvironment
import org.apache.flink.util.Collector
import org.apache.kafka.common.Cluster
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.LoggerFactory
import sun.nio.cs.StandardCharsets
import org.apache.flink.table.api.Expressions.$

import java.sql.PreparedStatement
import java.time.Duration
import java.util
import java.util.Properties
import java.util.concurrent.TimeUnit
import scala.collection.mutable
import scala.sys.{env, props}
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

object Demo {
  val log = LoggerFactory.getLogger(getClass)

  // 获取本地监控环境
  // val conf = new Configuration()
  // conf.setString(RestOptions.BIND_PORT, "8080")
  // val env: StreamExecutionEnvironment = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf)
  def main(args: Array[String]): Unit = {

    // val conf = new Configuration()
    // conf.setString(RestOptions.BIND_PORT, "8080")
    // val env: StreamExecutionEnvironment = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf)

    // fromKafkaReadData(args)
    // fromCustomDataSource(args)
    // operator(args)
    // flinkSinkToFile(args)
    // flinkSinkToKafka(args)
    // flinkSinkToJDBC(args)
    // flinkSinkToCustomSystem(args)
    // flinkGenerateWaterMark(args)
    // flinkCustomWatermarkGenerateStragegy(args)
    // flinkWindowUsing(args)
    // flinkProcessAllWindowFunctionUsing(args)
    // flinkProcessKeyedWindowFunctionUsing(args)
    // flinkConnectAPIUsing(args)
    // flinkCheckPointUsing(args)
    // flinkSQLUsaging(args)
    // flinkSQLInputOutputData(args)

    val conf = new Configuration()
    conf.setString(RestOptions.BIND_PORT, "8080")
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf)
    env.setParallelism(3)

    val parameterTool: ParameterTool = ParameterTool.fromArgs(args)
    val properties: Properties = getKafkaProperties()
    val kafkaDataStream: DataStream[String] = env.addSource(
      new FlinkKafkaConsumer[String](parameterTool.get("topic.name", "first"),
        new SimpleStringSchema(),
        properties)
    ).name("first")
    kafkaDataStream.map(new RichMapFunction[String, String] {
      override def map(value: String): String = {
        println(getRuntimeContext.getTaskName)
        println(getRuntimeContext.getIndexOfThisSubtask)
        println(getRuntimeContext.getTaskNameWithSubtasks)
        println(getRuntimeContext.getNumberOfParallelSubtasks)
        println(getRuntimeContext.getMaxNumberOfParallelSubtasks)
        value
      }
    }).addSink(
      new FlinkKafkaProducer[String]("hadoop101:9092,hadoop102:9092,hadoop103:9092", "second", new SimpleStringSchema())
    ).name("second")
    env.execute()

  }

  def getKafkaProperties(): Properties = {
    val properties: Properties = new Properties()
    properties.setProperty("bootstrap.servers", "hadoop101:9092,hadoop102:9092,hadoop103:9092")
    properties.setProperty("group.id", "flink.kafka.consumer.group")
    properties.setProperty("key.deserializer", classOf[StringDeserializer].getName)
    properties.setProperty("value.deserializer", classOf[StringDeserializer].getName)
    properties.setProperty("auto.offset.reset", "latest")
    properties
  }

  def flinkSQLInputOutputData(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val tableEnv: StreamTableEnvironment = StreamTableEnvironment.create(env)
    // 创建输入表
    tableEnv.executeSql(
      """
        |create table demo(name string, age bigint)
        |with('connector' = 'filesystem', 'path' = 'D:\dev\idea\project\demo\flink\src\main\resources\word.txt', 'format' = 'csv')
        |""".stripMargin)

    // 方式一：查询数据 推荐
    val table: Table = tableEnv.sqlQuery("select name from demo")
    tableEnv.toDataStream(table).print()
    // tableEnv.createTemporaryView("tmpTable", table)

    // 方式二：查询数据 不推荐
    // val demoTable: Table = tableEnv.from("demo")
    // tableEnv.toDataStream(demoTable.select($("name"))).print()

    // 创建输出表
    tableEnv.executeSql(
      """
        |create table demo_output(name string)
        |with('connector' = 'filesystem', 'path' = 'D:\dev\idea\project\demo\flink\src\main\resources\word_output.txt', 'format' = 'csv')
        |""".stripMargin)

    // executeInsert出发执行
    table.executeInsert("demo_output")
  }

  def flinkSQLUsaging(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val dataStream: DataStream[(String, Long, Long)] = env
      .addSource(new SourceFunction[String] {
        private var index = 0
        private val list = Array("alan", "adam", "jane", "jack")

        override def run(ctx: SourceFunction.SourceContext[String]): Unit = {
          while (true) {
            ctx.collect(list(index))
            index += 1
            if (index >= list.length) index = 0
            TimeUnit.MILLISECONDS.sleep(500L)
          }
        }

        override def cancel(): Unit = ???
      })
      .map(name => (name, System.currentTimeMillis(), Random.nextInt(100).toLong))

    val tableEnv: StreamTableEnvironment = StreamTableEnvironment.create(env)
    val table: Table = tableEnv.fromDataStream(dataStream).as("name", "timestamp", "price")
    // 基于tableEnv写对象 纯SQL 更经常使用
    tableEnv.toDataStream(
      tableEnv.sqlQuery(s"select name, price from ${table}")
    ).print()
    // 基于table对象处理 DSL 不常用
    // tableEnv.toDataStream(table.select($("name"), $("price"))).print()

    env.execute()
  }

  def flinkCheckPointUsing(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val dataStream: DataStream[(String, Long)] = env
      .addSource(new SourceFunction[String] {
        private var index = 0
        private val list = Array("alan", "adam", "jane", "jack")

        override def run(ctx: SourceFunction.SourceContext[String]): Unit = {
          while (true) {
            ctx.collect(list(index))
            index += 1
            if (index >= list.length) index = 0
            TimeUnit.MILLISECONDS.sleep(500L)
          }
        }

        override def cancel(): Unit = ???
      }).map(x => (x, System.currentTimeMillis()))

    dataStream
      .assignAscendingTimestamps(_._2)
      .map(_._1)
      .keyBy(x => x)
      .process(new KeyedProcessFunction[String, String, String] {
        private lazy val valueState = getRuntimeContext.getState(new ValueStateDescriptor[Long]("valueState", classOf[Long]))
        private lazy val timerFlagState = getRuntimeContext.getState(new ValueStateDescriptor[Long]("timerFlagState", classOf[Long]))

        override def processElement(value: String, ctx: KeyedProcessFunction[String, String, String]#Context, out: Collector[String]): Unit = {
          valueState.update(valueState.value() + 1)

          if (timerFlagState.value() == 0L) {
            ctx.timerService().registerEventTimeTimer(ctx.timestamp() + 1000 * 2)
            timerFlagState.update(ctx.timestamp() + 1000 * 2)
          }
        }

        override def onTimer(timestamp: Long, ctx: KeyedProcessFunction[String, String, String]#OnTimerContext, out: Collector[String]): Unit = {
          super.onTimer(timestamp, ctx, out)
          out.collect(valueState.value().toString)
          timerFlagState.clear()
        }
      })
      .print()

    env.execute()
  }

  def flinkConnectAPIUsing(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val dataStream: DataStream[(String, Long)] = env
      .addSource(new SourceFunction[String] {
        private var index = 0
        private val list = Array("alan", "adam", "jane", "jack")

        override def run(ctx: SourceFunction.SourceContext[String]): Unit = {
          while (true) {
            ctx.collect(list(index))
            index += 1
            if (index >= list.length) index = 0
            TimeUnit.MILLISECONDS.sleep(500L)
          }
        }

        override def cancel(): Unit = ???
      }).map(x => (x, System.currentTimeMillis()))

    dataStream
      .assignAscendingTimestamps(_._2)
      .map(_._1)
      .keyBy(x => x)
      .process(new KeyedProcessFunction[String, String, String] {
        private lazy val valueState = getRuntimeContext.getState(new ValueStateDescriptor[Long]("valueState", classOf[Long]))
        private lazy val timerFlagState = getRuntimeContext.getState(new ValueStateDescriptor[Long]("timerFlagState", classOf[Long]))

        override def processElement(value: String, ctx: KeyedProcessFunction[String, String, String]#Context, out: Collector[String]): Unit = {
          valueState.update(valueState.value() + 1)

          if (timerFlagState.value() == 0L) {
            ctx.timerService().registerEventTimeTimer(ctx.timestamp() + 1000 * 2)
            timerFlagState.update(ctx.timestamp() + 1000 * 2)
          }
        }

        override def onTimer(timestamp: Long, ctx: KeyedProcessFunction[String, String, String]#OnTimerContext, out: Collector[String]): Unit = {
          super.onTimer(timestamp, ctx, out)
          out.collect(valueState.value().toString)
          timerFlagState.clear()
        }
      })
      .print()

    env.execute()
  }

  def flinkProcessKeyedWindowFunctionUsing(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val dataStream: DataStream[(String, Long)] = env
      .addSource(new SourceFunction[String] {
        private var index = 0
        private val list = Array("alan", "adam", "jane", "jack")

        override def run(ctx: SourceFunction.SourceContext[String]): Unit = {
          while (true) {
            ctx.collect(list(index))
            index += 1
            if (index >= list.length) index = 0
            TimeUnit.MILLISECONDS.sleep(500L)
          }
        }

        override def cancel(): Unit = ???
      })
      .map(x => (x, System.currentTimeMillis()))
      .assignAscendingTimestamps(_._2)

    val A: OutputTag[(String, Long)] = OutputTag[(String, Long)]("A")
    val B: OutputTag[String] = OutputTag[String]("B")
    val dataStream1: DataStream[(String, Long)] = dataStream.process(new ProcessFunction[(String, Long), (String, Long)] {
      override def processElement(value: (String, Long), ctx: ProcessFunction[(String, Long), (String, Long)]#Context, out: Collector[(String, Long)]): Unit = {
        if (value._2 % 2 == 0) {
          ctx.output(A, value)
        } else {
          ctx.output(B, value._1)
        }

        out.collect(value)
      }
    })

    val a: DataStream[(String, Long)] = dataStream1.getSideOutput(A)
    val b: DataStream[String] = dataStream1.getSideOutput(B)
    val connectedStreams: ConnectedStreams[(String, Long), String] = a.connect(b)
    connectedStreams.map(x => x._1, x => x).print("AB")

    env.execute()
  }

  def flinkProcessAllWindowFunctionUsing(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val dataStream: DataStream[(String, Long)] = env
      .addSource(new SourceFunction[String] {
      private var index = 0
      private val list = Array("alan", "adam", "jane", "jack")

      override def run(ctx: SourceFunction.SourceContext[String]): Unit = {
        while (true) {
          ctx.collect(list(index))
          index += 1
          if (index >= list.length) index = 0
          TimeUnit.MILLISECONDS.sleep(500L)
        }
      }

      override def cancel(): Unit = ???
    })
      .map(x => (x, System.currentTimeMillis()))
      .assignAscendingTimestamps(_._2)

    dataStream
      .windowAll(SlidingEventTimeWindows.of(Time.seconds(10), Time.seconds(5)))
      .process(new ProcessAllWindowFunction[(String, Long), String, TimeWindow] {
        override def process(context: Context, elements: Iterable[(String, Long)], out: Collector[String]): Unit = {
          val hashMap: mutable.HashMap[String, Long] = mutable.HashMap.empty[String, Long]
          elements.map(_._1).foreach(x => {
            hashMap.get(x) match {
              case Some(count) => hashMap.put(x, count + 1)
              case None => hashMap.put(x, 1)
            }
          })

          val list: List[(String, Long)] = hashMap.toList.sortBy(_._2).reverse.take(2)
          val result: StringBuilder = new StringBuilder()
          result.append(s"=================== window: ${context.window.getStart} -> ${context.window.getEnd} ================== \n")
          list.indices.foreach(index => {
            result
              .append(s"top: ${index + 1} ")
              .append(s"name: ${list(index)._1} ")
              .append(s"browser number: ${list(index)._2} \n")
          })
          out.collect(result.toString())
        }
      })
      .print()
    env.execute()
  }

  def flinkWindowUsing(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    val dataStream: DataStream[(String, Long)] = env.addSource(new SourceFunction[(String, Long)] {
      override def run(ctx: SourceFunction.SourceContext[(String, Long)]): Unit = {
        val array: Array[String] = Array("alan", "adam", "jack", "jane")
        while (true) {
          ctx.collect((array(Random.nextInt(4)), System.currentTimeMillis()))
          TimeUnit.MILLISECONDS.sleep(100L)
        }
      }

      override def cancel(): Unit = ???
    })
    dataStream.assignTimestampsAndWatermarks(
      WatermarkStrategy.forBoundedOutOfOrderness[(String, Long)](Duration.ofSeconds(5))
        .withTimestampAssigner(new SerializableTimestampAssigner[(String, Long)] {
          override def extractTimestamp(element: (String, Long), recordTimestamp: Long): Long = element._2
        })
    ).keyBy(_._1)
      .window(TumblingEventTimeWindows.of(Time.seconds(5)))
      .reduce((x, y) => (x._1, x._2 + y._2))
      .print()

    env.execute()
  }

  def flinkCustomWatermarkGenerateStragegy(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.getConfig.setAutoWatermarkInterval(100L)
    env.setParallelism(1)
    val dataStream: DataStream[(String, Long)] = env.addSource(new SourceFunction[(String, Long)] {
      override def run(ctx: SourceFunction.SourceContext[(String, Long)]): Unit = {
        val array: Array[String] = Array("alan", "adam", "jack", "jane")
        while (true) {
          ctx.collect((array(Random.nextInt(4)), System.currentTimeMillis()))
          TimeUnit.MILLISECONDS.sleep(100L)
        }
      }

      override def cancel(): Unit = {}
    })
    dataStream.assignTimestampsAndWatermarks(new WatermarkStrategy[(String, Long)] {
      override def createTimestampAssigner(context: TimestampAssignerSupplier.Context): TimestampAssigner[(String, Long)] = {
        super.createTimestampAssigner(context)
        new SerializableTimestampAssigner[(String, Long)] {
          override def extractTimestamp(element: (String, Long), recordTimestamp: Long): Long = element._2
        }
      }

      override def createWatermarkGenerator(context: WatermarkGeneratorSupplier.Context): WatermarkGenerator[(String, Long)] = {
        new WatermarkGenerator[(String, Long)] {
          private var delay: Long = TimeUnit.SECONDS.toMillis(5)
          private var maxWatermark: Long = Long.MinValue + delay + 1

          override def onEvent(event: (String, Long), eventTimestamp: Long, output: WatermarkOutput): Unit = {
            maxWatermark = math.max(maxWatermark, event._2)
          }

          override def onPeriodicEmit(output: WatermarkOutput): Unit = {
            val watermark: Watermark = new Watermark(maxWatermark - delay - 1)
            output.emitWatermark(watermark)
          }
        }
      }
    })
    env.execute()
  }

  def flinkGenerateWaterMark(array: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.getConfig.setAutoWatermarkInterval(100L)
    env.setParallelism(1)
    val dataStream: DataStream[(String, Long)] = env.addSource(new SourceFunction[(String, Long)] {
      override def run(ctx: SourceFunction.SourceContext[(String, Long)]): Unit = {
        val array: Array[String] = Array("alan", "adam", "jack", "jane")
        while (true) {
          ctx.collect((array(Random.nextInt(4)), System.currentTimeMillis()))
          TimeUnit.MILLISECONDS.sleep(100L)
        }
      }

      override def cancel(): Unit = {}
    })
    dataStream.assignTimestampsAndWatermarks(
      WatermarkStrategy.forBoundedOutOfOrderness[(String, Long)](Duration.ofSeconds(5))
        .withTimestampAssigner(new SerializableTimestampAssigner[(String, Long)] {
          override def extractTimestamp(element: (String, Long), recordTimestamp: Long): Long = element._2
        })
    ).print()
    env.execute()
  }

  def flinkSinkToCustomSystem(args: Array[String]): Unit = {
    // val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val conf = new Configuration()
    conf.setString(RestOptions.BIND_PORT, "8080")
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(conf)
    env.setParallelism(1)
    val dataStream: DataStream[String] = env.addSource(new SourceFunction[String] {
      private var isRunning: Boolean = true
      private var index: Int = 0

      override def run(ctx: SourceFunction.SourceContext[String]): Unit = {
        val array: Array[String] = Array("alan", "jack", "adam", "marry")
        while (isRunning) {
          ctx.collect(array(index))
          index += 1
          if (index == 4) index = 0
          Thread.sleep(100L)
        }
      }

      override def cancel(): Unit = {
        isRunning = false
      }
    })
    dataStream.addSink(new RichSinkFunction[String] {
      // 定义连接对象
      // private var conn = xxxxxx

      // 获取连接
      override def open(parameters: Configuration): Unit = {

      }

      // 每条数据调用一次 真正执行写数据的方法
      override def invoke(value: String, context: SinkFunction.Context): Unit = {

      }

      // 关闭连接
      override def close(): Unit ={

      }
    })
    env.execute()
  }

  def flinkSinkToJDBC(args: Array[String]): Unit = {
    // 只能写入有界流数据 无界流数据无法写入 或许以后学习到窗口逻辑就可以写入了
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    env.setParallelism(1)
    // val dataStream: DataStream[String] = env.addSource(new SourceFunction[String] {
    //   private var isRunning: Boolean = true
    //   private var index: Int = 0
    //
    //   override def run(ctx: SourceFunction.SourceContext[String]): Unit = {
    //     val array: Array[String] = Array("alan", "jack", "adam", "marry")
    //     while (isRunning) {
    //       ctx.collect(array(index))
    //       index += 1
    //       if (index == 4) index = 0
    //       Thread.sleep(100L)
    //     }
    //   }
    //
    //   override def cancel(): Unit = {
    //     isRunning = false
    //   }
    // })
    val dataStream: DataStream[String] = env.readTextFile("D:\\dev\\idea\\project\\demo\\flink\\src\\main\\resources\\word.txt")
    dataStream.addSink(
      JdbcSink.sink(
        "INSERT INTO stu(name, age) VALUES (?, ?)",
        new JdbcStatementBuilder[String] {
          override def accept(t: PreparedStatement, u: String): Unit = {
            println(u)
            t.setString(1, u)
            t.setInt(2, 10)
          }
        },
        new JdbcConnectionOptions.JdbcConnectionOptionsBuilder()
          .withUrl("jdbc:mysql://hadoop101:3306/test?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true")
          .withDriverName("com.mysql.cj.jdbc.Driver")
          .withUsername("root")
          .withPassword("root")
          .build()
      )
    )
    env.execute()
  }

  def flinkSinkToKafka(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val dataStream: DataStream[String] = env.addSource(new SourceFunction[String] {
      private var isRunning: Boolean = true
      private var index: Int = 0

      override def run(ctx: SourceFunction.SourceContext[String]): Unit = {
        val array: Array[String] = Array("alan", "jack", "adam", "marry")
        while (isRunning) {
          ctx.collect(array(index))
          index += 1
          if (index == 4) index = 0
          Thread.sleep(10L)
        }
      }

      override def cancel(): Unit = {
        isRunning = false
      }
    })
    dataStream.addSink(
      new FlinkKafkaProducer[String]("hadoop101:9092,hadoop102:9092,hadoop103:9092", "second", new SimpleStringSchema())
    )
    env.execute()
  }

  def flinkSinkToFile(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val dataStream: DataStream[String] = env.addSource(new SourceFunction[String] {
      private var isRunning: Boolean = true
      private var index: Int = 0

      override def run(ctx: SourceFunction.SourceContext[String]): Unit = {
        val array: Array[String] = Array("alan", "jack", "adam", "marry")
        while (isRunning) {
          ctx.collect(array(index))
          index += 1
          if (index == 4) index = 0
          Thread.sleep(10L)
        }
      }

      override def cancel(): Unit = {
        isRunning = false
      }
    })
    dataStream.addSink(
      StreamingFileSink.forRowFormat(new Path("./flink/output"), new SimpleStringEncoder[String]("UTF-8"))
        .withRollingPolicy(DefaultRollingPolicy.builder()
          .withRolloverInterval(java.util.concurrent.TimeUnit.SECONDS.toMillis(10))
          .withInactivityInterval(java.util.concurrent.TimeUnit.SECONDS.toMillis(2))
          .withMaxPartSize(1024 * 10)
          .build()).build()
    ).setParallelism(4)
    env.execute()
  }

  def operator(args: Array[String]): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    // val dataStream: DataStream[String] = env.addSource(new RichParallelSourceFunction[String] {
    //   private var isRunning: Boolean = true
    //   private var index: Int = 0
    //
    //   override def run(ctx: SourceFunction.SourceContext[String]): Unit = {
    //     val array: Array[String] = Array("alan", "jack", "adam", "marry")
    //     val context: RuntimeContext = getRuntimeContext
    //     while (isRunning) {
    //       if (context.getIndexOfThisSubtask == ((index + 1) % 2)) {
    //         ctx.collect((index + 1).toString)
    //       }
    //       index += 1
    //       Thread.sleep(1000L)
    //     }
    //   }
    //
    //   override def cancel(): Unit = {
    //     isRunning = false
    //   }
    // }).setParallelism(2)
    val dataStream: DataStream[String] = env.addSource(new SourceFunction[String] {
      private var isRunning: Boolean = true
      private var index: Int = 0

      override def run(ctx: SourceFunction.SourceContext[String]): Unit = {
        val array: Array[String] = Array("alan", "jack", "adam", "marry")
        while (isRunning) {
          ctx.collect(array(index))
          index += 1
          if (index == 4) index = 0
          Thread.sleep(500L)
        }
      }

      override def cancel(): Unit = {
        isRunning = false
      }
    })
    dataStream.partitionCustom(new Partitioner[String] {
      override def partition(key: String, numPartitions: Int): Int = {
        key match {
          case "alan" => 0
          case "jack" => 1
          case "adam" => 2
          case "marry" => 3
        }
      }
    }, x => x).print()
    env.execute()
  }

  def fromCustomDataSource(args: Array[String]): Unit = {
    val parameterTool: ParameterTool = ParameterTool.fromArgs(args)
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val dataSource: DataStream[String] = env.addSource(new SourceFunction[String] {
      private var isRunning = true

      override def run(ctx: SourceFunction.SourceContext[String]): Unit = {
        while (isRunning) {
          ctx.collect("alan")
        }
      }

      override def cancel(): Unit = {
        isRunning = false
      }
    })
    dataSource.print()
    env.execute()
  }

  def fromKafkaReadData(args: Array[String]): Unit = {
    val parameterTool: ParameterTool = ParameterTool.fromArgs(args)
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val properties: Properties = getKafkaProperties()
    val kafkaDataStream: DataStream[String] = env.addSource(new FlinkKafkaConsumer[String](parameterTool.get("topic.name", "first"), new SimpleStringSchema(), properties))
    kafkaDataStream.print()
    env.execute()
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

  def boundedStream(): Unit = {
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val dataStream: DataStream[String] = env.readTextFile("D:\\dev\\idea\\project\\demo\\flink\\src\\main\\resources\\word.txt")
    val keyedStream: KeyedStream[(String, Int), String] = dataStream.flatMap(_.split(" ")).map(word => (word, 1)).keyBy(_._1)
    val sum: DataStream[(String, Int)] = keyedStream.sum(1)
    sum.print()
    env.execute()
  }

}
