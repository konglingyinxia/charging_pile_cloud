package com.suda.platform.controller.app;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suda.platform.VO.chargeStation.ChargingRecordVO;
import com.suda.platform.VO.chargeStation.RealTimeChargRecordVO;
import com.suda.platform.entity.ChargingPileInfo;
import com.suda.platform.entity.ChargingRecord;
import com.suda.platform.entity.ChargingStations;
import com.suda.platform.entity.StockUserCapitalFund;
import com.suda.platform.enums.finance.WalletTypeEnum;
import com.suda.platform.service.IChargingPileInfoService;
import com.suda.platform.service.IChargingRecordService;
import com.suda.platform.service.IChargingStationsService;
import com.suda.platform.service.IStockUserCapitalFundService;
import com.util.Respons.ResponseMsg;
import com.util.Respons.ResponseUtil;
import com.util.StringUtils;
import config.annotation.LogMenthodName;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *  用户充电管理
 * @author kongling
 * @package com.suda.platform.controller.app
 * @date 2019-06-17  11:25
 * @project charging_pile_cloud
 */
@RestController
@RequestMapping(value = "app/charging",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AppChargingController {
    @Autowired
    private IChargingRecordService chargingRecordService;
    @Autowired
    private IChargingStationsService chargingStationsService;
    @Autowired
    private IChargingPileInfoService chargingPileInfoService;
    @Autowired
    private IStockUserCapitalFundService stockUserCapitalFundService;

    /**
     * 开启放电 查询充电桩
     */
    @RequestMapping(value = "/openCharge", method = RequestMethod.POST)
    @ResponseBody
    @LogMenthodName(name = "开启充电")
    public Map<String, Object> getService(ChargingRecord record, ChargingPileInfo pileInfo) {
        if(record.getStockUserId()==null || StringUtils.isBlank(pileInfo.getSerialNumber())){
            return ResponseUtil.getNotNormalMap(ResponseMsg.ERROR_PARAM);
        }
        StockUserCapitalFund stockUserCapitalFund = stockUserCapitalFundService.upAndSelectFund(record.getStockUserId(), WalletTypeEnum.STATUS_2.getCode(),0L);
        if(stockUserCapitalFund.getUsableFund().compareTo(BigDecimal.ZERO)<=0){
            return ResponseUtil.getNotNormalMap("钱包余额不足！");
        }
        List<ChargingPileInfo> pileInfos = chargingPileInfoService.list(new QueryWrapper<ChargingPileInfo>()
        .eq("serial_number",pileInfo.getSerialNumber())
        .eq("is_deleted",0));
        if(pileInfos.size()==0){
            return ResponseUtil.getNotNormalMap("充电桩编号错误！");
        }
        //充电桩是否使用
        pileInfo = pileInfos.get(0);
        if(pileInfo.getUseStatus()){
            return ResponseUtil.getNotNormalMap("该充电桩正在使用！");
        }
        if(pileInfo.getOffLineIs()){
            return ResponseUtil.getNotNormalMap("该充电桩处于离线状态！");
        }
        //查询充电站是否禁用
        ChargingStations stations  = chargingStationsService.getById(pileInfo.getChargingStationsId());
        if(stations==null){
            return ResponseUtil.getNotNormalMap("该充电桩分组不正确！");
        }
        if(stations.getIsDisable()){
            return ResponseUtil.getNotNormalMap("该充电桩分组已经停用！");
        }
        //插入充电记录
        ChargingRecord record1 =   chargingRecordService.insertChargingRecord(record.getStockUserId(),pileInfo,stations);
        return ResponseUtil.getSuccessMap(record1.getId());
    }

    /**
     * 结束充电
     */
    @RequestMapping(value = "/endCharge", method = RequestMethod.POST)
    @ResponseBody
    @LogMenthodName(name = "结束充电")
    public Map<String, Object> endCharge(ChargingRecord record) {
        if(record.getStockUserId()==null|| record.getId()==null){
            return ResponseUtil.getNotNormalMap(ResponseMsg.ID_IS_EMPTY);
        }
        record = chargingRecordService.getOne(new QueryWrapper<ChargingRecord>()
        .eq("id",record.getId())
        .eq("stock_user_id",record.getStockUserId())
        .ne("charge_status",2));
        if(record !=null) {
            chargingRecordService.endCharge(record,WalletTypeEnum.STATUS_2.getCode());
        }
        return ResponseUtil.getSuccessMap();
    }


    /**
     * 充电记录实时接口
     */
    @RequestMapping(value = "/realTimeChargingRecord")
    @ResponseBody
    public Map<String,Object> realTimeChargingRecord(ChargingRecordVO vo){
        if(vo.getId()==null){
            return ResponseUtil.getNotNormalMap(ResponseMsg.ID_IS_EMPTY);
        }
        ChargingRecord  record = chargingRecordService.getById(vo.getId());
        RealTimeChargRecordVO realTimeVO =null;
        if(record!=null){
           realTimeVO =RealTimeChargRecordVO.builder().build();
            BeanUtils.copyProperties(record,realTimeVO);
            ChargingPileInfo pileInfo = chargingPileInfoService.getById(record.getChargingPileInfoId());
            ChargingStations stations = chargingStationsService.getById(record.getChargingStationsId());

            realTimeVO.setRateOfWork(pileInfo.getRateOfWork());
            realTimeVO.setSerialNumber(pileInfo.getSerialNumber());
            realTimeVO.setStationName(stations.getStationName());
            realTimeVO.setChargeTotalMoney(record.getChargeNum().multiply(record.getPrice()));
            return ResponseUtil.getSuccessMap(realTimeVO);
        }else {
            return ResponseUtil.getNotNormalMap("记录不存在");
        }
    }

}
