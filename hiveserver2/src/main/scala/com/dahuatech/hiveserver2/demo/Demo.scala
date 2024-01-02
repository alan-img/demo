package com.dahuatech.hiveserver2.demo

import com.dahuatech.hiveserver2.bean.Student
import com.dahuatech.hiveserver2.utils.SQLUtil
import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.handlers.BeanListHandler

import java.sql.Connection
import java.util

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.hiveserver2</p>
 * <p>className: Demo</p>
 * <p>date: 1/2/2024</p>
 *
 * @author qinjiawei(alan)
 * @since JDK8.0
 * @version 1.0.0
 */

object Demo {
  def main(args: Array[String]): Unit = {
    val conn: Connection = SQLUtil.getHiveConnection()
    val queryRunner: QueryRunner = new QueryRunner()
    val list: util.List[Student] = queryRunner.query(conn, "select name, age from stu", new BeanListHandler[Student](classOf[Student]))
    println(list)
  }
}
