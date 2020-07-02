package example01_servlets;

import javax.servlet.ServletContext;

public class learning_servlet_and_jsp {
	//java servlet 技术简称 servlet技术，是java开发web应用的底层技术，1996年发布，用来代替CGI(当时生成web动态内容的主流技术)
	//CGI的主要问题是每个web请求都要启动一个新进程来处理，创建进程会消耗很多cup周期，servlet有着比CGI更好的性能，因为servlet在创建后
	//(处理第一个请求时)就一直保存在内存中，此后sun发布了Java server page(JSP)技术，进一步简化servlet程序开发
	//自从servlet和jsp技术诞生后，有很多java的web框架出现，比较出名的有struts,springmvc
	//springmvc又叫springwebmvc，是spring框架的一个模块，用于快速开发web应用，mvc代表model-view-controller,是一个广泛应用于GUI
	//开发的设计模式，该模式不限于web开发，也应用于桌面技术开发，如：Java swing和javaFX
	
	//servlet/jsp应用架构
		//servlet是一个Java程序，一个servlet应用有一个或多个servlet成勋，jsp页面会被转换和编译成servlet程序
		//servlet应用无法独立运行，必须运行在servlet容器中，servlet容器（一般是tomcat）将用户请求传递给servlet应用
		//并将结果返回给用户，由于大多数servlet应用都包含多个jsp页面，因此更准确的说是“servlet/jsp”
		
		//web用户通过web浏览器（ie/chrome）来访问servlet应用，web浏览器又叫web客户端
		/*								servlet/jsp容器
		 * 			请求                  
		 *web浏览器--------》 			servlet servlet servlet ...
		 *		《--------
		 * 			响应 
		 */
	
	//web服务器和web客户端间通过http协议通信，因此web服务器也叫http服务器
	//servlet/jsp容器是一个可以同时处理servlet和静态内容的web容器, tomcat/jetty是当前流行的servlet/jsp容器
	//要运行javaee应用，需要一个javaee容器，常见来的javaee容器有GlassFish,Jboss,Oracle Weblogic或者IBM WebSphere
	//诚然我们可以将一个servlet/jsp应用部署到一个javaee容器上，但是一个servlet/jsp就已经满足需要了，并且更加轻量级，
	//tomcat/jetty不是javaee容器，因此无法运行ejb或jms技术
	
	//http协议使得web服务器与浏览器之间可以通过互联网或内网进行数据交互
	//web服务器7*24小时不间断运行，等待http客户端（通常是web浏览器）来连接并请求资源
	//RFC6455G规范，该协议允许一个http连接升级为一个websocket连接，支持双向通信，这就使得服务端可以通过websocket协议主动发起同客户端的会话通信
	
	//互联网用户需要通过点击或输入一个URL连接或地址来访问一个资源
	//http:google.com/index.html
	//url的第一部分是http,代表所采用的协议，除了http外url也可以采用其他的协议：
	//	mailto:joe@example.com
	//	ftp://marketing@ftp.example.org
	//通常http的url格式如下：
	//      protocol://[host.]domain[:port][/context][/resource][?query string]
	//或者  protocol://IP address[:port][/context][/resource][?query string]
		//中括号中的类容是可选的：因此最简单的url大致如下：http://google.com或者http://192.168.2.11
		//可以用ping命令获取域名对应的ip地址
	//由于ip地址不容易记住，实践中更倾向于使用域名，一台计算机可以托管不知一个域名，因此不同的域名可能指向同一ip
	//url中的host表示在互联网或内网中的一个唯一地址，通常www作为默认的主机名，http:www.baidu.com会被映射到http://baidu.com
	//但是http://yahoo.com(没有host)访问的地址完全不同于http:mail.yahoo.com(有host)
	
