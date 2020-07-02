package example16_model2_and_mvcModel.updateServlet.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example16_model2_and_mvcModel.domain.Product;
import example16_model2_and_mvcModel.updateServlet.validator.ProductValidator;

public class FormController implements Controller {

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				ProductValidator validat=new ProductValidator();
		        List<String>error=validat.validate((Product)(request.getAttribute("product")));
		        if(error.size()==0) {}
		        else {
		        	request.setAttribute("errors", error);
		        	request.setAttribute("form",(Product)(request.getAttribute("product")));
		        	return "jsp/example16/ProductForm.jsp";
		        }
		return "jsp/example16/ProductForm.jsp";
	}

}
