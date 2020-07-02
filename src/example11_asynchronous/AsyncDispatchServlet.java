package example11_asynchronous;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="AsyncDispatcherServlet",urlPatterns= {"/asyncDispatcherServlet"},asyncSupported=true)
public class AsyncDispatchServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		final AsyncContext asyncContext=req.startAsync();
		req.setAttribute("mainThread", Thread.currentThread().getName());
		asyncContext.setTimeout(5000);
		asyncContext.start(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				req.setAttribute("workThread", Thread.currentThread().getName());
				System.out.println(System.currentTimeMillis()+"+++++++++++++++-------------+");
				asyncContext.dispatch("/jsp/example11/threadNames.jsp");
				//除了调度到其他资源去完成任务，也已调用asyncContext.complete方法，此方法通知servlet容器该任务以完成
			}
		});
		
		System.out.println(System.currentTimeMillis()+"-------------++++++++++++++=");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}

}
