package com.nancy.petshow.util;

/**
 * @author chen
 * @date 2020/5/11 14:10
 */
public class RandomUtil {
    /**
     * 获取指定位数的数字字符串
     *
     * @param num 位数
     * @return
     */
    public static String getRandomByNum(int num) {
        double pow = Math.pow(10, num);
        double random = Math.random() * pow;
        String s = String.valueOf(random);
        return s.substring(0, num);
    }
}
