package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.suda.platform.entity.StockUserCapitalFund;
import com.suda.platform.mapper.StockUserCapitalFundMapper;
import com.suda.platform.service.ICommonService;
import com.suda.platform.service.IStockUserCapitalFundService;
import com.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 个人可提现资产 服务实现类
 * </p>
 *
 * @author 张龙飞
 * @since 2019-04-16
 */
@Service
@Slf4j
public class StockUserCapitalFundServiceImpl extends ServiceImpl<StockUserCapitalFundMapper, StockUserCapitalFund> implements IStockUserCapitalFundService {

    @Autowired
    private StockUserCapitalFundMapper  stockUserCapitalFundMapper;

    @Autowired
    private ICommonService commonService;


    /**
     * 查询用户该币种资产 不存在则更新
     * @param id
     * @param stockCode
     * @param agentUserId
     * @return
     */
    @Override
    public StockUserCapitalFund upAndSelectFund(Long id, String stockCode, Long agentUserId) {
        StockUserCapitalFund stockUserCapitalFund = baseMapper.selectOne(new QueryWrapper<StockUserCapitalFund>()
        .eq("stock_user_id",id).eq("stock_code",stockCode));
        if(stockUserCapitalFund ==null){
            stockUserCapitalFund = new StockUserCapitalFund();
            stockUserCapitalFund.setCreateTime(new Date());
            stockUserCapitalFund.setStockUserId(id);
            stockUserCapitalFund.setStockCode(stockCode);
            stockUserCapitalFund.setUsableFund(BigDecimal.ZERO);
            stockUserCapitalFund.setAgentUserId(agentUserId);
            baseMapper.insert(stockUserCapitalFund);
        }
        return stockUserCapitalFund;
    }



    /**
     * 更新账户资产
     * @param stockCode
     * @param stockUserId
     * @param money
     */

    @Override
    public int updateRechargeByCodeId(String stockCode, Long stockUserId, BigDecimal money) {
     int  num =   stockUserCapitalFundMapper.updateRechargeByCodeId(stockCode ,stockUserId ,money) ;
     return  num;
    }

    /**
     * 获取用户资产
     * @param id
     * @param stockCode 币种code
     * @return
     */
    @Override
    @Transactional(rollbackFor = {})
    public StockUserCapitalFund getStockUserCapitalFundS(Long id, String stockCode) {
        StockUserCapitalFund stockUserCapitalFund = baseMapper.selectOne(new QueryWrapper<StockUserCapitalFund>()
                .eq("stock_user_id",id).eq(StringUtils.isNotBlank(stockCode),"stock_code",stockCode));

        return stockUserCapitalFund;
    }
}
