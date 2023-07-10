package com.dahuatech.codeblock.io

import com.dahuatech.codeblock.sql.Person

import java.io.{File, FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream}

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.codeblock.io</p>
 * <p>className: ReadWriteObjectToFile</p>
 * <p>date: 2023/5/8</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 * @description
 */

/**
 * <font face="微软雅黑" color="red" size="6">使用时需去除 extends App 不然无法通过对象名直接调用</font>
 */
object ReadWriteObjectToFile extends App {
  // 读写的对象必须支持序列化
  // writeObject()
  readObject()

  def writeObject(): Unit = {
    var oos: ObjectOutputStream = null
    try {
      oos = new ObjectOutputStream(new FileOutputStream(new File("obj.dat")))
      val person: Person = Person("alan", 23)
      oos.writeObject(person)
    } catch {
      case exp: Exception => exp.printStackTrace()
    } finally {
      if (oos != null) {
        try {
          oos.close
        } catch {
          case exp: Exception => exp.printStackTrace()
        }
      }
    }
  }

  def readObject(): Unit = {
    var ois: ObjectInputStream = null
    try {
      ois = new ObjectInputStream(new FileInputStream(new File("obj.dat")))
      val person: Person = ois.readObject().asInstanceOf[Person]
      println(person)
    } catch {
      case exp: Exception => exp.printStackTrace()
    } finally {
      if (ois != null) {
        try {
          ois.close
        } catch {
          case exp: Exception => exp.printStackTrace()
        }
      }
    }
  }
}
