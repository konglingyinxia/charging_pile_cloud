package com.util.sms.lexinsms;


import com.util.MathUtil;
import com.util.PropertiesUtil;

/**
 * 乐信短信
 */
public class LeXinParam {
    private String accName = PropertiesUtil.readProperties("accName");
    private String accPwd = MathUtil.getMD5(PropertiesUtil.readProperties("accPwd")).toUpperCase();
    //签名验证
    private String sign = PropertiesUtil.readProperties("leXin_sign");
    //请求接口
    private String sendAddr = PropertiesUtil.readProperties("leXin_sendAddr");
    //消息内容
    private String content = PropertiesUtil.readProperties("leXin_content");

    private String aimcodes;
    private String dataType = "json";

    public String getContent() {
        return content;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public void setAccPwd(String accPwd) {
        this.accPwd = accPwd;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAccName() {
        return accName;
    }

    public String getAccPwd() {
        return accPwd;
    }

    public String getAimcodes() {
        return aimcodes;
    }

    public void setAimcodes(String aimcodes) {
        this.aimcodes = aimcodes;
    }


    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public String toString() {
        return "LeXinParam{" +
                "aimcodes='" + aimcodes + '\'' +
                ", content='" + content + '\'' +
                ", dataType='" + dataType + '\'' +
                '}';
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getSendAddr() {
        return sendAddr;
    }

    public void setSendAddr(String sendAddr) {
        this.sendAddr = sendAddr;
    }
}
