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
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.bridge.java.StreamTableEnvironment;

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

public class FlinkSQL {

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

        tableEnv.executeSql("create table tb(id string primary key NOT ENFORCED, name string) " +
                "with ('connector' = 'mysql-cdc', 'hostname' = '192.168.101.101', 'port' = '3306', " +
                "'username' = 'root', 'password' = 'root', 'database-name' = 'test', 'table-name' = 'demo')");

        Table tb = tableEnv.sqlQuery("select * from tb");

        tb.execute().print();

    }

}
