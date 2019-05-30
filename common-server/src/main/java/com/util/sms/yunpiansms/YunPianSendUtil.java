package com.util.sms.yunpiansms;


import com.alibaba.fastjson.JSONObject;
import config.advice.CommonException;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yanjiao on 2018/7/30.
 */
public class YunPianSendUtil {
    /**
     * 发送短信
     *
     * @return
     * @throws IOException
     */
    public static YunPianSendBack getYZM(YunPianParam sms) throws IOException {
        //发短信
        String result = "";
        if (sms.getTpl_id() != null) {
            Map<String, String> params = new HashMap<String, String>();
            params.put("apikey", YunPianParam.getApiKey());
            params.put("tpl_id", String.valueOf(sms.getTpl_id()));
            String tpl_value = null;
            try {
                tpl_value = URLEncoder.encode("#code#", YunPianUrlParam.ENCODING) + "=" +
                        URLEncoder.encode(sms.getCode(), YunPianUrlParam.ENCODING);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            params.put("tpl_value", tpl_value);
            params.put("mobile", sms.getMobile());
            result = post(YunPianUrlParam.URI_TPL_SEND_SMS, params);
        } else {
            Map<String, String> params = new HashMap<String, String>();
            params.put("apikey", YunPianParam.getApiKey());
            params.put("text", "您的验证码是" + sms.getCode());
            params.put("mobile", sms.getMobile());
            result = post(YunPianUrlParam.URI_SEND_SMS, params);
        }
        Map<String, Object> requestMap =JSONObject.toJavaObject(JSONObject.parseObject(result),Map.class);
        YunPianSendBack yunPianSendBack = new YunPianSendBack();
        String code = requestMap.get("code").toString();
        if (Integer.valueOf(code) != 0) {
            throw new CommonException(requestMap.get("msg").toString());
        }
        yunPianSendBack.setCode(Integer.valueOf(code));
        String count = requestMap.get("count").toString();
        yunPianSendBack.setCount(Integer.valueOf(count));
        String fee = requestMap.get("fee").toString();
        yunPianSendBack.setFee(Double.valueOf(fee));
        yunPianSendBack.setMobile(requestMap.get("mobile").toString());
        yunPianSendBack.setUnit(requestMap.get("unit").toString());
        String sid = requestMap.get("sid").toString();
        yunPianSendBack.setSid(Long.valueOf(sid));
        return yunPianSendBack;
    }

    /**
     * 付款通知
     *
     * @throws IOException
     */
    public static void confirmPay(String mobile, String code) throws IOException {
        mobile = URLEncoder.encode(mobile, YunPianUrlParam.ENCODING);
        YunPianParam yunPianParam = new YunPianParam();
        yunPianParam.setTpl_id("2416230");
        yunPianParam.setMobile(mobile);
        yunPianParam.setCode(code);
        getYZM(yunPianParam);
    }



    public static void main(String[] args) throws IOException, URISyntaxException {
        //修改为您要发送的手机号
        String mobile = URLEncoder.encode("13015582371", YunPianUrlParam.ENCODING);
        YunPianParam yunPianParam = new YunPianParam();
        yunPianParam.setTpl_id("2416230");
        yunPianParam.setMobile(mobile);
        yunPianParam.setCode("123456000000000000000");
        getYZM(yunPianParam);
    }

    /**
     * 基于HttpClient 4.3的通用POST方法
     *
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return 提交响应
     */

    public static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            //建立HttpPost对象
            HttpPost method = new HttpPost(url);
            //建立一个NameValuePair数组，用于存储欲传送的参数
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            if (paramsMap.get("tpl_id") != null) {
                params.add(new BasicNameValuePair("apikey", paramsMap.get("apikey")));
                params.add(new BasicNameValuePair("tpl_id", paramsMap.get("tpl_id")));
                params.add(new BasicNameValuePair("tpl_value", paramsMap.get("tpl_value")));
                params.add(new BasicNameValuePair("mobile", paramsMap.get("mobile")));
            } else {
                params.add(new BasicNameValuePair("apikey", paramsMap.get("apikey")));
                params.add(new BasicNameValuePair("text", paramsMap.get("text")));
                params.add(new BasicNameValuePair("mobile", paramsMap.get("mobile")));
            }
            method.setEntity(new UrlEncodedFormEntity(params, YunPianUrlParam.ENCODING));
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseText;
    }

    /**
     * 取账户信息
     *
     * @return json格式字符串
     * @throws IOException
     */

    public static String getUserInfo(String apikey) throws IOException, URISyntaxException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        return post(YunPianUrlParam.URI_GET_USER_INFO, params);
    }


}
