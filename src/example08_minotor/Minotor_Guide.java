package example08_minotor;

import javax.servlet.annotation.WebListener;

public class Minotor_Guide {
		//servlet api 提供了一系列的事件和事件监听接口，上层的servlet/jsp应用能够通过调用这些api进行事件驱动的开发
		//这里监听的所有事件都继承自java.util.Event对象，监听器接口可以分为3类：ServletContext,HttpSession,ServletRequest
		//Servlet3.0中出现的新监听器接口javax.servlet.AsyncListener将在第11章介绍
	
		//监听器接口和注册
			//监听器接口主要在javax.servlet和ajvax.servlet.http包中，有以下这些接口
				//1：javax.servlet.ServletContextListener:它能够响应ServletContext生命周期事件，它提供了ServletContext创建之后和关闭之前的会被调用的方法
				//2：javax.servlet.ServletContextAttributeListener:他能够响应servletcontext范围的属性添加，删除，替换事件
				//3：javax.servlet.http.HttpSessionListener:它能够响应HttpSession的创建，超时和失效事件
				//4：javax.servlet.http.HttpSessionAttributeListener:它能够响应httpsession范围的属性添加，删除，替换事件
				//5：javax.servlet.http.HttpSessionActivationListener:它在一个httpsession激活或失效时被调用
				//6：javax.servlet.http.HttpSessionBindingListener:可以实现这个接口来httpsession范围的属性，当有属性从httpsession添加或删除时，该接口能做出响应
				//7：javax.setvlet.ServletRequestListener:它能够响应一个servletrequest的创建或删除
				//8：javax.setvlet.ServletRequestAttributeListener:他能够响应servletRequest范围的属性添加，删除，修改事件
				//9：javax.servlet.AsyncListener:一个用于异步操作的监听器
	
		//编写一个监听器只需要写一个java类实现响应的接口就可以了，servlet3.0/3.1提供了2中注册监听器的方法
				//1：@WebListener
					//@WebListener
					//public class implements listenerInterface{}
	
				//2:在部署描述符中增加一个listener元素
					//<listener>
						//<listener-class>fully-qualified listener class</listener-class>
					//</listener>
		//你可以在应用程序中添加多个监听器，他们是同步工作的
	
	
		//ServletContext监听器
			//ServletContext的监听器接口有两个
				//1:ServletContextListener
				//2:ServletContextAttributeListener
			
			//ServletContextListener:能够对ServletContext的创建和销毁做出相应，
				//当servletcontext初始化时，容器会调用所有注册的ServletContextListeners的contextInitialized方法
					//void contextInitialized(ServletContextEvent event)
				//当ServletContext销毁时，容器会调用所有注册的ServletContextListeners的contextDestoryed方法
					//void contextDestoryed(ServletContxetEvent event)
				//上面两个方法都会从容器中获取ServletContextEvent对象，他是java.util.Event的子类，它定义了一个访问ServletContext的方法
					//ServletContext getServletContext
		
			//ServletContextAttributeListener:当一个ServletContext范围的属性被添加，删除或修改时，该接口的实现类会接收到消息，这个接口定义了如下3个方法
				//1:void attributeAdded(ServletContextAttributeEvent event):ServletContext属性在被添加时调用，
				//2:void attributeRemoved(ServletContextAttributeEvent event):移除时调用
				//3:void attributeReplaced(ServletContextAttributeEvent event):修改替换时调用
					//这三个方法都能获得一个ServletContextAttributeEvent对象，通过这个对象可以获取属性的名称和值
					//ServletContextAttributeEvent类继承自ServletContextAttribute,该类增加了两个方法用于获取属性的名称和值
						//1:java.lang.String getName();
						//2:java.lang.Object getValue();
		
	
		//Session监听器
			//一共有4个HttpSession相关的监听器接口，
				//1:HttpSessionListener
				//2:HttpSessionActivationListener
				//3:HttpSessionAttributeListener
				//4:HttpSessionBindingListener
			
