package com.dahuatech.parquet.bean

import org.apache.avro.generic.GenericData

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
case class Person(name: String, age: Long) {
  // 样例类/空参构造器必须这样设置 直接在类参数中设置通过反射调用空参构造器不生效
  def this() {
    this("", 0)
  }
}