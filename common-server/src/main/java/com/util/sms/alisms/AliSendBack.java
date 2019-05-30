package com.util.sms.alisms;

/**
 * 阿里云短信接口回调
 *
 * @author 卫星
 */
public class AliSendBack {

    private String Code;
    private String Message;
    private String RequestId;
    private String BizId;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public String getBizId() {
        return BizId;
    }

    public void setBizId(String bizId) {
        BizId = bizId;
    }

}
