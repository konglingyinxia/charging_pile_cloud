package com.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取请求路径
 *
 * @author 卫星
 * @package com.sskj.common.utils
 * @date 2018-11-17  14:16
 * @project centbtc
 */
public class RequestPathUtil {

    public static String getReqHttpPath() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String reqUrl = "";
        //获取服务器名，localhost；
        String serverName = request.getServerName();
        //获取服务器端口号，8080；
        Integer serverPort = request.getServerPort();
        reqUrl = "http://" + serverName + ":" + serverPort;
        return reqUrl;
    }


}
