package com.dahuatech.flink.bean

import org.apache.flink.streaming.api.functions.source.SourceFunction

import java.util.Random

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.flink.bean</p>
 * <p>className: RandomGenerateData</p>
 * <p>date: 2025/2/20</p>
 *
 * @author qinjiawei(Administrator)
 * @since JDK8.0
 * @version 1.0.0
 */

class RandomGenerateData extends SourceFunction[String] {
  var flag: Boolean = true

  override def run(ctx: SourceFunction.SourceContext[String]): Unit = {
    val random: Random = new Random()

    while (flag) {
      ctx.collect(s"${random.nextInt(100)}alan${random.nextInt(100)}")
    }
  }

  override def cancel(): Unit = {
    flag = false
  }
}
