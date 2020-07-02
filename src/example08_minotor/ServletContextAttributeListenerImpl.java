package example08_minotor;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;
@WebListener
public class ServletContextAttributeListenerImpl implements ServletContextAttributeListener {

	@Override
	public void attributeAdded(ServletContextAttributeEvent scae) {
		// TODO Auto-generated method stub
		System.out.println("add begin-----");
		ServletContext sContext=scae.getServletContext();
		System.out.println(sContext.getAttribute("add-name"));
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		// TODO Auto-generated method stub
		System.out.println("remove begin-----");
		ServletContext sContext=scae.getServletContext();
		System.out.println(sContext.getAttribute("add-name"));
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		// TODO Auto-generated method stub
		System.out.println("replace begin------");
		ServletContext sContext=scae.getServletContext();
		System.out.println(sContext.getAttribute("add-name"));
	}

}
