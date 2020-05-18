package com.nancy.petshow.controller;

import com.nancy.petshow.constants.CodeConstants;
import com.nancy.petshow.entity.Result;
import com.nancy.petshow.service.CommentService;
import com.nancy.petshow.util.RedisUtil;
import com.nancy.petshow.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private CommentService commentService;

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
        String token = TokenUtil.getToken(request);
        Long userId = Long.valueOf(String.valueOf(redisUtil.getString(token)));
        Boolean flag = commentService.addCommentInfo(userId, value, topicId, parentId, toUserId);
        if (flag) {
            log.info("添加评论信息成功：" + token);
            return new Result(CodeConstants.SUCCESS_CODE, "操作成功");
        }
        log.info("添加评论信息失败：" + token);
        return new Result(CodeConstants.ERROR_CODE, "添加失败");
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
        if ((type != 0 && type != 1) || (status != 0 && status != 1)) {
            return new Result(CodeConstants.PARAMETER_ERROR_CODE, "参数错误");
        }
        String token = TokenUtil.getToken(request);
        Long userId = Long.valueOf(String.valueOf(redisUtil.getString(token)));
        Boolean flag = commentService.operateComment(userId, commentId, type, status);
        if (flag) {
            log.info("评论操作成功：" + token);
            return new Result(CodeConstants.SUCCESS_CODE, "操作成功");
        }
        log.info("评论操作失败：" + token);
        return new Result(CodeConstants.ERROR_CODE, "操作失败");
    }
}
