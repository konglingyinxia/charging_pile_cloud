package com.util.sms.lexinsms;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.exceptions.ClientException;
import com.util.CommChange;
import com.util.httpUtil.HttpClientUtil;

import java.io.IOException;

public class LeXinSmsSend {
    /**
     * 发送短信
     *
     * @return
     * @throws IOException
     * @throws ClientException
     */
    public static LeXinSendBack getYZM(LeXinParam sms) throws Exception {
        sms.setContent(sms.getContent() + sms.getSign());
        //发短信
        String responseStr = HttpClientUtil.post(sms.getSendAddr(), CommChange.beanToMap(sms));
        LeXinSendBack sendBack = JSON.parseObject(responseStr, LeXinSendBack.class);
        return sendBack;
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        LeXinParam param = new LeXinParam();
        param.setAimcodes("18538051176");
        param.setContent(String.format("您的验证码是：%s,防止泄露！", "123456"));
        try {
            getYZM(param);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
