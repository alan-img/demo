package com.dahuatech.spark.demo

import com.dahuatech.spark.utils.SparkUtil
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types.StringType
import org.slf4j.{Logger, LoggerFactory}

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.spark.demo</p>
 * <p>className: SparkLocalDemo</p>
 * <p>date: 6/22/2024</p>
 *
 * @author qinjiawei(alan)
 * @since JDK8.0
 * @version 1.0.0
 */

/**
 * | 选项                            | 说明                      | 格式                                | 是否必填            |
 * | ----------------------------- | ----------------------- | --------------------------------- | --------------- |
 * | **`kafka.bootstrap.servers`** | Kafka Broker 列表，多个用逗号分隔 | `host1:9092,host2:9092`           | **是**           |
 * | **`subscribe`**               | 订阅指定 Topic（逗号分隔）        | `"topic1,topic2"`                 | **必填（或下面两个之一）** |
 * | **`subscribePattern`**        | 通过正则订阅 Topic            | `".*pattern.*"`                   | 互斥              |
 * | **`assign`**                  | 手动指定 Topic+分区           | `{"topicA":[0,1],"topicB":[2,4]}` | 互斥              |
 *
 * | 选项                               | 说明                                                                                    | 格式                                    | 批/流 |
 * | -------------------------------- | ------------------------------------------------------------------------------------- | ------------------------------------- | --- |
 * | **`startingOffsets`**            | 起始偏移：<br> - `earliest`：从最早<br> - `latest`：从最新<br> - JSON：`{"topicA":{"0":23,"1":-2}}` | `earliest` / `latest` / JSON          | 批、流 |
 * | **`endingOffsets`**              | 结束偏移（**批处理专用**）                                                                       | 同上                                    | 批   |
 * | **`startingOffsetsByTimestamp`** | 从指定时间戳起始偏移（ms）                                                                        | JSON：`{"topicA":{"0":1609459200000}}` | 批、流 |
 * | **`endingOffsetsByTimestamp`**   | 截止到指定时间戳（ms）（**批**）                                                                   | 同上                                    | 批   |
 * | **`failOnDataLoss`**             | 消费时找不到 offset（数据丢失）是否报错                                                               | `true` / `false`（默认 `true`）           | 批、流 |
 *
 * | 选项                            | 说明                                                     | 示例                                                                                                 |
 * | ----------------------------- | ------------------------------------------------------ | -------------------------------------------------------------------------------------------------- |
 * | **`kafka.security.protocol`** | 协议：`PLAINTEXT` / `SSL` / `SASL_PLAINTEXT` / `SASL_SSL` | `SASL_PLAINTEXT`                                                                                   |
 * | **`kafka.sasl.mechanism`**    | SASL 机制：`PLAIN` / `SCRAM-SHA-256` / `SCRAM-SHA-512`    | `PLAIN`                                                                                            |
 * | **`kafka.sasl.jaas.config`**  | SASL 配置                                                | `org.apache.kafka.common.security.plain.PlainLoginModule required username="user" password="pwd";` |
 *
 * | 选项                                | 说明                                         | 默认       |
 * | --------------------------------- | ------------------------------------------ | -------- |
 * | **`kafka.group.id`**              | Consumer Group ID（Structured Streaming 需要） | 无        |
 * | **`fetchOffset.numRetries`**      | 获取 offset 重试次数                             | `3`      |
 * | **`fetchOffset.retryIntervalMs`** | 获取 offset 重试间隔                             | `1000`ms |
 * | **`minPartitions`**               | 读取时最小分区数，Kafka 分区数 < minPartitions 时拆分     | 无        |
 */
object SparkLocalDemo {
  private val logger: Logger = LoggerFactory.getLogger(this.getClass)

  def main(args: Array[String]): Unit = {

    val sparkSession = SparkUtil.getLocalSparkSession()
    import sparkSession.implicits._

    sparkSession.read.table("default.stu")

  }
}
