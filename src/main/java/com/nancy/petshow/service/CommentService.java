package com.nancy.petshow.service;

/**
 * @author chen
 * @date 2020/5/19 0:01
 */
public interface CommentService {
    /**
     * 添加评论信息
     *
     * @param userId   用户id
     * @param value    内容
     * @param topicId  回复帖子id
     * @param parentId 父评论id
     * @param toUserId 回复用户id
     * @return
     */
    Boolean addCommentInfo(Long userId, String value, Long topicId, Long parentId, Long toUserId);

    /**
     * 评论操作
     *
     * @param userId    用户id
     * @param commentId 评论id
     * @param type      操作类型：0点踩、1点赞
     * @param status    操作状态：0取消、1添加
     * @return
     */
    Boolean operateComment(Long userId, Long commentId, Byte type, Byte status);
}
