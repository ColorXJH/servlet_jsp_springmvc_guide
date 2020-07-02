package example09_filter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class LoggingFilter
 */
@WebFilter(asyncSupported=true,
		description = "take request to log.txt", 
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "logFileName", value = "log.txt"), 
				@WebInitParam(name = "prefix", value = "URI:")
		})
public class LoggingFilter implements Filter {
	private PrintWriter logger;
	private String prefix;
    /**
     * Default constructor. 
     */
    public LoggingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destorying filter------");
		if(logger!=null) {
			logger.close();
		}
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("logging filter.doFilter");
		HttpServletRequest httpServletRequest=(HttpServletRequest)request;
		logger.println(new Date()+""+prefix+""+httpServletRequest.getRequestURI());
		logger.flush();
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		prefix=fConfig.getInitParameter("prefix");
		String logFileName=fConfig.getInitParameter("logFileName");
		String appPath=fConfig.getServletContext().getRealPath("/");
		//without path info in logfileName,the log file will be created in $tomcat_home/bin
		System.out.println("log file name:"+appPath);
		try {
			logger=new PrintWriter(new File("E:/logs",logFileName));//日志文件创建在工作路径，如果有则覆盖
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
	}

}
