package com.util.sms.yunpiansms;


import com.util.PropertiesUtil;

/**
 * 云片短信参数实体类
 * Created by yanjiao on 2018/7/30.
 */
public class YunPianParam {
    //apikey
    private static String apiKey = PropertiesUtil.readProperties("YUN_PIAN_APIKEY");
    //发送手机号
    private String mobile;
    //设置您要发送的内容
    private String text;
    //模板ID
    private String tpl_id;
    //模板拼接值
    private String tpl_value;
    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";
    private String code;

    public static String getApiKey() {
        return apiKey;
    }

    public static void setApiKey(String apiKey) {
        YunPianParam.apiKey = apiKey;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTpl_id() {
        return tpl_id;
    }

    public void setTpl_id(String tpl_id) {
        this.tpl_id = tpl_id;
    }

    public String getTpl_value() {
        return tpl_value;
    }

    public void setTpl_value(String tpl_value) {
        this.tpl_value = tpl_value;
    }

    public static String getENCODING() {
        return ENCODING;
    }

    public static void setENCODING(String ENCODING) {
        YunPianParam.ENCODING = ENCODING;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
