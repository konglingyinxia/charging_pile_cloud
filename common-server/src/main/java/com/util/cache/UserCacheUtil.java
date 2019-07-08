package com.util.cache;

import config.com.MyConfiguration;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;

/**
 * @author: Zhang Zhengliang
 * @create: 2018-07-04 09:56
 **/
@Configuration
public class UserCacheUtil {
    @Autowired
    private MyConfiguration myConfiguration;


    /**
     * 登录用户的信息
     */
    private static final String ADMIN_USER_CACHE_KEY = "login:com.suda.admin.user.cache";
    private static final String AGENT_USER_CACHE_KEY = "login:com.suda.agent.user.cache";
    private static final String APP_USER_CACHE_KEY = "login:com.suda.app.user.cache";
    private static final String PC_USER_CACHE_KEY = "login:com.suda.pc.user.cache";
    private static final String WX_LOGIN_APP_USER_CACHE_KEY = "wx:login:com.suda.app.user.cache";

    //==========================管理员后台登陆信息=======================================================

    /**
     * 存储后台用户登录信息
     * @param id
     * @param token
     */
    @Async
    public  void storeAdminUserLoginInfo(Long id, String token) {
        if (StringUtils.isNotBlank(token)) {
            //存储用户登录状态
            String onlineFlag = generateAdminOnlineKey(id.toString());
            JedisCache.setStr(onlineFlag, token);
            JedisCache.expire(onlineFlag, myConfiguration.getSessionTimeout());
        }
    }

    /**
     * 刷新admin 超时信息
     * @return
     */
    @Async
    public  void storeAdminUserRefreshExpire(Long id) {
            String onlineFlag = generateAdminOnlineKey(id.toString());
            JedisCache.expire(onlineFlag, myConfiguration.getSessionTimeout());
    }

    /**
     * 获取admin 存储 token 信息
     * @param id
     * @return
     */
    public  String getStoreAdminUser(Long id) {
        String onlineFlag = generateAdminOnlineKey(id.toString());
        return  JedisCache.getStr(onlineFlag);
    }

    public static String generateAdminOnlineKey(String str) {
        return ADMIN_USER_CACHE_KEY + str;
    }

    //=================================代理商后台登陆信息===============================================================

    /**
     * 存储后台用户登录信息
     * @param id
     * @param token
     */
    @Async
    public  void storeAgentUserLoginInfo(Long id, String token) {
        if (StringUtils.isNotBlank(token)) {
            //存储用户登录状态
            String onlineFlag = generateAgentOnlineKey(id.toString());
            JedisCache.setStr(onlineFlag, token);
            JedisCache.expire(onlineFlag, myConfiguration.getSessionTimeout());
        }
    }

    /**
     * 刷新agent超时信息
     * @return
     */
    @Async
    public  void storeAgentUserRefreshExpire(Long id) {
        String onlineFlag = generateAgentOnlineKey(id.toString());
        JedisCache.expire(onlineFlag, myConfiguration.getSessionTimeout());
    }

    /**
     * 获取agent 存储 token 信息
     * @param id
     * @return
     */
    public  String getStoreAgentUser(Long id) {
        String onlineFlag = generateAgentOnlineKey(id.toString());
        return  JedisCache.getStr(onlineFlag);
    }

    public static String generateAgentOnlineKey(String str) {
        return AGENT_USER_CACHE_KEY + str;
    }



    //==================================app 用户登陆信息=============================================================
    /**
     * 存储app用户登录信息
     * @param id
     * @param token
     */
    public  void storeAppStockUserLoginInfo(Long id, String token) {
        if (StringUtils.isNotBlank(token)) {
            //存储用户登录状态
            String onlineFlag = generateAppUserOnlineKey(id.toString());
            JedisCache.setStr(onlineFlag, token);
            JedisCache.expire(onlineFlag, myConfiguration.getSessionTimeout());
        }
    }
    /**
     * 刷新app 超时信息
     * @return
     */
    public  void storeAppStockUserRefreshExpire(Long id) {
        String onlineFlag = generateAppUserOnlineKey(id.toString());
        JedisCache.expire(onlineFlag, myConfiguration.getSessionTimeout());
    }
    /**
     * 获取app 存储 token 信息
     * @param id
     * @return
     */
    public  String getStoreAppStockUser(Long id) {
        String onlineFlag = generateAppUserOnlineKey(id.toString());
       return  JedisCache.getStr(onlineFlag);
    }

    public static String generateAppUserOnlineKey(String str) {
        return APP_USER_CACHE_KEY + str;
    }



    //===============================微信登陆信息 存储=====================================================


    //===============================微信登陆存储微信登陆session信息

    /**
     * 存储app用户登录信息
     * @param openId
     * @param jsonStr
     */
    public  void storeAppStockUserWxLoginInfo(String  openId, String jsonStr) {
        if (StringUtils.isNotBlank(jsonStr)) {
            //存储用户登录状态
            String onlineFlag = generateWxAppUserOnlineKey(openId);
            JedisCache.setStr(onlineFlag, jsonStr);

        }
    }
    public static String generateWxAppUserOnlineKey(String str) {
        return WX_LOGIN_APP_USER_CACHE_KEY + str;
    }

}
