package com.nancy.petshow.mapper;

import com.nancy.petshow.entity.Topic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author chen
 * @date 2020/5/14 17:24
 */
@Mapper
public interface TopicMapper {
    /**
     * 查询帖子集合
     *
     * @return
     */
    List<Topic> selectTopicList();

    /**
     * 查询帖子信息
     *
     * @param map id帖子id userId用户id
     * @return
     */
    List<Topic> selectTopicInfo(Map<String, Object> map);

    /**
     * 添加帖子信息
     *
     * @param topic
     * @return
     */
    Integer addTopicInfo(Topic topic);

    /**
     * 修改帖子信息
     *
     * @param topic
     * @return
     */
    Integer updateTopicInfo(Topic topic);

    /**
     * 删除帖子信息
     *
     * @param map userId用户id id帖子id
     * @return
     */
    Integer deleteTopicInfo(Map<String, Object> map);

    /**
     * 添加帖子图片
     *
     * @param map topicId帖子id pictureList图片数组
     * @return
     */
    Integer addTopicPicture(Map<String, Object> map);

    /**
     * 删除帖子图片
     *
     * @param topicId 帖子id
     * @return
     */
    Integer deleteTopicPicture(Long topicId);

    /**
     * 添加帖子操作
     *
     * @param map userId用户id topic_id帖子id type类型：0点踩、1点赞
     * @return
     */
    Integer addTopicLike(Map<String, Object> map);

    /**
     * 删除帖子操作
     *
     * @param map userId用户id topic_id帖子id
     * @return
     */
    Integer deleteTopicLike(Map<String, Object> map);
}