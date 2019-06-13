package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.finance.StockUserChargeVO;
import com.suda.platform.entity.StockUserCharge;
import com.suda.platform.enums.finance.PayTypeEnum;
import com.suda.platform.enums.finance.WithdrawStatusEnum;
import com.suda.platform.mapper.StockUserChargeMapper;
import com.suda.platform.service.IStockUserChargeService;
import com.util.DealDateUtil;
import com.util.StringUtils;
import com.util.pageinfoutil.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 用户出入金表 服务实现类
 * </p>
 *
 * @author kongling
 * @since 2019-06-12
 */
@Service
public class StockUserChargeServiceImpl extends ServiceImpl<StockUserChargeMapper, StockUserCharge> implements IStockUserChargeService {

    @Autowired
    private StockUserChargeMapper stockUserChargeMapper;
    /**
     * 充值记录入口
     * @param agentUserId 代理id
     * @param stockUserId 充值用户id
     * @param money 冲入金额
     * @param stockCode //充值卡号
     * @param typeEnum //充值方式
     * @param withdrawStatus //支付状态
     */
    @Override
    @Async
    public void addChargeRecord(Long agentUserId, Long stockUserId, BigDecimal money, String stockCode, PayTypeEnum typeEnum, WithdrawStatusEnum withdrawStatus) {
        StockUserCharge userCharge = new StockUserCharge();
        userCharge.setAgentUserId(agentUserId);
        userCharge.setCreateTime(DealDateUtil.getNowDate());
        userCharge.setFee(money);
        userCharge.setPayType(typeEnum.getCode());
        userCharge.setStockCode(stockCode);
        userCharge.setStockUserId(stockUserId);
        userCharge.setSwiftNo(stockUserId+System.currentTimeMillis() + StringUtils.getRandom(9));
        userCharge.setWithdrawStatus(withdrawStatus.getCode().intValue());
        baseMapper.insert(userCharge);
    }

    /**
     *
     * @param coinCharge
     * @param pageUtil
     * @return
     */

    @Override
    public PageInfo<StockUserChargeVO> getAdminStockUserCoinCharges(StockUserChargeVO coinCharge, PageUtil pageUtil) {
        PageUtil.page(pageUtil);
        List<StockUserChargeVO> lists = stockUserChargeMapper.getAdminStockUserCoinCharges(coinCharge);
        return new PageInfo<>(lists);
    }
}
