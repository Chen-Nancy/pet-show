package com.nancy.petshow.config;

import com.nancy.petshow.constants.UriConstants;
import com.nancy.petshow.interceptor.MyRequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author chen
 * @date 2020/5/11 15:15
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private MyRequestInterceptor myRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myRequestInterceptor).addPathPatterns("/**").excludePathPatterns(UriConstants.ERROR_URI, "/favicon.ico", "/swagger-ui.html", "/webjars/**", "/swagger-resources/**", "/v2/api-docs");
    }
}
