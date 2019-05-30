package com.util.sms.alisms;

import com.util.PropertiesUtil;

import java.io.IOException;

/**
 * 短信模版读取工具类
 *
 * @author 卫星
 */
public class SmsTemplate {

    /**
     * 登录验证短信模版
     *
     * @param tel
     * @param code
     * @throws IOException
     */
    public static AliParam getAliLogin(String code, String tel) throws IOException {
        AliParam ali = new AliParam();
        ali.setCode(code);
        ali.setTel(tel);
        ali.setTemplateCode(PropertiesUtil.readProperties("TemplateCode"));
        ali.setTemplateParam(PropertiesUtil.readProperties("TemplateParam"));
        return ali;

    }
}
