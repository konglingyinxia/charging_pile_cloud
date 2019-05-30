package com.util;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Date;

/**
 * 处理时间类，比如冬令时、夏令时
 *
 * @author zhengliangzhang
 */
public class DealDateUtil {

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_TIME_FORMATTER_SEQ = DateTimeFormat.forPattern("yyyyMMddHHmmss");
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormat.forPattern("HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd");

    /**
     * 判断时间是否是夏令时
     *
     * @return true夏令时 false冬令时
     */
    public static boolean isSummerTime() {
        boolean isSummerTime = false;
        LocalDateTime nowDate = LocalDateTime.now();

        // 夏令时
        if (nowDate.isAfter(getDSTStart()) && nowDate.isBefore(getDSTEnd())) {
            isSummerTime = !isSummerTime;
        }

        return isSummerTime;
    }


    /**
     * 获取夏令时开始时间（冬令时结束时间）每年三月第二个周日Daylight Saving Time：DST
     *
     * @return
     */
    public static LocalDateTime getDSTStart() {
        int year = YearMonth.now().getYear();
        int month = DateTimeConstants.MARCH;
        int dayOfWeek = DateTimeConstants.SUNDAY;

        return getSecondOfMonth(dayOfWeek, month, year);
    }

    /**
     * 获取夏令时结束时间（冬令时开始时间）每年十一月第一个周日Daylight Saving Time：DST
     *
     * @return
     */
    public static LocalDateTime getDSTEnd() {
        int year = YearMonth.now().getYear();
        int month = DateTimeConstants.NOVEMBER;
        int dayOfWeek = DateTimeConstants.SUNDAY;

        return getFirstOfMonth(dayOfWeek, month, year);
    }

    /**
     * 获取year年month月份，第一个周dayOfWeek的日期
     * 比如：获取2018年3月份的第一个周日 getFirstOfMonth(DateTimeConstants.SUNDAY, DateTimeConstants.MARCH, 2018)
     *
     * @param dayOfWeek 周几
     * @param month
     * @param year
     * @return
     */
    public static LocalDateTime getFirstOfMonth(int dayOfWeek, int month, int year) {
        return new LocalDateTime(year, month, 1, 0, 0, 0)
                .dayOfWeek()
                .setCopy(dayOfWeek);
    }

    /**
     * 获取year年month月份，第二个周dayOfWeek的日期
     * 比如：获取2018年3月份的第二个周日 getSecondOfMonth(DateTimeConstants.SUNDAY, DateTimeConstants.MARCH, 2018)
     *
     * @param dayOfWeek 周几
     * @param month
     * @param year
     * @return
     */
    public static LocalDateTime getSecondOfMonth(int dayOfWeek, int month, int year) {
        return new LocalDateTime(year, month, 1, 0, 0, 0)
                .dayOfWeek()
                .setCopy(dayOfWeek)
                .plusWeeks(1);
    }

    /**
     * 根据给定的日期字符串，获取LocalDateTime
     *
     * @param mDate 日期格式为yyyy-MM-dd hh:mm:ss
     * @return
     */
    public static LocalDateTime getLocalDateTime(String mDate) {
        return LocalDateTime.parse(mDate, DATE_TIME_FORMATTER);
    }

    /**
     * 根据给定的 格式化方法 转化日期字符串，获取LocalDateTime
     *
     * @param mDate 日期格式为yyyy-MM-dd hh:mm:ss
     *              DateTimeFormatter
     * @return
     */
    public static LocalDateTime getLocalDateTime(String mDate, DateTimeFormatter df) {
        return LocalDateTime.parse(mDate, df);
    }


    /**
     * 根据给定的日期字符串，获取LocalDateTime
     *
     * @param date
     * @param time
     * @return
     */
    public static LocalDateTime getLocalDateTime(String date, String time) {
        return LocalDateTime.parse(date + " " + time, DATE_TIME_FORMATTER);
    }

    /**
     * 获取东八区 LocalDateTime
     *
     * @return
     */
    public static LocalDateTime getUTC8LocalDateTime() {
        return LocalDateTime.now(DateTimeZone.UTC).plusHours(8);
    }

    /**
     * 判断当前日期是否是周六周日
     */
    public static boolean isWeekend() {
        LocalDateTime now = getUTC8LocalDateTime();
        int dayOfWeek = now.getDayOfWeek();
        return dayOfWeek == DateTimeConstants.SATURDAY || dayOfWeek == DateTimeConstants.SUNDAY;
    }

    /**
     * 判断给定日期是否合法yyyy-MM-dd
     *
     * @param dateStr
     * @return
     */
    public static boolean isDate(String dateStr) {
        boolean isDate = true;
        try {
            LocalDate.parse(dateStr, DateTimeFormat.mediumDate());
        } catch (Exception e) {
            isDate = false;
        }

        return isDate;
    }

    /**
     * 日期转字符串
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        if (null != date) {
            return LocalDateTime.fromDateFields(date).toString(DATE_TIME_FORMATTER);
        } else {
            return null;
        }
    }
    /**
     * 日期转字符串
     *
     * @param date
     * @return
     */
    public static String dateToStringSeq(Date date) {
        if (null != date) {
            return LocalDateTime.fromDateFields(date).toString(DATE_TIME_FORMATTER_SEQ);
        } else {
            return null;
        }
    }



    /**
     * 日期转时间字符串
     *
     * @param date
     * @return
     */
    public static String dateToTimeString(Date date) {
        if (null != date) {
            return LocalDateTime.fromDateFields(date).toString(TIME_FORMATTER);
        } else {
            return null;
        }
    }

    /**
     * 日期转日期字符串
     *
     * @param date
     * @return
     */
    public static String dateToDateString(Date date) {
        if (null != date) {
            return LocalDateTime.fromDateFields(date).toString(DATE_FORMATTER);
        } else {
            return null;
        }
    }

    /**
     * 日期字符串转为日期
     */
    public static Date stringDateToDate(String dateString) {
        if (null != dateString) {
            return LocalDateTime.parse(dateString, DATE_FORMATTER).toDate();
        } else {
            return null;
        }
    }

    public static Date getNowDate() {
        return DateTime.now().toDate();
    }

    /**
     * 日期 按添加
     */
    public static LocalDateTime addDay(Date date, int num) {
        LocalDateTime localDateTime = LocalDateTime.fromDateFields(date);
        return localDateTime.plusDays(num);
    }

    /**
     * 返回小数点位数
     *
     * @param slidingScalePrice
     * @return
     */
    public static int getSpotNumlen(BigDecimal slidingScalePrice) {
        return slidingScalePrice.scale();
    }

    /**
     * date 转 LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date){
        LocalDateTime localDateTime = new LocalDateTime(date);
        return localDateTime;
    }

    /**
     *  LocalDateTime转 date
     * @param localDateTime
     * @return
     */
    public static Date LocalDateTimeToDate(LocalDateTime localDateTime){
        return localDateTime.toDate();
    }

    /**
     * 获取年月日 去除时分秒的时间
     */
    public  static LocalDateTime getNoHms(LocalDateTime localDateTime){
        return  new LocalDateTime(localDateTime.getYear(),localDateTime.getMonthOfYear(),1
        ,0,0,0);
    }

    /**
     * 获取年月日 时间
     */
    public static LocalDateTime getYearMonthDay(LocalDateTime localDateTime) {
        return new LocalDateTime(localDateTime.getYear(), localDateTime.getMonthOfYear(),
                localDateTime.getDayOfMonth(), 0, 0, 0);
    }

    public static void main(String[] args) {
        System.out.println(getNoHms(LocalDateTime.now()));
    }


}
