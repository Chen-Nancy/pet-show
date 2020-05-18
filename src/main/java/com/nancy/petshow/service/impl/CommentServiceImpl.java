package com.nancy.petshow.service.impl;

import com.nancy.petshow.entity.Comment;
import com.nancy.petshow.mapper.CommentMapper;
import com.nancy.petshow.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chen
 * @date 2020/5/19 0:09
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addCommentInfo(Long userId, String value, Long topicId, Long parentId, Long toUserId) {
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setValue(value);
        comment.setTopicId(topicId);
        comment.setParentId(parentId);
        comment.setToUserId(toUserId);
        comment.setCreateTime(new Date());
        return commentMapper.addCommentInfo(comment) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean operateComment(Long userId, Long commentId, Byte type, Byte status) {
        Integer flag;
        Map<String, Object> map = new HashMap<>(3);
        map.put("userId", userId);
        map.put("commentId", commentId);
        if (status == 1) {
            map.put("type", type);
            flag = commentMapper.addCommentLike(map);
        } else {
            flag = commentMapper.deleteCommentLike(map);
        }
        return flag > 0;
    }
}
