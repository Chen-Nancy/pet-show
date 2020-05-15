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
}