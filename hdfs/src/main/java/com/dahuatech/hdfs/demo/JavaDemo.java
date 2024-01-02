package com.dahuatech.hdfs.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.hdfs.demo</p>
 * <p>className: JavaDemo</p>
 * <p>date: 2023/4/14</p>
 *
 * @author qinjiawei(336105)
 * @version 1.0.0
 * @since JDK8.0
 */
public class JavaDemo {
    private static Logger logger = LoggerFactory.getLogger(JavaDemo.class);

    public static void main(String[] args) throws Exception {
        FileSystem fileSystem = FileSystem.get(URI.create("hdfs://hdp-hadoop-hdp-namenode-0.hdp-hadoop-hdp-namenode.cloudspace.svc.cluster.local/"), new Configuration());
        System.out.println(fileSystem);
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path("/"));
        for (FileStatus fileStatus : fileStatuses) {
            System.out.println(fileStatus);
        }

        System.out.println(System.getProperty("java.security.krb5.conf"));
        System.out.println(System.getProperty("name"));
        System.out.println(System.getProperty("age"));
    }
}
