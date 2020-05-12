package com.nancy.petshow.controller;

import com.nancy.petshow.constants.CodeConstants;
import com.nancy.petshow.constants.WeCatConstants;
import com.nancy.petshow.entity.Result;
import com.nancy.petshow.util.HttpUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.io.IOException;

/**
 * @author chen
 * @date 2020/5/11 15:23
 */
@Controller
@Validated
@RequestMapping("user")
public class UserController {
    @RequestMapping("authorize")
    @ResponseBody
    public Result test(HttpServletRequest request, @NotBlank String code) throws IOException {
        String url = String.format(WeCatConstants.AUTH_URL, WeCatConstants.APP_ID, WeCatConstants.APP_SECRET, code);
        System.out.println(url);
        String res = HttpUtil.getHttp(url);
        System.out.println(res);
        return new Result(CodeConstants.SUCCESS_CODE, "操作成功");
    }
}
