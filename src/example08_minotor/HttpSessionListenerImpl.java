package example08_minotor;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.sun.org.apache.bcel.internal.generic.NEW;
@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener,ServletContextListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession hs=se.getSession();
		ServletContext sContext=hs.getServletContext();
		AtomicInteger userCount=(AtomicInteger)sContext.getAttribute("userCount");
		int ucount=userCount.incrementAndGet();
		System.out.println("user count increment to :"+ucount);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession hs1=se.getSession();
		ServletContext sc1=hs1.getServletContext();
		AtomicInteger userCount=(AtomicInteger)sc1.getAttribute("userCount");
		int ucount=userCount.decrementAndGet();
		System.out.println("user count decrement to:"+ucount);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext sc=sce.getServletContext();
		sc.setAttribute("userCount", new AtomicInteger());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}

}
