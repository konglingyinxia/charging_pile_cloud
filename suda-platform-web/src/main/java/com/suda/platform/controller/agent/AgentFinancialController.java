package com.suda.platform.controller.agent;

import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.finance.StockUserMoneyDetailVO;
import com.suda.platform.enums.finance.FinancialTypeEnum;
import com.suda.platform.service.IStockUserMoneyDetailService;
import com.util.Respons.ResponseUtil;
import com.util.pageinfoutil.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 财务管理
 * @author kongling
 * @package com.suda.server.service.admin.controller
 * @date 2019-05-09  10:18
 * @project suda_cloud
 */
@Controller
@Scope(value = "prototype")
@RequestMapping(value = "agent/financial",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AgentFinancialController {
    @Autowired
    private IStockUserMoneyDetailService stockUserMoneyDetailService;

    /**
     * 财务流水
     *
     * @param vo
     * @param pageUtil
     * @return
     */
    @RequestMapping(value = "/getMoneyDetails")
    @ResponseBody
    public Map<String, Object> getMoneyDetails(StockUserMoneyDetailVO vo, PageUtil pageUtil) {
        PageInfo<StockUserMoneyDetailVO> listVOPageInfo = stockUserMoneyDetailService.getAdminMoneyDetails(vo, pageUtil);
        for(StockUserMoneyDetailVO vos:listVOPageInfo.getList()){
            vos.setTypeStr(FinancialTypeEnum.getFinancialTypeEnumByCode(vos.getType()).getMessage());
        }
        return ResponseUtil.getSuccessMap(listVOPageInfo);
    }

}
