package example23_file_upload.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import example23_file_upload.UploadFile;

@Controller
public class ClientFileUploadController {
		
	private static final Log logger=LogFactory.getLog(ClientFileUploadController.class);
	@RequestMapping("/showHtml5")
	public String showHtml() {
		return "example23/html5";
	}
	@RequestMapping("/save_file")
	public void saveFile(HttpServletRequest request,@ModelAttribute UploadFile uploadFile,BindingResult bindingResult,Model model) {
		MultipartFile multipartFile=uploadFile.getMultipartFile();
		String fileName=multipartFile.getOriginalFilename();
		File file=new File(request.getServletContext().getRealPath("/images"),fileName);
		try {
			multipartFile.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
