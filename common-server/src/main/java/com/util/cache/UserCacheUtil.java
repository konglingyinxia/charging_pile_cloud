package com.util.cache;

import config.com.MyConfiguration;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

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
    private static final String ADMIN_USER_CACHE_KEY = "com.suda.admin.user.cache";
    private static final String AGENT_USER_CACHE_KEY = "com.suda.agent.user.cache";
    private static final String APP_USER_CACHE_KEY = "com.suda.app.user.cache";
    private static final String PC_USER_CACHE_KEY = "com.suda.pc.user.cache";


    /**
     * 存储后台用户登录信息
     * @param id
     * @param token
     */
    public  void storeAdminUserLoginInfo(Long id, String token) {
        if (StringUtils.isNotBlank(token)) {
            //存储用户登录状态
            String onlineFlag = generateAdminOnlineKey(id.toString());
            JedisCache.setStr(onlineFlag, token);
            JedisCache.expire(onlineFlag, myConfiguration.getSessionTimeout());
        }
    }

    public static String generateAdminOnlineKey(String str) {
        return ADMIN_USER_CACHE_KEY + str;
    }


    /**
     * 存储后台用户登录信息
     * @param id
     * @param token
     */
    public  void storeAgentUserLoginInfo(Long id, String token) {
        if (StringUtils.isNotBlank(token)) {
            //存储用户登录状态
            String onlineFlag = generateAgentOnlineKey(id.toString());
            JedisCache.setStr(onlineFlag, token);
            JedisCache.expire(onlineFlag, myConfiguration.getSessionTimeout());
        }
    }

    public static String generateAgentOnlineKey(String str) {
        return AGENT_USER_CACHE_KEY + str;
    }



    /**
     * 存储app用户登录信息
     * @param id
     * @param token
     */
    public  void storeAppStockUserLoginInfo(Long id, String token) {
        if (StringUtils.isNotBlank(token)) {
            //存储用户登录状态
            String onlineFlag = generateAdminOnlineKey(id.toString());
            JedisCache.setStr(onlineFlag, token);
            JedisCache.expire(onlineFlag, myConfiguration.getSessionTimeout());
        }
    }
    public static String generateAppUserOnlineKey(String str) {
        return APP_USER_CACHE_KEY + str;
    }
}
