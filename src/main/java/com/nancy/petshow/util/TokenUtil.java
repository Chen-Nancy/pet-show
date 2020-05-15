package com.nancy.petshow.util;

import com.nancy.petshow.constants.TokenConstants;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 获取token
     *
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        String token = request.getHeader(TokenConstants.HEADER_NAME);
        if (ParameterUtil.stringIsEmpty(token)) {
            return null;
        }
        if (!token.startsWith(TokenConstants.TOKEN_PRE)) {
            return null;
        }
        token = token.substring(TokenConstants.TOKEN_PRE.length());
        if (ParameterUtil.stringIsEmpty(token)) {
            return null;
        }
        return token;
    }
}
