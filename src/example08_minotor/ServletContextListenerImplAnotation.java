package example08_minotor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletContextListenerImplAnotation implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("begin----------");
		ServletContext sc=sce.getServletContext();
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("xjh", 26);
		map.put("kcy", 27);
		map.put("wxy", 26);
		sc.setAttribute("map", map);
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("destory-------");
	}

}
