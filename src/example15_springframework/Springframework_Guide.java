package example15_springframework;

import com.sun.swing.internal.plaf.basic.resources.basic;

public class Springframework_Guide {
		//spring框架是一个开源的企业应用开发框架，包含20多个不同的模块，这部分主要关注core和bean以及springMVC模块，springmvc是spring的一个子框架
		//core/bean 以及他们之间的依赖注入模块
			
		//spring入门
			//spring模块都打包成jar文件，其命名格式如下：
				//spring-moduleName-x.y.z.RELASE.jar
					//moduleName:模块的名字，
					//x.y.z:spring的版本号。例如4.1.12
			//推荐私用maven/gradle工具来下载spring模块，他们的好处是下载模块时会下载其对应的依赖
			//如果不熟悉工具可以http://repo.spring.io/release/org/springframework/spring/下载，但是要单独下载依赖
			
			//依赖注入和控制反转的区别
			//1:依赖注入：
				//有两个组件A,B A依赖于B，假定A是一个类，且A有一个方法importantMethod使用到了B，如下
			class A{
				public void importantMethod() {
					//B b=...//get a instance of B
					//b.usefulMethod();
					//...
				}
			}
			//要使用B，类A必须先获得组件B的实例引用，若B是一个具体类，则可以通过new关键字直接创建B实例，但是如果B是接口，且有多个实现，则问题就变的复杂了
			//我们固然可以任意选择一个B接口的实现，但是这也意味值A的可用性大大降低了，因为无法采用其他的实现
				//依赖注入是这样处理此类情景的，接管对象的创建工作，并将该对象的引用注入需要该对象的组件，以上述例子为准，依赖诸如框架会去
				//分别创建对象A和对象B，将对象B注入到对象A中
			//为了能使框架进行依赖注入，程序员需要编写特定的set方法或构造方法，例如，为了能够将B注入到A中，类A会被修改成如下形式：
			class A1{
				//private B b;
				/*public void setB(B b) {
					this.b=b;
				}*/
				public void importantMethod() {
					//no need to worry about create a instance of B
					//basic.usefulMethod();
				}
			}
			//修改后的类A增加了一个setter方法，该方法会被框架调用，以注入一个B的实例，由于对象依赖由依赖注入（依赖注入的由来），类A的importantMethod方法
			//不再需要在调用B的userfulMethod方法前出创建一个B的实例，当然也可以使用构造器注入：如下：
			class A2{
				//private B b;
				/*public A2(B b) {
					this.b=b;
				}*/
				public void importantMethod() {
					//b.userfulMethod();
				}
			}
			//本例子中spring 会先去创建B的实例，再去创建A的实例，然后把B注入到实例A中（spring管理的对象称为beans）
			//通过提供一个控制反转容器（或依赖注入容器），spring为我们提供一种优雅的管路java对象依赖关系的方法，其优雅之处在于，spring无需了解spring框架的存在，更不需要引入任何spring类型
			//从1.0版本开始，支持setter/构造器注入，从2.5版本开始支持Autowired注解注入，spring支持基于field的方式依赖注入，但是缺点是必须引入org.springframework.beeans.factory.annotation.Autowired
			//这对spring产生了依赖，这样程序无法直接迁移到另一个依赖注入容器
			//spring支持xml和注解两种配置方式，此外还需创建一个ApplicationContext对象，代表一个spring控制反转容器，org.springframework.context.ApplicationContext接口有许多实现
			//包括ClassPathXmlApplicationContext和FileSystemXmlApplicationContext,这两个实现都需要至少一个包含beans信息的xml文件
			//ClassPathXmlApplicationContext尝试在类加载路径中加载配置文件，而FileSystemXmlApplicationContext则从系统文件中加载
			
			//可以从类路径中加载config1.xml和congif2.xml而创建一个ApplicationContext:
				//ApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"config1.xml","config2.xml"})
			//然后可以通过该类的getBean方法获取对象：
				//Product product=context.getBean("product",Product.class)//查询id为product且类型为Product的bean对象
			//注意：理想情况下我们仅仅需要在测试代码中创建一个ApplicationContext,应用程序本身无需处理，对应springMVC引用，可以通过一个Spring Servlet来处理applicationContext,而无需直接处理
			
			
			//xml配置文件
				//配置文件的根元素一般如下：
					//<?xml version="1.0" encoding="utf-8"?>
					//<beans xmlns="http://www.springframework.org/schema/beans"
					//       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
					//		 xsi:schemaLocation="http://www.springframework.org/schema/beans 
					//							 http://www.springframework.org/schema/beans/spring-beans.xsd"
					//>
						//...
					//</beans>
			//如果需要更强的spring配置能力，可以在schemaLocation属性中添加相应的schema
			//配置文件可以是一份，也可以是多份，以支持模块化配置，applicationContext的实现类支持多份配置文件，另一种方法是支持一种主配置文件，将其他配置文件导入进来
			
			//spring控制反转容器的使用：spring如何管理bean和依赖关系
				//1：通过构造器创建一个bean
					//<bean name="product" class="...."/>//spring通过默认无参构造器来初始化一个bean,如果不存在该构造器（类作者重载了构造器），则抛出一个异常
						//一概采用id/name属性标识一个bean
				//2:通过工厂方法创建一个bean
					//<bean id="calendar" class="java.util.Calendar" factory-method="getInstance"/>
						//这个静态的类方法需要自己创建，（如果需要使用这个方法得到实例）
				//3:destroy-method的使用
					//有时希望在一些类被销毁前做一些事情，可以在bean中配置 destroy-method属性，来指定销毁前要执行的方法
				//4:向构造器传递参数：
					//使用<constructor-arg name="xxx",value="xxx"/>
						//注意构造器传递阐述可以是基本数据类型的参数也可以是引用数据类型的参数，如果为引用数据类型的参数，需要引用另一个bean的id/name
					//除了使用name指定参数，还可以通过指数index指定参数：<constructor-arg index="0" value="..."/>
						//这种方式应对构造器的所有参数传递指数index,缺一不可
				//5：setter方式依赖注入
					//使用<property name="xxx" value="xxx">//可以传递基本数据类型和引用数据类型，如果为引用数据类型则value改为ref,引用另一个bean的id/name属性
					//被引用的对象的定义无需早于引用其对象的定义



}
