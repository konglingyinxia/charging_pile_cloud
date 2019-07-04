package com.suda.platform.controller.agent;

import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.stockuser.AdminUpdateAssetVo;
import com.suda.platform.VO.stockuser.StockUserIcVO;
import com.suda.platform.entity.StockUser;
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
 * 代理商 会员管理
 * @author kongling
 * @package com.suda.server.service.admin.controller
 * @date 2019-04-20  16:47
 * @project niuwan_cloud
 */
@RestController
@RequestMapping(value = "agent/stockUser",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AgentStockUserController {

    @Autowired
    private IStockUserService stockUserService;

    @Autowired
    private IStockUserCapitalFundService stockUserCapitalFundService;
    /**
     * 查询所有用户
     */
    @RequestMapping(value = "/selectIcAllStockUser")
    @ResponseBody
    public Map<String,Object> selectIcAllStockUser(StockUserIcVO stockUserVO, PageUtil pageUtil){
        if(stockUserVO.getAgentUserId()==null){
            return ResponseUtil.getNotNormalMap(ResponseMsg.ERROR_PARAM);
        }
        stockUserVO.setUserType(StockUserTypeEnum.STATUS_2.getCode());
        PageInfo<StockUserIcVO> list = stockUserService.selectIcAllStockUser(stockUserVO,  pageUtil);
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
                ,vo.getCardNum())||vo.getAgentUserId()==null ) {
            return ResponseUtil.getNotNormalMap(ResponseMsg.ERROR_PARAM);
        }
        int res = stockUserService.upIcdateWallet(vo);
        return res > 0 ? ResponseUtil.getSuccessMap() : ResponseUtil.getNotNormalMap();
    }
}
