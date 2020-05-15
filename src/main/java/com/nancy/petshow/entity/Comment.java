package com.nancy.petshow.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author chen
 * @date 2020/5/14 17:24
 */
@Data
@ToString
@NoArgsConstructor
public class Comment {
    /**
     * 评论表id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String userNickName;

    /**
     * 用户头像
     */
    private String userAvatarUrl;

    /**
     * 内容
     */
    private String value;

    /**
     * 帖子id
     */
    private Long topicId;

    /**
     * 父评论id
     */
    private Long parentId;

    /**
     * 回复用户id
     */
    private Long toUserId;

    /**
     * 回复用户昵称
     */
    private String toUserNickName;

    /**
     * 回复用户头像
     */
    private String toUserAvatarUrl;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 登录用户是否点赞：0否、1是
     */
    private Byte isLike;

    /**
     * 登录用户是否点踩：0否、1是
     */
    private Byte isDislike;

    /**
     * 评论点赞数
     */
    private Long likeNum;

    /**
     * 评论点踩数
     */
    private Long dislikeNum;

    /**
     * 子评论
     */
    private List<Comment> commentList;
}