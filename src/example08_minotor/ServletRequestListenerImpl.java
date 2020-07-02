package example08_minotor;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class ServletRequestListenerImpl implements ServletRequestListener{

	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		System.out.println("666request end------");
		ServletRequest sr1=sre.getServletRequest();
		Long times=(Long)sr1.getAttribute("start");
		Long timee=System.currentTimeMillis();
		HttpServletRequest  request=(HttpServletRequest)sr1;
		String uri=request.getRequestURI();
		System.out.println("take time for:"+uri+" "+(timee-times)+" seconds");
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("666request start------");
		ServletRequest sr1=sre.getServletRequest();
		sr1.setAttribute("start", System.currentTimeMillis());
		// TODO Auto-generated method stub
		
	}

}
