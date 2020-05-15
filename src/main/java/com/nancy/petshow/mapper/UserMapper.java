package com.nancy.petshow.mapper;

import com.nancy.petshow.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chen
 * @date 2020/5/14 17:24
 */
@Mapper
public interface UserMapper {
    /**
     * 根据微信openId查询用户id
     *
     * @param openId
     * @return
     */
    Long selectUserId(String openId);

    /**
     * 添加用户信息
     *
     * @param user
     * @return
     */
    Integer addUserInfo(User user);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    Integer updateUserInfo(User user);

    /**
     * 查询用户信息
     *
     * @param id
     * @return
     */
    User selectUserInfo(Long id);
}