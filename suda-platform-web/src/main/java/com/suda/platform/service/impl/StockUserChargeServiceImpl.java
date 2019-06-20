package com.suda.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.constant.CommonConstant;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.pagehelper.PageInfo;
import com.suda.platform.VO.finance.StockUserChargeVO;
import com.suda.platform.entity.StockUser;
import com.suda.platform.entity.StockUserCapitalFund;
import com.suda.platform.entity.StockUserCharge;
import com.suda.platform.enums.finance.FinancialTypeEnum;
import com.suda.platform.enums.finance.PayTypeEnum;
import com.suda.platform.enums.finance.WaterTypeEnum;
import com.suda.platform.enums.finance.WithdrawStatusEnum;
import com.suda.platform.mapper.StockUserChargeMapper;
import com.suda.platform.service.IStockUserCapitalFundService;
import com.suda.platform.service.IStockUserChargeService;
import com.suda.platform.service.IStockUserMoneyDetailService;
import com.util.DealDateUtil;
import com.util.StringUtils;
import com.util.pageinfoutil.PageUtil;
import com.util.request.HttpRequestUtil;
import config.advice.CommonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

    @Autowired
    private IStockUserCapitalFundService stockUserCapitalFundService;

    @Autowired
    private IStockUserMoneyDetailService stockUserMoneyDetailService;

    @Autowired
    WxPayService wxPayService;
    @Autowired
    private HttpServletRequest request;
    /**
     * 充值记录入口
     * @param agentUserId 代理id
     * @param stockUserId 充值用户id
     * @param money 冲入金额
     * @param stockCode //充值卡号
     * @param typeEnum //充值方式
     * @param withdrawStatus //支付状态
     * @param order //订单号
     */
    @Override
    @Async
    public void addChargeRecord(Long agentUserId, Long stockUserId, BigDecimal money, String stockCode, PayTypeEnum typeEnum, WithdrawStatusEnum withdrawStatus, String order) {
        StockUserCharge userCharge = new StockUserCharge();
        userCharge.setAgentUserId(agentUserId);
        userCharge.setCreateTime(DealDateUtil.getNowDate());
        userCharge.setFee(money);
        userCharge.setPayType(typeEnum.getCode());
        userCharge.setStockCode(stockCode);
        userCharge.setStockUserId(stockUserId);
        userCharge.setSwiftNo(order);
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


    @Override
    @Transactional(rollbackFor = {})
    public Object createUserCharge(StockUser stockUser, StockUserCharge charge) throws WxPayException {
        BigDecimal fee = charge.getFee().setScale(CommonConstant.DECIMAL_PLACE, RoundingMode.DOWN);
        WxPayUnifiedOrderRequest orderRequest =new WxPayUnifiedOrderRequest();
        String orderNum = System.currentTimeMillis() + StringUtils.getRandom(9);
        orderRequest.setOpenid(stockUser.getOpenId());
        orderRequest.setOutTradeNo(orderNum);
        orderRequest.setTradeType(WxPayConstants.TradeType.JSAPI);
        orderRequest.setSpbillCreateIp(HttpRequestUtil.getIpAddr(request));
        orderRequest.setTotalFee(BaseWxPayRequest.yuanToFen(fee.toPlainString()));
        orderRequest.setBody("充电付款");
        orderRequest.setNonceStr(String.valueOf(System.currentTimeMillis()));

        StockUserCapitalFund stockUserCapitalFund = stockUserCapitalFundService.getOne(new QueryWrapper<StockUserCapitalFund>()
        .eq("stock_user_id",stockUser.getId()));
        if(stockUserCapitalFund ==null){
            throw  new CommonException("用户未办卡，暂不能充值");
        }
        WxPayMpOrderResult object = wxPayService.createOrder(orderRequest);
        addChargeRecord(null,stockUser.getId(), charge.getFee(),
                stockUserCapitalFund.getStockCode(),PayTypeEnum.STATUS_1, WithdrawStatusEnum.STATUS_4, orderNum);
        return  object;
    }

    /**
     * 付款成功
     *
     * @param stockUserCharge
     */
    @Override
    @Transactional(rollbackFor = {})
    public void withdrawStatusSuccess(StockUserCharge stockUserCharge) {
        stockUserCharge.setWithdrawStatus(WithdrawStatusEnum.STATUS_2.getCode().intValue());
        int b= baseMapper.update(stockUserCharge,new UpdateWrapper<StockUserCharge>()
        .eq("id",stockUserCharge.getId())
        .eq("withdraw_status",WithdrawStatusEnum.STATUS_4.getCode().intValue()));
       if(b>0){
           //更新用户资产
           StockUserCapitalFund stockUserCapitalFund = stockUserCapitalFundService.getOne(new QueryWrapper<StockUserCapitalFund>()
                   .eq("stock_user_id",stockUserCharge.getStockUserId()));

            //更新账户资产
           int j =  stockUserCapitalFundService.updateRechargeByCodeId(stockUserCapitalFund.getId(),stockUserCharge.getFee());
           //资产流水
           if(j>0) {
               stockUserMoneyDetailService.addUserMoneyDetail(
                       stockUserCapitalFund.getStockUserId(),
                       stockUserCharge.getFee(),
                       stockUserCapitalFund.getUsableFund(),
                       WaterTypeEnum.STATUS_2.getCode(),
                       FinancialTypeEnum.TYPE_2, "用户微信充值",
                       stockUserCharge.getId(),
                       stockUserCapitalFund.getStockCode());
           }
       }
    }
}
