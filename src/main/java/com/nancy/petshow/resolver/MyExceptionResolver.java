package com.nancy.petshow.resolver;

import com.nancy.petshow.constants.CodeConstants;
import com.nancy.petshow.entity.Result;
import com.nancy.petshow.exception.NoLoginException;
import com.nancy.petshow.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

/**
 * @author chen
 * @date 2020/5/11 14:38
 */
@ControllerAdvice
public class MyExceptionResolver {
    private static Logger log = LoggerFactory.getLogger(MyExceptionResolver.class);

    @ExceptionHandler(NoLoginException.class)
    @ResponseBody
    public Result noLoginExceptionResolver(NoLoginException e) {
        log.error(e.getMessage(), e);
        return new Result(CodeConstants.NO_LOGIN_CODE, "未登录");
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result bindExceptionResolver(BindException e) {
        log.error(e.getMessage(), e);
        return new Result(CodeConstants.PARAMETER_ERROR_CODE, "参数错误");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result constraintViolationExceptionResolver(ConstraintViolationException e) {
        log.error(e.getMessage(), e);
        return new Result(CodeConstants.PARAMETER_ERROR_CODE, "参数错误");
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    public Result notFoundExceptionResolver(NotFoundException e) {
        log.error(e.getMessage(), e);
        return new Result(CodeConstants.NOT_FOUND_CODE, "资源未找到");
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result exceptionResolver(Exception e) {
        log.error(e.getMessage(), e);
        return new Result(CodeConstants.SYSTEM_ERROR_CODE, e.getMessage());
    }
}
