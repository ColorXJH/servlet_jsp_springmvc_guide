package example01_servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(value="/noeffect",initParams= {
		@WebInitParam(name="age",value="24")
},asyncSupported=true)
public class NoAnnotationServlet extends HttpServlet{
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		System.out.println(getInitParameter("age"));
		System.out.println(getInitParameter("XJH"));
		System.out.println(getServletContext().getInitParameter("haha"));
		System.out.println("启动程序时因为配置了load on startup,所以该servlet提前加载了	");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("no annotation servlet has start");
		System.out.println(getServletContext().getInitParameter("haha"));
		System.out.println(getInitParameter("XJH"));
		System.out.println(getInitParameter("age"));
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
}
