package com.suda.platform.common.interceptors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Zhang on 2018/8/26.
 * @author  kongling
 */
@Configuration
public class SudaPlatformInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        boolean isNoInterceptor = true;

        return isNoInterceptor;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

    }

    private Boolean processApp(HttpServletRequest request, HttpServletResponse response) throws IOException {

        return true;
    }


    private Boolean processAdmin(HttpServletRequest request,HttpServletResponse response, String path) throws IOException {

        return true;
    }

}
