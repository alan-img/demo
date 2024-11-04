package com.dahuatech.flink.util

import org.apache.flink.runtime.state.KeyGroupRangeAssignment
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.flink.util</p>
 * <p>className: FlinkUtils</p>
 * <p>date: 2024/11/4</p>
 *
 * @author qinjiawei(Administrator)
 * @since JDK8.0
 * @version 1.0.0
 */

object FlinkUtils {

  /**
   * 计算flink任务的最大并行度和默认并行度
   * @param env
   * @return
   */
  def calculateMaxAndDefaultParallelism(env: StreamExecutionEnvironment): (Int, Int) = {
    val maxParallelism: Int = if (env.getMaxParallelism == -1) KeyGroupRangeAssignment.DEFAULT_LOWER_BOUND_MAX_PARALLELISM else env.getMaxParallelism
    val defaultParallelism: Int = env.getParallelism
    (maxParallelism, defaultParallelism)
  }

  /**
   * 计算KeyBy后数据进入的分区index
   * @param key
   * @param maxParallelism
   * @param defaultParallelism
   * @return
   */
  def calculateSubtaskIndexAfterKeyBy(key: Object, maxParallelism: Int, defaultParallelism: Int): Int = {
    KeyGroupRangeAssignment.assignKeyToParallelOperator(key, maxParallelism, defaultParallelism)
  }

}
