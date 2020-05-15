package com.nancy.petshow.util;

/**
 * @author chen
 * @date 2020/5/11 14:14
 */
public class ParameterUtil {
    /**
     * 判断字符串是否为空
     *
     * @param s
     * @return
     */
    public static boolean stringIsEmpty(String s) {
        return s == null || "".equals(s);
    }

    /**
     * 判断数字是否小于等于0
     *
     * @param n
     * @return
     */
    public static boolean numberIsEmpty(Number n) {
        return n == null || n.intValue() <= 0;
    }
}
