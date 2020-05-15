package com.nancy.petshow.controller;

import com.nancy.petshow.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author chen
 * @date 2020/5/15 16:15
 */
@Controller
@Validated
@RequestMapping("comment")
public class CommentController {
    private static Logger log = LoggerFactory.getLogger(CommentController.class);

    /**
     * 添加评论信息
     *
     * @param request
     * @param value    内容
     * @param topicId  回复帖子id
     * @param parentId 父评论id
     * @param toUserId 回复用户id
     * @return
     */
    @RequestMapping("addCommentInfo")
    @ResponseBody
    public Result addCommentInfo(HttpServletRequest request, @NotBlank String value, @NotNull Long topicId, @NotNull Long parentId, @NotNull Long toUserId) {

    }

    /**
     * 评论点赞/点踩
     *
     * @param request
     * @param commentId 评论id
     * @param type      操作类型：0点踩、1点赞
     * @param status    操作状态：0取消、1添加
     * @return
     */
    @RequestMapping("operateComment")
    @ResponseBody
    public Result operateComment(HttpServletRequest request, @NotNull Long commentId, @NotNull Byte type, @NotNull Byte status) {

    }
}
