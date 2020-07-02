package example15_springframework;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test1 {
	public static void main(String[] args) {
		//如果放在具体的包中直接在数组中写config1.xml(除非是直接放在src目录下)，可能会发现找不到，原因是java文件和.class文件存放的位置不一样，java文件放在src下,.class存放在build目录下（ieda可能是在target目录下），可以修改输出output到src下
		//或者将这么些xml文件放在classpath下(src下，或者新建一个source folder然后将其放入)
		ApplicationContext context=new ClassPathXmlApplicationContext(new String[] {"config1.xml","config2.xml"});
		Product productNoArgs=context.getBean("productNoArgs", Product.class);
		Product productWithArgs=context.getBean("productWithArgs", Product.class);
		Product productConstructor=context.getBean("productConstructor", Product.class);
		Person personNoArgs=context.getBean("personNoArgs", Person.class);
		Person1 personWithArgs=context.getBean("personWithArgs", Person1.class);
		Person personRef=context.getBean("personRef", Person.class);
		Person personConstructor=context.getBean("personConstructor", Person.class);
		Person personNoConstructor=context.getBean("personNoConstructor", Person.class);
		Person personConstructorIndex=context.getBean("personConstructorIndex", Person.class);
		System.out.println(productNoArgs);
		System.out.println(productWithArgs);
		System.out.println(productConstructor);
		System.out.println(personNoArgs);
		System.out.println(personWithArgs);
		System.out.println(personRef);
		System.out.println(personConstructor);
		System.out.println(personNoConstructor);
		System.out.println(personConstructorIndex);
	}
}
