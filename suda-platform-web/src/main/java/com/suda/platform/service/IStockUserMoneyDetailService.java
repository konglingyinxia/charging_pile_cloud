package com.suda.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.finance.StockUserMoneyDetailAppVO;
import com.suda.platform.VO.finance.StockUserMoneyDetailVO;
import com.suda.platform.entity.StockUserMoneyDetail;
import com.suda.platform.enums.finance.FinancialTypeEnum;
import com.util.pageinfoutil.PageUtil;

import java.math.BigDecimal;

/**
 * <p>
 * 用户资金明细表 服务类
 * </p>
 *
 * @author kongling
 * @since 2019-05-07
 */
public interface IStockUserMoneyDetailService extends IService<StockUserMoneyDetail> {

    /**
     * 资金流水
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
    int addUserMoneyDetail(Long stockUserId, BigDecimal money, BigDecimal moneyBefore, Byte waterType, FinancialTypeEnum type, String massage, Long typeId, String stockCode);

    /**
     * 后台查询资产流水
     * @param vo
     * @param pageUtil
     * @return
     */
    PageInfo<StockUserMoneyDetailVO> getAdminMoneyDetails(StockUserMoneyDetailVO vo, PageUtil pageUtil);

    /**
     * app端查询资金流水
     * @param vo
     * @param pageUtil
     * @return
     */
    PageInfo<StockUserMoneyDetailAppVO> getAppMoneyDetails(StockUserMoneyDetailAppVO vo, PageUtil pageUtil);
}
