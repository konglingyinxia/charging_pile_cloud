package com.suda.platform.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.AdminUserVO;
import com.suda.platform.VO.adminuser.AdminUserPwdVO;
import com.suda.platform.entity.AdminUser;
import com.suda.platform.service.IAdminUserService;
import com.util.Respons.ResponseMsg;
import com.util.Respons.ResponseUtil;
import com.util.auth.AuthSign;
import com.util.cache.UserCacheUtil;
import com.util.pageinfoutil.PageUtil;
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
@RequestMapping(value = "admin/user",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminUserComtroller {
    @Autowired
    private IAdminUserService adminUserService;
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
        AdminUser adminUserVO = adminUserService .selectByAccountLogin(account,password);
        Long id=adminUserVO.getId();
        //存储登陆用户信息
        redisUtils.setStorageAdminUser(id,JSONObject.toJSON(adminUserVO).toString());
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

    /**
     * 管理员列表
     *
     */
    @RequestMapping(value = "/getAdminUsers", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAdminUsers(AdminUserVO adminUserVO, HttpServletRequest req, PageUtil pageUtil) throws UnsupportedEncodingException {
        PageInfo<AdminUserVO> lists = adminUserService.selectByChoice(adminUserVO,pageUtil);
        return ResponseUtil.getSuccessMap(lists);
    }


    /**
     * 管理员禁用启用
     */
    @RequestMapping(value = "/adminUserDisable", method = RequestMethod.POST)
    @LogMenthodName(name = "禁用/启用管理员")
    @ResponseBody
    public Map<String, Object> adminUserDisable(AdminUserVO adminUserVO) throws UnsupportedEncodingException {
        if(adminUserVO.getId()==null
                || adminUserVO.getId() ==1
                || adminUserVO.getIsDisable() ==null){
            return ResponseUtil.getNotNormalMap(ResponseMsg.ERROR_PARAM);
        }
        adminUserService.adminUserDisable(adminUserVO);
        return ResponseUtil.getSuccessMap();
    }

    /**
     * 更改管理员角色
     */
    @RequestMapping(value = "/editUserRole", method = RequestMethod.POST)
    @LogMenthodName(name = "更改管理员角色")
    @ResponseBody
    public Map<String, Object> editUserRole(AdminUserVO adminUserVO)  {
        if(adminUserVO.getId()==null
                || adminUserVO.getId() ==1
                || adminUserVO.getRoleId() ==null){
            return ResponseUtil.getNotNormalMap(ResponseMsg.ERROR_PARAM);
        }
        adminUserService.editUserRole(adminUserVO);
        return ResponseUtil.getSuccessMap();
    }

    /**
     * 更改登陆账户账户密码
     */
    @RequestMapping(value = "/updateAdminPassword", method = RequestMethod.POST)
    @LogMenthodName(name = "更改账户密码")
    @ResponseBody
    public Map<String, Object> updateAdminPassword(AdminUserPwdVO adminUserVO) throws UnsupportedEncodingException {
        if(adminUserVO.getId()==null
        ){
            return ResponseUtil.getNotNormalMap(ResponseMsg.ERROR_PARAM);
        }
        AdminUser adminUserSel = adminUserService.getById(adminUserVO.getId());
        if(adminUserSel == null){
            return ResponseUtil.getNotNormalMap(ResponseMsg.NOUSER);
        }
        if(adminUserSel.getPassword().equalsIgnoreCase(adminUserVO.getNewPassword())){
            return ResponseUtil.getNotNormalMap(ResponseMsg.OLD_NEW_PASSWORD_NOT_EQUAL);
        }
        if(!adminUserSel.getPassword().equalsIgnoreCase(adminUserVO.getOldPassword())){
            return ResponseUtil.getNotNormalMap(ResponseMsg.OLD_PASSWORD_IS_ERROR);
        }
        AdminUser adminUser = new AdminUser();
        adminUser.setPassword(adminUserVO.getNewPassword());
        adminUser.setId(adminUserVO.getId());
        adminUserService.updateById(adminUser);
        return ResponseUtil.getSuccessMap();
    }
}
