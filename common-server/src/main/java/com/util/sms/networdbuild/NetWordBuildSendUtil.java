package com.util.sms.networdbuild;

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
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 中国网建
 *
 * @author 国威
 * @package com.sms.networdbuild
 * @date 2018-11-02  14:56
 * @project yucaishu
 */
public class NetWordBuildSendUtil {

    /**
     * 发送短信
     *
     * @return
     * @throws IOException
     */
    public static String send(NetWordBuildParam sms) throws IOException {
        //发短信
        String result = "";
        Map<String, String> params = new HashMap<String, String>();
        params.put("Uid", URLEncoder.encode(NetWordBulidConfig.chianToBuildKeyId,NetWordBulidConfig.ENCODING));
        params.put("smsText", "您的验证码是" + sms.getCode() + ",5分钟内有效，谨防泄露！");
        params.put("smsMob", sms.getMobile());
        result = post(NetWordBulidConfig.URI_TPL_SEND_SMS, params);
        return result;
    }

    /**
     * 付款通知
     *
     * @throws IOException
     */
    public static void confirmPay(String tel, String code) throws IOException {
        //发短信
        String result = "";
        Map<String, String> params = new HashMap<String, String>();
        params.put("Uid", NetWordBulidConfig.chianToBuildKeyId);
        params.put("smsText", "你的付款参考号为：" + code + "的订单买家已确认付款，请及时查看！");
        params.put("smsMob", tel);
        result = post(NetWordBulidConfig.URI_TPL_SEND_SMS, params);
    }

    public static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            //建立HttpPost对象
            HttpPost method = new HttpPost(url);
            //建立一个NameValuePair数组，用于存储欲传送的参数
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            params.add(new BasicNameValuePair("Uid", "中国龙6"));
            params.add(new BasicNameValuePair("Key", NetWordBulidConfig.chianToBuildKeySecret));
            params.add(new BasicNameValuePair("smsMob", paramsMap.get("smsMob")));
            params.add(new BasicNameValuePair("smsText", paramsMap.get("smsText")));

            method.setEntity(new UrlEncodedFormEntity(params, NetWordBulidConfig.ENCODING));
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
                System.out.println(responseText);
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


}
