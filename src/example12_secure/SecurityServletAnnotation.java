package example12_secure;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="annotationSecurityServlet" ,urlPatterns= {"/annotationSecurityServlet"})
@ServletSecurity(httpMethodConstraints= {@HttpMethodConstraint(value="GET",rolesAllowed="manager")}
)
public class SecurityServletAnnotation extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("xixixi=====xiajinhui=====");
		System.out.println("authen-type:"+req.getAuthType());
		System.out.println("remote-user:"+req.getRemoteUser());
		System.out.println("isUserInRole:"+req.isUserInRole("manager"));
		System.out.println("principal:"+req.getUserPrincipal());
		System.out.println("authenticate:"+req.authenticate(resp));
		//req.logout();
		//req.login("XJH", "12345678X");
	}  
}
