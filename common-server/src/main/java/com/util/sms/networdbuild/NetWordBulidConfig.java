package com.util.sms.networdbuild;


/**
 * 中国网建配置信息
 *
 * @author 国威
 * @package com.sms.networdbuild
 * @date 2018-11-02  16:45
 * @project yucaishu
 */
public class NetWordBulidConfig {
    //模板发送接口的http地址
    public static String URI_TPL_SEND_SMS = "http://utf8.api.smschinese.cn";

    //编码格式。发送编码格式统一用UTF-8
    public static String ENCODING = "UTF-8";

    public static String chianToBuildKeyId =""; //PropertiesUtil.readProperties("chianToBuildKeyId");
    public static String chianToBuildKeySecret = "";//PropertiesUtil.readProperties("chianToBuildKeySecret");


}
