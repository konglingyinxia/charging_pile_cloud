package com.suda.platform.controller.agent;

import com.alibaba.fastjson.JSONObject;
import com.suda.platform.VO.adminuser.AdminUserPwdVO;
import com.suda.platform.entity.AgentUser;
import com.suda.platform.service.IAgentUserService;
import com.util.Respons.ResponseMsg;
import com.util.Respons.ResponseUtil;
import com.util.auth.AuthSign;
import com.util.cache.UserCacheUtil;
import config.annotation.LogMenthodName;
import config.redis.RedisUtils;
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
    private IAgentUserService agentUserService;
    @Autowired
    private  UserCacheUtil  userCacheUtil;
    @Autowired
    private RedisUtils redisUtils;

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
        AgentUser agentUserVO = agentUserService .selectByAccountLogin(account,password);
        Long id=agentUserVO.getId();

        //存储登陆用户信息
        redisUtils.setStorageAgentUser(id,JSONObject.toJSON(agentUserVO).toString());
        /**
         * 生成token 存储
         */
        String token = AuthSign.tokenSign(id, JSONObject.parseObject(JSONObject.toJSONString(agentUserVO)));

        /**
         * 设置sessionId
         */
        userCacheUtil.storeAgentUserLoginInfo(id,token);
        agentUserVO.setSessionId(token);
        agentUserVO.setPassword(null);
        return ResponseUtil.getSuccessMap(agentUserVO);
    }


    /**
     * 更改登陆账户账户密码
     */
    @RequestMapping(value = "/updateAdminPassword", method = RequestMethod.POST)
    @LogMenthodName(name = "更改账户密码")
    @ResponseBody
    public Map<String, Object> updateAdminPassword(AdminUserPwdVO agentUserVO) throws UnsupportedEncodingException {
        if(agentUserVO.getId()==null
        ){
            return ResponseUtil.getNotNormalMap(ResponseMsg.ERROR_PARAM);
        }
        AgentUser agentUserSel = agentUserService.getById(agentUserVO.getId());
        if(agentUserSel == null){
            return ResponseUtil.getNotNormalMap(ResponseMsg.NOUSER);
        }
        if(agentUserSel.getPassword().equalsIgnoreCase(agentUserVO.getNewPassword())){
            return ResponseUtil.getNotNormalMap(ResponseMsg.OLD_NEW_PASSWORD_NOT_EQUAL);
        }
        if(!agentUserSel.getPassword().equalsIgnoreCase(agentUserVO.getOldPassword())){
            return ResponseUtil.getNotNormalMap(ResponseMsg.OLD_PASSWORD_IS_ERROR);
        }
        AgentUser adminUser = new AgentUser();
        adminUser.setPassword(agentUserVO.getNewPassword());
        adminUser.setId(agentUserVO.getId());
        agentUserService.updateById(adminUser);
        return ResponseUtil.getSuccessMap();
    }



}
