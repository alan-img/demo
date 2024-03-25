package com.dahuatech.codeblock.sql

import org.apache.commons.dbutils.QueryRunner
import org.apache.commons.dbutils.handlers.BeanListHandler

import java.sql.{Connection, PreparedStatement, ResultSet}
import java.util
import java.util.concurrent.TimeUnit


object Demo {
  def main(args: Array[String]): Unit = {

    val conn: Connection = ConnUtil.getConnection(true)

    // JDBC原生查询操作
    val psQuery: PreparedStatement = conn.prepareStatement("select * from stu;")
    val resultSet: ResultSet = psQuery.executeQuery()
    while (resultSet.next()) {
      val name: String = resultSet.getString(1)
      val age: Int = resultSet.getInt(2)
      println(s"name = ${name}, age = ${age}")
    }

    // JDBC原生更新操作
    val psUpdate: PreparedStatement = conn.prepareStatement("insert into stu values (?, ?);")
    psUpdate.setObject(1, "alan")
    psUpdate.setObject(2, 10)
    psUpdate.execute()

    /**
     * <font face="微软雅黑" color="red" size="6">使用第三方库DBUtils库执行查询操作 强烈推荐使用</font>
     */
    val queryRunner = new QueryRunner()
    val list: util.List[Person] = queryRunner.query(conn, "select * from stu", new BeanListHandler(classOf[Person]))
    val len: Int = list.size()
    for (elem <- 0 until len) {
      println(list.get(elem))
    }

    TimeUnit.SECONDS.sleep(10000)
  }
}
