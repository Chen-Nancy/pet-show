package com.nancy.petshow.controller;

import com.nancy.petshow.constants.CodeConstants;
import com.nancy.petshow.constants.WeCatConstants;
import com.nancy.petshow.entity.Result;
import com.nancy.petshow.util.HttpUtil;
import com.nancy.petshow.util.ParameterUtil;
import com.nancy.petshow.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.Map;

import static com.nancy.petshow.util.JsonUtil.jsonToObject;

/**
 * @author chen
 * @date 2020/5/11 15:23
 */
@Controller
@Validated
@RequestMapping("user")
public class UserController {
    private static Logger log = LoggerFactory.getLogger(UserController.class);
    @Resource
    private RedisUtil redisUtil;

    @RequestMapping("authorize")
    @ResponseBody
    public Result test(@NotBlank String code) throws IOException {
        //访问微信接口进行授权
        String url = String.format(WeCatConstants.AUTH_URL, WeCatConstants.APP_ID, WeCatConstants.APP_SECRET, code);
        String res = HttpUtil.getHttp(url);
        Map map = jsonToObject(res, Map.class);
        String sessionKey = String.valueOf(map.get("session_key"));
        String openId = String.valueOf(map.get("openid"));
        //openId或sessionKey为空则授权失败
        if (ParameterUtil.stringIsEmpty(sessionKey) || ParameterUtil.stringIsEmpty(openId)) {
            return new Result(CodeConstants.ERROR_CODE, "授权失败");
        }
        //查询数据库有无用户信息，没有则新增用户数据

        return new Result(CodeConstants.SUCCESS_CODE, "操作成功");
    }
}
