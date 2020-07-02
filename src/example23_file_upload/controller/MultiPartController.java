package example23_file_upload.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import example23_file_upload.Book2;


@Controller
public class MultiPartController {
	@RequestMapping("/showFileJsp")
	public String showFileJsp(Model model) {
		model.addAttribute("book2", new Book2());
		return "example23/showfile";
	}
	@RequestMapping("/saveFile")
	public String saveFile(@Valid Book2 book2,BindingResult  bindingResult, Model model,HttpServletRequest request) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("book2", book2);
			return"example23/showfile";
		}
		List<MultipartFile>files=book2.getImages();
		List<String>fileNames=new ArrayList<String>();
		if(files!=null&&files.size()>0) {
			for(MultipartFile file:files) {
				String fileName=file.getOriginalFilename();
				fileNames.add(fileName);
				File imageFile=new File(request.getServletContext().getRealPath("/images"), fileName);
				try {
					file.transferTo(imageFile);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		model.addAttribute("book3", book2);
		return "example23/detail";
	}
}
