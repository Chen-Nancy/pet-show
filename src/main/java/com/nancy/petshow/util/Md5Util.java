package com.nancy.petshow.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author chen
 * @date 2020/5/12 11:59
 */
public class Md5Util {
    /**
     * 对字符串进行指定次数的MD5加密
     *
     * @param info 被加密的字符串
     * @param num  加密次数
     * @return
     * @throws Exception
     */
    public static String onEncrypt(String info, int num) throws Exception {
        return encrypt(info, num);
    }

    /**
     * 对string进行md5加密
     *
     * @param info 需要加密的string
     * @param i    加密次数
     * @return
     * @throws Exception
     */
    private static String encrypt(String info, int i) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(info.getBytes(StandardCharsets.UTF_8));
        byte[] b = digest.digest();
        String result = Base64Util.encoder(b);
        i -= 1;
        if (i > 0) {
            return encrypt(result, i);
        }
        return result;
    }
}
