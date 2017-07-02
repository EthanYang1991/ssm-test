package cn.etao.ssm.utils;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对时间的操作，比如格式化 Created by yonghu on 16-8-26.
 */
public class DateTimeUtils {
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT = "yyyy-MM";
    public static final String DATE_DAY_FORMAT = "yyyy-MM-dd";
    public static final String DATE_DAY_FORMAT2 = "yyyyMMdd";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern(TIME_FORMAT);
    private static final String ES_TIME_REGEX = "\\d{4}-\\d{2}-\\d{2}";

    private static ThreadLocal<SimpleDateFormat> DATE_FORMATTER_LOCAL = new ThreadLocal<SimpleDateFormat>();
    private static ThreadLocal<SimpleDateFormat> DATE_DAY_YYYYMMDD_FORMATTER_LOCAL = new ThreadLocal<SimpleDateFormat>();
    private static ThreadLocal<SimpleDateFormat> DATE_DAY_FORMATTER_LOCAL = new ThreadLocal<SimpleDateFormat>();

    public static SimpleDateFormat getYYYYMMDateFormat() {
        SimpleDateFormat df = DATE_FORMATTER_LOCAL.get();
        if (df == null) {
            df = new SimpleDateFormat(DATE_FORMAT);
            DATE_FORMATTER_LOCAL.set(df);
        }
        return df;
    }

    /**
     * @return yyyy-MM-dd
     */
    public static SimpleDateFormat getYYYYMMDDDateFormat() {
        SimpleDateFormat df = DATE_DAY_FORMATTER_LOCAL.get();
        if (df == null) {
            df = new SimpleDateFormat(DATE_DAY_FORMAT);
            DATE_DAY_FORMATTER_LOCAL.set(df);
        }
        return df;
    }

    /**
     * @return yyyyMMdd
     */
    public static SimpleDateFormat getYYYYMMDDDateFormat2() {
        SimpleDateFormat df = DATE_DAY_YYYYMMDD_FORMATTER_LOCAL.get();
        if (df == null) {
            df = new SimpleDateFormat(DATE_DAY_FORMAT2);
            DATE_DAY_YYYYMMDD_FORMATTER_LOCAL.set(df);
        }
        return df;
    }

    /**
     * 获取当前时间的下一月起始时间
     *
     * @param startDate 传入的起始时间
     * @return 当前时间的下一个起始时间
     */
    public static DateTime getNextMonthBeginDate(DateTime startDate) {
        Preconditions.checkNotNull(startDate);
        DateTime endDateTime = startDate.plusMonths(1);
        DateTime nextMonthTime = new DateTime(endDateTime.getYear(), endDateTime.getMonthOfYear(), 1, 0, 0, 0);
        return nextMonthTime;
    }

    /**
     * 获取下一个小时的起始时间
     *
     * @param startDate 传入的时间
     * @return 下一个小时的起始时间
     */
    public static DateTime getNextHourBeginDate(DateTime startDate) {
        Preconditions.checkNotNull(startDate);
        startDate = startDate.plusHours(1);
        return new DateTime(startDate.getYear(), startDate.getMonthOfYear(), startDate.getDayOfMonth(),
                startDate.getHourOfDay(), 0, 0);
    }

    /**
     * 判断两个时间是否在一个月以内
     *
     * @param startDate 起始时间
     * @param endDate 结束时间
     * @return 是否在一个月内
     */
    public static boolean checkDateInOneMonth(DateTime startDate, DateTime endDate) {
        Preconditions.checkNotNull(startDate);
        Preconditions.checkNotNull(endDate);
        String startMonth = getYearAndMonth(startDate);
        String endMonth = getYearAndMonth(endDate);
        return startMonth.equals(endMonth);
    }

    /**
     * 提取年月信息，匹配booking表单名 eg：输入 2012-12-13 00：00：00 返回 201212
     *
     * @param date 时间字符串
     * @return 年月字符串
     */
    public static String getYearAndMonth(DateTime date) {
        Preconditions.checkNotNull(date);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(date.getYear());
        String month = String.valueOf(date.getMonthOfYear());
        if (month.length() < 2) {
            month = "0" + month;
        }
        stringBuilder.append(month);
        return stringBuilder.toString();
    }

    /**
     * 格式化时间，格式为yyyy-MM-dd HH:mm:ss
     *
     * @param date 时间，类型为DateTime
     * @return 时间字符串
     */
    public static String formatDate(DateTime date) {
        return date.toString(DATE_TIME_FORMATTER);
    }

    /**
     * 格式化时间，格式为yyyy-MM
     *
     * @param date 时间，类型为Date
     * @return 时间字符串
     */
    public static String formatDateYYYYMM(Date date) {
        return getYYYYMMDateFormat().format(date);
    }

    /**
     * 格式化时间，格式为yyyy-MM-dd
     *
     * @param date 时间，类型为Date
     * @return 时间字符串
     */
    public static String formatDateYYYYMMDD(Date date) {
        return getYYYYMMDDDateFormat().format(date);
    }

