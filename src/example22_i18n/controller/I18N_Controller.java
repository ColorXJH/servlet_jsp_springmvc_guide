package example22_i18n.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import example18_base_on_annotation_controller.domain.Book;

@Controller
public class I18N_Controller {
	@RequestMapping("/getResolverPage")
	public String getResolverPage(Model model) {
		Book book=new Book();
		book.setIsbn("XJH");
		book.setPrice(20.2f);
		book.setBirth(new Date());
		book.setBookName("new book");
		model.addAttribute("book",book );
		return "/example22/resolverPage";
	}
	
	
}
