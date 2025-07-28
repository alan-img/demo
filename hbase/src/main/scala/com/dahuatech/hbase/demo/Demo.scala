package com.dahuatech.hbase.demo

import com.dahuatech.hbase.utils.HbaseUtil
import org.apache.hadoop.hbase.{CellUtil, CompareOperator, TableName}
import org.apache.hadoop.hbase.client.{Get, Result, ResultScanner, Scan, Table}
import org.apache.hadoop.hbase.filter.{ColumnValueFilter, Filter, FilterList, RegexStringComparator, RowFilter, SingleColumnValueFilter}
import org.apache.hadoop.hbase.protobuf.ProtobufUtil
import org.apache.hadoop.hbase.util.Bytes

import scala.collection.JavaConverters._

object Demo {

  def main(args: Array[String]): Unit = {

    scanData("default", "stu", ".*(25_10_10|25_10_11).*")

  }

  def scanData(namespace: String, tableName: String, reg: String): Unit = {

    val hbaseConn = HbaseUtil.connection
    val table: Table = hbaseConn.getTable(TableName.valueOf(namespace, tableName))

    val scan: Scan = new Scan()

//    val single = new SingleColumnValueFilter(Bytes.toBytes("info"), Bytes.toBytes("name"), CompareOperator.EQUAL, Bytes.toBytes("jack"))
//    single.setFilterIfMissing(true)
//    scan.setFilter(single)

//    scan.setTimeRange(0L, 1752822751000L)

    val scanner: ResultScanner = table.getScanner(scan)
    scanner.asScala.foreach {
      result =>
        result.rawCells().foreach {
          cell => println(Array(CellUtil.cloneRow(cell), CellUtil.cloneFamily(cell), CellUtil.cloneQualifier(cell), CellUtil.cloneValue(cell)).map(x => Bytes.toString(x)).mkString(" | "))
        }
    }
    table.close()
  }

}
