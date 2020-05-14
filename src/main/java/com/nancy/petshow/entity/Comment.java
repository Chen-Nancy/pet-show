package com.nancy.petshow.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *  @author chen
 *  @date 2020/5/14 17:24
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
    * 创建时间
    */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}