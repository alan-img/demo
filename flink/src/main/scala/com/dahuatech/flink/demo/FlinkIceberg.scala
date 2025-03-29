package com.dahuatech.flink.demo

import org.apache.flink.api.common.functions.MapFunction
import org.apache.flink.configuration.{Configuration, RestOptions}
import org.apache.flink.streaming.api.datastream
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.table.data.RowData
import org.apache.iceberg.flink.TableLoader
import org.apache.iceberg.flink.source.FlinkSource
import org.slf4j.LoggerFactory

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

object FlinkIceberg {
  val log = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]): Unit = {

    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    val tableLoader: TableLoader = TableLoader.fromHadoopTable("hdfs://hadoop101:8020/user/hive/warehouse/spark_hadoop_catalog/test/test3")
    val batch: datastream.DataStream[RowData] = FlinkSource
      .forRowData()
      .env(env)
      .tableLoader(tableLoader)
      .streaming(true)
      .startSnapshotId(8049766748014798254L)
      .build()

    batch.map(new MapFunction[RowData, (String, Long)] {
      override def map(value: RowData): (String, Long) = (value.getString(0).toString, value.getInt(1))
    }).print()

    env.execute()

  }

}