			//HttpSessionListener
				//当一个HttpSession创建或者销毁时，容器都会通知所有的HttpSessionListener监听器，，该监听器有两个方法：sessionCreated,sessionDestoryed
					//1:void sessionCreated(HttpSessionEvent event)
					//2:void sessionDestoryed(HttpSessionEvent event)
				//这两个方法接收一个继承自java.util.Event的HttpSessionEvent对象，该对象有一个方法可以获得当前httpsession:
					//HttpSession getSession()
			
			//HttpSessionAttributeListener
				//该接口和ServletContextAttributeListener接口类似，它响应的是HttpSession范围内的属性的添加，修改，替换，该接口有3个方法
					//1:void attributeAdded(HttpSessionBindingEvent event):属性在httpsession范围被添加时调用
					//2:void attributeRemove(HttpSessionBindingEvent event)：被删除时调用
					//3:void attributeReplace(HttpSessionBindingEvent event)：被替换时调用
				//这三个方法都能获得一个HttpsessionBindingEvent对象，这个对象有两个方法可以获取属性的名称和值
					//1:java.lang.String getName();
					//2:java.lang.Object getValue();
				//HttpSessionBindingEvent 是 HttpSessionEvent的子类，因此也可以在HttpSessionAttributeListener实现类中获取HttpSession
	
			//HttpSessionActivationListener
				//在分布式环境下，会用多个容器进行负载均衡，有可能需要将session保存起来，在容器之间传递，例如当一个容器内存不足时，会把很少用到的对象转存到其他容器上，这时候，容器就会通知所有HttpSessionActivationListener接口的实现类
				//该接口有如下两个方法
					//1:void sessionDidactivate(HttpSessionEvent event)//当httpsession被转移到其他容器之后，该方法就会被调用，容器将一个HttpSessionEvent对象传递到方法里，可以从这个对象获取HttpSession
					//2:void sessionWillPassivate(HttpSessionEvent event)//当一个HttpSession要失效时，容器会调用该方法，其他同上
	
			//HttpSessionBindingListener
				//当有属性绑定或者解绑到HttpSession上时，该监听器就会被调用，如果队HttpSession属性的绑定和解绑感兴趣，就可以实现该接口来监听
	
		//ServletRequest监听器:ServletRequest范围的监听器接口有3个
			//1:ServletRequestListener
			//2:ServletRequestAttributeListener
			//3:AysncListener
	
			//ServletRequestListener
				//该监听器会对ServletRequest的创建和销毁事件进行响应，容器会听过一个池来存放并重复利用多个ServletRequest，ServletRequest的创建是从容器池里被分配出来的时刻开始，而它的销毁时刻是放回到容器池里面的时刻
				//该接口有两个方法	
					//1:void requestInitialized(ServletRequestEvent event)
					//2:void requestDestoryed(ServletRequestEvent event)
						//当一个servletrequest创建时(从容器池中取出)，1方法会被调用，当servletrequest销毁时，2方法会被调用
				//这两个方法都接收一个ServletRequestEvent对象，可以通过该对象获得servletRequest
					//ServletRequest getServletRequest()
				//ServletRequestEvent接口还提供获取servletContext对象的方法
					//ServeltContext getServletContext();
	
			//ServletRequestAttributeListener
				//当一个ServletRequest范围的属性被添加，删除或替换时，ServletRequestAttributeListener会被调用，该接口提供三个方法
					//1:void attributeAdded(ServletRequestAttributeEvent event)
					//2:void attributeRemove(ServletRequestAttributeEvent event)
					//3:void attributeReplace(ServletRequestAttributeEvent event)
				//这些方法都会获得继承自ServletRequestEvent的ServletRequestAttributeEvent对象，通过该对象提供的getName/value方法可以获得属性名称的k/v
					//java.lang.String getName()
					//java.lang.Object getVlaue()
	
	
	
	//小结：本章学习了servlet api的多个监听器类，这些监听器类可以分为3类：1：application范围 2：session范围 3：reqeust范围
	//可以通过两种方式注册监听器：1：@WebListener注解 2：部署描述符文件中增加<listener><listenenr-class>class-path</listener-class></listener>
	
	
	
	
}
