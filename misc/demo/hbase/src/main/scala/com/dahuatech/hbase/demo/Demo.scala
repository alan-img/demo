package com.dahuatech.hbase.demo

import com.dahuatech.hbase.utils.HbaseUtil
import org.apache.hadoop.hbase.{CellUtil, TableName}
import org.apache.hadoop.hbase.client.{Connection, Get, Put, Result, Table}
import org.apache.hadoop.hbase.util.Bytes

import java.util
import scala.collection.mutable.ArrayBuffer

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.demo</p>
 * <p>className: Demo</p>
 * <p>date: 2023/3/26</p>
 *
 * @author qinjiawei(alan)
 * @since JDK8.0
 * @version 1.0.0
 */
object Demo {

  def main(args: Array[String]): Unit = {

    // putData("default", "stu", "1005", "info", "name", "alan")
    getData("default", "stu", "1001", "1002", "1003", "1004", "1005")
  }

  def getData(namespace: String, tableName: String, rowKeys: String*): Unit = {
    val table: Table = HbaseUtil.getConnection.getTable(TableName.valueOf(namespace, tableName))

    val gets: util.ArrayList[Get] = new util.ArrayList[Get]()
    rowKeys.foreach {
      rowKey =>
        val get: Get = new Get(Bytes.toBytes(rowKey))
        gets.add(get)
    }
    val results: Array[Result] = table.get(gets)

    results.foreach {
      result =>
        result.rawCells().foreach {
          cell =>
            val rowKey: String = new String(CellUtil.cloneRow(cell))
            val columnFamily: String = new String(CellUtil.cloneFamily(cell))
            val qualifier: String = new String(CellUtil.cloneQualifier(cell))
            val value: String = new String(CellUtil.cloneValue(cell))

            println(rowKey, columnFamily, qualifier, value)
        }
    }
    table.close()
  }

  def putData(namespace: String, tableName: String, rowKey: String,
              columnFamily: String, columnQualifer: String, value: String): Unit = {

    val conn: Connection = HbaseUtil.getConnection
    val table: Table = conn.getTable(TableName.valueOf(namespace, tableName))
    val list: util.ArrayList[Put] = new util.ArrayList[Put]()
    for (i <- 0 until 10) {
      val put: Put = new Put(Bytes.toBytes(rowKey))
      put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(columnQualifer), Bytes.toBytes(value))
      list.add(put)
    }
    table.put(list)
    table.close()
  }
}
