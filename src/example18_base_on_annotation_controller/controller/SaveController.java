package example18_base_on_annotation_controller.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import example17_springMVC.demo1.Product;
import example17_springMVC.demo1.ProductForm;
import example18_base_on_annotation_controller.domain.Book;

@Controller
public class SaveController {
	private static final Log logger=LogFactory.getLog(SaveController.class);
	private static Map<String,Object>hmap=new HashMap<>();
	@RequestMapping("/product_save")
	//BindingResult bindingResult需要放在(@ModelAttribute Book book后面,他会捕获绑定消息异常
	public String handeRequest(@ModelAttribute Book book,BindingResult bindingResult,HttpServletRequest request,Model model,RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			return "example19/spring-taglib";
		}
		logger.info("annotation controller-saveController called");
		ProductForm pForm=new ProductForm();
		pForm.setName(request.getParameter("name"));
		pForm.setDescription(request.getParameter("description"));
		pForm.setPrice(request.getParameter("price"));
		
		Product product=new Product();
		product.setDescription(pForm.getDescription());
		product.setName(pForm.getName());
		product.setPrice(Float.parseFloat(pForm.getPrice()));
		//return new ModelAndView("/example17/ProductDetail","product",product);
		
		//model.addAttribute("product", product);
		//return "/example17/ProductDetail";
		hmap.put(product.getName(),product);
		redirectAttributes.addAttribute("abc", "xjh");//这种方法相当于在url后面添加参数，不安全
		redirectAttributes.addFlashAttribute("BCD", "HAHAHA");//可以传递到页面和controller,传递到页面直接使用el表达式，到controller需要使用@ModelAttribute接收
		return "redirect:/view_product/"+product.getName();//重定向，防止二次提交(可以重定向到一个url,也可以重定向到一个jsp==重定向到新的资源)
	}
	@RequestMapping("/view_product/{name}")
	public String viewProduct(@PathVariable String name,Model model,@ModelAttribute Product product,@ModelAttribute(value="BCD")String name2,@ModelAttribute(value="abc")String name1) {
		logger.info("3333777-------00000-------");
		System.out.println(name1);
		System.out.println(name2);
		Product ps=(Product)hmap.get(name);
		if (ps!=null)model.addAttribute("product",ps);
		return "/example17/ProductDetail";
	}
	@ModelAttribute
	public Product getOneInstance() {
		Product pst=new Product();
		pst.setDescription("xjhnb");
		pst.setName("xjh");
		pst.setPrice(666);
		return pst;
	}
}
