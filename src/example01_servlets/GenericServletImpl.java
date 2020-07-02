package example01_servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(value="/genericservlet",initParams= {
		@WebInitParam(name="xjh",value="24"),
		@WebInitParam(name="java",value="core")
},asyncSupported=true)
public class GenericServletImpl extends GenericServlet{

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String  protocol = req.getProtocol();
		System.out.println("if u wangt do something at first,you can override the init method rather than init method int the servlet interface");
		ServletConfig servletConfig=getServletConfig();
		String age=servletConfig.getInitParameter("xjh");
		String java=servletConfig.getInitParameter("java");
		res.setContentType("text/html");
		PrintWriter printWriter=res.getWriter();
		printWriter.print("<html><h1>"+age+"</h1>"+"<h2>"+java+"</h2></html>");
		
	}

}
