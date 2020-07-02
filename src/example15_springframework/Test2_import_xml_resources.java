package example15_springframework;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2_import_xml_resources {
	public static void main(String[] args) {
		ApplicationContext context=new ClassPathXmlApplicationContext(new String[] {"all-import-config.xml"});
		Product ps=(Product)context.getBean("productWithArgs");
		Product psfactory=context.getBean("factoryMethodCreat", Product.class);
		Executor e1=context.getBean("executorService",Executor.class);
		ExecutorService es=(ExecutorService)e1;
		es.submit(()->System.out.println("hahaha"));
		System.out.println(ps);
		System.out.println(psfactory);
	}
	
}
