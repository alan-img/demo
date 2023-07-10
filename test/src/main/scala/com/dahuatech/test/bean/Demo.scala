package com.dahuatech.test.bean

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.streaming.bean</p>
 * <p>className: Demo</p>
 * <p>date: 2023/5/6</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 */

object Demo {
  def main(args: Array[String]): Unit = {
    val faceRecord: FaceRecord = FaceRecord(101, Array(1.2f, 1.4f), "alan")
    println(faceRecord.featureId)
    println(faceRecord.feature.toBuffer)
    println(faceRecord.faceAttribute)
    faceRecord.feature = null
    println(faceRecord)
  }
}
