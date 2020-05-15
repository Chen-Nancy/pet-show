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
public class Topic {
    /**
     * 帖子表id
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
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String value;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 状态：0存在、1删除
     */
    private Byte status;

    /**
     * 登录用户是否点赞：0否、1是
     */
    private Byte isLike;

    /**
     * 登录用户是否点踩：0否、1是
     */
    private Byte isDislike;

    /**
     * 帖子点赞数
     */
    private Long likeNum;

    /**
     * 帖子点踩数
     */
    private Long dislikeNum;

    /**
     * 帖子一级评论
     */
    private List<Comment> commentList;

    /**
     * 帖子图片
     */
    private List<String> pictureList;
}