package com.dahuatech.scala.demo

import scala.collection.mutable

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.scala.demo</p>
 * <p>className: Demo</p>
 * <p>date: 2023/5/4</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */
object Demo {
  def main(args: Array[String]): Unit = {

    val list: List[Int] = List(1, 2, 3, 4, 5, 6, 7, 8, 9)
    val nestedList: List[List[Int]] = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
    val wordList: List[String] = List("hello world", "hello atguigu", "hello scala")
    //（1）过滤
    val filters: List[Int] = list.filter(x => x % 2 == 0)
    println(filters)
    //（2）转化/映射
    val maps: List[Int] = list.map(x => x + 1)
    println(maps)
    //（3）扁平化
    val flattens: List[Int] = nestedList.flatten
    println(flattens)
    //（4）扁平化+映射 注：flatMap 相当于先进行 map 操作，在进行 flatten操作
    val strings: List[String] = wordList.flatMap(x => x.split(" "))
    println(strings)
    //（5）分组
    println(list.groupBy(x => x % 2))

    val result: Int = list.reduce(_ + _)
    println(result)
    val result1: Int = list.reduceRight(_ + _)
    println(result1)

    val i: Int = list.fold(10)(_ + _)
    println(i)
    val i1: Int = list.foldLeft(10)(_ + _)
    println(i1)

    val queue: mutable.Queue[Int] = mutable.Queue(1, 2, 3)
    queue.enqueue(4)
    println(queue)
    val i2: Int = queue.dequeue()
    println(i2)
    println(queue)

    val stack: mutable.Stack[Int] = mutable.Stack(1, 2, 3)
    stack.push(4)
    stack.push(5)
    println(stack)
    val i3: Int = stack.pop()
    println(i3)
    println(stack)

    val opt1: Option[Int] = None
    val opt2: Option[Int] = Some(10)
    println(opt1.getOrElse("dfa"))

    try {
      val sum = 10 / 0
    } catch {
      case exp: Exception => println("exp")
    } finally {
      println("finally")
    }

    println()

    val p: Person = Person("alan", 24)

    println(p == Person("alan", 24))
  }
}
