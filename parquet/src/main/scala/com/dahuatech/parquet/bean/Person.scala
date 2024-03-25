package com.dahuatech.parquet.bean

import com.dahuatech.parquet.demo.Demo
import org.apache.avro.Schema

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.parquet.bean</p>
 * <p>className: Person</p>
 * <p>date: 1/2/2024</p>
 *
 * @author qinjiawei(alan)
 * @since JDK8.0
 * @version 1.0.0
 */

@SerialVersionUID(234288747753438L)
case class Person(var name: String, var age: Long) {
  def this() = this("", 0L)

  override def getSchema: Schema = Demo.getSchema()

  override def get(i: Int): AnyRef = i match {
    case 0 => this.name
    case 1 => this.age.asInstanceOf[AnyRef]
    case _ => null
  }

  override def put(i: Int, v: Any): Unit = i match {
    case 0 => this.name = v.toString
    case 1 => this.age = v.asInstanceOf[Long]
    case _ =>
  }
}
