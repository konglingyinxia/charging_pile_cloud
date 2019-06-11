package com.suda.platform.controller.admin;

import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.chargeStation.ChargingPileInfoVO;
import com.suda.platform.VO.chargeStation.ChargingStationsVO;
import com.suda.platform.entity.ChargingPileInfo;
import com.suda.platform.entity.ChargingStations;
import com.suda.platform.service.IChargingPileInfoService;
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
    @Autowired
    private IChargingPileInfoService chargingPileInfoService;

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
    @LogMenthodName(name = " 添加/编辑充电站")
    public Map<String,Object> addAndEditStations(ChargingStations vo){
        if(vo.getId()!=null){
            chargingStationsService.updateById(vo);
        }else {
            vo.setCreateTime(DealDateUtil.getNowDate());
            chargingStationsService.save(vo);
        }
        return ResponseUtil.getSuccessMap();
    }

    /**
     * 查看所有充电站下的充电桩
     */
    @RequestMapping(value = "/selectAllChargingPileInfos")
    @ResponseBody
    public Map<String,Object> selectAllChargingPileInfos(ChargingPileInfoVO vo, PageUtil pageUtil){
        PageInfo<ChargingPileInfo> list = chargingPileInfoService.selectAllChargingPileInfos(vo,  pageUtil);
        return ResponseUtil.getSuccessMap(list);
    }

    /**
     * 编辑和添加充电站
     */
    @RequestMapping(value = "/addAndEditChargingPileInfo")
    @ResponseBody
    @LogMenthodName(name = " 编辑/添加充电站")
    public Map<String,Object> addAndEditChargingPileInfo(ChargingPileInfo vo){
        if(vo.getId()==null){
            if(vo.getChargingStationsId()==null){
                return ResponseUtil.getNotNormalMap("未选择充电桩所属充电站");
            }
            chargingPileInfoService.save(vo);
        }else {
            if(vo.getChargingStationsId()==null){
                return ResponseUtil.getNotNormalMap("未选择充电桩所属充电站");
            }
            if(chargingStationsService.getById(vo.getChargingStationsId())==null){
                return ResponseUtil.getNotNormalMap("所属充电站不存在");
            }
            chargingPileInfoService.updateById(vo);
        }
        return ResponseUtil.getSuccessMap();
    }



}
