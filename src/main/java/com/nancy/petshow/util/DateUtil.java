package com.nancy.petshow.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chen
 * @date 2020/5/11 14:31
 */
public class DateUtil {
    private static SimpleDateFormat sdf;

    /**
     * 根据格式化时间字符串返回date
     *
     * @param pattern     格式化规则
     * @param formatValue 格式化时间字符串
     * @return
     * @throws ParseException
     */
    public static Date getParse(String pattern, String formatValue) throws ParseException {
        sdf = new SimpleDateFormat(pattern);
        return sdf.parse(formatValue);
    }

    /**
     * 获取格式化时间字符串
     *
     * @param pattern 格式化规则
     * @param date    date
     * @return
     */
    public static String getFormat(String pattern, Date date) {
        sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 获取当前时间的格式化时间字符串
     *
     * @param pattern 格式化规则
     * @return
     */
    public static String getFormat(String pattern) {
        sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }
}
