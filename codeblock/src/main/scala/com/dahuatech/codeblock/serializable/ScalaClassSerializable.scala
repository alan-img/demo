package com.dahuatech.codeblock.serializable

import scala.beans.BeanProperty

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.codeblock.serializable</p>
 * <p>className: ScalaClassSerializable</p>
 * <p>date: 2023/5/9</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 * @description
 */

/**
 * <font face="微软雅黑" color="red" size="6">继承Serializable标记 & 加一个SerialVersionUID注解</font>
 */
@SerialVersionUID(6849794470754667710L)
class ScalaClassSerializable() extends Serializable { // 在主构造器()前加private可以私有化主构造器，如果主构造器没有参数，()可省略
  @BeanProperty
  var name: String = _ // 属性一般不用private修饰，且为了生成getter/setter用@BeanProperty修饰
  @BeanProperty
  var age: Int = _ // 默认值，智能用在var变量

  def this(name: String, age: Int) = {
    this // 辅助构造器必须直接或间接调用主构造器，且必须放第一行
    this.name = name
    this.age = age
  }

  def canEqual(other: Any): Boolean = other.isInstanceOf[ScalaClassSerializable]

  /**
   * 在Scala中 == 和 equals 是相同的，如果没有重写equals方法
   * val p = Person("alan", 24); p == Person("alan", 24)或者p.equals(Person("alan", 24))结果是false
   * 如果重写equals方法 p == Person("alan", 24)或者p.equals(Person("alan", 24))结果是true
   *
   * 除此之外p还能调用eq和ne方法 此方法直接比较对象的首地址 也就是说无论是否重写equals
   * p.eq(Person("alan", 24))都为false; p.ne(Person("alan", 24))都为true
   *
   * canEqual是equals的辅助方法 用于判断类型是否一致
   *
   * @param other
   * @return
   */
  override def equals(other: Any): Boolean = other match {
    case that: ScalaClassSerializable =>
      (that canEqual this) &&
        name == that.name &&
        age == that.age
    case _ => false
  }

  override def hashCode(): Int = {
    val state = Seq(name, age)
    state.map(_.hashCode()).foldLeft(0)((a, b) => 31 * a + b)
  }

  override def toString = s"ScalaClassSerializable($name, $age)"
}