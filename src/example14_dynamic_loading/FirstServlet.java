package example14_dynamic_loading;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet{
	private String name;
	public void setName(String name) {
		this.name=name;
	}
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer=resp.getWriter();
		writer.println("<html><head><title>First servlet" + 
				"</title></head><body>" + name);
		writer.println("</body></head>");
	}

}
