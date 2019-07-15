package com.suda.platform.controller.app;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.suda.platform.entity.ChargingPileInfo;
import com.suda.platform.entity.ChargingRecord;
import com.suda.platform.entity.StockUserCapitalFund;
import com.suda.platform.enums.finance.WalletTypeEnum;
import com.suda.platform.service.IChargingPileInfoService;
import com.suda.platform.service.IChargingRecordService;
import com.suda.platform.service.IStockUserCapitalFundService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * UART1  串口 通信 接口
 *
 * @author kongling
 * @package com.suda.platform.controller.app
 * @date 2019-06-29  14:34
 * @project charging_pile_cloud
 */
@RestController
@RequestMapping(value = "app/uart1",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Slf4j
public class Uart1CommunicationController {

    @Autowired
    private IChargingRecordService chargingRecordService;
    @Autowired
    private IChargingPileInfoService chargingPileInfoService;
    @Autowired
    private IStockUserCapitalFundService stockUserCapitalFundService;
    @Autowired
    private HttpServletResponse response;

    /**
     * UART1通讯字符串：桩号，桩插枪状态，授权状态，电表度数，卡标志，卡号
     * 最大值：buf="FFFF,1,0,FFFF,1,FFFFFFF" //定长
     * 最小值：buf="0001,1,0,0001,1,0000001"
     * 0 否  1 是
     * @param data 交换数据
     */
    @RequestMapping(value = "exchangeData", method = RequestMethod.POST)
    public void exchangeData(@RequestParam("data") String data){
        String[]  strArr = data.split(",");
        //桩编号
        String pileNum = strArr[0];
        //桩插枪状态 0 否  1 是
        String pileStatus = strArr[1];
        //桩授权充电状态 0 否  1 是
        String  pileActivated = strArr[2];
        //电表度数
        String chargeNumStr = strArr[3];
        String chargeNum = String.valueOf(Integer.parseInt(chargeNumStr,16));
        //是否是 IC 卡充电
        String isIcCard=strArr[4];
        String icCard=null;
        if(Integer.valueOf(isIcCard)==1){
            //IC卡号
            icCard = strArr[5];
        }
        if(Integer.valueOf(pileStatus)==1){
                //更新充电金额
                try{
                    //小程序充电
                    if(Integer.valueOf(isIcCard)==0) {
                        pileActivated =  chargingRecordService.updateChargeMoney(pileNum, chargeNum);
                        //ic 卡充电
                    }else {
                        pileActivated = chargingRecordService.updateIcChargeMoney(pileNum, chargeNum,icCard);
                    }
                }catch (Exception e){
                    //更新充电金额失败
                    log.error("更新充电金额失败，放电失败！："+ ExceptionUtils.getFullStackTrace(e));
                    pileActivated="0";
                }
            //未插枪 结束放电
        }else {
            try{
                endCharge(pileNum,isIcCard);
            }catch (Exception e){
                log.error("结束放电！："+ ExceptionUtils.getFullStackTrace(e));
            }
            pileActivated="0";
        }
        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            if(Integer.valueOf(isIcCard)==1) {
                writer.print(pileNum + "," + pileStatus + "," + pileActivated
                        + "," + chargeNumStr + "," + isIcCard + "," + icCard);
            }else {
                writer.print(pileNum + "," + pileStatus + "," + pileActivated
                        + "," + chargeNumStr + "," + isIcCard);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *  Uart1 通信结束充电
     *
     * @param pileNum
     * @param isIcCard
     */
    void endCharge(String pileNum, String isIcCard){
        ChargingPileInfo pileInfo = chargingPileInfoService.getOne(new QueryWrapper<ChargingPileInfo>()
                .eq("serial_number",pileNum)
                .eq("is_deleted",0));
        if(pileInfo !=null){
            //查询充电记录
            ChargingRecord record = chargingRecordService.getOne(new QueryWrapper<ChargingRecord>()
                    .eq("charging_pile_info_id",pileInfo.getId())
                    .ne("charge_status",2));
            //记录存在，并且是充电中
            if(record!=null && record.getChargeStatus()==1){
                String walletType = WalletTypeEnum.STATUS_1.getCode();
                if(Integer.valueOf(isIcCard)==0){
                    walletType = WalletTypeEnum.STATUS_2.getCode();
                }
                StockUserCapitalFund fund = stockUserCapitalFundService.upAndSelectFund(record.getStockUserId(),
                        walletType,0L);
                if(fund !=null){
                    chargingRecordService.endCharge(record, WalletTypeEnum.STATUS_2.getCode());
                }
            }
        }
    }

    @RequestMapping(value = "exchangeDataTest", method = RequestMethod.POST)
    public  void   y(@RequestParam("data") String data){
        String[]  strArr = data.split(",");
        //分组号
        String grNum = strArr[0];
        //桩编号
        String pileNum = strArr[1];
        //桩插枪状态 0 否  1 是
        String pileStatus = strArr[2];
        //桩授权充电状态 0 否  1 是
        String  pileActivated = strArr[3];
        //电表度数
        String chargeNumStr = strArr[4];
        //IC卡号
        String stockCode = strArr[5];

        try {
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print(grNum+","+pileNum+","+pileStatus+","+pileActivated
                    +","+chargeNumStr+","+stockCode);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *问题描述
    　　从键盘输入一个不超过8位的正的十六进制数字符串，将它转换为正的十进制数后输出。
     　　注：十六进制数中的10~15分别用大写的英文字母A、B、C、D、E、F表示。
     *样例输入
        FFFF
     *样例输出
    65535
*/
    public static void main(String[] args) {
        System.out.println(Integer.parseInt("ffff",16));


    }
}
