package example11_asynchronous;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="myAsyncListenerServlet",urlPatterns= {"/myAsyncListenerServlet"},asyncSupported=true)
public class MyAsyncListenerServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final AsyncContext asyncContext=req.startAsync();
		asyncContext.setTimeout(5000);
		asyncContext.addListener(new MyAsyncListener());
		asyncContext.start(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				req.setAttribute("greeting", "hi,xjh~~");
				asyncContext.dispatch("/jsp/example11/test.jsp");//相当于请求转发到其他页面
			}
		});
		System.out.println("xixixi-------");
		//resp.sendRedirect("/servlet_jsp_springmvc_guide/jsp/example11/response_sendRedirect.jsp");//重定向，不会带参数（若此时在异步线程中包含请求转发，异步转发到的哪个页面的数据不会保留，只显示此页面）
		req.getRequestDispatcher("/jsp/example11/request_getRequestDispatcher_forward.jsp").forward(req, resp);//请求转发，会带参数,若此时在异步线程中包含请求转发，则将异步线程中的数据追加到本页面
		
	}
}
