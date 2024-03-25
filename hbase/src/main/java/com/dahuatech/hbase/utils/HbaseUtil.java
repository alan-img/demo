package com.dahuatech.hbase.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.hbase.utils</p>
 * <p>className: HbaseUtil</p>
 * <p>date: 2023/4/13</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 */
@Slf4j
public class HbaseUtil {
    public static Connection connection;

    static {
        org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
        // ############### 两者而选一 都设置也没关系 ###############
        configuration.addResource("hbase-site.xml");
        configuration.set("hbase.zookeeper.quorum", "hadoop101,hadoop102,hadoop103");
        // ############### 两者而选一 都设置也没关系 ###############
        configuration.set("hbase.zookeeper.property.clientPort", "2181");
        configuration.set("zookeeper.znode.parent", "/hbase");
        configuration.setInt("zookeeper.recovery.retry", Integer.parseInt("3"));
        configuration.setInt("hbase.rpc.timeout", Integer.parseInt("10000"));
        configuration.setInt("hbase.client.retries.number", Integer.parseInt("3"));
        configuration.setInt("hbase.client.operation.timeout", Integer.parseInt("30000"));
        configuration.setInt("hbase.client.scanner.timeout.period", 10000);
        try {
            connection = ConnectionFactory.createConnection(configuration);
        } catch (IOException e) {
            log.info("create hbase connection exception", e);
            throw new RuntimeException(e);
        }
    }
    public static void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (IOException e) {
                log.info("close hbase connection exception", e);
                throw new RuntimeException(e);
            }
        }
    }
}
