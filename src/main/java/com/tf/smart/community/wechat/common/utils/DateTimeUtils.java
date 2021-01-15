package com.tf.smart.community.wechat.common.utils;

import com.google.common.collect.Maps;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * 日期时间工具类
 *
 * @author 翟晶
 **/
public class DateTimeUtils {


    public static final String QUARTZ_PATTERN_FORMAT = "%s %s %s %s %s ? %s";

    public static final String DATE_DEFAULT_FORMAT = "yyyy-MM-dd";

    /**
     * 获取当前日期时间
     * 格式: yyyy-MM-dd HH:mm:sss
     *
     * @return
     */
    public static String getCommonCurrentDateStr() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:sss").format(new Date());
    }

    /**
     * 根据 pattern 获取当前时间的字符串
     *
     * @param pattern
     * @return
     */
    public static String getCurrentStr(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }

    /**
     * 将日期转为Quartz的日期Pattern
     *
     * @param date
     * @return
     */
    public static String convertDateToQuartzPattern(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String pattern = String.format(QUARTZ_PATTERN_FORMAT, cal.get(Calendar.SECOND), cal.get(Calendar.MINUTE), cal.get(Calendar.HOUR_OF_DAY)
                , cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));
        return pattern;
    }

    public static Date parse(String maintainDateStart, SimpleDateFormat sdf) throws ParseException {
        return sdf.parse(maintainDateStart);
    }

    /**
     * 获取当前日期的后一天
     * @return
     */
    public static String getNextDateStr() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date date = calendar.getTime();
        return new SimpleDateFormat(DATE_DEFAULT_FORMAT).format(date);
    }

    public static Map<String, Date> getByMonth(DateTime dateTime) {
        Map<String, Date> monthMap = Maps.newHashMap();

        monthMap.put("begin", dateTime.dayOfMonth().withMinimumValue().millisOfDay().withMinimumValue().toDate());
        monthMap.put("end", dateTime.dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue().toDate());

        return monthMap;
    }

    /**
     * 将日期的时分秒置为0
     * @param beginDate
     * @return
     */
    public static Date setDateTimeToZero(Date beginDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取上个月的起始时间
     * @return
     */
    public static Date getLastMonthStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取上个月的结束时间
     * @return
     */
    public static Date getLastMonthEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 获取本月的起始时间
     * @return
     */
    public static Date getCurrentMonthStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date getDayOfMinimum(Date date) {
        return new DateTime(date).millisOfDay().withMinimumValue().toDate();
    }

    public static Date getDayOfMaximum(Date date) {
        return new DateTime(date).millisOfDay().withMaximumValue().toDate();
    }
}
