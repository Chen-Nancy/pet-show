package com.nancy.petshow.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author chen
 * @date 2020/5/14 17:24
 */
@Data
@ToString
@NoArgsConstructor
public class User {
    /**
     * 用户表id
     */
    private Long id;

    /**
     * 微信openId
     */
    private String openId;

    /**
     * 微信sessionKey
     */
    private String sessionKey;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 性别：0未知、1男、2女
     */
    private Byte gender;

    /**
     * 国家
     */
    private String country;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 注册时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}