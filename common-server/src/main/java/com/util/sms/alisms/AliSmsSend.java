package com.util.sms.alisms;
/**
 * 阿里云下发短信
 */

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;

import java.io.IOException;

/**
 * @author zhengliangzhang
 */
public class AliSmsSend {
    /**
     * 发送短信
     *
     * @param ali
     * @return
     * @throws IOException
     * @throws ClientException
     */
    public static AliSendBack getYZM(AliParam ali) throws IOException, ClientException {
        //发短信
        SendSmsResponse response = AliSmsUtil.sendSms(ali);
        AliSendBack sms = new AliSendBack();
        sms.setCode(response.getCode());
        sms.setMessage(response.getMessage());
        sms.setBizId(response.getBizId());
        sms.setRequestId(response.getRequestId());
        return sms;
    }
}
