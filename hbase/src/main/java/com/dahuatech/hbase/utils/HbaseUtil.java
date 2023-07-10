package com.dahuatech.hbase.utils;

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
public class HbaseUtil {
    public static Connection getHbaseConnection() throws IOException {
        org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
        conf.set("hbase.zookeeper.quorum", "hdp-zookeeper-hdp-zookeeper.cloudspace.svc.cluster.local");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("zookeeper.znode.parent", "/hbase");
        conf.setInt("zookeeper.recovery.retry", Integer.parseInt("3"));
        conf.setInt("hbase.rpc.timeout", Integer.parseInt("10000"));
        conf.setInt("hbase.client.retries.number", Integer.parseInt("3"));
        conf.setInt("hbase.client.operation.timeout", Integer.parseInt("30000"));
        conf.setInt("hbase.client.scanner.timeout.period", 10000);
        return ConnectionFactory.createConnection(conf);
    }
}
