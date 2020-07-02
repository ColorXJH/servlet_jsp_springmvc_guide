package example23_file_upload;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import com.sun.istack.internal.NotNull;

public class Book2 implements Serializable{

	private static final long serialVersionUID = 1L;
	@Size(min=3)
	private String name;
	private int age;
	private List<MultipartFile>images;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public List<MultipartFile> getImages() {
		return images;
	}
	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}
	
		
}
