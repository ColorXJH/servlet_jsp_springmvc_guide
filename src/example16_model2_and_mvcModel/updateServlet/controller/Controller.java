package example16_model2_and_mvcModel.updateServlet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
		public String handleRequest(HttpServletRequest request,HttpServletResponse response);
}
