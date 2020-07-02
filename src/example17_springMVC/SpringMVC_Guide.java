package example17_springMVC;

public class SpringMVC_Guide {
		//springMVC介绍
			//介绍springMVC的基本组件包括dispatcherServlet
			//开发传统风格的控制器以及注解风格的控制器
			//介绍springMVC配置
	
		//采用springMVC的好处
			//若采用某个框架开发一个模型2的应用程序，我们要负责编写一个dispatcherServlet和控制类，其中DispatcherServlet必须能做如下事情：
				//1:根据url调用相应的action
				//2:实例化正确的控制类
				//3:根据请求参数值来构造表单bean
				//4:调用控制器对象的相应方法
				//5:转发到一个视图(jsp页面)
		//springMVC是一个包含了DispatcherServlet的mvc框架，它调用控制器方法并转发到视图，这是使用springmvc的第一个好处
		//不需要编写DispatcherServlet,以下是springMVC具有的能够加速开发的功能列表
			//1：springMVC提供了一个DispatcherServlet，无需额外开发
			//2：springMVC中使用基于xml的配置文件，可以编辑而无需重新编译应用程序
			//3：springMVC实例化控制器，并根据用户输入来构造bean
			//4：springMVC可以自动绑定用户输入，并正确的转换数据类型，例如：能自动解析字符串，并设置float或decimal数据类型
			//5：springMVC可以校验用户输入，若不通过则重定向回用户表单，校验是可选的，支持编程式和声明式方式，springMVC内置了常用的校验器
			//6:springMVC是spring框架的一部分，可以利用spring提供的其他功能
			//7:springMVC支持国际化和本地化，支持根据用户取域显示多国语言
			//8：springMVC支持多种视图技术，最常见的是jsp,velocity,freemarker
	
		//springMVC的DispatcherServlet
			//他是一个充当调度员的servlet,springMVC自带一个开箱即用的DispatcherServlet(org.springframework.web.servlet.DispatcherServlet)
			//要使用这个servlet,需要配置在部署描述符中(web.xml)
			//servlet元素内的<load-on-startup>1</load-on-startup>元素是可选的，如果他存在，则他将在应用程序启动时装在servlet，并调用他的init方法，
			//若它不存在，则在第一次请求该servlet时加载
			//DispatcherServlet将使用springMVC诸多的默认组件，此外在初始化时，他会寻找一个在应用程序的WEB-INF目录下的配置文件，该配置文件的命名规则如下：
				//servletName-servlet.xml 
					//servletName:是在部署描述符中的DispatcherServlet的名字，如果这个名字是springMVC,则在web-inf目录下的对应文件是springMVC-servlet.xml
				//此外你也可以把springMVC的配置文件放在应用程序目录的任何地方，你可以使用servlet的init-param元素，以便dispatcherServlet加载到该文件
					//init-param拥有一个值为contextConfigLocation的param-name元素，其param-value则包含了配置文件的路径
	
		//Controller接口
			//在spring2.5版本前，开发一个控制器的唯一办法是实现org.springframework.web.servlet.mvc.Controller
			//这个接口公开了一个handelRequest方法
				//ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response)
					//返回一个包含了视图路径的mv(MoldeAndView)
			//controller接口的实现类只能处理一个单一的动作，而基于注解的控制器可以处理多个动作，而且无需实现任何接口
		
		//第一个SpringMVC应用：example17
			//web-inf目录下包含了所有需要的jar包，webmvc的jar包含了DispatcherServlet的类
			//注意springMVC依赖于apache commons logging组件，没有它springmvc就无法运作
	
		//ViewResolver
			//springMVC中的视图解析器用来解析视图，可以通过在配置文件中定义一个viewresolver来配置视图解析器
				/*<bean id="viewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
						<property name="prefix" value="/jsp"/>
						<property name="suffix" value=".jsp"/>
				</bean>*/
	
		//小结：在springmvc中我们无需编写自己的dispatcherservlet
		//控制器有传统风格的基于接口的和基于注解的
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
