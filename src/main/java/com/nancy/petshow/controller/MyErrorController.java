package com.nancy.petshow.controller;

import com.nancy.petshow.constants.CodeConstants;
import com.nancy.petshow.constants.UriConstants;
import com.nancy.petshow.exception.NotFoundException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author chen
 * @date 2020/5/11 22:13
 */
@Controller
public class MyErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return UriConstants.ERROR_URI;
    }

    @RequestMapping(UriConstants.ERROR_URI)
    @ResponseBody
    public void error(HttpServletResponse response) {
        int status = response.getStatus();
        if (status == CodeConstants.NOT_FOUND_CODE) {
            throw new NotFoundException("资源未找到");
        } else {
            throw new RuntimeException();
        }
    }
}
