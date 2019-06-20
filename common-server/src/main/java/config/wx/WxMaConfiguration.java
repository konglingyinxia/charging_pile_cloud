package config.wx;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.WxMaInMemoryConfig;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import config.com.MyConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
    @Autowired
    WxPayProperties wxPayProperties;

    private static WxMaService wxMaService = null;

    @Bean
    public Object services(){
        WxMaInMemoryConfig config = new WxMaInMemoryConfig();
        config.setAppid(wxPayProperties.getWxAppId());
        config.setSecret(wxPayProperties.getWxAppSecret());
        //config.setToken(token);
        //config.setAesKey(aesKey);

        wxMaService = new WxMaServiceImpl();
        wxMaService.setWxMaConfig(config);

        return Boolean.TRUE;
    }

    public static WxMaService getWxMaService(){
        return wxMaService;
    }


    @Bean
    @ConditionalOnMissingBean
    public WxPayService wxService() {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(StringUtils.trimToNull(wxPayProperties.getAppId()));
        payConfig.setMchId(StringUtils.trimToNull(wxPayProperties.getMchId()));
        payConfig.setMchKey(StringUtils.trimToNull(wxPayProperties.getMchKey()));
        payConfig.setSignType("MD5");
        payConfig.setNotifyUrl(StringUtils.trimToNull(wxPayProperties.getNotifyUrl()));
       // payConfig.setKeyPath(StringUtils.trimToNull(wxPayProperties.getKeyPath()));

        // 可以指定是否使用沙箱环境
        payConfig.setUseSandboxEnv(false);

        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig);
        return wxPayService;
    }
}
