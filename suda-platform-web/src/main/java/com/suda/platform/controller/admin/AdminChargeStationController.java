package com.suda.platform.controller.admin;

import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.chargeStation.ChargingStationsVO;
import com.suda.platform.entity.ChargingStations;
import com.suda.platform.service.IChargingStationsService;
import com.util.DealDateUtil;
import com.util.Respons.ResponseUtil;
import com.util.pageinfoutil.PageUtil;
import config.annotation.LogMenthodName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 充电站管理
 *
 * @author kongling
 * @package com.suda.platform.controller.admin
 * @date 2019-05-30  11:16
 * @project charging_pile_cloud
 */
@RestController
@RequestMapping(value = "admin/chargeStation",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminChargeStationController {
    @Autowired
    private IChargingStationsService chargingStationsService;

    /**
     * 查看所有充电站
     */
    @RequestMapping(value = "/selectAllChargingStations")
    @ResponseBody
    public Map<String,Object> selectAllChargingStations(ChargingStationsVO vo, PageUtil pageUtil){
        PageInfo<ChargingStations> list = chargingStationsService.selectAllChargingStations(vo,  pageUtil);
        return ResponseUtil.getSuccessMap(list);
    }

    /**
     * 添加编辑充电站
     */
    @RequestMapping(value = "/addAndEditStations")
    @ResponseBody
    @LogMenthodName(name = " 添加编辑充电站")
    public Map<String,Object> addAndEditStations(ChargingStations vo){
        if(vo.getId()!=null){
            chargingStationsService.updateById(vo);
        }else {
            vo.setCreateTime(DealDateUtil.getNowDate());
            chargingStationsService.save(vo);
        }
        return ResponseUtil.getSuccessMap();
    }

}
