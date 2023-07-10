package com.dahuatech.codeblock.sql

import com.alibaba.druid.pool.DruidDataSourceFactory

import java.sql.{Connection, DriverManager}
import java.util.Properties
import javax.sql.DataSource
import com.dahuatech.codeblock.io.FileUtil;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.template.sql</p>
 * <p>className: ConnUtil</p>
 * <p>date: 2023/5/4</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */
/*
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>${mysql.driver.version}</version>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>${druid.version}</version>
        </dependency>
 */
object ConnUtil {

  val prop = FileUtil.loadResourceAsProperties("pgsql.properties")
  val dataSource: DataSource = DruidDataSourceFactory.createDataSource(prop)

  /**
   * <h1>获取数据库连接 可选原生方式获取或从连接池获取 默认原生方式获取</h1>
   *
   * @param isUsePool true 表示使用数据库连接池
   * @return
   */
  def getConnection(isUsePool: Boolean = false): Connection = if (isUsePool) fromPoolGetConnection() else fromOriginGetConnection()

  /**
   * <h1>原生方式获取连接</h1>
   *
   * @return
   */
  def fromOriginGetConnection(): Connection = {
    Class.forName(prop.getProperty("driverClassName"))
    DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"))
  }

  /**
   * <h1>从连接池获取连接</h1>
   *
   * @return
   */
  def fromPoolGetConnection(): Connection = dataSource.getConnection
}
