package com.suda.platform.controller.agent;

import com.alibaba.fastjson.JSONObject;
import com.suda.platform.entity.AdminUser;
import com.suda.platform.service.IAdminUserService;
import com.util.Respons.ResponseUtil;
import com.util.auth.AuthSign;
import com.util.cache.UserCacheUtil;
import config.annotation.LogMenthodName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author 卫星
 * @package com.suda.server.service.admin.controller
 * @date 2019-04-19  23:51
 * @project niuwan_cloud
 */
@RestController
@RequestMapping(value = "agent/user",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AgentUserComtroller {
    @Autowired
    private IAdminUserService adminUserService;
    @Autowired
    private  UserCacheUtil  userCacheUtil;

    /**
     * 登陆请求
     *
     * @param account
     * @param password
     * @return
     */
    @LogMenthodName(name = "AOP环绕登陆请求")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> doLogin(String account, String password, HttpServletRequest req) throws UnsupportedEncodingException {
        AdminUser adminUserVO = adminUserService .selectByAccountLogin(account,password);
        Long id=adminUserVO.getId();
        /**
         * 生成token 存储
         */
        String token = AuthSign.tokenSign(id, JSONObject.parseObject(JSONObject.toJSONString(adminUserVO)));

        /**
         * 设置sessionId
         */
        userCacheUtil.storeAdminUserLoginInfo(id,token);
        adminUserVO.setSessionId(token);
        adminUserVO.setPassword(null);
        return ResponseUtil.getSuccessMap(adminUserVO);
    }



}
