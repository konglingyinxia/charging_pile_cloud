package com.suda.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.suda.platform.entity.StockUserCapitalFund;

import java.math.BigDecimal;
import java.util.List;

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
     * @return
     */
    StockUserCapitalFund upAndSelectFund(Long id, String stockCode);

    /**
     * 更新账户资产
     * @param stockCode
     * @param id
     * @param money
     * @return
     */
    int updateRechargeByCodeId(String stockCode, Long id, BigDecimal money);

    /**
     * 获取资产列表信息
     *
     * @param id
     * @param stockCode
     * @return
     */
    List<StockUserCapitalFund> getStockUserCapitalFundS(Long id, String stockCode);


}
