package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import com.suda.platform.enums.finance.WalletTypeEnum;
import com.suda.platform.enums.finance.WaterTypeEnum;
import com.suda.platform.mapper.ChargingRecordMapper;
import com.suda.platform.service.*;
import com.util.DealDateUtil;
import com.util.pageinfoutil.PageUtil;
import config.advice.CommonException;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ChargingRecordServiceImpl extends ServiceImpl<ChargingRecordMapper, ChargingRecord> implements IChargingRecordService {

    @Autowired
    private ChargingRecordMapper chargingRecordMapper;
    @Autowired
    private IChargingPileInfoService chargingPileInfoService;
    @Autowired
    private IStockUserCapitalFundService stockUserCapitalFundService;
    @Autowired
    private IStockUserMoneyDetailService stockUserMoneyDetailService;
    @Autowired
    private IChargingStationsService chargingStationsService;

    @Override
    public PageInfo<ChargingRecordVO> selectAllChargingRecords(ChargingRecordVO vo, PageUtil pageUtil) {
        PageUtil.page(pageUtil);
        List<ChargingRecordVO> list = chargingRecordMapper.selectAllChargingRecords(vo);
        return new PageInfo<>(list);
    }

    /**
     *  开启放电，插入充电记录
     * 插入充电记录
     * @param stockUserId
     * @param pileInfo
     * @param stations
     */
    @Override
    @Transactional(rollbackFor = {})
    public ChargingRecord insertChargingRecord(Long stockUserId, ChargingPileInfo pileInfo, ChargingStations stations) {

        return  insertCommontChargingRecord(stockUserId, pileInfo,stations);


    }

    /**
     *  避免事务中套事务 抽离方法
     * @param stockUserId
     * @param pileInfo
     * @param stations
     * @return
     */
    private ChargingRecord insertCommontChargingRecord(Long stockUserId, ChargingPileInfo pileInfo, ChargingStations stations) {
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

    /**
     * 停止放电
     *
     * @param record
     * @param walletType  //钱包类型
     */
    @Override
    @Transactional(rollbackFor = {})
    public void endCharge(ChargingRecord record, String walletType) {
        commonEndCharge(record,walletType);
    }

    /**
     * 防止事务中嵌套事务 使事务失效
     *
     */
    private void commonEndCharge(ChargingRecord record, String walletType) {
        Long stockUserId = record.getStockUserId();
        //充电总金额
        BigDecimal chargeTotalMoney = null;
        //充电中需要结束充电并结算费用
        if(record.getChargeStatus()==1){
            StockUserCapitalFund stockUserCapitalFund = stockUserCapitalFundService.upAndSelectFund(stockUserId,walletType,0L);
            String  stockCode =stockUserCapitalFund.getStockCode();
            BigDecimal usableFund = stockUserCapitalFund.getUsableFund();
            BigDecimal money = record.getChargeNum().multiply(record.getPrice());
            BigDecimal serviceFee = record.getServiceCharge().multiply(record.getChargeNum());
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

    /**
     * 更新充电金额
     *
     * @param pileNum
     * @param chargeNumStr
     */
    @Override
    @Transactional(rollbackFor = {})
    public String updateChargeMoney(String pileNum, String chargeNumStr) {
        //查询充电桩
        ChargingPileInfo pileInfo = chargingPileInfoService.getOne(new QueryWrapper<ChargingPileInfo>()
        .eq("serial_number",pileNum)
        .eq("is_deleted",0));
        if(pileInfo==null){
            throw new CommonException("充电桩错误！");
        }
        if(!pileInfo.getUseStatus()){
            throw new CommonException("充电桩未被使用，不能充电！");
        }
        //查询充电记录
        ChargingRecord record = chargingRecordMapper.selectOne(new QueryWrapper<ChargingRecord>()
        .eq("charging_pile_info_id",pileInfo.getId())
        .ne("charge_status",2));
        if(record==null){
            throw new CommonException("本次充电已经结束！");
        }
        //充电度数
        BigDecimal chargeNum =new BigDecimal(chargeNumStr).divide(new BigDecimal(10),1);

        StockUserCapitalFund fund = stockUserCapitalFundService.upAndSelectFund(record.getStockUserId(),WalletTypeEnum.STATUS_2.getCode(),0L);
        //本次充电费用
        BigDecimal  nowChargeFee = chargeNum.multiply(pileInfo.getPrice()).add(chargeNum.multiply(pileInfo.getServiceCharge()));
        if(fund.getUsableFund().compareTo(nowChargeFee)<0){
            commonEndCharge(record,WalletTypeEnum.STATUS_1.getCode());
            return "0";
        }
        //更新充电金额
        int i =  chargingRecordMapper.update(null,new UpdateWrapper<ChargingRecord>()
        .set("charge_total_money",chargeNum)
                .set("charge_status",1)
        .eq("id",record.getId())
        .ne("charge_status",2));
        if(i==0){
            throw new CommonException("本次充电已经结束！");
        }
        return "1";
    }

    /**
     * IC 卡充电更新金额
     *
     * @param pileNum
     * @param chargeNumStr 充电桩
     * @param icCard //IC 卡号
     */
    @Override
    @Transactional(rollbackFor = {})
    public String updateIcChargeMoney( String pileNum, String chargeNumStr,String icCard) {
        StockUserCapitalFund fund = stockUserCapitalFundService.getOne(new QueryWrapper<StockUserCapitalFund>()
        .eq("stock_code", WalletTypeEnum.STATUS_1.getCode())
        .eq("card_num",icCard));
        if(fund ==null){
            log.info("IC卡错误！");
            return "0";
        }
        //查询充电桩
        ChargingPileInfo pileInfo = chargingPileInfoService.getOne(new QueryWrapper<ChargingPileInfo>()
                .eq("serial_number",pileNum)
                .eq("is_deleted",0));
        if(pileInfo==null){
            log.info("充电桩错误！");
            return "0";
        }
        //充电度数
        BigDecimal chargeNum =new BigDecimal(chargeNumStr).divide(new BigDecimal(10),1);
        //本次充电费用
        BigDecimal  nowChargeFee = chargeNum.multiply(pileInfo.getPrice()).add(chargeNum.multiply(pileInfo.getServiceCharge()));
        if(fund.getUsableFund().compareTo(nowChargeFee)<=0){
            //查询充电记录
            ChargingRecord record = chargingRecordMapper.selectOne(new QueryWrapper<ChargingRecord>()
                    .eq("charging_pile_info_id",pileInfo.getId())
                    .ne("charge_status",2));
            if(record==null){
                log.info("本次充电已经结束！");
                return "0";
            }
            commonEndCharge(record,WalletTypeEnum.STATUS_1.getCode());
            return "0";
        }
        //查询充电记录
        ChargingRecord record = chargingRecordMapper.selectOne(new QueryWrapper<ChargingRecord>()
                .eq("charging_pile_info_id",pileInfo.getId())
                .ne("charge_status",2).last("limit 1"));
        //第一次插枪充电
        if(record==null){
            if(pileInfo.getUseStatus()){
                log.info("该充电桩正在使用！");
                return "0";
            }
            if(pileInfo.getOffLineIs()){
                log.info("该充电桩处于离线状态！");
                return "0";
            }
            //查询充电站是否禁用
            ChargingStations stations  = chargingStationsService.getById(pileInfo.getChargingStationsId());
            if(stations==null){
                log.info("该充电桩分组不正确！");
                return "0";
            }
            if(stations.getIsDisable()){
                log.info("该充电桩分组已经停用！");
                return "0";
            }
            insertCommontChargingRecord(fund.getStockUserId(), pileInfo,stations);
        }else {
            //更新充电金额
            int i =  chargingRecordMapper.update(null,new UpdateWrapper<ChargingRecord>()
                    .set("charge_total_money",chargeNum)
                    .set("charge_status",1)
                    .eq("id",record.getId())
                    .ne("charge_status",2));
            if(i==0){
                throw new CommonException("本次充电已经结束！");
            }
        }
        return "1";
    }
}
