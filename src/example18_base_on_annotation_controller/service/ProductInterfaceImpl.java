package example18_base_on_annotation_controller.service;

import org.springframework.stereotype.Service;

@Service
public class ProductInterfaceImpl implements ProductInterface {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "xjh";
	}

}
