package example23_file_upload;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class UploadFile implements Serializable{

	private static final long serialVersionUID = 1L;
	private MultipartFile multipartFile;
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	

}
