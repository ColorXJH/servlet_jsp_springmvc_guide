package example18_base_on_annotation_controller;

import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseOnAnnotationControllerGuide {
			//基于注解的控制器
				//springMVC注解类型，使用基于注解的控制器有几个优点
					//1：一个控制器可以处理多个动作(实现一个Controller接口只能处理一个动作)，减少了控制类的数目
					//2：基于注解的控制器的请求映射不需要存储在配置文件中，使用@RequestMapping注解类型，可以对一个方法进行请求处理
	
			//@Controller注解类型
				//org.springframework.stereotype.Controller注解类型用于指示spring类的实例是一个控制器，下面是一个带注解的例子
					//@Controller
					//public class CustomerControlelr{}
				//spring使用扫描机制来找到应用程序中所有基于注解的控制器类，为了保证spring能够找到你的控制器，需要完成两件事情
					//1：需要在springMVC的配置文件中声明spring-context,以及对应的xsi:schemaLocation
						//xmlns:context="http://www.springframework.org/schema/context".....
					//2:需要应用<component-scan/>元素
						//<component-scan base-package="basePackage"/>
							//在该元素中指定控制器的基本包，例如所有的控制器都在com.example.controller下，则需要写一个如下的组件扫描元素
								//<component-scan base-package="com.example.controller"/>
			
			//@RequestMapping注解类型
				//要让spring知道用哪一种方法来处理它的动作，需要使用org.springframework.web.bind.annotation.RequestMapping注解类型映射的url与方法
					//映射一个请求和一个方法，可以使用它注解一个方法或一个类	
					//一个采用@RequestMapping注解的方法将成为一个请求处理方法，并由调度程序在接到对应url请求时调用
						//value属性是该注解的默认属性，因此若只有唯一的属性，则可以省略属性名称
							//@RequestMapping(value = "/customer_input")======@RequestMapping("/customer_input")
						//除了value属性以外还有其他属性，例如method属性指示该方法仅处理哪些http方法
							//若method属性只有一个http方法值，则无需括号：@RequestMapping(value="/desyt" method=RequestMethod.POST)
							//如果没有指定method属性，则请求处理方法可以处理任何http方法
					//此外该方法也可以用来注解一个控制器类，在这种情况下，所有的方法都将映射为相对于类级别的请求
			
			//编写请求处理方法
				//每一个请求处理方法有多个不同类型的参数，可以为http'session,也可以为httpservletrequest
				//下面是可以在请求处理方法中出现的类型参数
					//1:javax.servlet.ServletRequest/javax.servlet.http.HttpServletRequest
					//2:javax.servlet.ServletResponse/javax.servlet.http.HttpServletResponse
					//3:javax.servlet.http.HttpSession
					//4:org.springframework.web.context.request.WebRequest/org.springframework.web.context.request.NativeWebRequest
					//5:java.util.Locale
					//6:java.io.InputStream/java.io.Reader
					//7:java.io.OutputStream/java.io.Writer
					//8:java.security.Principal
					//9:HttpEntity<?>
					//10:java.util.Map/org.springframework.ui.Model
					//11:org.springframework.ui.ModelMap
					//12:org.springframework.web.servlet.mvc.support.Redirect
					//13:org.springframework.validation.Errors
					//14:org.springframework.validation.BindingResult
				//命令或表单对象
					//1:org.springframework.web.bind.support.SessionStatus
					//2:org.springframework.web.util.UriComponentsBuilder
					//3:带@PathVariable,@MatrixVariable注释的对象
					//4:@RequestParam,@RequestHeader,@RequestBody,@RequestPart

		//特别重要的是org.springframework.ui.Model类型，这不是一个servletAPI类型，而是一个包含MapdespringMVC类型
		//每次调用请求处理方法时,springMVC都创建model对象并将各种对象注入到map中
		
		//请求处理方法可以返回如下类型的对象
			//1:ModelAndView
			//2:Model
			//3:Map包含模型的属性
			//4:View
			//5:代表逻辑视图名的String
			//6:void
			//7:提供对servlet的访问，以响应http头部和内容HttpEntity或ResponseEntity对象
			//8:Callable
			//9:DeferredResult
			//10:其他任意类型，Spring将其视作输出给view的对象模型
		
		
		//应用基于注解的控制器
			//见案例example18
		
		//应用@Autowired和@Service进行依赖注入
			//org.springframework.beans.factory.annotation
			//org.springframework.stereotype
		
		//重定向和flash属性
			//要知道转发与重定向的区别：转发比重定向快，因为转发没有经过客户端，重定向需要经过客户端，但有时重定向更好
			//比如需要重定向到一个外部网站
			//另一个使用重定向的场景是避免用户重新加载页面时再次调用相同的动作，比如提交表单
			//你可能更希望将用户重定向到另一个页面
				//return "redirect:/product_view"
	
			//使用重定向有一个不便之处就是：无法轻松的传值给目标页面，而采用请求转发则可以简单的添加属性到model,使得目标可以轻松访问
			//由于重定向经过客户端，所以model中的一切都在重定向时丢失，幸运的是spring3.1之后，通过flash属性提供了一种重定向传值的方法
				//要使用flash属性必须在springmvc配置文件中使用<annotation-driven/>元素，然后在方法中添加一个新的参数类型：org.springframework.web.servlet.mvc.support.RedirectAttribute
				//@ModelAttribute在方法参数中接收该值
			
		//请求参数和路径变量
			//请求参数和路径变量都可以用于值发送给服务器，二者都是url的一部分，请求参数采用k/v的形式，并用&分隔
				//http://localhost:8080/springproject/index?name=xjh&age=26
			//在传统的servlet编程中，可以使用HttpServletRequest的getParameter方法获得一个请求的参数值
				//String name=httpServletRequest.getParameter("name")
			//springMVC提供了一个更简单的方法来获取请求的参数值：通过使用org.springframework.web.bind.annotation.RequestParam注解类型来注解参数
				//public void sendProduct(@RequestParam String name)
			
			//路径变量类似请求参数，但是没有key部分，只是一个值：
				//  /springMVC/project_id
					//project_id在springMVC中被称作路径变量，用来发送一个值到服务器
				//通常在一个请求中使用@PathVariable注解来接收这个路径变量，而且在方法的映射参数@RequestMapping中，还需要在url中书写路径参数的占位符{}
					//@RequestMapping("/project/{name}")
					//public void getName(@PathVariable String name){...}
				//可以在请求映射中使用多个路径变量
				//有时候使用路径变量会遇到一个小问题，某些情况下浏览器会误解路径变量，如下面的url
					//	http://example.com/context/abc
						//浏览器会(正确)认为abc是一个动作，任何静态文件路径的解析，如css时，将会使用http://example.com/context作为基本路径
						//这就是说，如果服务器发送的网页包含如下img元素：<img src="logo.png"/>
						//该浏览器视图通过http://example.com/context/logo.png来加载logo.png
						//然而如果一个应用程序部署了默认上下文(默认上下文路径是一个空字符串)，队医同一个目标的url会是如下的：
							//http://example.com/abc
						//下面是带哦有路径的url: http://example.com/abc/1
							//在这种情况下浏览器会认为abc是上下文，如果页面中使用<img src="logo.png"/>
							//浏览器将试图通过：http://example.com/abc/logo.png来寻找图像，最终将不会找到图像
					//幸运的是我们有一个简单的解决方案，通过jstl的url标签来修复这个问题，例如将如下的导入的css：
						//<style type="text/css">@import url(css/main.css)</style>
						//修改为如下格式：
						//<style type="text/css">@import url("<c:url value="/css/main.css"/>")</style?
					//若程序部署为默认上下文，上述标签会将url转换为如下形式
						//<style type="text/css">@import url("/css/main.css")</style>
					//若是不再默认上下文中部署，会变为如下形式：
						//<style type="text/css">@import url("/springMvc/css/main.css")</style>
	
				//@ModelAttribute
					//springMVC在每次调用请求处理方法的时候，都会创建model类型的一个实例，若打算使用该实例，则可以在方法中添加一个Model类型的参数
					//事实上还可以使用在方法中添加@ModelAttribute注解类型来访问Model实例，该注解也是org.springframework.web.bind.annotation包的成员
					//可以使用@ModelAttribute来注解方法参数或方法
						//1：带@ModelAttribute注解的方法会将其输入的或创建的参数对象添加到Model对象中(若方法中没有显式的添加	)
							//例如下免：springMVC将会在每次调用onSubmit方法时创建一个Order实例
								//@RequestMapping(method=RequestMethod.POST)
								//public String onSubmit(@ModelAttribute("newOrder")Order order,Model model){}
									//输入或创建的Order实例对象将使用键名newOrder添加到Model对象中，如果未定义键名，则使用该对象的名称
						//2：@ModelAttribute的第二个用途是标注一个非请求的处理方法，被@ModelAttribute注解的方法会在每次调用该控制器的处理请求方法时被调用
							//这就意味着如果一个处理器有两个请求控制方法，则该注解的方法会被调用两次
						//springMVC会在请求处理方法之前调用带@ModelAttribute注解的方法，带注解的方法可以返回一个对象或者一个void类型，若返回一个对象，该对象会自动添加到Model中
							//@ModelAttribute
							//public Product addProduct(String id){return productService.get(id)}
							//若方法返回一个void,则还必须添加一个Model类型的参数，并自行将实例添加到Model中：
								//@ModelAttribute
								//public void someMode(@RequestParam String id,Model model){model.addAttribute(new Account(id))}
	
			//小结：学会编写基于注解的控制器，使用一些注解类，方法，参数的注解类型
	
	
	
	
}
