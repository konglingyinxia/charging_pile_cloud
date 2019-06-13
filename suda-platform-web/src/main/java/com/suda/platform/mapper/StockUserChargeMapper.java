package com.suda.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suda.platform.VO.finance.StockUserChargeVO;
import com.suda.platform.entity.StockUserCharge;

import java.util.List;

/**
 * <p>
 * 用户出入金表 Mapper 接口
 * </p>
 *
 * @author kongling
 * @since 2019-06-12
 */
public interface StockUserChargeMapper extends BaseMapper<StockUserCharge> {

    /**
     * 查询用户充值记录
     *
     * @param coinCharge
     * @return
     */
    List<StockUserChargeVO> getAdminStockUserCoinCharges(StockUserChargeVO coinCharge);
}
