package com.dahuatech.hbase.utils

import org.apache.hadoop.hbase.client.{Connection, ConnectionFactory}

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.demo.com.dahuatech.utils</p>
 * <p>className: HbaseUtil</p>
 * <p>date: 2023/3/27</p>
 *
 * @author qinjiawei(alan)
 * @since JDK8.0
 * @version 1.0.0
 */
object HbaseUtil {
  var hbaseConn: Connection = null
  try {
    hbaseConn = ConnectionFactory.createConnection()
  } catch {
    case exp: Exception => exp.printStackTrace()
  }

  def getConnection: Connection = hbaseConn

  def close: Unit = if (hbaseConn != null) hbaseConn.close()
}
