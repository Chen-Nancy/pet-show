package com.nancy.petshow.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author chen
 * @date 2020/5/12 11:56
 */
public class Base64Util {
    /**
     * base64编码
     *
     * @param s 被编码的字符串
     * @return
     */
    public static String encoder(String s) {
        return Base64.getEncoder().encodeToString(s.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * base64编码
     *
     * @param b 被编码的byte数组
     * @return
     */
    public static String encoder(byte[] b) {
        return Base64.getEncoder().encodeToString(b);
    }

    /**
     * base64解码
     *
     * @param s 被解码的字符串
     * @return
     */
    public static String decoder(String s) {
        byte[] bytes = Base64.getDecoder().decode(s.getBytes(StandardCharsets.UTF_8));
        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * base64解码
     *
     * @param b 被解码的byte数组
     * @return
     */
    public static String decoder(byte[] b) {
        byte[] bytes = Base64.getDecoder().decode(b);
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
