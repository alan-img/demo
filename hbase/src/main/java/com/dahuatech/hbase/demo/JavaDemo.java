package com.dahuatech.hbase.demo;

import com.dahuatech.hbase.utils.HbaseUtil;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.hbase.demo</p>
 * <p>className: JavaDemo</p>
 * <p>date: 2023/4/13</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 */
public class JavaDemo {
    public static void main(String[] args) throws IOException {
        // Admin admin = HbaseUtil.connection.getAdmin();
        // NamespaceDescriptor namespaceDescriptor = NamespaceDescriptor.create("test").build();
        // admin.createNamespace(namespaceDescriptor);
        // System.out.println(admin.tableExists(TableName.valueOf("default:stu")));
        // TableDescriptor tableDescriptor = TableDescriptorBuilder
        //         .newBuilder(TableName.valueOf("default:test"))
        //         .setColumnFamily(ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("info")).build())
        //         .build();
        // admin.createTable(tableDescriptor);
        // admin.disableTable(TableName.valueOf("test"));
        // admin.deleteTable(TableName.valueOf("test"));
        // admin.close();

        Table table = HbaseUtil.connection.getTable(TableName.valueOf("stu"));
        // Put put = new Put(Bytes.toBytes("1006"));
        // put.addColumn(Bytes.toBytes("info"), Bytes.toBytes("name"), Bytes.toBytes("alan"));
        // table.put(put);

        // ArrayList<Get> list = new ArrayList<>();
        // list.add(new Get(Bytes.toBytes("1001")));
        // Result[] results = table.get(list);
        // for (Result result : results) {
        //     Cell[] cells = result.rawCells();
        //     for (Cell cell : cells) {
        //         System.out.println(new String(CellUtil.cloneRow(cell)));
        //     }
        // }

        // Scanner scanner = new Scanner();
        // table.getScanner()

        Delete delete = new Delete(Bytes.toBytes("1006"));
        table.delete(delete);

        table.close();
    }
}