package example16_model2_and_mvcModel.updateServlet.validator;

import java.util.ArrayList;
import java.util.List;

import example16_model2_and_mvcModel.domain.Product;
import example16_model2_and_mvcModel.form.ProductForm;

public class ProductValidator {
	public List<String> validate(Product from){
		List<String>errors=new ArrayList<String>();
		String name=from.getName();
		String price=Float.toString(from.getPrice());
		if (name == null || name.trim().isEmpty()) {
			errors.add("Product must have a name");
		}
		if(price==null||price.length()<1) {
			errors.add("Product must have a price");
		}else {
			try {
				Float.parseFloat(price);
			} catch (NumberFormatException e) {
				errors.add("Invalid price value");
			}
		}
		return errors;
	}
}