	//http默认的端口是80，因此对于采用80端口的服务器，可以无需输入端口号，但是有的时候web服务器未必运行在80端口上，此时必须输入响应的端口号，例如tomcat为8080
	//http:localhost:8080  localhost作为一个保留关键字，用于指向本机
	//url中的context部分用来代表应用名称，该部分是可选的，，一个web服务器可以运行多个上下文，对于访问默认上下文中的资源，可以跳过context部分
	//最后，一个context可以有一个或多个默认资源，通常为index.html,index.htm...,一个没有带资源名称的url通常指向默认资源，当存在多个默认资源时，优先级最高的优先返回
	//在资源名之后可以有一个或多个查询语句或路径参数，为k/v组
	
	//一个http请求包含三部分内容
		//1：方法/url协议/版本（http规范定义了7种类型的方法：get/post/head/options/put/delete/trace）
		//请求头信息
		//请求正文
	//一个http响应包含三部分
		//1：协议/状态码/描述
		//2:响应头信息
		//3：响应正文
	
	//ServletAPI有以下4个包
		//1：javax.servlet:其中包含定义Servlet	和servlet容器之间契约的类和接口
		//2：javax.servlet.http:其中包含http servlet和servlet容器之间契约的类和接口
		//3：javax.servlet.annotation:其中包含标注Servlet,Filter,Listener的标注，它还为被标注原件定义元数据
		//4：javax.servlet.descriptor:其中包含提供程序化登陆web应用程序的配置信息的类型
	//javax.servelt包种的主要类型有：
	    //<接口>Servlet     <接口>ServletRequest       <接口>ServletResponse    <接口>ServletContext
        //GenericServlet(实现servlet接口)      <接口>ServletConfig   <接口>RequestDispatcher  <接口>Filter
	
	//Servlet技术的核心时Servlet,它是所有servlet简介或直接实现的一个接口
	//Servlet接口定义了Servlet与Servlet容器之间的契约：Servlet容器将Servlet类载入内存，并在Servlet实例上调用具体的方法，在一个应用程序中，每种Servlet类型只能有一个实例
	//用户请求致使Servlet容器调用servlet的service方法并传入一个servletRequest和servletResponse实例，servletRequest种封装了http请求，因此开发人员
	//不必操作原始的http数据，ServletResponse表示当前用户的http响应
	
	//对于每一个应用程序，servlet还会创建一个servletContext实例，这个对象中封装了上下文（应用程序）的环境详情，每个上下文只有一个servletContext
	//每个servlet实例也都有一个封装servlet配置的servletconfig
	
	//servlet api
		//void init(servletconfig config)throws ServletException --声明周期方法
			//--当该servlet第一次被请求时，servlet容器会调用这个方法，这个方法在后续请求中不会再被调用，可以利用这个方法执行相应的初始化工作
			//--调用这个方法时，servlet容器会传入一个servletconfig,一般来说你会讲servletconfig赋值给一个类级别的变量，因此这个对象可以通过servlet类的其他点来使用
		//void service(servletRequest request,ServletResponse response)throws ServletException,java.io.IOException --声明周期方法
			//--每当请求servlet时，servlet容器就会调用这个方法，编写代码时是假设servlet要在这里被请求
		//void destroy() --声明周期方法
			//--当要销毁servlet时，容器会调用这个方法，例如：卸载应用程序，关闭servlet容器，一般在这个方法中编写清除代码
		//java.lang.String.getServletInfo()
			//--这个方法会返回servlet的描述，可以返回有用或者为null的任意字符串，
		//ServletConfig getServletConfig()
			//--这个方法返回由servlet容器传给init方法的servletconfig,但是为了让getservletconfig返回一个非null值
			//--必须将传给init方法的servletconfig赋值给一个类级变量
	
	//servlet实例会被一个应用程序中的所有用户共享，因此不建议使用类级变量，除非他们都是只读的，或者时juc.atomic包的成员
	//为了编译servlet,必须将ServletAPI中的所有类型都放在你的类路径下
	//servlet/jsp应用程序一般都有jsp页面，html文件，图片文件和其他资料，这些资源应该放在应用程序目录下
	//放在应用程序下的任何资源，用户只要输入资源url,就可以直接访问到，如果想让某一资源被servlet访问，但是不可以被用户访问，那么就要把他放在WEB-INF目录下
	//部署servlet/jsp应用程序时，建议将它bu'shu成一个war文件，war文件就是扩展名为.war的jar文件，将其复制到webapps目录下，当开始启动tomcat时，他会自动解压整个war文件
	
