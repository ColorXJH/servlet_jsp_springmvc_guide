package example16_model2_and_mvcModel.updateServlet.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import example16_model2_and_mvcModel.domain.Product;
import example16_model2_and_mvcModel.form.ProductForm;
import example16_model2_and_mvcModel.updateServlet.controller.Controller;
import example16_model2_and_mvcModel.updateServlet.controller.DetailController;
import example16_model2_and_mvcModel.updateServlet.controller.FormController;

//@WebServlet(name="webservlet",urlPatterns= {"*.action1","*.action"})
public class ControllerServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}
	
	private void process(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String url=request.getRequestURI();
		int lastIndex=url.lastIndexOf("/");
		String action=url.substring(lastIndex+1);
		  // execute an action
        if (action.equals("product_input.action1")) {
            // no action class, there is nothing to be done
        } else if (action.equals("product_save.action1")) {
            // create form
            ProductForm productForm = new ProductForm();
            // populate action properties
            productForm.setName(request.getParameter("name"));
            productForm.setDescription(
                    request.getParameter("description"));
            productForm.setPrice(request.getParameter("price"));
            // create model
            Product product = new Product();
            product.setName(productForm.getName());
            product.setDescription(productForm.getDescription());
            try {
            	product.setPrice(Float.parseFloat(
            			productForm.getPrice()));
            } catch (NumberFormatException e) {
            }
            
            // code to save product
            
            // store model in a scope variable for the view
            request.setAttribute("product", product);
        }
        String dispatchUrl = null;
        if (action.equals("product_input.action1")) {
        	Controller controller=new FormController();
            dispatchUrl =controller.handleRequest(request, response) ;
        } else if (action.equals("product_save.action1")) {
        	Controller controlle=new DetailController();
            dispatchUrl =controlle.handleRequest(request, response) ;
        }
        if (dispatchUrl != null) {
            RequestDispatcher rd = 
                    request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }
}
}
