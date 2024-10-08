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
        initEnv();
    }

    public static void initEnv() throws IOException {
        HbaseUtil hbaseUtil = new HbaseUtil();
        // 1、删除所有表
        hbaseUtil.deleteAllTable();
        // 2、新建表
        hbaseUtil.createTable("default", "stu", "info");
        // 3、插入数据
        for (int i = 0; i < 10; i++) {
            hbaseUtil.insert("default", "stu", i + "", "info", "name", i + "alan");
            hbaseUtil.insert("default", "stu", i + "", "info", "age", i + 20 + "");
        }
    }
}