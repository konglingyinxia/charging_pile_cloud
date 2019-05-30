package com.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * <p>
 * Title: PropertiesUtil
 * Description:根据配置参数自动获取配置文件值
 *
 * @author zhengliangzhang
 */
public class PropertiesUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);


    private static Properties props = null;

    /**
     * 初始加载配置文件
     */
    static {
        InputStreamReader in = null;
        props = new Properties();
        try {
            in = new InputStreamReader(PropertiesUtil.class.getResourceAsStream("/data.properties"), "UTF-8");
            props.load(in);
        } catch (IOException e) {
            LOGGER.error("加载失败", e);
        }
    }


    /**
     * @param name
     * @return
     */
    public static String readProperties(String name) {
        //保存所有的键值
        String property = "";
        if (StringUtils.isNotBlank(name)) {
            property = props.getProperty(name);
        }
        return property;
    }

}
