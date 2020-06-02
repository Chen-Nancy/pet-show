package com.nancy.petshow.controller;

import com.nancy.petshow.constants.CodeConstants;
import com.nancy.petshow.constants.UriConstants;
import com.nancy.petshow.exception.MyException;
import com.nancy.petshow.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author chen
 * @date 2020/5/11 22:13
 */
@Controller
public class MyErrorController implements ErrorController {
    private final ErrorAttributes errorAttributes;

    @Autowired
    public MyErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @Override
    public String getErrorPath() {
        return UriConstants.ERROR_URI;
    }

    @ApiIgnore
    @RequestMapping(UriConstants.ERROR_URI)
    @ResponseBody
    public void error(HttpServletRequest request, HttpServletResponse response) {
        int status = response.getStatus();
        if (status == CodeConstants.NOT_FOUND_CODE) {
            throw new NotFoundException("资源未找到");
        } else {
            ServletWebRequest requestAttributes = new ServletWebRequest(request);
            Map<String, Object> attr = this.errorAttributes.getErrorAttributes(requestAttributes, false);
            String message = attr.get("message").toString();
            throw new MyException(message);
        }
    }
}
