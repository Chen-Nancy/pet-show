package com.nancy.petshow.interceptor;

import com.nancy.petshow.constants.UriConstants;
import com.nancy.petshow.exception.NoLoginException;
import com.nancy.petshow.util.RedisUtil;
import com.nancy.petshow.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chen
 * @date 2020/5/11 14:39
 */
@Component
public class MyRequestInterceptor implements HandlerInterceptor {
    private static Logger log = LoggerFactory.getLogger(MyRequestInterceptor.class);
    /**
     * 无需登录验证的请求集合
     */
    private static List<String> noLoginUriList;
    @Resource
    private RedisUtil redisUtil;

    static {
        noLoginUriList = new ArrayList<>();
        noLoginUriList.add(UriConstants.FILE_SHOW_URI);
        noLoginUriList.add(UriConstants.FILE_DOWNLOAD_URI);
        noLoginUriList.add(UriConstants.AUTHORIZE_URI);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle=====");
        String url = new String(request.getRequestURL());
        log.info("访问URL：" + url);
        String uri = request.getRequestURI();
        //无需登录，放行
        for (String s : noLoginUriList) {
            if (uri.startsWith(s)) {
                return true;
            }
        }
        //进行登录验证
        String token = TokenUtil.getToken(request);
        if (token == null) {
            throw new NoLoginException("未登录");
        }
        Boolean flag = redisUtil.hasKey(token);
        if (flag) {
            return true;
        }
        throw new NoLoginException("未登录");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
