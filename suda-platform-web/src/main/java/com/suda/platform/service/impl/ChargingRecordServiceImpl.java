package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.constant.CommonConstant;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.chargeStation.ChargingRecordVO;
import com.suda.platform.VO.finance.StockUserMoneyDetailAppVO;
import com.suda.platform.VO.finance.StockUserMoneyDetailVO;
import com.suda.platform.entity.ChargingRecord;
import com.suda.platform.entity.StockUserMoneyDetail;
import com.suda.platform.enums.finance.FinancialTypeEnum;
import com.suda.platform.enums.finance.IncomeEnum;
import com.suda.platform.mapper.ChargingRecordMapper;
import com.suda.platform.mapper.StockUserMoneyDetailMapper;
import com.suda.platform.service.IChargingRecordService;
import com.suda.platform.service.IStockUserMoneyDetailService;
import com.util.DealDateUtil;
import com.util.pageinfoutil.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    @Override
    public PageInfo<ChargingRecordVO> selectAllChargingRecords(ChargingRecordVO vo, PageUtil pageUtil) {
        PageUtil.page(pageUtil);
        List<ChargingRecordVO> list = chargingRecordMapper.selectAllChargingRecords(vo);
        return new PageInfo<>(list);
    }

    /**
     * <p>
     * 用户资金明细表 服务实现类
     * </p>
     *
     * @author kongling
     * @since 2019-05-07
     */
    @Service
    @Slf4j
    public static class StockUserMoneyDetailServiceImpl extends ServiceImpl<StockUserMoneyDetailMapper, StockUserMoneyDetail> implements IStockUserMoneyDetailService {

        @Autowired
        private StockUserMoneyDetailMapper stockUserMoneyDetailMapper;
        /**
         * 用户流水
         * @param stockUserId 用户id
         * @param money 变化金额
         * @param moneyBefore 变化前金额
         * @param waterType 流水类型（1：后台控制 2：正常流水）
         * @param type 资金类型
         * @param massage 信息详情
         * @param typeId  来源表id
         * @param stockCode 币种
         * @return
         */
        @Override
        public int addUserMoneyDetail(Long stockUserId, BigDecimal money, BigDecimal moneyBefore, Byte waterType, FinancialTypeEnum type, String massage, Long typeId, String stockCode) {
            BigDecimal moneyAfter = money.add(moneyBefore).setScale(CommonConstant.DECIMAL_PLACE, RoundingMode.DOWN);
            StockUserMoneyDetail moneyDetail = new StockUserMoneyDetail();
            moneyDetail.setCreateTime(DealDateUtil.getNowDate());
            moneyDetail.setDetail(massage);
            moneyDetail.setIsDeleted(false);
            moneyDetail.setMoney(money);
            moneyDetail.setMoneyBefore(moneyBefore);
            moneyDetail.setStockCode(stockCode);
            moneyDetail.setStockUserId(stockUserId);
            moneyDetail.setWaterType(waterType);
            moneyDetail.setMoneyAfter(moneyAfter);
            moneyDetail.setType(type.getCode());
            if(money.compareTo(BigDecimal.ZERO)>0){
                moneyDetail.setIncome(IncomeEnum.STATUS_1.getCode());
            }else{
                moneyDetail.setIncome(IncomeEnum.STATUS_0.getCode());
            }
            moneyDetail.setTypeId(typeId);
            int i=baseMapper.insert(moneyDetail);
            return i;
        }

        /**
         * 后台查询资产流水
         * @param vo
         * @param pageUtil
         * @return
         */

        @Override
        public PageInfo<StockUserMoneyDetailVO> getAdminMoneyDetails(StockUserMoneyDetailVO vo, PageUtil pageUtil) {
            PageUtil.page(pageUtil);
            List<StockUserMoneyDetailVO>  lists = stockUserMoneyDetailMapper.getAdminMoneyDetails(vo);
            return new PageInfo<>(lists);
        }

        /**
         * app端查询资金流水
         *
         * @param vo
         * @param pageUtil
         * @return
         */
        @Override
        public PageInfo<StockUserMoneyDetailAppVO> getAppMoneyDetails(StockUserMoneyDetailAppVO vo, PageUtil pageUtil) {
            PageUtil.page(pageUtil);
            List<StockUserMoneyDetailAppVO>  lists = stockUserMoneyDetailMapper.getAppMoneyDetails(vo);
            return new PageInfo<>(lists);
        }
    }
}
