package com.nancy.petshow.service.impl;

import com.nancy.petshow.entity.User;
import com.nancy.petshow.mapper.UserMapper;
import com.nancy.petshow.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author chen
 * @date 2020/5/15 15:18
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Long selectUserId(String openId) {
        return userMapper.selectUserId(openId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long addUserInfo(String openId) {
        User user = new User();
        user.setOpenId(openId);
        user.setCreateTime(new Date());
        userMapper.addUserInfo(user);
        return user.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateUserInfo(Long userId, String sessionKey, String avatarUrl, String nickName, Byte gender, String country, String province, String city) {
        User user = new User();
        user.setId(userId);
        user.setSessionKey(sessionKey);
        user.setAvatarUrl(avatarUrl);
        user.setNickName(nickName);
        user.setGender(gender);
        user.setCountry(country);
        user.setProvince(province);
        user.setCity(city);
        user.setUpdateTime(new Date());
        return userMapper.updateUserInfo(user) > 0;
    }

    @Override
    public User selectUserInfo(Long userId) {
        return userMapper.selectUserInfo(userId);
    }
}
