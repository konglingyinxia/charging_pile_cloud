package config.wx;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import config.com.MyConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kongling
 * @package config.wx
 * @date 2019-06-14  10:33
 * @project charging_pile_cloud
 */
@Configuration
public class WxMaConfiguration {
    @Autowired
    MyConfiguration myConfiguration;

    private static WxMaService wxMaService = null;

    @Bean
    public Object services(){
        WxMaInMemoryConfig config = new WxMaInMemoryConfig();
        config.setAppid(myConfiguration.getWxAppId());
        config.setSecret(myConfiguration.getWxAppSecret());
        //config.setToken(token);
        //config.setAesKey(aesKey);

        wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(config);

        return Boolean.TRUE;
    }

    public static WxMaService getWxMaService(){
        return wxMaService;
    }
}
