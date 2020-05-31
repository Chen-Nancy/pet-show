package com.nancy.petshow.service;

import com.nancy.petshow.entity.Topic;

import java.util.List;

/**
 * @author chen
 * @date 2020/5/20 0:09
 */
public interface TopicService {
    /**
     * 查询帖子集合
     *
     * @param userId 用户id
     * @return
     */
    List<Topic> selectTopicList(Long userId);

    /**
     * 查询帖子信息
     *
     * @param userId  用户id
     * @param topicId 帖子id
     * @return
     */
    Topic selectTopicInfo(Long userId, Long topicId);

    /**
     * 添加帖子信息
     *
     * @param userId      用户id
     * @param title       标题
     * @param value       内容
     * @param pictureList 图片集合
     * @return
     */
    Boolean addTopicInfo(Long userId, String title, String value, String[] pictureList);

    /**
     * 修改帖子信息
     *
     * @param userId      用户id
     * @param topicId     帖子id
     * @param title       标题
     * @param value       内容
     * @param pictureList 图片集合
     * @return
     */
    Boolean updateTopicInfo(Long userId, Long topicId, String title, String value, String[] pictureList);

    /**
     * 删除帖子信息
     *
     * @param userId  用户id
     * @param topicId 帖子id
     * @return
     */
    Boolean deleteTopicInfo(Long userId, Long topicId);

    /**
     * 帖子操作
     *
     * @param userId  用户id
     * @param topicId 帖子id
     * @param type    操作类型：0点踩、1点赞
     * @param status  操作状态：0取消、1添加
     * @return
     */
    Boolean operateTopic(Long userId, Long topicId, Byte type, Byte status);
}
