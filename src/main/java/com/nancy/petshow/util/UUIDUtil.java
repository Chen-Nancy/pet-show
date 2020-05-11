package com.nancy.petshow.util;

import java.util.UUID;

/**
 * @author chen
 * @date 2020/5/11 14:03
 */
public class UUIDUtil {
    /**
     * 获得指定位数的随机UUID
     *
     * @param num UUID位数，１－３６
     * @return
     */
    public static String getUUIDByNum(int num) {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring(0, num);
    }
}
