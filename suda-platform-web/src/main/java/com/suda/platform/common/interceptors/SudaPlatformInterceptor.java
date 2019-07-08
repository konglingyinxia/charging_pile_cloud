package com.suda.platform.common.interceptors;

import com.alibaba.fastjson.JSONObject;
import com.suda.platform.entity.AdminUser;
import com.util.Respons.ResponseUtil;
import com.util.StringUtils;
import com.util.auth.AuthSign;
import com.util.cache.UserCacheUtil;
import config.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Zhang on 2018/8/26.
 * @author  kongling
 */
@Configuration
public class SudaPlatformInterceptor implements HandlerInterceptor {
    private  String admin ="/admin";
    private  String app = "/app";
    private  String common = "/common";
    private  String agent = "/agent";

    @Autowired
    UserCacheUtil userCacheUtil;

    @Autowired
    RedisUtils redisUtils;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        boolean isNoInterceptor = false;
        if(true){
            //return true;
        }
        String token = request.getHeader(AuthSign.token);
        if(StringUtils.isBlank(token)){
            ResponseUtil.out(response, ResponseUtil.getNoLoginResponseMap());
        }else {
            String URI = request.getRequestURI().toLowerCase();
            if(URI.startsWith(admin)){
                isNoInterceptor = processAdmin(request,response,URI,token);
            }else if(URI.startsWith(app)){
                isNoInterceptor = processApp(request,response,URI,token);
            }else if(URI.startsWith(agent)){
                isNoInterceptor = processAgent(request,response,URI,token);
            } else {
                isNoInterceptor=true;
            }
        }
        return isNoInterceptor;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

    }

    private Boolean processApp(HttpServletRequest request, HttpServletResponse response, String path,String token) {
       /* Long id = AuthSign.getUserId(token);
        //获取存储sessionId
        String sessionId = userCacheUtil.getStoreAppStockUser(id);
        if(sessionId==null){
            ResponseUtil.out(response, ResponseUtil.getLoginOutTimeMap());
            return false;
        }
        if(!token.equalsIgnoreCase(sessionId)){
            ResponseUtil.out(response, ResponseUtil.getLoginOutResponseMap());
            return false;
        }
        //用户禁用启用状态
        String jsonStr =  redisUtils.getStorageStockUser(id);
        if(jsonStr !=null){
           StockUser stockUser = JSONObject.toJavaObject(JSONObject.parseObject(jsonStr), StockUser.class);
           if(stockUser.getIsDisable()){
               ResponseUtil.out(response, ResponseUtil.getDisableResponseMap());
               return false;
           }
        }
        userCacheUtil.storeAppStockUserRefreshExpire(id);*/
        return true;
    }


    /**
     * 管理员登陆校验
     *
     * @param request
     * @param response
     * @param path
     * @param token
     * @return
     */
    private Boolean processAdmin(HttpServletRequest request,HttpServletResponse response, String path,String token) {
        Long id = AuthSign.getUserId(token);
        //获取存储sessionId
        String sessionId = userCacheUtil.getStoreAdminUser(id);
        if(sessionId==null){
            ResponseUtil.out(response, ResponseUtil.getLoginOutTimeMap());
            return false;
        }
        if(!token.equalsIgnoreCase(sessionId)){
            ResponseUtil.out(response, ResponseUtil.getLoginOutResponseMap());
            return false;
        }
        //用户禁用启用状态
        String jsonUser =  redisUtils.getStorageAdminUser(id);
        if(jsonUser != null){
            AdminUser adminUser = JSONObject.toJavaObject(JSONObject.parseObject(jsonUser), AdminUser.class);
            if(!adminUser.getIsDisable()){
                return false;
            }
        }
        userCacheUtil.storeAdminUserRefreshExpire(id);
        return true;
    }

    /**
     * 代理登陆校验
     *
     * @param request
     * @param response
     * @param path
     * @param token
     * @return
     */
    private Boolean processAgent(HttpServletRequest request,HttpServletResponse response, String path,String token) {
        Long id = AuthSign.getUserId(token);
        //获取存储sessionId
        String sessionId = userCacheUtil.getStoreAgentUser(id);
        if(sessionId==null){
            ResponseUtil.out(response, ResponseUtil.getLoginOutTimeMap());
            return false;
        }
        if(!token.equalsIgnoreCase(sessionId)){
            ResponseUtil.out(response, ResponseUtil.getLoginOutResponseMap());
            return false;
        }
        //用户禁用启用状态
        String jsonUser =  redisUtils.getStorageAgentUser(id);
        if(jsonUser != null){
            AdminUser adminUser = JSONObject.toJavaObject(JSONObject.parseObject(jsonUser), AdminUser.class);
            if(!adminUser.getIsDisable()){
                return false;
            }
        }
        userCacheUtil.storeAdminUserRefreshExpire(id);
        return true;
    }

}
