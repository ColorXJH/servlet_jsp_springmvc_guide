package example14_dynamic_loading;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebListener;

@WebListener
public class AddNoXmlAndAnnotationServletListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("开始动态创建servlet--------");
		ServletContext sc=sce.getServletContext();
		Servlet firstServlet=null;
		try {
			firstServlet=sc.createServlet(FirstServlet.class);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(firstServlet!=null&& firstServlet instanceof FirstServlet) {
			((FirstServlet)firstServlet).setName("dynamic registered servlet");
			ServletRegistration.Dynamic dynamic=sc.addServlet("firstServlet", firstServlet);
			dynamic.addMapping("/dynamicRegisteredServlet");
			
		}
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
