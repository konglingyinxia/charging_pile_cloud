package com.util.sms.networdbuild;

/**
 * @author 国威
 * @package com.sms.networdbuild
 * @date 2018-11-02  14:49
 * @project yucaishu
 */
public class NetWordBuildParam {

    //apikey
    private static String apiKey = NetWordBulidConfig.chianToBuildKeyId;
    //发送手机号
    private String mobile;
    //设置您要发送的内容
    private String text;
    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = NetWordBulidConfig.ENCODING;
    private String code;

    public static String getApiKey() {
        return apiKey;
    }

    public static void setApiKey(String apiKey) {
        NetWordBuildParam.apiKey = apiKey;
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

    public static String getENCODING() {
        return ENCODING;
    }

    public static void setENCODING(String ENCODING) {
        NetWordBuildParam.ENCODING = ENCODING;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
