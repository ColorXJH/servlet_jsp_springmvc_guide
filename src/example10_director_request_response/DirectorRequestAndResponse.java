package example10_director_request_response;

import com.sun.xml.internal.ws.api.Component;

public class DirectorRequestAndResponse {
		//Servlet API包含4个可修饰的类，用于改变ServletRequest和ServletResponse，这种修饰允许修改ServletRequest以及ServletResponse
		//或者http中的等价类（HttpServletRequest/HttpServletResponse）中的任务方法，这种修饰遵顼Decorator/Wrapper模式，在使用前先连接以下装饰器模式
		
		//从Decorator开始，说明如何通过修饰HttpServletRequest来修改该对象的行为，该技术同样适用于HttpServletResponse对象
	
	
		//Decorator模式
			//允许修饰或者封装一个对象，即使你没有该对象的源代码，或者该对象标识为final，该模式适用于无法继承的类（例如对象实现类使用final标识）
			//或者无法创建该类的实例，但可以从另外的系统中可以取得该类的实现时
				//例如：Servlet容器方法，只有一种方法可以修改servletRequest或者ServletResponse行为，即在另外的对象中封装该实例，唯一的限制是：修饰对象必须继承一个接口
				//然后实现接口以封装这些方法
					/*
									interface
									componemt
								methodA void
								|				|<>
							componentImpl		Decorator
							methodA				methodA 
													|
												DecoratorA
												methodA
					*/
					
					//一个component接口和其实现类，为了修饰其实现类componentImpl的实例，需要创建一个Decorator类，并实现component接口
					//然后在子类中扩展Decorator的新行为，每个Decorator实例需要包含component的一个实例
						/*class Decorator implements Component{
							private Component decorated;
							public Decorator(Component decorated) {
								this.decorated=decorated;
							}
							@Override
							public void methodA() {
								decorated.methodA()
							}
						}*/
					//Decorator类以及被修饰的类需要实现相同的接口，为了实现Decorator,可以在其中封装被修饰的对象，并把decorator作为component的一个实现，事实上你可以把修饰对象传入另一个修饰对象，实现双重修饰
	
	
		//Servlet封装类
			//ServletAPI源自4个实现类
				//1:ServletRequestWrapper
				//2:ServletResponseWrapper
				//3:HttpServletRequestWrapper
				//4:HttpServletResponseWrapper
			//ServletRequestWrapper(或其他3个wapper类)非常便于使用，因为它提供了每个方法的默认实现
				//ServletRequest封闭的配置方法，通过继承ServletRequestWrapper,只需要实现你需要变更的方法
				//如果不用ServletRequestWrapper，则需要继承ServletRequest并实现其中的所有方法
				//servlet容器在每次servlet服务调用时创建servlettequest,containerImpl.直接扩展ServletRequestWrapper就可以修饰ServletRequest了
	
	//小结：ServletAPI中，可以继承4个封闭的类：
		//1:ServletRequestWrapper
		//2:ServletResponseWrapper
		//3:HttpServletRequestWrapper
		//4:HttpServletResponseWrapper
	//用于修饰servlet请求以及响应，Filter或listener中可以使用它们创建Servlet封装，并将它们传入servlet服务方法中
		
	
	
	
	
	
	
	
	
	
}
	