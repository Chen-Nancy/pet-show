package com.nancy.petshow.mapper;

import com.nancy.petshow.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author chen
 * @date 2020/5/14 17:24
 */
@Mapper
public interface CommentMapper {
    /**
     * 添加评论信息
     *
     * @param comment
     * @return
     */
    Integer addCommentInfo(Comment comment);

    /**
     * 添加评论操作
     *
     * @param map userId用户id commentId评论id type类型：0点踩、1点赞
     * @return
     */
    Integer addCommentLike(Map<String, Object> map);

    /**
     * 删除评论操作
     *
     * @param map userId用户id commentId评论id
     * @return
     */
    Integer deleteCommentLike(Map<String, Object> map);
}