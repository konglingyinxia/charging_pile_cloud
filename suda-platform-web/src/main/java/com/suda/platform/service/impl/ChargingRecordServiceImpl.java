package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.chargeStation.ChargingRecordVO;
import com.suda.platform.entity.ChargingPileInfo;
import com.suda.platform.entity.ChargingRecord;
import com.suda.platform.entity.ChargingStations;
import com.suda.platform.entity.StockUserCapitalFund;
import com.suda.platform.enums.finance.FinancialTypeEnum;
import com.suda.platform.enums.finance.FinancialTypeMessageEnum;
import com.suda.platform.enums.finance.WaterTypeEnum;
import com.suda.platform.mapper.ChargingRecordMapper;
import com.suda.platform.service.IChargingPileInfoService;
import com.suda.platform.service.IChargingRecordService;
import com.suda.platform.service.IStockUserCapitalFundService;
import com.suda.platform.service.IStockUserMoneyDetailService;
import com.util.DealDateUtil;
import com.util.pageinfoutil.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 充电记录 服务实现类
 * </p>
 *
 * @author kongling
 * @since 2019-05-30
 */
@Service
public class ChargingRecordServiceImpl extends ServiceImpl<ChargingRecordMapper, ChargingRecord> implements IChargingRecordService {

    @Autowired
    private ChargingRecordMapper chargingRecordMapper;
    @Autowired
    private IChargingPileInfoService chargingPileInfoService;
    @Autowired
    private IStockUserCapitalFundService stockUserCapitalFundService;
    @Autowired
    private IStockUserMoneyDetailService stockUserMoneyDetailService;

    @Override
    public PageInfo<ChargingRecordVO> selectAllChargingRecords(ChargingRecordVO vo, PageUtil pageUtil) {
        PageUtil.page(pageUtil);
        List<ChargingRecordVO> list = chargingRecordMapper.selectAllChargingRecords(vo);
        return new PageInfo<>(list);
    }

    /**
     * 插入充电记录
     * @param stockUserId
     * @param pileInfo
     * @param stations
     */
    @Override
    @Transactional(rollbackFor = {})
    public ChargingRecord insertChargingRecord(Long stockUserId, ChargingPileInfo pileInfo, ChargingStations stations) {
        Date now = DealDateUtil.getNowDate();
        ChargingRecord record = ChargingRecord.builder()
                .address(stations.getAddress())
                .chargeStartTime(now)
                .chargingPileInfoId(pileInfo.getId())
                .chargingStationsId(stations.getId())
                .city(stations.getCity())
                .county(stations.getCounty())
                .createTime(now)
                .price(pileInfo.getPrice())
                .province(stations.getProvince())
                .stockUserId(stockUserId)
                .paymentStatus(false)
                .chargeStatus(0)
                .serviceCharge(pileInfo.getServiceCharge())
                .build();
        baseMapper.insert(record);
        //更新充电桩正在使用中
        chargingPileInfoService.update(new UpdateWrapper<ChargingPileInfo>()
        .set("use_status",1)
        .eq("id",pileInfo.getId()));
        return record;
    }

    @Override
    @Transactional(rollbackFor = {})
    public void endCharge(ChargingRecord record) {
        Long stockUserId = record.getStockUserId();
        //充电总金额
        BigDecimal chargeTotalMoney = null;
        //充电中需要结束充电并结算费用
        if(record.getChargeStatus()==1){
            StockUserCapitalFund stockUserCapitalFund = stockUserCapitalFundService.getStockUserCapitalFundS(stockUserId,null);
            String  stockCode =stockUserCapitalFund.getStockCode();
            BigDecimal usableFund = stockUserCapitalFund.getUsableFund();
            BigDecimal money = record.getChargeNum().multiply(record.getPrice());
            BigDecimal serviceFee = record.getServiceCharge();
            chargeTotalMoney = money.add(serviceFee);
            BigDecimal moneyB = money.add(serviceFee).multiply(new BigDecimal(-1));
            //更新账户资产
            int j =  stockUserCapitalFundService.updateRechargeByCodeId(stockUserId,moneyB);
            //资产流水
            if(j>0) {
                stockUserMoneyDetailService.addUserMoneyDetail(
                        stockUserId,
                        money.multiply(new BigDecimal(-1)),
                        usableFund,
                        WaterTypeEnum.STATUS_2.getCode(),
                        FinancialTypeEnum.TYPE_3, FinancialTypeMessageEnum.MESSAGE_1.getMessage(),
                        record.getId(),
                        stockCode);
                stockUserMoneyDetailService.addUserMoneyDetail(
                        stockUserId,
                        serviceFee.multiply(new BigDecimal(-1)),
                        usableFund.subtract(money),
                        WaterTypeEnum.STATUS_2.getCode(),
                        FinancialTypeEnum.TYPE_3,FinancialTypeMessageEnum.MESSAGE_2.getMessage(),
                        record.getId(),
                        stockCode);
            }
        }
        baseMapper.update(null,new UpdateWrapper<ChargingRecord>()
                .set("charge_status",2)
                .set("charge_end_time", DealDateUtil.getNowDate())
                .set(chargeTotalMoney!=null,"charge_total_money",chargeTotalMoney)
                .eq("id",record.getId()));
        chargingPileInfoService.update(new UpdateWrapper<ChargingPileInfo>()
                .set("use_status",0)
                .eq("id",record.getChargingPileInfoId()));
    }
}
