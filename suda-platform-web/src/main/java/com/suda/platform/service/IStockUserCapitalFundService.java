package com.suda.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suda.platform.entity.StockUserCapitalFund;

import java.math.BigDecimal;

/**
 * <p>
 * 个人可提现资产 服务类
 * </p>
 *
 * @author 张龙飞
 * @since 2019-04-16
 */
public interface IStockUserCapitalFundService extends IService<StockUserCapitalFund> {

    /**
     * 查询账户币种资产不存在更新
     * @param id
     * @param stockCode
     * @param agentUserId
     * @return
     */
    StockUserCapitalFund upAndSelectFund(Long id, String stockCode, Long agentUserId);

    /**
     * 根据记录id更新用户资产
     * @param id
     * @param money
     * @return
     */
    int updateRechargeByCodeId( Long id, BigDecimal money);

}
