package example18_base_on_annotation_controller.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.sun.istack.internal.NotNull;

public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	@Size(min=1,max=5)
	private String isbn;
	@NotNull
	private String bookName;
	@Digits(integer=2,fraction=2)
	private float price;
	@Past
	private Date birth;
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", bookName=" + bookName + ", price=" + price +",birth="+birth+ "]";
	}
	
	
	
}
