package com.dahuatech.scala.demo

/**
 * 样例类创建模板，该模板创建的类不能通过反射创建，因为该类没有空参构造器
 * 参数不用val或者var修饰
 * 样例类是为模式匹配优化的类，通用性较差
 *
 * @param name
 * @param age
 */
case class Human(name: String, age: Int)
