package com.constant;

/**
 * @author: Zhang Zhengliang
 * @create: 2018-06-21 16:28
 **/
public class RedisKeysPrefix {

    /**
     * 存储短信信息
     */
    public  static  String SMS_SEND_TIME_MESSAGE="SMS_SEND_TIME_MESSAGE_:"+ CommonConstant.project;



    //=============================== 编辑 角色 / 用户信息 ========================================================
    /**
     * 存储编辑用户信息
     */
    public static final String ADMIN_USER_CACHE_EDIT_KEY = "edit:com.suda.admin.user.cache"+ CommonConstant.project;
    public static final String APP_USER_CACHE_EDIT_KEY = "edit:com.suda.app.user.cache"+ CommonConstant.project;
    public static final String AGENT_USER_CACHE_EDIT_KEY = "edit:com.suda.agent.user.cache"+ CommonConstant.project;

    public static final String ADMIN_USER_ROLE_PERMISSION_EDIT_KEY = "edit:com.suda.admin.user.role.permission.cache"+ CommonConstant.project;


}
