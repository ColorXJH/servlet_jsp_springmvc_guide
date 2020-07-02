package example14_dynamic_loading;

public class DynamicLoadingGuide {
		//动态加载及servlet容器加载器
			//动态加载是servlet3.0的新特性，他可以实现在不重启web应用的情况下加载新的web对象(servlet,listener,filter)
			//servlet容器加载器也是servlet3.0中的新特性，对于框架的开发者来说特别有用
			
		//动态加载
			//为了实现动态加载，servletcontext接口中增加了如下方法，用于动态创建web对象
				//<T extend Filter>createFilter(java.lang.Class<T>clazz)
				//<T extend java.util.EventListener>createListener(java.lang.Class<T>clazz)
				//<T extend Servlet>createServlet(java.lang.Class<T>clazz)
			//如果MyServlet是一个直接或间接继承javax.servlet.Servlet的类，那么可以通过createServlet方法初始化它：
				//Servlet myServlet=createServlet(MyServlet.class)
			//在创建了web对象后，可以通过servletcontext中的如下方法把它注册到servletcontext(servlet3.0新特性)
				//FilterRegistration.Dynamic addFilter(java.lang.String filterName,Filter filter)
				//<T extends java.util.EventListener>void addListener(T t)
				//ServletRegistration.Dynamic addServlet(java.lang.String servletName,Servlet servlet)
			//也可以使用servletcontext中的如下方法，创建web对象并将其加入到servletcontext中
				//FilterRegistration.Dynamic addFilter(java.lang.String filterName,java.lang.Class<? extends Filter> filterClass)
				//FilterRegistration.Dynamic addFilter(java.lang.String filterName,java.lang.String className)
				
				//void addListener(java.lang.Class<? extends java.util.EventListener> listenerClass)
				//void addListener(java.lang.String className)
				
				//ServletRegistration.Dynamic addServlet(java.lang.String servletName,java.lang.Class<? extends Servlet> servletClass)
				//ServletRegistration.Dynamic addServlet(java.lang.String servletName.java.lang.String servletClass)
	
			//要创建或者增加Listener,传递给第一个addListener方法的类需要实现以下的一个或者多个接口
				//ServletContextAttributeListener
				//ServletRequestListener
				//ServletRequestAttributeListener
				//HttpSessionListener
				//HttpSessionAttributeListener
			//如果servletcontext是用于servletcontextinitializer中onStartup方法的参数，那么listener也需要实现Servletcontextlistener,关于startUp方法以及
			//servletcontextinitializer接口的更多信息，请看下面一小节
			
			//addFilter和addServlet的方法返回值为FilterRegistration.Dynamic和ServletRegistration.Dynamic,者两个接口都是Registration.Dynamic的子接口，
			//前者允许配置filter,后者允许配置servlet
	
		//servlet容器加载器
			//如果使用javaweb框架，如struts,struts2,则需要在使用该框架前先对引用进行配置，典型的例子是，通过修改部署描述符来高数servlet容器你在使用某个框架，例如在使用struts2
			//就需要加入如下配置到部署描述符中：
				/*<filter>
					<filter-name>struts2</filter-name>
					<filter-class>org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter	</filter-class>
				</filter>
				<filter-mapping>
				<filter-name>struts2</filter-name>
				<url-pattern></url-pattern>
				</filter-mapping>*/
			//在servlet3中，这个步骤可以省略了，框架打包时使用这种方法，就可以对这些web对象实现自动初始化了
			//servlet容器初始化主要是通过javax.servlet.ServletContainerInitializer这个接口，这个接口只有一个方法：onStartup,servlet容器中，这个方法在
			//任何ServletContextListener初始化前都可能会被调用到
				//void onStartup(java.util.Set<java.lang.Class<?>>clazz,ServletContext context)
			//ServletContainerInitializer的实现类必须使用HandleTypes的注解，以便让加载器能够识别到
	
	//小结：部署应用以及插件化的两个特性
		//一个特性是动态加载，它能在不重启应用的情况下加载servlet,listener,filter
		//第二个特性是servlet容器加载器，他能以插件的形式发布应用，而不是在应用程序中修改部署描述符，servlet容器加载器对框架开发者特别有用




}
