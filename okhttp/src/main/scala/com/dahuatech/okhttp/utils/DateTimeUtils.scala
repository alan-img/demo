package com.dahuatech.okhttp.utils

import java.time.format.DateTimeFormatter
import java.time._
import java.util.TimeZone

/**
 * @author qinjiawei
 */
object DateTimeUtils {

  private val currentSystemTimeZone: String = "+" + (TimeZone.getDefault.getRawOffset / (1000 * 60 * 60))

  /**
   * 获取日期的字符串形式
   * 不进行格式化, LcoalDate默认是以'-'连接 eg: 2022-05-20
   *
   * @param localDate
   * @return
   */
  def format(localDate: LocalDate): String = {
    // eg: 20220520
    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")
    // eg: 2022/05-20
    //    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd")

    localDate.format(dateTimeFormatter)
  }

  /**
   * 获取时间的字符串形式
   * 不进行格式化, LcoalTime默认是以':'连接且带小数点，影响使用，所以经常需要格式化
   * eg: 14:29:03.014
   *
   * @param localTime
   */
  def format(localTime: LocalTime): String = {
    // eg: 14:29:03
    val dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    // eg: 14-29-03
    //    val dateTimeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss")
    // eg: 14/29/03
    //    val dateTimeFormatter = DateTimeFormatter.ofPattern("HH/mm/ss")

    localTime.format(dateTimeFormatter)
  }

  /**
   * 获取时间日期的字符串形式
   * 不进行格式化, LcoalDateTime默认是以'2022-05-20T14:29:03.015'格式显示，所以要格式化
   *
   * @param localTime
   */
  def format(localDateTime: LocalDateTime): String = {
    // eg: 2022-05-20 14:29:03
    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    // eg: 2022-05-20-14-29-03
    //    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss")
    // eg: 2022/05/20 14:29:03
    //    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")

    localDateTime.format(dateTimeFormatter)
  }

  /**
   * 这里解析时间日期字符串->日期对象
   *
   * @param dateTimeString
   * @return
   */
  def parse(dateTimeString: String) = {
    // 解析格式, 这个格式可以修改 eg: 2022/05/22 10:10:10
    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    // 解析成的对象可以自己制定
    //    LocalDate.parse(dateTimeString, dateTimeFormatter)
    //    LocalTime.parse(dateTimeString, dateTimeFormatter)
    LocalDateTime.parse(dateTimeString, dateTimeFormatter)
  }

  /**
   * 获取LocalDateTime对象对应的时间戳
   * eg: 1653028143015
   * 如果LocalDateTime对象对应的是当前时间，则它的结果等价于 <=> System.currentTimeMillis()
   *
   * @param localDateTime
   * @return
   */
  def toEpochMilli(localDateTime: LocalDateTime): Long = {
    localDateTime.toInstant(ZoneOffset.of(currentSystemTimeZone)).toEpochMilli
  }

  /**
   * 从时间戳解析时间日期对象
   *
   * @param milli
   * @return
   */
  def parse(milli: Long): LocalDateTime = {
    val instant: Instant = Instant.ofEpochMilli(milli)
    LocalDateTime.ofInstant(instant, ZoneOffset.of(currentSystemTimeZone))
  }
}
