package com.dahuatech.hiveserver2.utils
import com.alibaba.druid.pool.DruidDataSourceFactory

import java.sql.{Connection, SQLException}
import java.util.Properties
import javax.sql.DataSource

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.hiveserver2.util</p>
 * <p>className: SQLUtil</p>
 * <p>date: 1/2/2024</p>
 *
 * @author qinjiawei(alan)
 * @since JDK8.0
 * @version 1.0.0
 */

object SQLUtil {
  var properties: Properties = new Properties()
  properties.load(ClassLoader.getSystemClassLoader.getResourceAsStream("mysql.properties"))
  val sqlDataSource: DataSource = DruidDataSourceFactory.createDataSource(properties)
  properties.clear()

  properties.load(ClassLoader.getSystemClassLoader.getResourceAsStream("hive.properties"))
  val hiveDataSource: DataSource  = DruidDataSourceFactory.createDataSource(properties)

  def getSqlConnection(): Connection = {
    var conn: Connection = null
    try {
      conn = sqlDataSource.getConnection
    } catch {
      case e: SQLException =>
        throw new RuntimeException(e)
    }
    conn
  }

  def getHiveConnection(): Connection = {
    var conn: Connection = null
    try {
      conn = hiveDataSource.getConnection
    } catch {
      case e: SQLException =>
        throw new RuntimeException(e)
    }
    conn
  }
}
