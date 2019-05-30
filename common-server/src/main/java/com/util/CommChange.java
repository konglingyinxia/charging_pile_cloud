package com.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.servlet.http.HttpServletRequest;
import java.beans.PropertyDescriptor;
import java.net.URLDecoder;
import java.util.*;

/**
 * 工具转化类
 * 
 * @author 卫星
 *
 */
public class CommChange {

    /**
     * 将javabean实体类转为map类型，然后返回一个map类型的值
     * 
     * @param obj
     * @return
     */
    public static SortedMap<String, String> beanToMap(Object obj) {
        SortedMap<String, String> params = new TreeMap<String, String>();
        try {
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
            for (int i = 0; i < descriptors.length; i++) {
                String name = descriptors[i].getName();
                if (!"class".equals(name)) {
                    params.put(name,
                            (String) (propertyUtilsBean.getNestedProperty(obj, name) == null ? null
                                    : propertyUtilsBean.getNestedProperty(obj, name)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }

    /**
     * 解析request请求参数
     * 
     * @param request
     * @return
     */
    public static Map<String, String> showParams(HttpServletRequest request) {
        Map map = new HashMap();
        Enumeration paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();

            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                if (paramValue.length() != 0) {
                    map.put(paramName, paramValue);
                }
            }
        }
        return map;
    }

    /**
     * 请求参数解析map
     * 
     * @param url
     * @return
     */
    public static Map<String, String> getParameter(String url) {
        Map<String,String> map = new HashMap<String, String>();
        try {
            final String charset = "utf-8";
            url = URLDecoder.decode(url, charset);
            String[] keyValues = url.split("&");
            for (int i = 0; i < keyValues.length; i++) {
                String key = keyValues[i].substring(0, keyValues[i].indexOf("="));
                String value = keyValues[i].substring(keyValues[i].indexOf("=") + 1);
                map.put(key, value);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
    /**
     * 服务器权限调用户查询
     * @return
     */
    public static boolean verified(String netUrl) {
        boolean verified = false;
        //创建默认的httpClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            //xunying.com
            //用get方法发送http请求
            HttpGet get = new HttpGet(
                    "http://pms.wjclouds.com/index.php?g=&m=Public&a=verify_validity&host="+netUrl);
            CloseableHttpResponse httpResponse = null;
            //发送get请求
            httpResponse = httpClient.execute(get);
            try {
                //response实体
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity) {
                    JSONObject obj = JSONObject.parseObject(EntityUtils.toString(entity));
                    if (obj.containsKey("err") && 0 == obj.getInteger("err")) {
                        verified = true;
                    }
                }
            } finally {
                httpResponse.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HttpClientUtils.closeQuietly(httpClient);
        }
        return verified;
    }
    public static void main(String[] args) {
      System.out.println(verified("url"));  ;
    }

   

}
