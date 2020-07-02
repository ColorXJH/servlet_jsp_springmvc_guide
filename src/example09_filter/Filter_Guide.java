package example09_filter;

public class Filter_Guide {
		//Filter是拦截Request请求的对象，在用户的请求访问资源前处理ServletRequest和ServletResponse,它可以用于日志记录
		//加解密，Session检查，图像文件保护等，通过filter可以拦截处理某个资源或者某些资源，filter的配置可以通过annotation
		//或者部署描述符文件来完成，，当一个资源或某些资源需要被对各filter所使用时，他们的触发顺序很重要，这时只能通过部署描述符来配置
	
		//Filter API
			//Filter相关的几个接口：Filter,FilterConfig,FilterChain
		//filter的实现必须继承javax.servlet.Filter接口，这个接口包含filter的三个声明周期方法
			//1:init	Servlet容器初始化Filter时，会触发filter的init方法，一般来说是在应用开始时，也就是说：init方法并不是在该filter
				//相关的资源使用到时才开始初始化的，，而且这个方法之调用一次，用于初始化filter.init方法如下：
					//void init(FilterConfig filterConfig)//filterConfig实例由servlet容器传入init方法中的
			//2:doFilter	当servlet容器每次处理filter相关的资源时，就会调用该filter实例的doFilter方法，doFilter方法包含3个参数
				//void doFilter(ServletRequest request,ServletResponse response,FilterChain  chain)
					//说明一下doFilter实现中访问ServletRequest,ServletResponse,也就意味着允许给ServletRequest增加属性或增加Header
					//当然也可以修饰ServletRequest或者ServletResponse来改变他们的行为
					//在doFilter的实现中，最后一行需要调用FilterChain的doFilter方法，filter的doFilter方法里的第三个参数就是filterChain的实例
						//filterChain.doFilter(request,response)
	
				//一个资源可能被多个filter关联到(更专业一点来说这是一个filter链条)，这时Filter.doFilter方法将会触发Filter链中的下一个filter
				//只有在filter链条的最后一个filter里调用filterChain的doFilter，才会触发处理资源的方法
				//如果在Filter.doFilter()的实现中没有在结尾处调用filterChain的doFilter()方法，那么该request请求中止，后面的处理就会中断
				//注意Filter接口中的doFilter方法不同于FilterChain接口中的doFilter方法
	
			//3：destory		该方法在servlet容器要销毁Filter时触发，一般在应用停止的时候调用
	
		//除非filter被部署描述符中多次定义到，否则Servlet窗口只会为filter创建单一实例，由于servlet/jsp的应用通常要处理用户的并发请求，此时的filter实例会被
		//多个线程同时关联到，因此要非常小心的处理多线程问题；可参考相关demo
	
		//Filter配置
			//当完成filter的实现后，就可以开始配置filter了，步骤如下：
				//1：确认哪些资源需要这个filter拦截处理
				//2：配置filter的初始化参数值，这些参数可以在filter的init方法中读取到
				//3：给filter取一个别名，可以用来识别filter
			//FilterConfig接口允许通过它的getServletContext方法来访问ServletContext
				//ServletContext getServletContext()
			//如果配置了Filter的名字，在filterConfig的getFilterName()中就可以获得filter的名字：
				//java.lang.String getFilterName()
			//当然最重要的还是获得开发者/运维者给filter配置的初始化参数，这里有两个方法需要用到
				//1：java.util.Enumeration<String>getParameterNames()
				//2:java.lang.String getInitParameter(String name)
		
		//由两种方法可以配置filter
			//1:@WebFilter注解
			//2:部署描述符注册
				//@WebFilter有以下属性
					//1：asyncSupported			Filter是否支持异步操作
					//2：description				filter的描述
					//3：dispatcerTypes			filter所生效范围
					//4：displayName				filter的显示名
					//5：filterName				filter的名称
					//6：initParams				filter的初始化参数
					//7：largeIcon				filter的大图标名称
					//8：servletName				filter所生效的servlet名称
					//9：smallIcon				filter的小图名称
					//10：urlPatterns			filter所生效的url路径
					//11:value					filter所生效的url路径
	//如下两个filter相同
		//@WebFilter(filterName="filterOne",urlPatterns={"/*"},initParams={@WebInitParam(name="test1",value="1996"),@WebInitParams(name="test2",value="1997")})
		//<filter>
			//<filter-name>filterOne</filter-name>
			//<filter-class>the full class-path</filter-class>
			//<init-param>
				//<param-name>test1</param-name>
				//<param-value>1996</param-value>
			//</init-param>
			//<init-param>
				//<param-name>test2</param-name>
				//<param-value>1997</param-value>
			//</init-param>
		//</filter>
		//<filter-mapping>
			//<filter-name>filterOne</filter-name>
			//<url-pattern>/*</url-pattern>
		//</filter-mapping>
		
	
		//filter顺序，如果多个filter应用于同一个资源，filter的触发顺序变得非常重要，这时就需要使用部署描述符来管理filter,指定哪个filter先触发，那么哪个filter就要放在前面
	
	//小结：本章介绍了FilterAPI相关的接口，：Filter,FilterConfig,FilterChain
	//掌握如何实现一个filter以及filter的两种配置：1：@WebFilter 2:部署描述符
	//每个filter仅有一个实现，因此如果需要保持或者改变其状态需要注意线程安全的问题
	
	
	
	
	
	
}
