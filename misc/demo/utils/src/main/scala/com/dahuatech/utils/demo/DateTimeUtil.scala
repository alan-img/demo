package com.dahuatech.utils.demo

import java.time._
import java.time.format.DateTimeFormatter
import java.util.TimeZone

/**
 * <p>projectName: bigdata</p>
 * <p>packageName: com.dahuatech.utils</p>
 * <p>className: DateTimeUtil</p>
 * <p>date: 2023/2/28</p>
 *
 * @author qinjiawei(alan)
 * @since JDK8.0
 * @version 1.0.0
 */
object DateTimeUtil {
  // 获取系统默认时区
  private val currentSystemTimeZone: String = "+" + (TimeZone.getDefault.getRawOffset / (1000 * 60 * 60))

  /**
   * 获取日期的字符串形式
   * 不进行格式化 LcoalDate默认是以'-'连接 eg: 2023-01-30
   *
   * @param localDate 2023-01-30
   * @return 20230130
   */
  def toLocalDateString(localDate: LocalDate, format: String = "yyyyMMdd"): String = localDate.format(DateTimeFormatter.ofPattern(format))

  /**
   * 获取时间的字符串形式
   * 不进行格式化 LcoalTime默认是以':'连接且带小数影响使用 eg: 17:43:50.437
   *
   * @param localTime 17:43:50.437
   * @return 17:43:50
   */
  def toLocalTimeString(localTime: LocalTime, format: String = "HHmmss"): String = localTime.format(DateTimeFormatter.ofPattern(format))

  /**
   * 获取时间日期的字符串形式
   * 不进行格式化 LcoalDateTime默认是以"2023-01-30T17:43:50.437"格式显示
   *
   * @param localTime 2023-01-30T17:43:50.437
   * @return 20230130 17:43:50
   */
  def toLocalDateTimeString(localDateTime: LocalDateTime, format: String = "yyyyMMddHHmmss"): String = localDateTime.format(DateTimeFormatter.ofPattern(format))

  /**
   * 毫秒直接转化为String
   *
   * @param milliSeconds
   * @param format
   * @return
   */
  def milliSecondsToLocalDateTimeString(milliseconds: Long, format: String = "yyyyMMddHHmmss", timeZone: String = currentSystemTimeZone): String = {
    val instant: Instant = Instant.ofEpochMilli(milliseconds)
    val localDateTime: LocalDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.of(timeZone))
    localDateTime.format(DateTimeFormatter.ofPattern(format))
  }

  /**
   * 按照指定格式将字符串转换为时间日期对象
   *
   * @param dateString 20230130
   * @return LocalDate对象
   */
  def toLocalDate(dateString: String, format: String = "yyyyMMdd"): LocalDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(format))

  /**
   * 按照指定格式将字符串转换为时间日期对象
   *
   * @param timeString 17:43:50
   * @return LocalTime对象
   */
  def toLocalTime(timeString: String, format: String = "HHmmss"): LocalTime = LocalTime.parse(timeString, DateTimeFormatter.ofPattern(format))

  /**
   * 按照指定格式将字符串转换为时间日期对象
   *
   * @param dateTimeString 2023-01-30-17-43-50
   * @return LocalDateTime对象
   */
  def toLocalDateTime(dateTimeString: String, format: String = "yyyyMMddHHmmss"): LocalDateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern(format))

  /**
   * Long类型的时间戳毫秒数转为LocalDateTime对象
   *
   * @param milliSecond
   * @param timeZone
   * @return
   */
  def millisecondsToLocalDateTime(milliseconds: Long, timeZone: String = currentSystemTimeZone): LocalDateTime = {
    val instant: Instant = Instant.ofEpochMilli(milliseconds)
    LocalDateTime.ofInstant(instant, ZoneOffset.of(timeZone))
  }

  /**
   * LocalDateTime对象转为Long类型的毫秒数 LocalDate和LocalTime不能转 因为时间信息不全
   *
   * @param localDateTime
   * @return 1653028143015
   */
  def localDateTimeToMilliseconds(localDateTime: LocalDateTime, timeZone: String = currentSystemTimeZone): Long = localDateTime.toInstant(ZoneOffset.of(timeZone)).toEpochMilli

  /**
   * 字符串转换为毫秒数
   *
   * @param milliSecond 1653028143015
   * @return LocalDateTime对象
   */
  def stringToMilliseconds(dateTimeString: String, format: String = "yyyyMMddHHmmss", timeZone: String = currentSystemTimeZone): Long = {
    localDateTimeToMilliseconds(toLocalDateTime(dateTimeString, format), timeZone)
  }
}