package com.nancy.petshow.controller;

import com.nancy.petshow.constants.CodeConstants;
import com.nancy.petshow.entity.Result;
import com.nancy.petshow.entity.Topic;
import com.nancy.petshow.service.TopicService;
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
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author chen
 * @date 2020/5/15 16:08
 */
@Controller
@Validated
@RequestMapping("topic")
public class TopicController {
    private static Logger log = LoggerFactory.getLogger(TopicController.class);
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private TopicService topicService;

    /**
     * 查询帖子集合
     *
     * @return
     */
    @RequestMapping("selectTopicList")
    @ResponseBody
    public Result selectTopicList() {
        List<Topic> topicList = topicService.selectTopicList();
        log.info("查询帖子集合成功");
        return new Result(CodeConstants.SUCCESS_CODE, "操作成功", topicList);
    }

    /**
     * 查询帖子信息
     *
     * @param request
     * @param topicId 帖子id
     * @return
     */
    @RequestMapping("selectTopicInfo")
    @ResponseBody
    public Result selectTopicInfo(HttpServletRequest request, @NotNull Long topicId) {
        String token = TokenUtil.getToken(request);
        Long userId = Long.valueOf(String.valueOf(redisUtil.getString(token)));
        Topic topic = topicService.selectTopicInfo(userId, topicId);
        log.info("查询帖子信息成功" + token);
        return new Result(CodeConstants.SUCCESS_CODE, "操作成功", topic);
    }

    /**
     * 添加帖子信息
     *
     * @param request
     * @param title       标题
     * @param value       内容
     * @param pictureList 图片集合
     * @return
     */
    @RequestMapping("addTopicInfo")
    @ResponseBody
    public Result addTopicInfo(HttpServletRequest request, String title, String value, String[] pictureList) {
        String token = TokenUtil.getToken(request);
        Long userId = Long.valueOf(String.valueOf(redisUtil.getString(token)));
        Boolean flag = topicService.addTopicInfo(userId, title, value, pictureList);
        if (flag) {
            log.info("添加帖子信息成功：" + token);
            return new Result(CodeConstants.SUCCESS_CODE, "操作成功");
        }
        log.info("添加帖子信息失败：" + token);
        return new Result(CodeConstants.ERROR_CODE, "添加失败");
    }

    /**
     * 修改帖子信息
     *
     * @param request
     * @param topicId     帖子id
     * @param title       标题
     * @param value       内容
     * @param pictureList 图片集合
     * @return
     */
    @RequestMapping("updateTopicInfo")
    @ResponseBody
    public Result updateTopicInfo(HttpServletRequest request, @NotNull Long topicId, String title, String value, String[] pictureList) {
        String token = TokenUtil.getToken(request);
        Long userId = Long.valueOf(String.valueOf(redisUtil.getString(token)));
        Boolean flag = topicService.updateTopicInfo(userId, topicId, title, value, pictureList);
        if (flag) {
            log.info("修改帖子信息成功：" + token);
            return new Result(CodeConstants.SUCCESS_CODE, "操作成功");
        }
        log.info("修改帖子信息失败：" + token);
        return new Result(CodeConstants.ERROR_CODE, "修改失败");
    }

    /**
     * 删除帖子信息
     *
     * @param request
     * @param topicId 帖子id
     * @return
     */
    @RequestMapping("deleteTopicInfo")
    @ResponseBody
    public Result deleteTopicInfo(HttpServletRequest request, @NotNull Long topicId) {
        String token = TokenUtil.getToken(request);
        Long userId = Long.valueOf(String.valueOf(redisUtil.getString(token)));
        Boolean flag = topicService.deleteTopicInfo(userId, topicId);
        if (flag) {
            log.info("删除帖子信息成功：" + token);
            return new Result(CodeConstants.SUCCESS_CODE, "操作成功");
        }
        log.info("删除帖子信息失败：" + token);
        return new Result(CodeConstants.ERROR_CODE, "删除失败");
    }

    /**
     * 帖子点赞/点踩
     *
     * @param request
     * @param topicId 帖子id
     * @param type    操作类型：0点踩、1点赞
     * @param status  操作状态：0取消、1添加
     * @return
     */
    @RequestMapping("operateTopic")
    @ResponseBody
    public Result operateTopic(HttpServletRequest request, @NotNull Long topicId, @NotNull Byte type, @NotNull Byte status) {
        if ((type != 0 && type != 1) || (status != 0 && status != 1)) {
            return new Result(CodeConstants.PARAMETER_ERROR_CODE, "参数错误");
        }
        String token = TokenUtil.getToken(request);
        Long userId = Long.valueOf(String.valueOf(redisUtil.getString(token)));
        Boolean flag = topicService.operateTopic(userId, topicId, type, status);
        if (flag) {
            log.info("帖子操作成功：" + token);
            return new Result(CodeConstants.SUCCESS_CODE, "操作成功");
        }
        log.info("帖子操作失败：" + token);
        return new Result(CodeConstants.ERROR_CODE, "操作失败");
    }
}
