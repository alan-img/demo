package com.dahuatech.flink.demo

import org.apache.flink.api.common.eventtime.{SerializableTimestampAssigner, TimestampAssigner, TimestampAssignerSupplier, Watermark, WatermarkGenerator, WatermarkGeneratorSupplier, WatermarkOutput, WatermarkStrategy}
import org.apache.flink.api.common.functions.{FilterFunction, FlatMapFunction, IterationRuntimeContext, MapFunction, Partitioner, RichMapFunction, RuntimeContext}
import org.apache.flink.api.common.serialization.{SimpleStringEncoder, SimpleStringSchema}
import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.configuration.{Configuration, RestOptions}
import org.apache.flink.connector.jdbc.{JdbcConnectionOptions, JdbcSink, JdbcStatementBuilder}
import org.apache.flink.core.fs.Path
import org.apache.flink.streaming.api.functions.sink.{RichSinkFunction, SinkFunction}
import org.apache.flink.streaming.api.functions.sink.filesystem.StreamingFileSink
import org.apache.flink.streaming.api.functions.sink.filesystem.rollingpolicies.DefaultRollingPolicy
import org.apache.flink.streaming.api.functions.source.{ParallelSourceFunction, RichParallelSourceFunction, SourceFunction}
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer, FlinkKafkaProducer}
import org.apache.flink.util.Collector
import org.apache.kafka.common.Cluster
import org.apache.kafka.common.serialization.StringDeserializer
import org.slf4j.LoggerFactory
import sun.nio.cs.StandardCharsets

import java.sql.PreparedStatement
import java.time.Duration
import java.util
import java.util.Properties
import java.util.concurrent.TimeUnit
import scala.collection.mutable
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
    flinkCustomWatermarkGenerateStragegy(args)

  }

  def flinkCustomWatermarkGenerateStragegy(args: Array[String]): Unit = {
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
          override def extractTimestamp(element: (String, Long), recordTimestamp: Long): Long = {
            element._2
          }
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
    val properties: Properties = new Properties()
    properties.setProperty("bootstrap.servers", "hadoop101:9092,hadoop102:9092,hadoop103:9092")
    properties.setProperty("group.id", "flink.kafka.consumer.group")
    properties.setProperty("key.deserializer", classOf[StringDeserializer].getName)
    properties.setProperty("value.deserializer", classOf[StringDeserializer].getName)
    properties.setProperty("auto.offset.reset", "latest")
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
