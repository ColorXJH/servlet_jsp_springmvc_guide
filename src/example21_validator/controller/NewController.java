package example21_validator.controller;


import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import example18_base_on_annotation_controller.domain.Book;
import example21_validator.BookValidator;

@Controller
public class NewController {
	//或者不用再方法中书写验证器验证，在此书写一个总的方法，会在每个方法中执行验证
	//@InitBinder//注意使用了这个注解之后，formatter的格式化器就不起作用了
	//可以使用注解配置，也可以单独在springMVC配置文件中配置一个bean
	//public void initBinder(WebDataBinder binder) {//该方法在每个请求前执行一次
		//用于再不写formatter的情况下注解注册一个转换器
		//binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	//}
	
	@RequestMapping(value="/getValidatorXDR")
	public String getValidatorJsp(Model model) {
		model.addAttribute("book", new Book());
		return "example21/tr-validator";
	}
	@RequestMapping(value="/product_save_new")
	public String validateTheBook(@Valid Book book,BindingResult bindingResult,Model model) {
		//BindingResult 必须紧随@Valid其后，不然报错
		//有了JSR303注解之后也就不需要使用验证器了，同时需要在方法中加入注解：@Valid
		/*BookValidator bValidator=new BookValidator();
		bValidator.validate(book, bindingResult);*/
		if(bindingResult.hasErrors()) {
			model.addAttribute("book", book);
		}
		return "example21/tr-validator";
	}
	@RequestMapping("/product_save_new1")
	public String validateTheBook1(Book book,Model model,BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("book", book);
		}
		model.addAttribute("book", book);
		return "example21/tr-validator";
	}
}
