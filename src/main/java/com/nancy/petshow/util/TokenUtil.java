package com.nancy.petshow.util;

/**
 * @author chen
 * @date 2020/5/12 10:27
 */
public class TokenUtil {
    /**
     * 创建token
     *
     * @param openId 微信openId
     * @return
     * @throws Exception
     */
    public static String creatToken(String openId) throws Exception {
        //用微信的openId+当前时间毫秒值为基准创建token
        String s = openId + System.currentTimeMillis();
        //对基准字符串进行md5加密
        return Md5Util.onEncrypt(s, 1);
    }
}
