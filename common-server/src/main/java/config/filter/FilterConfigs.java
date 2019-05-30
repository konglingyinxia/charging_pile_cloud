package config.filter;

import com.google.common.collect.Maps;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 *
 */
@Configuration
public class FilterConfigs {

	String  noFilter="" +
			"/favicon.ico," +
			"/img/*," +
			"/js/*," +
			"/css/," +
			"/swagger-ui.html," +
			"/webjars/*," +
			"/swagger-resources," +
			"/v2/*," +
			"/swagger-resources/*";

	/**
	 * xss过滤拦截器
	 */
	@Bean
	public FilterRegistrationBean xssFilterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new XssFilter());
		filterRegistrationBean.setOrder(1);
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("/*");
		Map<String, String> initParameters = Maps.newHashMap();
		initParameters.put("excludes", noFilter);
		initParameters.put("isIncludeRichText", "true");
		filterRegistrationBean.setInitParameters(initParameters);
		return filterRegistrationBean;
	}

}
