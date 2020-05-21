package com.nancy.petshow.service.impl;

import com.nancy.petshow.entity.Topic;
import com.nancy.petshow.mapper.TopicMapper;
import com.nancy.petshow.service.TopicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chen
 * @date 2020/5/20 0:21
 */
@Service
public class TopicServiceImpl implements TopicService {
    @Resource
    private TopicMapper topicMapper;

    @Override
    public List<Topic> selectTopicList() {
        return topicMapper.selectTopicList();
    }

    @Override
    public Topic selectTopicInfo(Long userId, Long topicId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("id", topicId);
        map.put("userId", userId);
        return topicMapper.selectTopicInfo(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addTopicInfo(Long userId, String title, String value, String[] pictureList) {
        Topic topic = new Topic();
        topic.setUserId(userId);
        topic.setTitle(title);
        topic.setValue(value);
        Date date = new Date();
        topic.setCreateTime(date);
        topic.setUpdateTime(date);
        Integer flag = topicMapper.addTopicInfo(topic);
        if (pictureList != null && pictureList.length > 0) {
            Map<String, Object> map = new HashMap<>(2);
            map.put("topicId", topic.getId());
            map.put("pictureList", pictureList);
            topicMapper.addTopicPicture(map);
        }
        return flag > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateTopicInfo(Long userId, Long topicId, String title, String value, String[] pictureList) {
        Topic topic = new Topic();
        topic.setId(topicId);
        topic.setUserId(userId);
        topic.setTitle(title);
        topic.setValue(value);
        topic.setUpdateTime(new Date());
        Integer flag = topicMapper.updateTopicInfo(topic);
        if (pictureList != null && pictureList.length > 0) {
            topicMapper.deleteTopicPicture(topicId);
            Map<String, Object> map = new HashMap<>(2);
            map.put("topicId", topic.getId());
            map.put("pictureList", pictureList);
            topicMapper.addTopicPicture(map);
        }
        return flag > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteTopicInfo(Long userId, Long topicId) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("id", topicId);
        map.put("userId", userId);
        return topicMapper.deleteTopicInfo(map) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean operateTopic(Long userId, Long topicId, Byte type, Byte status) {
        Integer flag;
        Map<String, Object> map = new HashMap<>(3);
        map.put("userId", userId);
        map.put("topicId", topicId);
        if (status == 1) {
            topicMapper.deleteTopicLike(map);
            map.put("type", type);
            flag = topicMapper.addTopicLike(map);
        } else {
            flag = topicMapper.deleteTopicLike(map);
        }
        return flag > 0;
    }
}
