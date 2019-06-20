package com.suda.platform.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.finance.StockUserChargeVO;
import com.suda.platform.entity.StockUser;
import com.suda.platform.entity.StockUserCharge;
import com.suda.platform.enums.finance.PayTypeEnum;
import com.suda.platform.enums.finance.WithdrawStatusEnum;
import com.util.pageinfoutil.PageUtil;

import java.math.BigDecimal;

/**
 * <p>
 * 用户出入金表 服务类
 * </p>
 *
 * @author kongling
 * @since 2019-06-12
 */
public interface IStockUserChargeService extends IService<StockUserCharge> {

    /**
     * 后台充值记录
     * @param agentUserId 代理id
     * @param stockUserId 充值用户id
     * @param money 冲入金额
     * @param stockCode //充值卡号
     * @param typeEnum //充值方式
     * @param withdrawStatus //支付状态
     * @param order //订单号
     */
    void addChargeRecord(Long agentUserId, Long stockUserId, BigDecimal money, String stockCode, PayTypeEnum typeEnum, WithdrawStatusEnum withdrawStatus, String order);

    /**
     * 查询用户充值充值记录
     *
     * @param coinCharge
     * @param pageUtil
     * @return
     */
    PageInfo<StockUserChargeVO> getAdminStockUserCoinCharges(StockUserChargeVO coinCharge, PageUtil pageUtil);

    /**
     * 用户充值创建订单
     * @param stockUser
     * @param charge
     * @return
     */
    Object createUserCharge(StockUser stockUser, StockUserCharge charge) throws WxPayException;

    /**
     * 付款成功
     *
     * @param stockUserCharge
     */
    void withdrawStatusSuccess(StockUserCharge stockUserCharge);
}
