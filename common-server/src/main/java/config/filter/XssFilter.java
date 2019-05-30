package config.filter;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** 
 * 拦截防止xss注入
 * 通过Jsoup过滤请求参数内的特定字符
 * @author yangwk 
 */  
public class XssFilter implements Filter {  
	private static Logger logger = LoggerFactory.getLogger(XssFilter.class);

	/**
	 * 是否过滤富文本内容
	 */
	private static boolean IS_INCLUDE_RICH_TEXT = false;

	private List<String> excludes = new ArrayList<>();
  
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException,ServletException {
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
		httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		httpServletResponse.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-requested-with, sid, mycustom, smuser,appToken,sessionId");
		//30 min
		httpServletResponse.addHeader("Access-Control-Max-Age", "1800");
		if (logger.isDebugEnabled()) {
  			logger.debug("xss filter is open");
  		}
  		
  		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		if (FilterComm.handleExcludeURL(req, resp, excludes)) {
  			filterChain.doFilter(request, response);
			return;
		}
  		
  		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request,IS_INCLUDE_RICH_TEXT);
  		filterChain.doFilter(xssRequest, response);
    }


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		if(logger.isDebugEnabled()){
			logger.debug("xss filter init~~~~~~~~~~~~");
		}
		String isIncludeRichText = filterConfig.getInitParameter("isIncludeRichText");
		if(StringUtils.isNotBlank(isIncludeRichText)){
			IS_INCLUDE_RICH_TEXT = BooleanUtils.toBoolean(isIncludeRichText);
		}
		
		String temp = filterConfig.getInitParameter("excludes");
		if (temp != null) {
			String[] url = temp.split(",");
			for (int i = 0; url != null && i < url.length; i++) {
				excludes.add(url[i]);
			}
		}
	}

	@Override
	public void destroy() {}  
  
}  
