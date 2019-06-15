package com.suda.platform.controller.app;

import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.chargeStation.ChargingStationSelAppVO;
import com.suda.platform.VO.chargeStation.ChargingStationsAppVO;
import com.suda.platform.service.IChargingPileInfoService;
import com.suda.platform.service.IChargingStationsService;
import com.util.Respons.ResponseUtil;
import com.util.pageinfoutil.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *
 * app充电站管理
 *
 * @author kongling
 * @package com.suda.platform.controller.app
 * @date 2019-06-15  09:54
 * @project charging_pile_cloud
 */
@RestController
@RequestMapping(value = "app/chargeStation",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AppChargeStationController {
    @Autowired
    private IChargingStationsService chargingStationsService;
    @Autowired
    private IChargingPileInfoService chargingPileInfoService;
    /**
     * 查看所有充电站
     */
    @RequestMapping(value = "/selectAppChargingStations")
    @ResponseBody
    public Map<String,Object> selectAppChargingStations(ChargingStationSelAppVO vo, PageUtil pageUtil){
        PageInfo<ChargingStationsAppVO> list = chargingStationsService.selectAppChargingStations(vo,  pageUtil);
        return ResponseUtil.getSuccessMap(list);
    }



}
