package com.nancy.petshow.controller;

import com.nancy.petshow.constants.CodeConstants;
import com.nancy.petshow.entity.Result;
import com.nancy.petshow.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author chen
 * @date 2020/5/11 15:23
 */
@Controller
@Validated
@RequestMapping("user")
public class UserController {
    @RequestMapping("test")
    @ResponseBody
    public Result test(HttpServletRequest request, User user) {
        System.out.println("controller=====");
        System.out.println("name=" + user.getName() + "&" + "age=" + user.getAge());
        System.out.println("url=" + request.getRequestURL());
        System.out.println("uri=" + request.getRequestURI());
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            System.out.println(key + "=" + value);
        }
        System.out.println("controller=====");
        return new Result(CodeConstants.SUCCESS_CODE, "操作成功");
    }
}
