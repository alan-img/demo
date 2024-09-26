package com.dahuatech.hbase.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.NamespaceDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    /**
     * 删除所有自建表
     */
    public void deleteAllTable() throws IOException {
        Admin admin = HbaseUtil.connection.getAdmin();
        TableName[] tableNames = admin.listTableNames();
        for (TableName tableName : tableNames) {
            String namespace = Bytes.toString(tableName.getNamespace());
            String table = Bytes.toString(tableName.getName());
            if (namespace.contains("default")) {
                deleteTable(namespace, table);
            } else {
                String[] splits = table.split(":");
                deleteTable(splits[0], splits[1]);
            }
        }
    }

    /**
     * 插入数据
     * @param namespace
     * @param tableName
     * @param rowKey
     * @param columnFamily
     * @param columnQualifier
     * @param value
     */
    public void insert(String namespace, String tableName, String rowKey, String columnFamily,
                       String columnQualifier, String value) throws IOException {
        Table table = HbaseUtil.connection.getTable(TableName.valueOf(namespace, tableName));
        try {
            Put put = new Put(Bytes.toBytes(rowKey));
            put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(columnQualifier), Bytes.toBytes(value));
            table.put(put);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            table.close();
        }
    }

    /**
     * 删除表
     * @param namespace
     * @param inputTableName
     * @throws IOException
     */
    public void deleteTable(String namespace, String inputTableName) throws IOException {
        if (!tableExists(namespace, inputTableName)) {
            log.info("table {}:{} not exists", namespace, inputTableName);
        }

        Admin admin = HbaseUtil.connection.getAdmin();
        try {
            TableName tableName = TableName.valueOf(namespace, inputTableName);
            admin.disableTable(tableName);
            admin.deleteTable(tableName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            admin.close();
        }
    }

    /**
     * 创建表
     * @param namespace
     * @param tableName
     * @param columnFamilies
     * @throws IOException
     */
    public void createTable(String namespace, String tableName, String... columnFamilies) throws IOException {
        if (columnFamilies.length == 0) {
            log.info("create column family as least set one column family");
            return;
        }

        if (tableExists(namespace, tableName)) {
            log.info("table {}:{} already exists", namespace, tableName);
            return;
        }

        Admin admin = HbaseUtil.connection.getAdmin();
        TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(TableName.valueOf(namespace, tableName));
        for (String columnFamily : columnFamilies) {
            ColumnFamilyDescriptorBuilder columnFamilyDescriptorBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(columnFamily));
            columnFamilyDescriptorBuilder.setMaxVersions(5);
            ColumnFamilyDescriptor columnFamilyDescriptor = columnFamilyDescriptorBuilder.build();
            tableDescriptorBuilder.setColumnFamily(columnFamilyDescriptor);
        }

        TableDescriptor tableDescriptor = tableDescriptorBuilder.build();
        try {
            admin.createTable(tableDescriptor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            admin.close();
        }
    }

    /**
     * 判断表是否存在
     * @param namespace
     * @param inputTableName
     * @return
     * @throws IOException
     */
    public boolean tableExists(String namespace, String inputTableName) throws IOException {
        Admin admin = HbaseUtil.connection.getAdmin();
        TableName tableName = TableName.valueOf(namespace, inputTableName);
        boolean isExists = false;
        try {
            isExists = admin.tableExists(tableName);
        } catch (IOException e) {
            log.info("judge table exists", e);
            throw new RuntimeException(e);
        } finally {
            admin.close();
        }

        return isExists;
    }

    /**
     * 创建命名空间
     * @param namespace
     */
    public void createNamespace(String namespace) throws IOException {
        Admin admin = HbaseUtil.connection.getAdmin();
        NamespaceDescriptor.Builder namespaceDescriptorBuilder = NamespaceDescriptor.create(namespace);
        namespaceDescriptorBuilder.addConfiguration("createTime", CurrentTimeToFormattedString());
        NamespaceDescriptor namespaceDescriptor = namespaceDescriptorBuilder.build();
        try {
            admin.createNamespace(namespaceDescriptor);
        } catch (IOException e) {
            log.info("hbase create namespace exception", e);
            throw new RuntimeException(e);
        } finally {
            // 关闭admin
            admin.close();
        }
    }

    /**
     * 关闭连接
     */
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

    /**
     * 将当前时间转为【yyyy-MM-dd HH:mm:ss】格式
     * @return
     */
    public String CurrentTimeToFormattedString() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 定义日期时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 格式化当前时间
        String formattedDate = now.format(formatter);

        // 输出格式化的时间
        return formattedDate;
    }
}
