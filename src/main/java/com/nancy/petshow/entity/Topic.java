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
}