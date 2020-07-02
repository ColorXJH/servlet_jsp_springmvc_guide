package example09_filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

//@WebFilter(filterName="ImagePTFilter",urlPatterns= {"*.jpg","*.png","*.gif"},asyncSupported=true)
public class ImageProtectorFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("image-protector-filter");
		HttpServletRequest  hRequest=(HttpServletRequest)request;
		String referer=hRequest.getHeader("referer");
		System.out.println("referer:"+referer);
		if(referer!=null) {//检查http header的referer值，如果该值为null,意味着当前请求中没有referer值，，即当前的请求时直接通过url来访问该资源的，如果非空，，将返回request语法的原始页面作为referer值
			chain.doFilter(request, response);
		}else {
			throw new ServletException("images are not available");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
