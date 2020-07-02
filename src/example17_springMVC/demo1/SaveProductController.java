package example17_springMVC.demo1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class SaveProductController implements Controller {
	private static final Log logger=LogFactory.getLog(SaveProductController.class);
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		logger.info("SaveProductController called");
		ProductForm pForm=new ProductForm();
		pForm.setName(request.getParameter("name"));
		pForm.setDescription(request.getParameter("description"));
		pForm.setPrice(request.getParameter("price"));
		
		Product product=new Product();
		product.setDescription(pForm.getDescription());
		product.setName(pForm.getName());
		product.setPrice(Float.parseFloat(pForm.getPrice()));
		
		return new ModelAndView("/example17/ProductDetail","product",product);
	}

}
