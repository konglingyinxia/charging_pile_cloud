package com.suda.platform.controller.app;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.suda.platform.VO.stockuser.StockUserParamsVO;
import com.suda.platform.entity.StockUser;
import com.suda.platform.entity.StockUserCharge;
import com.suda.platform.enums.finance.WithdrawStatusEnum;
import com.suda.platform.service.IStockUserChargeService;
import com.suda.platform.service.IStockUserService;
import com.util.Respons.ResponseMsg;
import com.util.Respons.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 用户充值操作
 * @author kongling
 * @package com.suda.platform.controller.app
 * @date 2019-06-17  11:21
 * @project charging_pile_cloud
 */
@RestController
@RequestMapping(value = "app/operation",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
public class AppWxPayController {


    @Autowired
    WxPayService wxPayService;
    @Autowired
    private IStockUserService stockUserService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IStockUserChargeService stockUserChargeService;

    /**
     * 用户小程序充值
     */
    @RequestMapping(value = "wxAppPay", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> wxPay(StockUserParamsVO paramsVO, StockUserCharge charge) throws WxPayException {
        if(paramsVO.getStockUserId() == null||charge.getFee()==null){
            return ResponseUtil.getNotNormalMap(ResponseMsg.ERROR_PARAM);
        }
        StockUser stockUser = stockUserService.getById(paramsVO.getStockUserId());
        if(stockUser == null){
            return ResponseUtil.getNotNormalMap(ResponseMsg.NOUSER);
        }
        Object result= stockUserChargeService.createUserCharge(stockUser,charge);

        return ResponseUtil.getSuccessMap(result);
    }




    /**
     * 微信充值回调
     */
    @ResponseBody
    @RequestMapping("/wxBack")
    public String payNotify(HttpServletRequest request, HttpServletResponse response) {
        try {
            String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            WxPayOrderNotifyResult result = wxPayService.parseOrderNotifyResult(xmlResult);
            // 结果正确
            String orderId = result.getOutTradeNo();
            String tradeNo = result.getTransactionId();
            //自己处理订单的业务逻辑，需要判断订单是否已经支付过，否则可能会重复调用
            StockUserCharge stockUserCharge = stockUserChargeService.getOne(new QueryWrapper<StockUserCharge>()
            .eq("swift_no",orderId));
            if(stockUserCharge.getWithdrawStatus() == WithdrawStatusEnum.STATUS_4.getCode().byteValue()){
               //更新付款状态 用户付款成功
                stockUserCharge.setTranId(tradeNo);
                stockUserChargeService.withdrawStatusSuccess(stockUserCharge);
            }
            return WxPayNotifyResponse.success("处理成功!");
        } catch (Exception e) {
            log.error("微信回调结果异常,异常原因{}", e.getMessage());
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }

}
