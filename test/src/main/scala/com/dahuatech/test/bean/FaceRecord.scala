package com.dahuatech.test.bean

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.streaming.bean</p>
 * <p>className: FaceRecord</p>
 * <p>date: 2023/5/5</p>
 *
 * @author qinjiawei(336105)
 * @since JDK8.0
 * @version 1.0.0
 * @description
 */
case class FaceRecord(var featureId: Int,
                      var feature: Array[Float],
                      var faceAttribute: String)
