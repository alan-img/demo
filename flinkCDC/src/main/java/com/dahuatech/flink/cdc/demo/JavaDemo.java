package com.dahuatech.flink.cdc.demo;

import com.ververica.cdc.connectors.mysql.source.MySqlSource;
import com.ververica.cdc.connectors.mysql.table.StartupOptions;
import com.ververica.cdc.debezium.JsonDebeziumDeserializationSchema;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.RestOptions;
import org.apache.flink.runtime.state.hashmap.HashMapStateBackend;
import org.apache.flink.runtime.state.storage.FileSystemCheckpointStorage;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.CheckpointConfig;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.time.Duration;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.cdc.demo</p>
 * <p>className: JavaDemo</p>
 * <p>date: 2025/3/3</p>
 *
 * @author qinjiawei(Administrator)
 * @version 1.0.0
 * @since JDK8.0
 */

public class JavaDemo {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);

        // env.enableCheckpointing(1000L, CheckpointingMode.AT_LEAST_ONCE);
        // env.setStateBackend(new HashMapStateBackend());
        // env.setRestartStrategy(RestartStrategies.failureRateRestart(3, Time.days(1), Time.minutes(60 * 1000L)));
        // env.getCheckpointConfig().setCheckpointStorage(new FileSystemCheckpointStorage("hdfs://hadoop101:8020/flink/checkpoint"));
        // env.getCheckpointConfig().setMinPauseBetweenCheckpoints(60 * 1000);
        // env.getCheckpointConfig().setCheckpointTimeout(60 * 1000L);
        // env.getCheckpointConfig().setMaxConcurrentCheckpoints(1);
        // env.getCheckpointConfig().enableExternalizedCheckpoints(CheckpointConfig.ExternalizedCheckpointCleanup.RETAIN_ON_CANCELLATION);

        MySqlSource<String> mySqlSource = MySqlSource.<String>builder()
                .hostname("192.168.101.101")
                .port(3306)
                .username("root")
                .password("root")
                .databaseList("test")
                .tableList("test.demo")
                .deserializer(new JsonDebeziumDeserializationSchema())
                .startupOptions(StartupOptions.initial())
                .build();

        DataStreamSource<String> ds = env.fromSource(mySqlSource, WatermarkStrategy.noWatermarks(), "mysqlSource");

        ds.print();

        env.execute();

    }

}
