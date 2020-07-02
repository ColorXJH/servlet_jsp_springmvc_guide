package example10_director_request_response;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@WebFilter(filterName="auot-correct-filter",urlPatterns= {"/*"},asyncSupported=true)
public class AutoCorrectFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest hRequest=(HttpServletRequest)request;
		AutoCorrectHttpServletRequestWrapper ahw=new AutoCorrectHttpServletRequestWrapper(hRequest);
		chain.doFilter(ahw, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	class AutoCorrectHttpServletRequestWrapper extends 	HttpServletRequestWrapper{
		private HttpServletRequest httpServletRequest;
		public AutoCorrectHttpServletRequestWrapper(HttpServletRequest request) {
			super(request);
			// TODO Auto-generated constructor stub
			System.out.println("----pppp-----");
			httpServletRequest=request;
		}
		@Override
		public String getParameter(String name) {
			// TODO Auto-generated method stub
			System.out.println("hahhaahah---------");
			httpServletRequest.setAttribute("xjhxi","00000-----");
			return super.getParameter(name);
		}
		
	}

}
