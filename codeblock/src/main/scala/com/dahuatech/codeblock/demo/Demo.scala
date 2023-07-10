package com.dahuatech.codeblock.demo

import org.slf4j.LoggerFactory

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.template.demo</p>
 * <p>className: Demo</p>
 * <p>date: 2023/5/4</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */
object Demo {
  private val logger = LoggerFactory.getLogger(getClass)

  def main(args: Array[String]): Unit = {
    val s = "jack"
    logger.info(s"alan ${s}")
    logger.info("alan {}", s)
  }
}
