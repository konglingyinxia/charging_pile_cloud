package com.suda.platform.controller.app;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.suda.platform.VO.stockuser.StockUserLoginVO;
import com.suda.platform.VO.stockuser.StockUserSignInVO;
import com.suda.platform.entity.StockUser;
import com.suda.platform.entity.StockUserInfo;
import com.suda.platform.service.ICommonService;
import com.suda.platform.service.IStockUserService;
import com.util.DealDateUtil;
import com.util.Respons.ResponseMsg;
import com.util.Respons.ResponseUtil;
import com.util.auth.AuthSign;
import com.util.cache.UserCacheUtil;
import config.advice.CommonException;
import config.wx.WxMaConfiguration;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 *  用户登录注册 密码重置
 * @author: Zhang Zhengliang
 * @create: 2018-07-05 14:51
 **/
@Controller
@RequestMapping(value = {"app/user"},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(description = "app用户登录注册及密码修改 和重置", tags = "app/user")
public class AppUserController  {
    @Autowired
    private ICommonService commonService;
    @Autowired
    private IStockUserService stockUserService;
    @Autowired
    private UserCacheUtil userCacheUtil;




    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ApiOperation(notes = "用户注册 ：account:传为手机号；pswd:密码(md5加密)；code:验证码；invitationCode:邀请码 ", value = "用户注册")
    @ResponseBody
    @Deprecated
    public Map<String, Object> telRegister(StockUserSignInVO vo) throws IOException {
        if(com.util.StringUtils.isBlank(vo.getAccount(),vo.getCode())){
            return  ResponseUtil.getNotNormalMap(ResponseMsg.ERROR_PARAM);
        }
        StockUserLoginVO  loginVO =  stockUserService.selectByAccount(vo.getAccount());
        if(loginVO !=null){
            return  ResponseUtil.getNotNormalMap(ResponseMsg.USER_HAS_EXIST);
        }
        try {
            commonService.checkTelToken(vo.getAccount(), vo.getCode());
        }catch (Exception e){
            return  ResponseUtil.getNotNormalMap(e.getMessage());
        }
        stockUserService.telRegister(vo);
        return ResponseUtil.getSuccessMap();
    }
    //=================================修改手机号 ================================================================
    /**
     * 验证验证码 绑定手机号
     *
     * @param vo
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "bindPhone", method = RequestMethod.POST)
    @ApiOperation(notes = "用户手机号绑定 ：openId:用户微信openId ;account:手机号；code:验证码", value = "用户手机号绑定")
    @ResponseBody
    public Map<String, Object> bindPhone(StockUserSignInVO vo, HttpServletRequest request, HttpServletResponse response) throws IOException {

        StockUserLoginVO  loginVO =  stockUserService.selectByAccount(vo.getAccount());
        if(loginVO !=null){
            return  ResponseUtil.getNotNormalMap(ResponseMsg.USER_HAS_EXIST);
        }
        try {
            commonService.checkTelToken(vo.getAccount(), vo.getCode());
        }catch (Exception e){
            return  ResponseUtil.getNotNormalMap(e.getMessage());
        }
       boolean success= stockUserService.update(new UpdateWrapper<StockUser>()
        .set("tel",vo.getAccount())
        .eq("open_id",vo.getOpenId()));
        if(success){
          return   ResponseUtil.getSuccessMap();
        }
        return ResponseUtil.getNotNormalMap();
    }

    /**
     * 不须验证验证码绑定手机号
     * @param vo
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "bindPhoneNoCode", method = RequestMethod.POST)
    @ApiOperation(notes = "用户手机号绑定 ：openId:用户微信openId ;account:手机号；code:验证码", value = "用户手机号绑定")
    @ResponseBody
    public Map<String, Object> bindPhoneNoCode(StockUserSignInVO vo, HttpServletRequest request, HttpServletResponse response) throws IOException {

        StockUserLoginVO  loginVO =  stockUserService.selectByAccount(vo.getAccount());
        if(loginVO !=null){
            return  ResponseUtil.getNotNormalMap(ResponseMsg.USER_HAS_EXIST);
        }
        boolean success= stockUserService.update(new UpdateWrapper<StockUser>()
                .set("tel",vo.getAccount())
                .eq("open_id",vo.getOpenId()));
        if(success){
            return   ResponseUtil.getSuccessMap();
        }
        return ResponseUtil.getNotNormalMap();
    }

    /**
     * 微信用户手机号信息解密
     */
    @RequestMapping(value = "wxPhoneDecode", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(notes = "微信用户手机号信息解密", value = "")
    @ResponseBody
    public Map<String, Object> wxDecode(StockUserSignInVO vo, HttpServletRequest req,
                                        HttpServletResponse res) throws WxErrorException, UnsupportedEncodingException {
        if(com.util.StringUtils.isBlank(vo.getOpenId(),vo.getEncryptedData(),vo.getIvStr())){
            return  ResponseUtil.getNotNormalMap(ResponseMsg.ERROR_PARAM);
        }
        WxMaJscode2SessionResult sessionResult =JSONObject.toJavaObject(JSONObject.parseObject(userCacheUtil.getAppStockUserWxLoginInfo(vo.getOpenId())),WxMaJscode2SessionResult.class);
        WxMaPhoneNumberInfo p =WxMaConfiguration.getWxMaService().getUserService().getPhoneNoInfo(sessionResult.getSessionKey(),vo.getEncryptedData(),vo.getIvStr());
        stockUserService.update(new UpdateWrapper<StockUser>()
                .set("tel",p.getPurePhoneNumber())
                .eq("open_id",vo.getOpenId()));
        return ResponseUtil.getSuccessMap(p);
    }


    // ==========================================修改密码 =============================================================
    /**
     * 修改帐号密码
     */
    @RequestMapping(value = "alterPswd", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(notes = "修改帐号密码 ： alterPswd;newPswd 新密码;pswd 旧密码;account 传为手机号或邮箱" +
            "（根据邮箱就传邮箱，根据手机号就传手机号）", value = "修改帐号密码")
    public Map<String, Object> alterPswd(StockUser user, HttpServletRequest req,
                                         HttpServletResponse res) {
            return ResponseUtil.getNotNormalMap();
    }

    @ApiOperation(notes = "重置密码 ：\n " +
            "\n account  传为手机号或邮箱;" +
            "（根据邮箱就传邮箱，根据手机号就传手机号）" +
            "\n pswd:密码(md5加密)；" +
            "\n code:验证码 ", value = "重置密码")
    @RequestMapping(value = "rewNewPwd", method = RequestMethod.POST)
    @ResponseBody
    @Deprecated
    public Map<String, Object> rewNewPwd(StockUserSignInVO vo, HttpServletRequest request, HttpServletResponse response) {

        if(com.util.StringUtils.isBlank(vo.getAccount(),vo.getCode(),vo.getPswd())){
            return  ResponseUtil.getNotNormalMap(ResponseMsg.ERROR_PARAM);
        }
        StockUserLoginVO  loginVO =  stockUserService.selectByAccount(vo.getAccount());
        if(loginVO ==null){
            return  ResponseUtil.getNotNormalMap(ResponseMsg.NOUSER);
        }
        try {
            commonService.checkTelToken(vo.getAccount(), vo.getCode());
        }catch (Exception e){
            return  ResponseUtil.getNotNormalMap(e.getMessage());
        }
        stockUserService.updatePswd(loginVO.getId(),vo.getPswd());
        return ResponseUtil.getSuccessMap();
    }



    /**************************************邮箱*********************************/


    @RequestMapping(value = "registerEmail", method = RequestMethod.POST)
    @ApiOperation(notes = "用户邮箱注册 ：email:账号；pswd:密码(md5加密)；code:验证码；invitationCode:邀请码 ", value = "用户邮箱注册")
    @ResponseBody
    @Deprecated
    public Map<String, Object> registerEmail(StockUser user) throws IOException {
        if (StringUtils.isBlank(user.getEmail())) {
            throw new CommonException(ResponseMsg.MISS_PARAM);
        }
        return ResponseUtil.getNotNormalMap();
    }

    @RequestMapping(value = "bindEmail", method = RequestMethod.POST)
    @ApiOperation(notes = "用户邮箱绑定 ：id:用户id ;email:绑定邮箱；code:验证码", value = "用户邮箱绑定")
    @ResponseBody
    @Deprecated
    public Map<String, Object> bindEmail(StockUser user, StockUserInfo userInfo, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (StringUtils.isBlank(user.getEmail()) || null == user.getId()) {
            throw new CommonException(ResponseMsg.MISS_PARAM);
        }

        return ResponseUtil.getNotNormalMap();
    }



    @ApiOperation(notes = "邮箱重置密码 ：\n " +
            "\n email:账号；" +
            "\n pswd:密码(md5加密)；" +
            "\n code:验证码 ", value = "邮箱重置密码")
    @RequestMapping(value = "rewEmailNewPwd", method = RequestMethod.POST)
    @ResponseBody
    @Deprecated
    public Map<String, Object> rewEmailNewPwd(StockUser user, HttpServletRequest request, HttpServletResponse response) {

        if (StringUtils.isBlank(user.getEmail())) {
            throw new CommonException(ResponseMsg.MISS_PARAM);
        }

        return ResponseUtil.getNotNormalMap();
    }

    @RequestMapping(value = "checkEmailTel", method = RequestMethod.POST)
    @ApiOperation(notes = "验证邮箱和手机号 ：id:用户id ;account:邮箱和手机号；code:验证码", value = "验证邮箱和手机号")
    @ResponseBody
    @Deprecated
    public Map<String, Object> checkEmailTel(StockUser user, StockUserInfo userInfo, HttpServletRequest request, HttpServletResponse response) throws IOException {

        return ResponseUtil.getSuccessMap();
    }



    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ApiOperation(notes = "用户登录 ：account账号；  pswd密码", value = "用户登录")
    @ResponseBody
    @Deprecated
    public Map<String, Object> userLogin(StockUserSignInVO vo, HttpServletRequest req,
                                         HttpServletResponse res) throws UnsupportedEncodingException {
        if(com.util.StringUtils.isBlank(vo.getAccount(),vo.getPswd())){
            return  ResponseUtil.getNotNormalMap(ResponseMsg.ERROR_PARAM);
        }
        StockUserLoginVO  loginVO =  stockUserService.selectByAccount(vo.getAccount());
        if(loginVO ==null){
            return  ResponseUtil.getNotNormalMap(ResponseMsg.NOUSER);
        }

        /**
         * 生成token 存储
         */
        String token = AuthSign.tokenSign(loginVO.getId(), JSONObject.parseObject(JSONObject.toJSONString(loginVO)));

        /**
         * 设置sessionId
         */
        userCacheUtil.storeAppStockUserLoginInfo(loginVO.getId(),token);
        loginVO.setSessionId(token);
        return ResponseUtil.getSuccessMap(loginVO);
    }

    /**
     * 用户获取openId (小程序登陆授权)
     */
    @RequestMapping(value = "wxOpenId", method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(notes = "用户登录 ：account账号； code:微信code", value = "用户登录")
    @ResponseBody
    public Map<String, Object> wxOpenId(StockUserSignInVO vo, HttpServletRequest req,
                                         HttpServletResponse res) throws WxErrorException, UnsupportedEncodingException {
        if(com.util.StringUtils.isBlank(vo.getCode())){
            return  ResponseUtil.getNotNormalMap(ResponseMsg.ERROR_PARAM);
        }
        WxMaJscode2SessionResult sessionResult = WxMaConfiguration.getWxMaService().jsCode2SessionInfo(vo.getCode());
        StockUserLoginVO  loginVO =  stockUserService.wxLogin(sessionResult);
        stockUserService.update(new UpdateWrapper<StockUser>()
        .set("last_login_time", DealDateUtil.getNowDate()));
        userCacheUtil.storeAppStockUserWxLoginInfo(sessionResult.getOpenid(),JSONObject.toJSONString(sessionResult));
        /**
         * 生成token 存储
         */
        String token = AuthSign.tokenSign(loginVO.getId(), JSONObject.parseObject(JSONObject.toJSONString(loginVO)));

        /**
         * 设置sessionId
         */
        userCacheUtil.storeAppStockUserLoginInfo(loginVO.getId(),token);
        loginVO.setSessionId(token);
        loginVO.setSessionResult(sessionResult);
        return ResponseUtil.getSuccessMap(loginVO);
    }

    public static void main(String[] args) {
        System.out.println(JSONObject.toJSONString(ResponseUtil.getSuccessMap(new WxMaPhoneNumberInfo()), SerializerFeature.WriteMapNullValue));
    }


}