	//调用servlet
		//http:localhost:8080/servlet_jsp_springmvc_guide/my
	
	//ServletRequest
		//对于每一个http请求，servlet容器都会创建一个servletRequest实例，并将他传递给servlet的service方法
	//API
		//public int getContentLength()//返回请求主体的字节数，如果不知道字节长度，整个方法返回-1
		//public java.lang.String getContentType()//返回请求主题的mime类型，如果不知道类型则返回null
		//public java.lang.String getParameter(java.lang.String name)//返回指定请求参数的值
		//public java.lang.String getProtocol()//返回整个http请求的协议名和版本
			//getParameter(String key)是使用最多的方法，通常用于返回html表单域的值或者url中查询字符串的值，如果参数不存在返回null
	
	//ServletResponse
	//javax.servlet.ServletResponse接口表示一个Servlet响应，在调用servlet的service的方法前，servlet容器首先创建一个ServletResponse,并将它作为第二个人参数传递给service方法
	//servletResponse中定义的一个方法是getWriter,他返回一个可以向客户端发送文本的java.io.printWriter,默认编码格式是iso-8859-1
		//在发送任何html标签前，应该设置响应的内容类型：setContentType("text/html")--告诉浏览器将类容设置为html
	
	//ServletCongfig
	//当servlet容器初始化servlet时，servlet容器会给servlet的init方法传入一个servletconfig,servletconfig封装可以@WebServlet或部署描述符web.xml中传递给servlet的配置信息
	//这样的信息称为初始参数，一个初始参数有key/value两个元件，为了从servlet内部得到初始化参数，，要在init方法中使用servletconfig调用getInitParameter方法
		//java.lang.String getInitParameter(java.lang.String)//获取指定方法参数值
		//java.util.Enumeration<java.lang.String>getInitParameters()//获取所有初始化参数值
		//ServletContext getServletContext()//从servlet内部获取servletcontext
	
	//ServletContext
	//表示servlet应用程序，每个web应用程序只有一个上下文，在将一个应用程序同时部署到多个容器的分布式环境中，每台java虚拟机上的web应用都会有一个ServletContext对象
	//通过在servletconfig中调用getServletContext方法可以获得sevletcontext
	//有了servletcontext,就可以共享从应用程序中的所有资料处访问信息，并且可以动态注册web对象，保存在其中的对象称作属性，保存在servletcontext中的一个内部map中
	//API
		//java.lang.Object getAttribute(String name)
		//java.util.Enumeration<String>getAttributeNames()
		//void setAttribute(String name)
	
	//GenericServlet
	//实现Servlet接口编写servlet必须实现其全部方法，此外还需要将servlet对象保存在类级变量中，genericservlet抽象类实现了servlet和servletconfig接口，并完成以下任务
		//1：将init方法中的ServletConfig变量赋值给一个类变量，以便可以通过getServletConfig方法获取
		//2：为servlet接口中的方法提供默认的实现
		//3：提供方法，包围ServletConfig中的方法
			//如果覆盖带参数的init方法就会调用servlet中的init方法，并且要调用super.init(servletconfig)用来保存servletconfig,可以覆盖不带参数的init方法，这样ServletCinfig任然由genericservlet实例保存
		//唯一覆盖service方法且不需要情字保存servletconfig
	
	//httpservlet
	//大部分应用程序需要与httpservlet结合起来，这样可以 利用http提供的特性，javax.http.servlet中的许多类型覆盖了javax.servlet中的类型
	//主要类型：<接口>javax.servlet.Servlet<--(实现)--javax.servlet.GenericServlet<--(继承)--HttpServlet
			// <接口>javax.servlet.ServletRequest<--(继承)--HttpServletRequest
			// <接口>javax.servlet.ServletResponse<--(继承)--HttpServletRespons	
			// <接口>HttpSession    Cookie
	
