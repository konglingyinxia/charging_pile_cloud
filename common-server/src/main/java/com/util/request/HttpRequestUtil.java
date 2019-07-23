package com.util.request;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Description:
 * @Auther: 康文
 * @Date: 2019-03-29 14:54
 * @Version:
 */
@Slf4j
public class HttpRequestUtil {

    /***
     * 判断一个请求是否为AJAX请求,是则返回true
     * @param request
     * @return
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        //如果requestType能拿到值，并且值为 XMLHttpRequest ,表示客户端的请求为异步请求，那自然是ajax请求了，反之如果为null,则是普通的请求
        if (requestType == null) {
            return false;
        }
        return true;

    }

    /**
     * 获取IP地址
     *
     * @param request
     * @return
     */

    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 获取请求路径
     * @return
     */

    public static String getReqHttpAndHttpsPath() {
        HttpServletRequest request =getHttpServletRequest();
        String reqUrl = "";
        //获取服务器名，localhost；
        String serverName = request.getServerName();
        //获取服务器端口号，8080；
        Integer serverPort = request.getServerPort();
        reqUrl = "http://" + serverName + ":" + serverPort;
        return reqUrl;
    }

    /**
     * 获取请求 request
     * @return
     */
    public  static  HttpServletRequest  getHttpServletRequest(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    /**
     * 获取系统设备类型信息
     * @param request
     * @return
     */
    public  static  String  getSystemDevice(HttpServletRequest request){
        String info = "";
        try{
            Browser browser = UserAgent.parseUserAgentString(request.getHeader("User-Agent")).getBrowser();
            //获取浏览器版本号
            Version version = browser.getVersion(request.getHeader("User-Agent"));
            info = browser.getName() + "/" + version.getVersion();
        }catch (Exception e){
            log.info("获取系统设备信息失败："+ ExceptionUtils.getStackTrace(e));
        }
        return info;
    }


    /**
     * 获取request 字节流
     */

    public  static String  getRequestInputStream(HttpServletRequest request,String encoding){
        InputStream inputStream;
        String str = "";
        // 获得响应流，获得输入对象
        try {
            request.setCharacterEncoding(encoding);

            inputStream = request.getInputStream();
            // 建立接收流缓冲，准备处理
            StringBuffer requestBuffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, encoding));
            // 读入流，并转换成字符串
            String readLine;
            while ((readLine = reader.readLine()) != null) {
                requestBuffer.append(readLine);
            }
            reader.close();
            str = StringUtils.trim(requestBuffer.toString());
        } catch (Exception e) {
             log.error("接收同步消息失败"+e);
        }
        return str;
    }

}
