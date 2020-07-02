package example11_asynchronous;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(asyncSupported=true,urlPatterns= {"/asyncCompleteServlet"})
public class AsyncCompleteServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("text/html");
		final PrintWriter writer=resp.getWriter();
		writer.println("<html><head><title>" +
				"Async Servlet</title></head>");
				writer.println("<body><div id='progress'></div>");
		final AsyncContext asyncContext=req.startAsync();
		asyncContext.setTimeout(6000);
		asyncContext.start(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("new Thread: "+Thread.currentThread().getName());
				for(int i=0;i<5;i++) {
					writer.println("<script>");
					writer.println("document.getElementById(" +
					"'progress').innerHTML = '" +
					(i * 20) + "% complete'");
					writer.println("</script>");
					writer.flush();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				writer.println("<script>");
				writer.println("document.getElementById(" +
						"'progress').innerHTML = 'DONE'");
						writer.println("</script>");
						writer.println("</body></html>");
						asyncContext.complete();
				
			}
		});
		
		
		
	}
}
