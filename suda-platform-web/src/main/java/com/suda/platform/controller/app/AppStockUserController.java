package com.suda.platform.controller.app;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suda.platform.VO.finance.StockUserCapitalFundAppVO;
import com.suda.platform.VO.stockuser.*;
import com.suda.platform.entity.StockUser;
import com.suda.platform.entity.StockUserCapitalFund;
import com.suda.platform.entity.StockUserInfo;
import com.suda.platform.enums.finance.WalletTypeEnum;
import com.suda.platform.service.ICommonService;
import com.suda.platform.service.IStockUserCapitalFundService;
import com.suda.platform.service.IStockUserInfoService;
import com.suda.platform.service.IStockUserService;
import com.util.Respons.ResponseMsg;
import com.util.Respons.ResponseUtil;
import com.util.StringUtils;
import config.annotation.LogMenthodName;
import config.redis.RedisUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * app 用户管理
 * @author kongling
 * @package com.suda.app.controller.shop
 * @date 2019-05-10  10:09
 * @project suda_cloud
 */
@RestController
@RequestMapping(value = "app/stockUser",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Log4j
public class AppStockUserController {

    @Autowired
    HttpServletRequest request;

    @Autowired
    private IStockUserService stockUserService;

    @Autowired
    private ICommonService commonService;
    @Autowired
    private IStockUserInfoService stockUserInfoService;
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private IStockUserCapitalFundService stockUserCapitalFundService;




    /**
     * 用户信息
     */
    @RequestMapping(value = "/getUserInfos")
    @ResponseBody
    public Map<String, Object> getUserInfos(StockUserParamsVO vo){
        StockUser stockUser = stockUserService.getById(vo.getStockUserId());
        AppStockUserInfoVO infoVO = new AppStockUserInfoVO();
        BeanUtils.copyProperties(stockUser,infoVO);
        return ResponseUtil.getSuccessMap(infoVO);
    }

    /**
     * 编辑头像 昵称
     */
    @RequestMapping(value = "/editStockUser",method = {RequestMethod.POST})
    @ResponseBody
    @LogMenthodName(name = "编辑头像/昵称")
    public Map<String, Object> editStockUser(AppEditStockUser vo){
        if(vo.getStockUserId() ==null){
            return ResponseUtil.getNotNormalMap(ResponseMsg.ID_IS_EMPTY);
        }
        StockUser user = new StockUser();
        user.setId(vo.getStockUserId());
        user.setNickname(vo.getNickname());
        user.setHeadUrl(vo.getHeadUrl());
        stockUserService.updateById(user);
        return ResponseUtil.getSuccessMap();
    }

    /**
     * 更换手机号
     */
    @LogMenthodName(name = "更换手机号")
    @RequestMapping(value = "/changeTel",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> changeTel(StockUserSignInVO signInVO,StockUserParamsVO paramsVO){
        if(paramsVO.getStockUserId() ==null|| StringUtils.isBlank(signInVO.getCode(),signInVO.getAccount())){
            return ResponseUtil.getNotNormalMap(ResponseMsg.ERROR_PARAM);
        }
        if(stockUserService.getById(paramsVO.getStockUserId())==null){
            return  ResponseUtil.getNotNormalMap(ResponseMsg.NOUSER);
        }
        try {
            commonService.checkTelToken(signInVO.getAccount(), signInVO.getCode());
        }catch (Exception e){
            return  ResponseUtil.getNotNormalMap(e.getMessage());
        }
        List<StockUser> users = stockUserService.list(
                new QueryWrapper<StockUser>()
        .eq("tel",signInVO.getAccount())
        .ne("id",paramsVO.getStockUserId()));
        if(users.size()!=0){
            return  ResponseUtil.getNotNormalMap(ResponseMsg.USER_HAS_EXIST);
        }
        StockUser user =new StockUser();
        user.setId(paramsVO.getStockUserId());
        user.setTel(signInVO.getAccount());
        stockUserService.updateById(user);
        return ResponseUtil.getSuccessMap();
    }

    /**
     *
     * @param userinfo
     * @param req
     * @param res
     * @return
     */
    @LogMenthodName(name = "提交实名认证")
    @RequestMapping(value = "/openAccount",method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object> openAccount(StockUserInfo userinfo,
                                           HttpServletRequest req, HttpServletResponse res) {
        if(userinfo.getStockUserId() ==null){
            return ResponseUtil.getNotNormalMap(ResponseMsg.ERROR_PARAM);
        }
        StockUser stockUser =stockUserService.getById(userinfo.getStockUserId());
        if(stockUser==null){
            return  ResponseUtil.getNotNormalMap(ResponseMsg.NOUSER);
        }
        boolean data = stockUserInfoService.openAccount(userinfo,stockUser);
        if (data) {
            return ResponseUtil.getSuccessMap();
        }
        return ResponseUtil.getNotNormalMap();
    }

    @ApiOperation(notes = "重置支付密码 ：\n " +
            "\n account  传为手机号或邮箱;" +
            "（根据邮箱就传邮箱，根据手机号就传手机号）" +
            "\n tradePwd:密码(md5加密)；" +
            "\n code:验证码 ", value = "重置密码")
    @RequestMapping(value = "setTradePwd", method = RequestMethod.POST)
    @ResponseBody
    @LogMenthodName(name = "重置支付密码")
    public Map<String, Object> setTradePwd(StockUserSignInVO vo, HttpServletRequest request, HttpServletResponse response) {

        if(com.util.StringUtils.isBlank(vo.getAccount(),vo.getCode(),vo.getTradePwd())){
            return  ResponseUtil.getNotNormalMap(ResponseMsg.ERROR_PARAM);
        }
        StockUserLoginVO loginVO =  stockUserService.selectByAccount(vo.getAccount());
        if(loginVO ==null){
            return  ResponseUtil.getNotNormalMap(ResponseMsg.NOUSER);
        }
        try {
            commonService.checkTelToken(vo.getAccount(), vo.getCode());
        }catch (Exception e){
            return  ResponseUtil.getNotNormalMap(e.getMessage());
        }
        StockUser stockUser = new StockUser();
        stockUser.setTradePwd(vo.getTradePwd());
        stockUser.setId(loginVO.getId());
        stockUserService.updateById(stockUser);
        return ResponseUtil.getSuccessMap();
    }



    /**
     * 用户资产查询
     *
     * @param vo
     * @return
     */
    @RequestMapping(value = "/getStockUserFunds",method ={RequestMethod.POST,RequestMethod.GET} )
    @ResponseBody
    public Map<String, Object> getStockUserFunds(StockUserParamsVO vo) {
        if (vo.getStockUserId() == null) {
            return ResponseUtil.getNotNormalMap(ResponseMsg.ID_IS_EMPTY);
        }
        //创建小程序钱包
        StockUserCapitalFund app =  stockUserCapitalFundService.upAndSelectFund(vo.getStockUserId(), WalletTypeEnum.STATUS_2.getCode(),null);
        StockUserCapitalFundAppVO appVO = new StockUserCapitalFundAppVO();
        BeanUtils.copyProperties(app,appVO);
        return ResponseUtil.getSuccessMap(appVO);
    }













}
