package com.util.httpUtil;

import org.apache.commons.io.IOUtils;

import javax.net.ssl.*;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * 过滤https请求证书验证
 * @author Administrator
 *
 */

public class SSLUtil {
    private static void trustAllHttpsCertificates() throws Exception {  
        TrustManager[] trustAllCerts = new TrustManager[1];  
        TrustManager tm = new miTM();  
        trustAllCerts[0] = tm;  
        SSLContext sc = SSLContext.getInstance("SSL");  
        sc.init(null, trustAllCerts, null);  
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());  
    }  
      
    static class miTM implements TrustManager,X509TrustManager {  
        public X509Certificate[] getAcceptedIssuers() {  
            return null;  
        }  
      
        public boolean isServerTrusted(X509Certificate[] certs) {  
            return true;  
        }  
      
        public boolean isClientTrusted(X509Certificate[] certs) {  
            return true;  
        }  
      
        public void checkServerTrusted(X509Certificate[] certs, String authType)  
                throws CertificateException {  
            return;  
        }  
      
        public void checkClientTrusted(X509Certificate[] certs, String authType)  
                throws CertificateException {  
            return;  
        }  
    }  
       
    /** 
     * 忽略HTTPS请求的SSL证书，必须在openConnection之前调用 
     * @throws Exception 
     */  
    public static void ignoreSsl() throws Exception{  
        HostnameVerifier hv = new HostnameVerifier() {  
            public boolean verify(String urlHostName, SSLSession session) {  
                System.out.println("Warning: URL Host: " + urlHostName + " vs. " + session.getPeerHost());  
                return true;  
            }
        };  
        trustAllHttpsCertificates();  
        HttpsURLConnection.setDefaultHostnameVerifier(hv);  
    } 
    
    public String getRequest(String url,int timeOut) throws Exception{  
        URL u = new URL(url);  
        if("https".equalsIgnoreCase(u.getProtocol())){  
            SSLUtil.ignoreSsl();  
        }  
        URLConnection conn = u.openConnection();  
        conn.setConnectTimeout(timeOut);  
        conn.setReadTimeout(timeOut);  
        return IOUtils.toString(conn.getInputStream());  
    }  
       
    public String postRequest(String urlAddress,String args,int timeOut) throws Exception{  
        URL url = new URL(urlAddress);  
        if("https".equalsIgnoreCase(url.getProtocol())){  
            SSLUtil.ignoreSsl();  
        }  
        URLConnection u = url.openConnection();  
        u.setDoInput(true);  
        u.setDoOutput(true);  
        u.setConnectTimeout(timeOut);  
        u.setReadTimeout(timeOut);  
        OutputStreamWriter osw = new OutputStreamWriter(u.getOutputStream(), "UTF-8");  
        osw.write(args);  
        osw.flush();  
        osw.close();  
        u.getOutputStream();  
        return IOUtils.toString(u.getInputStream());  
    }  
    //test
    public static void main(String[] args) {  
        try {  
            SSLUtil st = new SSLUtil();  
            String a = st.getRequest("https://gateway.gopay.com.cn/time.do", 3000);  
            System.out.println(a);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
    
    
    
    
    
    
    
    
    
    
}





