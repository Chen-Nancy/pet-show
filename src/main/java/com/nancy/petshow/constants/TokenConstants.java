package com.nancy.petshow.constants;

/**
 * @author chen
 * @date 2020/5/15 11:31
 */
public class TokenConstants {
    /**
     * token过期时间-秒
     */
    public static final int LOGIN_TIMEOUT = 7 * 24 * 60 * 60;

    /**
     * 存token的headerName
     */
    public static final String HEADER_NAME = "authorize";

    /**
     * token前缀（盐）
     */
    public static final String TOKEN_PRE = "buNTKayfU/8NuSu233OVGg==";
}
