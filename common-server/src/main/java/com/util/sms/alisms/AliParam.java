package com.util.sms.alisms;


import com.util.PropertiesUtil;

/**
 * 短信参数实体类
 *
 * @author 卫星
 */
public class AliParam {

    private static String accessKeyId = PropertiesUtil.readProperties("accessKeyId");
    private static String accessKeySecret =PropertiesUtil.readProperties("accessKeySecret");
    private static String signName =PropertiesUtil.readProperties("signName");
    private String TemplateCode;
    private String TemplateParam;
    private String tel;
    private String code;


    public AliParam() {
        super();
    }

    public AliParam(String templateCode, String templateParam, String tel,
                    String code) {
        super();
        TemplateCode = templateCode;
        TemplateParam = templateParam;
        this.tel = tel;
        this.code = code;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public String getSignName() {
        return signName;
    }

    public String getTemplateCode() {
        return TemplateCode;
    }

    public void setTemplateCode(String templateCode) {
        TemplateCode = templateCode;
    }

    public String getTemplateParam() {
        return TemplateParam;
    }

    public void setTemplateParam(String templateParam) {
        TemplateParam = templateParam;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public static void main(String[] args) {

        System.out.println(new AliParam().getAccessKeyId());
    }
}
