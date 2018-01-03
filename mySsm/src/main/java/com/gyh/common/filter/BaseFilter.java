package com.gyh.common.filter;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author
 * 过滤敏感词、设置字符编码、url级别权限访问等控制
 *
 */
public class BaseFilter implements Filter {
    private static Logger log = Logger.getLogger(BaseFilter.class);

	private List<String> noAuthorizedUrls=new ArrayList<String>();


	@Override
	public void destroy() {
		System.out.println("BaseFilter...destroy");
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("BaseFilter...init,filterConfig = "+filterConfig);
		//从web.xml中获取filter配置的init-param信息，指定无需过滤的url
		String noAuthorized=filterConfig.getInitParameter("noAuthorized");
		if(StringUtils.isNotBlank(noAuthorized)){
			String[] noAuthorizeds=noAuthorized.split(",");
			noAuthorizedUrls= Arrays.asList(noAuthorizeds);
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chian) throws IOException, ServletException {


		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession();
		

		// 请求地址
		String url = httpRequest.getRequestURI() ;
		// 拼接参数
		String allArl = url  + "?" + (httpRequest.getQueryString());

		//此处可进行过滤敏感词、设置字符编码、url级别权限访问等控制等操作
		//TODO
		chian.doFilter(request, response);
		// return or other code
		// 跳转到登录页 httpResponse.sendRedirect(loginUrl);
	}

}
