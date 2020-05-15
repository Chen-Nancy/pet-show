package com.nancy.petshow.service;

import com.nancy.petshow.entity.User;

/**
 * @author chen
 * @date 2020/5/15 10:58
 */
public interface UserService {
    /**
     * 根据微信openId查询用户id
     *
     * @param openId 微信openId
     * @return
     */
    Long selectUserId(String openId);

    /**
     * 添加用户信息
     *
     * @param openId 微信openId
     * @return
     */
    Long addUserInfo(String openId);

    /**
     * 修改用户信息
     *
     * @param userId     用户id
     * @param sessionKey 微信sessionKey
     * @param avatarUrl  头像
     * @param nickName   昵称
     * @param gender     性别：0未知、1男、2女
     * @param country    国家
     * @param province   省份
     * @param city       城市
     * @return
     */
    Boolean updateUserInfo(Long userId, String sessionKey, String avatarUrl, String nickName, Byte gender, String country, String province, String city);

    /**
     * 查询用户信息
     *
     * @param userId 用户id
     * @return
     */
    User selectUserInfo(Long userId);
}
