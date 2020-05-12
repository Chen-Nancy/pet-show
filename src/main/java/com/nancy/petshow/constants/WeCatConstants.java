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
//    public static final String APP_ID = "wxdebcb16383e8509c";

    /**
     * 微信小程序appSecret
     */
    public static final String APP_SECRET = "45f5048f5bf8cff717652228cc760441";
//    public static final String APP_SECRET = "bf024b6352ae278ff682baee3ce83b8d";

    /**
     * 微信小程序登录校验接口
     */
    public static final String AUTH_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
}
