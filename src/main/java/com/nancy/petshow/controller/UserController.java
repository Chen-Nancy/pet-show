package com.nancy.petshow.controller;

import com.nancy.petshow.constants.CodeConstants;
import com.nancy.petshow.constants.TokenConstants;
import com.nancy.petshow.constants.WeCatConstants;
import com.nancy.petshow.entity.Result;
import com.nancy.petshow.entity.User;
import com.nancy.petshow.service.UserService;
import com.nancy.petshow.util.HttpUtil;
import com.nancy.petshow.util.ParameterUtil;
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
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    @Resource
    private UserService userService;

    /**
     * 登录授权
     *
     * @param code 微信code
     * @return
     * @throws Exception
     */
    @RequestMapping("authorize")
    @ResponseBody
    public Result authorize(@NotBlank String code) throws Exception {
        //访问微信接口进行授权
        String url = String.format(WeCatConstants.AUTH_URL, WeCatConstants.APP_ID, WeCatConstants.APP_SECRET, code);
        String res = HttpUtil.getHttp(url);
        Map map = jsonToObject(res, Map.class);
        String sessionKey = String.valueOf(map.get("session_key"));
        String openId = String.valueOf(map.get("openid"));
        //openId或sessionKey为空则授权失败
        if (ParameterUtil.stringIsEmpty(sessionKey) || ParameterUtil.stringIsEmpty(openId)) {
            log.info("登录授权失败");
            return new Result(CodeConstants.ERROR_CODE, "授权失败");
        }
        //生成token
        String token = TokenUtil.creatToken(openId);
        //查询数据库有无用户信息，没有则新增用户数据
        Long userId = userService.selectUserId(openId);
        if (ParameterUtil.numberIsEmpty(userId)) {
            userId = userService.addUserInfo(openId);
            //将token与userId存入redis，token返回前端
            redisUtil.setString(token, userId, TokenConstants.LOGIN_TIMEOUT, TimeUnit.SECONDS);
            log.info("登录授权成功：" + token);
            return new Result(CodeConstants.SUCCESS_CODE, "操作成功", TokenConstants.TOKEN_PRE + token);
        }
        //存在用户，更新用户sessionKey
        userService.updateUserInfo(userId, sessionKey, null, null, null, null, null, null);
        //将token与userId存入redis，token返回前端
        redisUtil.setString(token, userId, TokenConstants.LOGIN_TIMEOUT, TimeUnit.SECONDS);
        log.info("登录授权成功：" + token);
        return new Result(CodeConstants.SUCCESS_CODE, "操作成功", TokenConstants.TOKEN_PRE + token);
    }

    /**
     * 更新用户信息
     *
     * @param request
     * @param avatarUrl 头像
     * @param nickName  昵称
     * @param gender    性别：0未知、1男、2女
     * @param country   国家
     * @param province  省份
     * @param city      城市
     * @return
     */
    @RequestMapping("updateUserInfo")
    @ResponseBody
    public Result updateUserInfo(HttpServletRequest request, String avatarUrl, String nickName, Byte gender, String country, String province, String city) {
        String token = TokenUtil.getToken(request);
        Long userId = Long.valueOf(String.valueOf(redisUtil.getString(token)));
        Boolean flag = userService.updateUserInfo(userId, null, avatarUrl, nickName, gender, country, province, city);
        if (flag) {
            log.info("更新用户信息成功：" + token);
            return new Result(CodeConstants.SUCCESS_CODE, "操作成功");
        }
        log.info("更新用户信息失败：" + token);
        return new Result(CodeConstants.ERROR_CODE, "更新失败");
    }

    /**
     * 查询用户信息
     *
     * @param request
     * @return
     */
    @RequestMapping("selectUserInfo")
    @ResponseBody
    public Result selectUserInfo(HttpServletRequest request) {
        String token = TokenUtil.getToken(request);
        Long userId = Long.valueOf(String.valueOf(redisUtil.getString(token)));
        User user = userService.selectUserInfo(userId);
        log.info("查询用户信息成功" + token);
        return new Result(CodeConstants.SUCCESS_CODE, "操作成功", user);
    }
}