    /**
     * 格式化时间，格式为yyyyMMdd
     *
     * @param date 时间，类型为Date
     * @return 时间字符串
     */
    public static String formatDateYYYYMMDD2(Date date) {
        return getYYYYMMDDDateFormat2().format(date);
    }

    /**
     * 获取前一天起始时间
     *
     * @param date 时间，类型为Date
     * @return 前一天的起始时间
     */
    public static DateTime getPreDayDate(DateTime date) {
        Preconditions.checkNotNull(date);
        DateTime preDay = date.minusDays(1);
        return new DateTime(preDay.getYear(), preDay.getMonthOfYear(), preDay.getDayOfMonth(), 0, 0, 0);
    }

    /**
     * 获取DateTimeFormatter
     * 
     * @return 返回DateTimeFormatter
     */
    public static DateTimeFormatter getDateTimeFormatter() {
        return DATE_TIME_FORMATTER;
    }

    /**
     * 判断时间字符串是否满足格式yyyy-MM-dd
     * 
     * @param dateTime 时间字符串
     * @return 返回判断结果
     */
    public static boolean checkESDateTime(String dateTime) {
        if (Strings.isNullOrEmpty(dateTime)) {
            return false;
        }
        Pattern pattern = Pattern.compile(ES_TIME_REGEX);
        Matcher matcher = pattern.matcher(dateTime);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param startDate
     * @param endDate
     * @return
     */
//    public static Map<Date, Date> shardDateByYearReverse(Date startDate, Date endDate) {
//        Map<Date, Date> dateMap = Maps.newLinkedHashMap();
//        while (endDate.getYear() > startDate.getYear()) {
//            Date tempDate = Date.valueOf(Integer.toString(endDate.getYear() + 1900) + "-01-01");
//            dateMap.put(tempDate, endDate);
//            endDate = Date.valueOf(Integer.toString(endDate.getYear() + 1900 - 1) + "-12-31");
//        }
//        dateMap.put(startDate, endDate);
//        return dateMap;
//    }

    public static Map<Date, Date> orderDateByYearReverse(Date firstDate, Date secondDate) {
        Map<Date, Date> dateMap = Maps.newLinkedHashMap();
        if (firstDate.compareTo(secondDate) < 0) {
            dateMap.put(secondDate, secondDate);
            dateMap.put(firstDate, firstDate);
        } else {
            dateMap.put(firstDate, firstDate);
            dateMap.put(secondDate, secondDate);
        }

        return dateMap;
    }

    /**
     * 向后推num天
     * 
     * @param date
     * @param num
     * @return
     */
    public static Date toNextDay(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, num);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 向后推num周
     * 
     * @param date
     * @param num
     * @return
     */
    public static Date toNextWeek(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_MONTH, num);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 向后推num月
     * 
     * @param date
     * @param num
     * @return
     */
    public static Date toNextMonth(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, num);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 向后推num年
     * 
     * @param date
     * @param num
     * @return
     */
    public static Date toNextYear(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, num);
        return new Date(calendar.getTime().getTime());
    }

    /**
     * 向后推num月
     * 
     * @param date 2016-10
     * @param num
     * @return
     */
    public static String toNextYear(String date, int num) {
        Date fromdate = Date.valueOf(date + "-01");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fromdate);
        calendar.add(Calendar.YEAR, num);

        SimpleDateFormat formater = new SimpleDateFormat();
        formater.applyPattern("yyyy-MM");
        return formater.format(calendar.getTime());
    }

    /**
     * 判断当前日期是否为周末/节假日
     * 
     * @param date
     * @return
     */
//    public static boolean isWeekend(String date) {
//        // 特殊节假日
//        if (QConfigLoader.getHolidays().contains(date)) {
//            return true;
//        }
//        // 非特殊工作日
//        if (QConfigLoader.getWorkdays().contains(date)) {
//            return false;
//        }
//        DateTime dateTime = new DateTime(date);
//        return dateTime.getDayOfWeek() == 6 || dateTime.getDayOfWeek() == 7;
//    }

    public static List<Date> convertDate(Date startDate, Date endDate) {
        List<Date> dateList = Lists.newArrayList();
        DateTime startDateTime = new DateTime(startDate);
        DateTime endDateTime = new DateTime(endDate);
        while (startDateTime.isBefore(endDateTime)) {
            dateList.add(new Date(startDateTime.getMillis()));
            startDateTime = startDateTime.plusDays(1);
        }
        dateList.add(new Date(endDateTime.getMillis()));
        return dateList;
    }

    public static List<String> getInternalDays(String startDate, String endDate) {
        List<String> dateList = Lists.newArrayList();
        DateTime startDateTime = new DateTime(startDate);
        DateTime endDateTime = new DateTime(endDate);
        while (startDateTime.isBefore(endDateTime)) {
            dateList.add(startDateTime.toString(DATE_DAY_FORMAT));
            startDateTime = startDateTime.plusDays(1);
        }
        dateList.add(endDate);
        return dateList;
    }
}
