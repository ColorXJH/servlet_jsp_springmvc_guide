package example11_asynchronous;

public class Asynchronous_Guide {
			//异步处理:Servlet3.0引入了一个新功能，运行使用Servlet处理异步请求
			//一台机器的内存有限，该servle/jsp容器的设计者直到这一点，并提供了一些可配置的设置，以确保容器内可以运行托管机器的方法
			//例如tomcat7中，处理传入请求的最大线程数时200
	
			//servlet或过滤器占有请求处理线程直到它完成任务，如果任务需要很长事件才能完成，当用户的并发请求数目超过了线程数，容器可能
			//发生无线程可用的风险，如果发生这种情况发，tomcat会堆叠在内部服务器套接字多余的请求，如果有更多的请求进来会被拒绝，直到有空闲资源来处理他们
	
			//异步处理功能可以节约容器线程，你应该将此功能使用在长时间运行的操作上，此功能的作用是释放正在等待完成的线程，使该线程能够被另一请求所使用
			//注意：这个异步支持只适合你有一个长时间运行的任务并且要把运行结果通知给客户，如果你只有一个长期运行的任务，但用户并不需要直到处理结果，那么你可以提交一个Runnable
			//给Executor(执行器)并立即返回
				//例如：当你需要生成报告（需要一段时间），当它生成完毕时通过邮件发送这个报告，这时servlet异步处理功能不是最佳的解决方案
				//如果你需要生成报告，并在报告准备好时显示给客户，这时异步处理可能就是你想要的
	
			//编写异步servlet和过滤器
				//WebServlet和WebFilter注解类型可能包含新的asyncSupport属性，要编写支持异步处理的Servlet或过滤器，需要设置asyncSupport属性为true
					//@WebServlet(asyncSupport="true")
					//@WebFilter(asyncSupport="true")
				//也可以在部署描述符文件里指定这个描述符
					//<servlet>
						//<servlet-name>name</servlet-name>
						//<servlet-class>class-path</servlet-class>
						//<async-support>true</async-support>
					//</servlet>	
	
				//servlet或过滤器要进行异步操作，可以通过调用ServletRequest的startAsync方法来启动一个新的线程，有两个重载的方法
					//1:AsyncContext startAsync() throws exception
					//2:AsyncContext startAsync(ServletRequest request,ServletResponse response) throws exception
						//这两个重载方法都返回一个AsyncContext实例，这个实例提供各种方法并且包含ServletRequest,ServletResponse
						//方法1比较简单，由此生成的AsyncContext将包含原生的ServletRequest,ServletResponse
						//方法2允许你将原来的ServletRequest,ServletResponse进行重写封装后传递给AsyncContext,需要注意的是你只能传递原生的和封装过的到startAsync方法（封装在上一节说过）
					//startAsync重复调用将返回相同的AsyncContext,若一个servlet或过滤器调用startAsync时不支持异步处理，将抛出异常，
					//AsyncContext的start方法是非阻塞的，所以下一行代码任然执行，，即使还未调度线程qi'dong
	
	
			//编写异步servlet:当有一个需要相当成时间完成的任务时，需要创建一个异步servlet或过滤器，在其中需要做如下操作
				//1：调用ServletRequest的startAsync()方法，该方法返回一个AsyncContext
				//2:调用AsyncContext的setTimeOut()，传递容器等待任务超时时间的毫秒数，此步骤是可选的，如果不设置，将使用默认时间，如果超时未完成，将抛出一个异常
				//3：调用AsyncContext的start方法，传递一个runnable来执行一个长时间的任务，
				//4：调用runnable的AsyncContext.complete或AsyncContext.dispatch方法来完成任务
				
	//异步监听器
		//为支持Servlet和过滤器配合执行异步操作，servlet3.0还增加了asynclistener接口用于接收异步处理过程中发生事件的通知，
		//该接口定义了如下方法：
			//void onStartAsync(AsyncEvent event)//在异步操作启动完毕后调用该方法
			//void onComplete(AsyncEvent event)//在异步操作完成后调到该方法
			//void onError(AsyncEvent event)//在异步操作失败后调用该方法
			//void onTimeout(AsyncEvent event)//在异步操作超时后调用该方法：即它未在指定时间内完成时调用
		//所有四种方法可以分别通过他们的getAsyncContext,getSuppliedRequest,getSuppliedResponse,方法
		//从AysncEvent中获取相关的AsyncContext,ServletRequest,ServletResponse
	
	//小结：servlet3.0/3.1自带用于处理异步操作的功能，当你的servlet/jsp应用程序需要一个长时间的操作功能时，使用异步，，他会将这些耗时操作分配给一个新的线程
	//从而将请求处理线程放回池中，准备好服务另一个请求，包括异步的servlet,filter，在前两者中添加异步的监听器（AsyncListener）
	
}
