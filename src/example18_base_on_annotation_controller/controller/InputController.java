package example18_base_on_annotation_controller.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.bcel.internal.generic.NEW;

import example18_base_on_annotation_controller.domain.Book;
import example18_base_on_annotation_controller.service.ProductInterface;
@Controller
public class InputController {
	private static final Log logger=LogFactory.getLog(InputController.class);
	@Autowired
	private ProductInterface face;
	@RequestMapping("/product_input.action")
	public String handleRequest() {
		logger.info("annotation-controller-InputController called");
		System.out.println("------xxx-----");
		logger.info(face.getName());
		return "/example17/ProductForm";
	}
	@RequestMapping("/product_show.action")
	public ModelAndView showForm(Model model) {
		ModelAndView mvAndView=new ModelAndView();
		mvAndView.addObject("book",new Book());
		mvAndView.setViewName("example19/spring-taglib");
		return mvAndView;
	}
	@RequestMapping("/saveBook")
	public String watchForm(Book book,Model model,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {return "example19/spring-taglib";}
		book.setIsbn("666");
		model.addAttribute("book", book);
		return "example19/spring-taglib";
	}
}
