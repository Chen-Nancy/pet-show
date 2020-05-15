package com.nancy.petshow.controller;

import com.nancy.petshow.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 * @author chen
 * @date 2020/5/15 16:08
 */
@Controller
@Validated
@RequestMapping("topic")
public class TopicController {
    private static Logger log = LoggerFactory.getLogger(TopicController.class);

    /**
     * 查询帖子集合
     *
     * @return
     */
    @RequestMapping("selectTopicList")
    @ResponseBody
    public Result selectTopicList() {

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

    }
}
