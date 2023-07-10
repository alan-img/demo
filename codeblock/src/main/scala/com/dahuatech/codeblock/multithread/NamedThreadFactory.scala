package com.dahuatech.codeblock.multithread

import java.util.concurrent.ThreadFactory

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.codeblock.multithread</p>
 * <p>className: NamedThreadFactory</p>
 * <p>date: 2023/5/15</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 * @description
 */

class NamedThreadFactory(prefix: String, isDaemonThread: Boolean) extends ThreadFactory{
  override def newThread(r: Runnable): Thread = {
    val thread = new Thread(r)
    thread.setName(prefix + ": Thread-" + thread.getId)
    thread.setDaemon(isDaemonThread)
    thread
  }
}
