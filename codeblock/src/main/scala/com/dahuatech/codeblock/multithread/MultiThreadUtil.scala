package com.dahuatech.codeblock.multithread

import org.slf4j.{Logger, LoggerFactory}

import java.util.concurrent.{Callable, ExecutorService, Executors, FutureTask}

/**
 * <h1>使用建议: 优先使用线程池 不建议使用原生创建线程池的方式</h1>
 * <font face="微软雅黑" color="green" size="6">使用建议:优先使用线程池 不建议使用原生创建线程池的方式</font> <br/>
 * 1.对于execute方法
 * 1.1 对于不需要获取线程返回值: 优先使用Thread t1 = new Thread(new Runnable {})包一层 这个可以获取这个线程以便对线程做一些如join操作
 * 1.2 需要获取线程返回值: 优先使用val ft = new FutureTask[Int](new Callable[Int] {}); Thread t1 = new Thread(ft)包一层 使用ft.get()获取返回值
 * (注意: val ft = new FutureTask[Int](new Runnable{}, 10); Thread t1 = new Thread(ft)只能获取固定值不建议使用)
 *
 * 2.对于submit方法 建议直接使用匿名实现类的匿名对象来实现 获取返回值通过println(threadPool.submit(new Callable[Int] {}).get())
 * 2.1 使用threadPool.submit(new Runnable {})开启线程 不带返回值 强行获取返回null </br>
 * 2.2 使用threadPool.submit(new Runnable {}, 10)获取固定返回值10 不推荐使用 </br>
 * 2.3 使用threadPool.submit(new Callable[Int] {})获取线程返回值 推荐使用 </br>
 */

object MultiThreadUtil {

  val logger: Logger = LoggerFactory.getLogger(getClass)

  var tickets: Int = 100

  def main(args: Array[String]): Unit = {

    /**
     * <h1>原生方式创建多线程 不建议使用</h1>
     * <font face="微软雅黑" color="green" size="6">原生方式创建多线程 不建议使用</font>
     */
    // 1.继承Thread 匿名实现类
    val thread: Thread = new Thread() {
      override def run(): Unit = {
        println(Thread.currentThread().getName)
      }
    }
    thread.start()

    // 2.Thread中套Runnable的匿名实现类的匿名对象
    new Thread(
      new Runnable {
        override def run(): Unit = {
          println(Thread.currentThread().getName)
        }
      }
    ).start()

    // 3.实现Runnable接口 在scala中实现接口也用extends 其实和上面2一样 只是上面简化了而已
    class MyRunnable extends Runnable {
      override def run(): Unit = {
        println("xxxx")
      }
    }
    val myRunnable = new MyRunnable()
    val thread1 = new Thread(myRunnable)
    thread1.start()


    // 4.实现Callable接口
    val ft = new FutureTask[Int](
      new Callable[Int] {
        override def call(): Int = {
          println(Thread.currentThread().getName)
          10
        }
      }
    )
    new Thread(ft).start()
    // 获取返回值
    println(ft.get())

    /**
     * <h1>使用线程池创建多线程 建议使用</h1>
     * <font face="微软雅黑" color="green" size="6">使用线程池创建多线程 建议使用</font>
     */
    val threadPool: ExecutorService = Executors.newFixedThreadPool(10)

    val t1: Thread = new Thread(new Runnable {
      override def run(): Unit = {
        while (true) {
          logger.synchronized {
            if (tickets > 0) {
              logger.info(s"${Thread.currentThread().getName} tickets: ${tickets}")
              tickets -= 1
            } else {
              return
            }

            // wait和notify只能由锁对象在同步代码块中调用 wait方法会自动释放锁
            // sleep和yield只能有Thread类调用 如果获得同步锁的情况下不会释放锁
            // join方法只能有Thread类的对象调用 调用join方法的线程会独占整个资源
            logger.wait()
            logger.notify()
          }
        }
      }
    })

    val t = new Thread();
    threadPool.submit(t)

    val t2: Thread = new Thread(new Runnable {
      override def run(): Unit = {
        while (true) {
          logger.synchronized {
            if (tickets > 0) {
              logger.info(s"${Thread.currentThread().getName} tickets: ${tickets}")
              tickets -= 1
            } else {
              return
            }

            logger.notify()
            logger.wait()
          }
        }
      }
    })

    val ft1 = new FutureTask[Int](new Callable[Int] {
      override def call(): Int = 10
    }) // 如果要获取线程返回值 调用ft.get()方法
    val t3: Thread = new Thread(ft1)

    val ft2 = new FutureTask[Int](new Runnable {
      override def run(): Unit = {

      }
    }, 10)
    val t4 = new Thread(ft2)

    t1.setName("t1")
    t2.setName("t2")
    t3.setName("t3")
    t4.setName("t4")
    // threadPool.execute(t1)
    // threadPool.execute(t2)
    // threadPool.execute(t3)
    // println(ft1.get())
    // threadPool.execute(t4)
    // println(ft2.get())

    threadPool.shutdown()

  }

}
