package com.dahuatech.codeblock.multithread

import java.util.concurrent.{ExecutorService, Executors}

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.codeblock.multithread</p>
 * <p>className: ThreadUtil</p>
 * <p>date: 2023/5/15</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 * @description
 */

/**
 * 自定义线程工厂 优点：线程名前缀可自定义
 */
object ThreadUtil {
  def newFixedThreadPool(prefix: String, threadNum: Int): ExecutorService = {
    val namedThreadFactory = new NamedThreadFactory(prefix, false)
    Executors.newFixedThreadPool(threadNum, namedThreadFactory)
  }
}