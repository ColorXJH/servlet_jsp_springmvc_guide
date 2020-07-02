package example01_servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
@WebServlet(asyncSupported=true,name="MyServlet",urlPatterns= {"/my","/myServlet"},initParams = { 
		@WebInitParam(name = "name", value = "xjh"), 
		@WebInitParam(name = "age", value = "24"), 
		@WebInitParam(name = "sex", value = "male")
})//servlet名称以及那个url调用servlet容器（url样式必须以一个/开头）
public class MyServlet implements Servlet {
	private transient ServletConfig servletconfig;
	@Override
	public void init(ServletConfig config) throws ServletException {
		servletconfig=config;
		System.out.println(servletconfig.getInitParameter("name"));
		Enumeration<String> sss=servletconfig.getInitParameterNames();
		while(sss.hasMoreElements()) {
			
			System.out.println(sss.nextElement());
		}
		
	}
	

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return servletconfig;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String,String[]> parameters=req.getParameterMap();
		for(Entry<String, String[]> ss:parameters.entrySet()) {
			System.out.println(ss.getValue().toString());
		}
		System.out.println(parameters);
		String servletName=servletconfig.getServletName();
		res.setContentType("text/html");
		PrintWriter printWriter=res.getWriter();
		printWriter.print("<html><head></head>"+"<body>hello from"+servletName+"</body></html>");
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return "MyServlet";
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
