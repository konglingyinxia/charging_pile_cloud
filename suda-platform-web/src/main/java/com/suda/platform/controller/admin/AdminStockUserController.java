package com.suda.platform.controller.admin;

import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.stockuser.StockUserVO;
import com.suda.platform.entity.StockUser;
import com.suda.platform.service.IStockUserService;
import com.util.Respons.ResponseMsg;
import com.util.Respons.ResponseUtil;
import com.util.pageinfoutil.PageUtil;
import config.annotation.LogMenthodName;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    /**
     * 查询所有用户
     */
    @RequestMapping(value = "/selectAllStockUser")
    @ResponseBody
    public Map<String,Object> selectAllStockUser(StockUserVO stockUserVO, PageUtil pageUtil){

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


}