		//httpservlet类覆盖了genericservlet,使用httpserlet时需要借助httpservletrequest/httpservletresponse
		//它覆盖了genericservlet的service方法，并在结尾调用一个新的service方法，整个方法的参数为httpservletrequest/httpservletresponse而不是servletrquest/servletresponse
		//通过将原始参数都转为http类型的，这种转换总会成功，因为servlet容器总会传入一个httpservletrequest/httpservletresponse
		//新的service方法会检测用来发送的http方法并调用7中方法之一，因此不再需要覆盖service方法了，常见是覆盖doget/dopost方法
		//与genericservlet的区别：
			//1：不用覆盖service方法，而是覆盖doget/dopost方法
			//2:使用httpservletrequest/response而不是servletrequest/response
	
	//httpservletrequest
	//表示http环境中的servlet请求，扩展servletrequest接口，新增了几个方法
		//1：String getContextPath()//返回请求上下文部分的url(不是整个url,只是上下文部分的url)
		//Cookie[] getCookies()//返回一个cookie对象数组
		//String getHeader(String name)//返回指定http标题的值
		//String getMethod()//返回生成整个请求的http方法名称
		//String getQueryString()//返回请求url中的查询字符串
		//httpSession getSession()//返回与整个请求相关的会话对象，如果没有将创建一个新的对象
		//httpSession getSession(boolean flag)//同上，只有在flag为true时才会创建新的session
	
	//HttpServletResponse
	//表示http环境中的servlet响应
		//void addCookie(Cookie c)//给整个响应对象增加一个cookie
		//void addHeader(String name,String value)//给这个响应对象增加一个header
		//void sendRedirect(String location)//发送一条响应码，将浏览器跳转到指定位置	
	
	//处理html表单
	//可以将一个html表单从servlet发送到浏览器，当用户提交表单时，表单元素中的输入值就会被当作请求参数发送到服务器
	//html输入域（文本。隐藏，密码域）servletrequest.getParameter不会返回null,如果select元素中没有任何选项被选中，那么就显示显示值
	//包含多个值的select(允许选择多个选项并且用<select multiple>表示的select元素，通过servletrequest.getParameterValues进行处理)
	//核查过的复选框会发送字符串“on”到服务器，未经核查的复选框不会向服务器发送任何类容，servletrequest.getparameter(name)返回null
	//单选按钮将被选中的值发送到服务器，如果没有选择任何按钮，将没有任何值发送到服务器，servletrequest.getparameter(name)返回null
	//如果表单包含多个同名输入数据，所有的值都会被提交，servletrequest.getparametervalues(name)获取他们，servletrequest.getParameter(name)只返回最后一个值
		//表单的方法为post,他的action属性默认，表示该表单会被提交到请求它时的相同url
	
	//使用部署描述符
	//编写和部署servlet都是很容易的事情，部署的一个方面是用一个路径配置servlet的映射，比如@WebServlet中value/urlPatterns
	//利用部署描述符是配置servlet的另一种方法
	//要创建一个servlet,并在部署描述符（web.xml）中哦欸之servlet和servletmapping
	//使用部署描述符的好处：
		//1：可以使用load-0n-startup:启动应用程序时加载该servlet而不是第一次访问该servlet时
		//2：修改配置值时不需要重新编译servlet类
		//可以将初始参数传递给servlet，<init-param>,使用getInitParam(String name)获取，在整个应用程序上下文中，可以在部署描述符中配置该参数:<context-param>,该参数只能在servlet的init方法中获得：getServletContext.getInitParamter(String name)
	
	
	//小结：servlet技术是Javaee的一部分，所有servlet都运行在servlet容器中，容器和servlet之间的接口为javax.servlet.Servlet,javax.servlet包
	//还为Servlet接口提供了一个实现类，是一个辅助类，可以更方便的而创建servlet
	//大部分servlet都运行在http环境中，因此派生一个javax.servlet.http.HttpServlet的子类更有用，他也是GenericServlet的子类
	
	
	
	
	
	
	
	
	
	
	
}
