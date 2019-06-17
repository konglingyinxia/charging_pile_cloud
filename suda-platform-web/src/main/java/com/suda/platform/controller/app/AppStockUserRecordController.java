package com.suda.platform.controller.app;

import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.chargeStation.ChargingRecordVO;
import com.suda.platform.VO.finance.StockUserChargeVO;
import com.suda.platform.service.IChargingRecordService;
import com.suda.platform.service.IStockUserChargeService;
import com.util.Respons.ResponseMsg;
import com.util.Respons.ResponseUtil;
import com.util.pageinfoutil.PageUtil;
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
 * 用户记录
 *
 * @author kongling
 * @package com.suda.platform.controller.app
 * @date 2019-06-17  09:56
 * @project charging_pile_cloud
 */
@RestController
@RequestMapping(value = "app/stockUserRecord",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AppStockUserRecordController {
    @Autowired
    private IChargingRecordService chargingRecordService;
    @Autowired
    private IStockUserChargeService stockUserChargeService;

    /**
     * 用户充电记录
     */
    @RequestMapping(value = "/selectAllChargingRecords")
    @ResponseBody
    public Map<String,Object> selectAllChargingRecords(ChargingRecordVO vo, PageUtil pageUtil){
        if(vo.getStockUserId() == null){
            return ResponseUtil.getNotNormalMap(ResponseMsg.ID_IS_EMPTY);
        }
        PageInfo<ChargingRecordVO> list = chargingRecordService.selectAllChargingRecords(vo,  pageUtil);
        return ResponseUtil.getSuccessMap(list);
    }

    /**
     * 获取用户充值记录
     * @param coinCharge
     * @return
     */
    @RequestMapping(value = "getStockUserCoinCharges", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(notes = "", value = "")
    public Map<String, Object> getAppStockUserCoinCharges(StockUserChargeVO coinCharge, PageUtil pageUtil) {
        if(coinCharge.getStockUserId() == null){
            return ResponseUtil.getNotNormalMap(ResponseMsg.ID_IS_EMPTY);
        }
        PageInfo<StockUserChargeVO> lists = stockUserChargeService.getAdminStockUserCoinCharges(coinCharge,pageUtil);
        return ResponseUtil.getSuccessMap(lists);
    }

}
