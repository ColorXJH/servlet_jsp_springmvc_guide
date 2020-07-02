package example16_model2_and_mvcModel.updateServlet.controller;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DetailController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		return "jsp/example16/ProductDetail.jsp";
	}

}
