package com.suda.platform.controller.admin;

import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.stockuser.AdminUpdateAssetVo;
import com.suda.platform.VO.stockuser.StockUserIcVO;
import com.suda.platform.VO.stockuser.StockUserVO;
import com.suda.platform.entity.StockUser;
import com.suda.platform.entity.StockUserCapitalFund;
import com.suda.platform.enums.finance.WalletTypeEnum;
import com.suda.platform.enums.stockuser.StockUserTypeEnum;
import com.suda.platform.service.IStockUserCapitalFundService;
import com.suda.platform.service.IStockUserService;
import com.util.Respons.ResponseMsg;
import com.util.Respons.ResponseUtil;
import com.util.StringUtils;
import com.util.pageinfoutil.PageUtil;
import config.annotation.LogMenthodName;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *
 * 系统会员管理
 * @author kongling
 * @package com.suda.server.service.admin.controller
 * @date 2019-04-20  16:47
 * @project niuwan_cloud
 */
@RestController
@RequestMapping(value = "admin/stockUser",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminStockUserController {

    @Autowired
    private IStockUserService stockUserService;

    @Autowired
    private IStockUserCapitalFundService stockUserCapitalFundService;


    //====================小程序用户 =======================================
    /**
     * 查询所有用户
     */
    @RequestMapping(value = "/selectAllStockUser")
    @ResponseBody
    public Map<String,Object> selectAllStockUser(StockUserVO stockUserVO, PageUtil pageUtil){
        stockUserVO.setUserType(StockUserTypeEnum.STATUS_1.getCode());
       PageInfo<StockUserVO> list = stockUserService.selectAllStockUser(stockUserVO,  pageUtil);
       return ResponseUtil.getSuccessMap(list);
    }

    /**
     * 禁用启用用户
     *
     * @param stockUser
     * @return
     */
    @RequestMapping(value = "updateDisableUser", method = RequestMethod.POST)
    @ResponseBody
    @LogMenthodName(name = "禁用/启用用户")
    @ApiOperation(notes = "禁用/启用用户 ：用户ID id; 账户状态:0-禁用 1-启用 disable", value = "禁用/启用用户")
    public Map<String, Object> updateDisableUser(StockUser stockUser) {
        if (null == stockUser.getId() || null == stockUser.getIsDisable()) {
            return ResponseUtil.getNotNormalMap(ResponseMsg.ERROR_PARAM);
        }
        int res = stockUserService.updateDisableUser(stockUser);
        return res > 0 ? ResponseUtil.getSuccessMap() : ResponseUtil.getNotNormalMap();
    }

    /**
     * 后台充值扣款
     */
    @RequestMapping(value = "updateRecharge", method = RequestMethod.POST)
    @ResponseBody
    @LogMenthodName(name = "会员后台充值/扣款")
    @ApiOperation(notes = "会员账户资产管理:id 用户id;操作(1-充值2-扣款) operation; money 充值金额;备注 remark", value = "会员账户资产管理")
    public Map<String, Object> recharge(AdminUpdateAssetVo vo, HttpServletRequest request,
                                        HttpServletResponse response) {
        if (StringUtils.isBlank(vo.getId(), vo.getMoney(), vo.getRemark(), vo.getOperation())) {
            return ResponseUtil.getNotNormalMap(ResponseMsg.ERROR_PARAM);
        }
        vo.setStockCode(WalletTypeEnum.STATUS_2.getCode());
        vo.setAgentUserId(0L);
        int res = stockUserService.updateWallet(vo);
        return res > 0 ? ResponseUtil.getSuccessMap() : ResponseUtil.getNotNormalMap();
    }

    /**
     * 我的资产
     *
     * @param stockUserId
     * @param stockCode
     * @return
     */
    @RequestMapping(value = "/getMyStockUserFunds",method ={RequestMethod.POST,RequestMethod.GET} )
    @ResponseBody
    public Map<String, Object> getStockUserFunds(Long stockUserId , String stockCode) {
        if (stockUserId == null) {
            return ResponseUtil.getNotNormalMap(ResponseMsg.ID_IS_EMPTY);
        }
        StockUserCapitalFund funds = stockUserCapitalFundService.upAndSelectFund(stockUserId,WalletTypeEnum.STATUS_2.getCode(),0L);
        return ResponseUtil.getSuccessMap(funds);
    }

    //===============================IC 卡 用户查询=================================================

    @RequestMapping(value = "/selectIcAllStockUser")
    @ResponseBody
    public Map<String,Object> selectIcAllStockUser(StockUserIcVO stockUserVO, PageUtil pageUtil){
        stockUserVO.setUserType(StockUserTypeEnum.STATUS_2.getCode());
        PageInfo<StockUserIcVO> list = stockUserService.selectIcAllStockUser(stockUserVO,  pageUtil);
        return ResponseUtil.getSuccessMap(list);
    }

    /**
     * ic 卡充值 扣款
     *
     * @param vo
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "updateIcRecharge", method = RequestMethod.POST)
    @ResponseBody
    @LogMenthodName(name = "会员后台充值/扣款")
    @ApiOperation(notes = "会员账户资产管理:id 用户id;卡号 cardNum;操作(1-充值2-扣款) operation; money 充值金额;备注 remark", value = "会员账户资产管理")
    public Map<String, Object> rechargeIc(AdminUpdateAssetVo vo, HttpServletRequest request,
                                        HttpServletResponse response) {
        if (StringUtils.isBlank(vo.getMoney(), vo.getRemark(), vo.getOperation()
        ,vo.getCardNum())) {
            return ResponseUtil.getNotNormalMap(ResponseMsg.ERROR_PARAM);
        }
        vo.setAgentUserId(0L);
        int res = stockUserService.upIcdateWallet(vo);
        return res > 0 ? ResponseUtil.getSuccessMap() : ResponseUtil.getNotNormalMap();
    }


}
