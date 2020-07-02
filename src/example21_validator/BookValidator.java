package example21_validator;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import example18_base_on_annotation_controller.domain.Book;

public class BookValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Book.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Book book=(Book)target;
		ValidationUtils.rejectIfEmpty(errors, "isbn", "isbn.required");
		ValidationUtils.rejectIfEmpty(errors, "bookName", "bookName.required");
		Float price=book.getPrice();
		if(price<0) {errors.rejectValue("price","price must greater than 0");}
		Date ds=book.getBirth();
		if(ds!=null&&ds.after(new Date())) {errors.rejectValue("birth", "dateBeforeInvalidate");}
	}

}
