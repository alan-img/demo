package com.dahuatech.codeblock.timer

import java.util.concurrent.{Executors, ScheduledExecutorService, TimeUnit}
import java.util.{Timer, TimerTask}

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.codeblock.timer</p>
 * <p>className: Timer</p>
 * <p>date: 2023/5/10</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 * @description
 */

object Timer {
  def main(args: Array[String]): Unit = {

    // 1.第一种实现 单独启动一个线程 + while (true) + Thread.sleep() 不推荐
    // 2.使用原生Timer类 Timer 是单线程的，假如有任务 A,B,C，任务 A 如果执行时
    // 那么就会影响任务 B,C 的启动和执行时间，如果 B,C 执行时间也比较长，那就会相互影响
    // 3.实现调度线程池 推荐使用
    scheduleThreadPool()
    // timerSchedule()
  }

  def scheduleThreadPool(): Unit ={
    val threadPool: ScheduledExecutorService = Executors.newScheduledThreadPool(10)

    // 只执行一次
    // threadPool.schedule()

    // 按固定频率调度
    // threadPool.scheduleAtFixedRate()

    // 按固定延迟调度
    threadPool.scheduleWithFixedDelay(new Runnable {
      override def run(): Unit = println("alan")
    }, 0, 1, TimeUnit.SECONDS)
  }

  def timerSchedule(): Unit = {
    // 第一次执行延迟0秒 没1秒执行一次
    val timer = new Timer()
    timer.schedule(new TimerTask {
      override def run(): Unit = println("alan")
    }, 0L)

    // 可以指定首次执行时间
    // timer.schedule(timerTask, DateUtils.addSeconds(new Date(), 5), period);

    Thread.sleep(5000L)
    // 终止调度任务
    timer.cancel()
    // 移除调度任务
    timer.purge()
  }
}
