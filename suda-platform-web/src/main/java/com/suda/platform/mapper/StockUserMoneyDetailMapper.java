package com.suda.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.suda.platform.VO.finance.StockUserMoneyDetailAppVO;
import com.suda.platform.VO.finance.StockUserMoneyDetailVO;
import com.suda.platform.entity.StockUserMoneyDetail;

import java.util.List;

/**
 * <p>
 * 用户资金明细表 Mapper 接口
 * </p>
 *
 * @author kongling
 * @since 2019-05-07
 */
public interface StockUserMoneyDetailMapper extends BaseMapper<StockUserMoneyDetail> {

    /**
     * 后台查询资产流水
     *
     * @param vo
     * @return
     */
    List<StockUserMoneyDetailVO> getAdminMoneyDetails(StockUserMoneyDetailVO vo);

    /**
     * app端查询资金流水
     *
     * @param vo
     * @return
     */
    List<StockUserMoneyDetailAppVO> getAppMoneyDetails(StockUserMoneyDetailAppVO vo);
}
