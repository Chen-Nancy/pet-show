package com.nancy.petshow.constants;

/**
 * @author chen
 * @date 2020/5/12 12:57
 */
public class WeCatConstants {
    /**
     * 微信小程序appId
     */
    public static final String APP_ID = "wx6ef29256da8f1331";

    /**
     * 微信小程序appSecret
     */
    public static final String APP_SECRET = "45f5048f5bf8cff717652228cc760441";

    /**
     * 微信小程序登录校验接口
     */
    public static final String AUTH_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
}
