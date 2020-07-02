package example03_jsp;

public class Jsp_guide {
	//servlet有两个缺点是无法克服的：
		//1：写在servlet中的所有html标签必须包含java字符串，这使得处理http响应报文十分麻烦
		//2：所有的文本和html都是硬编码，即使表现呈出现微小变化，也需要重新编译
	//java server page(jsp)解决了上述两个问题，同时jsp不会取代servlet,他们具有互补性
	
	//jsp概述
	//jsp本质上是一个servlet,然而jsp页面比servlet开发更容易，1：不必编译jsp页面，2作为一个扩展名为.jsp的文本文件，可以用任何编辑器编辑
	//jsp页面在jsp容器中运行，一个servlet容器通常也是jsp容器，例如tomcat就是一个servlet/jsp容器
	
	//当一个jsp页面第一次被请求时，servlet/jsp容器主要做以下两件事情
		//(demo01.jsp==>demo01_jsp extends org.apache.jasper.runtime.HttpJspBase implements org.apache.jasper.runtime.JspSourceDependent)
		//1：转换jsp页面到jsp页面实现类，该实现类是一个是实现javax.servlet.jsp.JspPage接口或子接口javax.servlet.jsp.HttpJspPage的Java类
		//JspPage是javax.servlet.Servlet的子接口，这使得每一个jsp页面都是一个servlet,该实现类的类名由servlet/jsp容器生成，如果出现转换错误，错误信息会被发送到客户端
		//2：如果转换成功，servlet/jsp容器随后编译该servlet类，并装载和实例化类，像其他servlet一样执行生命周期操作
	//对于同一个jsp页面的后续请求：servlet/jsp容器会先检查jsp页面是否被修改过，如果是则重新编译该页面，如果不是则执行已经在内存中jsp servlet,这样，一个jsp页面第一次调用时间比后来调用多
	//因为设计编译和翻译，为了解决这个问题可以执行下列动作，
		//1：配置应用程序，使所有的jsp页面在应用启动时被调用（实际上也可视为翻译和编译），而不是在第一次请求时调用
		//2：预编译jsp页面并将其部署为servlet
	//Jsp API包含4个包：
		//1：javax.servlet.jsp:包含用于servlet/jsp容器将jsp页面翻译为servlet的核心类和接口，其中的两个重要成员是JspPage/HttpJspPage,所有的jsp页面实现类必须实现前面两个接口，在http环境下，实现HttpJspPage
		//2:javax.servlet.jsp.tagext:包括用于开发自定义标签的类型
		//3：javax.el:提供了统一表达式的语言
		//4：javax.servlet.jsp.el:提供了一组必须由servlet/jsp容器支持，以便在jsp页面中使用表达式语言的类
	//除了javax.servlet.jsp.tagext,我们很少直接使用jsp的api,事实上编写jsp页面时我们更关心servlet api ,在开发jsp容器或jsp编译器时，jsp api已经被广泛使用
	//jsp页面可以包含模板数据和语法元素，这里语法元素是一些具有特殊意义的转换符，例如：“<%”是一个元素，代表java代码块的开始，“%>”也是一个元素，代表Java代码块的结束，除去语法元素外的一切数据是模板数据，模板数据会原样发给浏览器，例如，html标记和文字
	//在demo01.jsp在第一次请求时被翻译成demo01_jsp,可以在tomcat的工作目录的子目录中找到，该servlet继承自org.apache.jasper.runtimeHttpJspBase,这是一个抽象类，继承自javax.servlet.http.HttpServlet并实现了javax.servlet.jsp.HttpJspPage
	//一个jsp不同于servlet的另外一个方面是，前者不需要添加注解或在部署描述符中配置映射url,在应用程序目录的每一个jsp页面可以直接在浏览器中输入路径页面访问（放在webcontent目录下）
	//添加新的jsp页面后无需重启tomcat
	
	//java代码可以出现在jsp的任何位置，并通过“<% %>”包裹起来
	//可以使用page指令的import属性导入在jsp页面中使用的Java类型，如果没有导入的类型，必须写Java类的全路径名称
	//<% %>被称为scriptlet
	//页面访问：http://localhost:8080/servlet_jsp_springmvc_guide/jsp/example03/todaysDate.jsp
	
	//注释
	//jsp页面支持两种不同的注释
		//1：jsp注释：该注释记录页面做了什么，以“<%--”开始，以“--%>”结束：<%-- 这是一个jsp注释  --%>，这个注释不会发送到浏览器也不会被潜逃
		//2: html/xhtml注释：这些注释将会发送到浏览器上，<!-- 这是一个html注释 -->，该注释不会被容器处理，会原样发给浏览器，一般用来确定jsp页面本身
	
	//隐式对象
	//servlet容器会传递几个对象给它运行的servlet,例如可以通过servlet的service方法拿到httpservletrequest/httpservletresponse对象，以及可以通过init方法访问到servletconfig对象
	//此外可以通过调用httpservletrequest对象的getSession方法访问到httpsession对象
	//在jsp中，可以通过隐式对象来访问上述对象。隐式对象如下：
		//1：request              javax.servlet.http.HttpServletRequest
		//2:response			  javax.servlet.http.HttpServletResponse
		//3:out					 javax.servlet.jsp.JspWriter
		//4:session				  javax.servlet.http.HttpSession
		//5:application          javax.servlet.ServletContext
		//6:config				 javax.servlet.ServletConfig
		//7:pageContext		     javax.servlet.jsp.PageContext
		//8:page			      javax.servlet.jsp.HttpJspPage
		//9:exception			 java.lang.Throwable
	
	//以request为例：该隐式对象代表servlet/jsp容器传递给servlet服务方法的httpservletrequest对象，可以将request理解为一个指向httpservletrequest对象的引用
		//例如：从httpservletrequest对象中返回username:<% String username=request.getParameter("username")%>
			//该参数在请求中携带get/post
	
	//pageContext用于javax.servlet.jsp.PageContext,它提供了有用的上下文信息，并通过其自说明的方法来访问各种servlet相关对象
	//如：getRequest,getResponse,getServletContext,getServletConfig,getSession,当然这些方法在脚本中不是很有用，可以更直接的通过隐式对象来访问
	//如：request    response    application       config           session
	//此外PageContext中提供了一组有趣的方法，用于获取和设置属性：getAttribute/setAttribute方法，属性值可以存储在四个范围之一：页面（page）,请求（request）,会话（session），应用程序（application）
	//签名如下：void setAttribute(String name,Object value, int scope)//scope为：PAGE_SCOPE,REQUEST_SCOPE,SESSION_SCOPE,APPLICATION_SCOPE
	//保存在jsp页面可以直接使用重载方法：void setAttribute(String name,Object value)
		//pageContext.setAttribute("XJH", "HAHA",pageContext.PAGE_SCOPE);===request.setAttribute("XJH","HAHA")
	
	//out
	//out引用了一个javax.servlet.jsp.JspWriter对象，这类似于你在调用HttpServletResponse.getWriter时得到的java.io.PrintWriter,可以通过它调用print将消息发送到浏览器
	//在默认情况下，jsp编译器会将jsp页面的内容类型设置为“text/html”,如果要使用不同的类型，则需要调用response.setContentType(),或使用页面指令
	//还要注意的是：页面隐式对象标识当前的jsp页面，jsp页面的设计者一般不使用它
	
	//指令
	//指令时jsp语法元素的第一种类型，他们指示jsp转换器如何翻译jsp页面为servlet,jsp2.2定义了很多指令，但是只有page/include最重要
	
	//page指令
	//可以使用page指令来控制jsp转换器转换当前jsp页面的某些方面，例如：告诉jsp用于转换隐式对象out的缓冲区大小，内容类型，需要导入的java类型
	//语法如下：<% @ attribute1="value1" attribute2="value2"...%>
		//@和page间的空格不是必须的，attribute1...是page指令的属性，以下是属性列表
			//1：import:定义一个或多个本页面中将被导入和使用的java类型,例如 import="java.util.List"将导入list接口，可以使用通配符*来引入整个包：import="java.util.*"
			//也可以在两个类型间加入“,”来导入多个类型：import="java.util.List,java.util.Calendar",此外jsp默认导入如下包：java.lang,javax.servlet,javax.servlet.http,javax.servlet.jsp
			
			//2：session:值为true,本页面加入会话管理，值为false则相反，默认为true,访问该页面时，若当前不存在javax.servlet.http.HttpSession时，则会创建一个
	
			//3：buffer:以KB为单位，定义隐式对象out的缓冲大小，必须以KB后缀结尾，默认为8KB或更大（取决于jsp容器），该值可以为none,这意味着没有缓冲，所有数据将直接写入PrintWriter
			
			//4:autoFlush:默认值为true,当输出缓冲满时将自动写入输出流，若为false,则仅当调用隐式对象的flush方法时，才会写入输出流，因此若缓冲区溢出则会抛出异常
			
			//5：isThreadSafe:定义该页面的线程安全级别，不推荐使用jsp参数，因为使用改参数后，会生成一些servlet容器已过期的代码
			
			//6：info:返回调用容器生成的servlet类的getServletInfo方法的结果
	
			//7:errorPage:定义当出错时用来处理错误的页面
			
			//8:isErrorPage:标识本页面是一个错误处理页面
	
			//9：contentType:定义本页面隐式对象response的内容类型,默认为“text/html”
			
			//10:pageEncoding:定义本页面的字符编码，默认是iso-8859-1
	
			//11:isElIgnored:配置是否忽略el表达式，el式expression language的缩写
	
			//12:language:定义本页面的脚本语言类型，默认java,这在jsp2.2中是唯一的合法值
	
			//13:extends:定义jsp实现类要继承的父类，很少使用
		
			//14:deferredSyntaxAllowedAsLiteral:定义是否解析字符串中的“#{”符号(表达式语言的起始符号)，默认为false,
	
			//15:trimDirectiveWhitespaces:定义是否不输出多余的空行/格，默认为false
	//大部分page指令可以出现在页面的任何位置，但是当page指令包含contentType或pageEncoding属性时，其必须出现在java代码发送任何内容之前
	//page指令也可以出现多次，但是多次的属性必须又相同的值，不过import是例外，他是累加的
		//<%@ page import="java.util.List"%> <%@ page import="java.util.Calendar"%>
		//等价于：<%@ page import="java.util.List,java.util.Calendar"%>
	//一个page指令可以有多个属性：<%@ page import="java.util.List" session="false" buffer="32kb"%>
	
	//include指令
	//可以使用include指令将其他文件中的内容包含到当前jsp页面，一个页面中可以有多个include指令，若存在一个内容会在多个页面中使用或一个页面不同位置使用的场景，可以将该内容模块化到一个include文件非常有用
	//include指令的语法如下：<%@ include file="url"%>
		//其中@和include之间的空格不是必须的，url为被包含文件的相对路径，若url以一个斜杠“/”开始，则该url为文件在服务器上的绝对路径，否则为当前jsp页面的相对路径
			//在被插入的页面加入：<%@ page pageEncoding="utf8"%>可以防止中文乱码
		//按照惯例，以jspf为扩展名的文件代表jsp fragement(片段)，jsp fragement现在被称为jsp segment(部分，段)
		//include指令也可以包含静态html文件
	
	//脚本元素
	//一个脚本程序是一个Java代码块包含在<% %>中，定义在一个脚本程序中的变量可以被后续的脚本程序使用
	
	//表达式
	//每个表达式都会被jsp容器执行，并使用隐式对象out答应方法输出结果，以“<%=”开始，以“%>”结束
	//例如：<%=java.util.Calendar.getInstance().getTime()%> 注意表达式无需分号结尾
		//jsp容器首先执行java.util.Calendar.getInstance().getTime()，并将计算结果传递给out.print(),等同如下效果：
		//<%
		//		out.print(java.util.Calendar.getInstance.getTime());	
		// %>
		
	//声明
	//可以声明能在jsp页面中使用的变量和方法，声明以“<%!”开始，以“%>”结束，如下声明一个放啊并调用
		//<%! pubic String getTodaysDate(){return new java.util.Date();}%>
		//today is <%=getTodaysDate()  %>
		//<% out.print("today is"+getTodaysDate()); %>
		//声明既可以放在表达式中也可以放在脚本中（上面两个就是）
		//jsp页面中，一个声明可以出现在任何地方，一个页面可以有多个声明
	
	//可以通过声明来重写jsp页面，实现类的init和destory方法，通过调用jspInit和jspDestory来重写init和destory方法，这两个方法说明如下
		//1：jspInit:这种方法类似于javax.servlet.Servlet的init方法，jsp页面在初始化期间调用jspInit,不同于init方法，jspInit没有参数，还可以通过隐式对象config访问servletconfig对象
		//2:jspDestory:这种方法类似于servlet的destory方法，在jsp页面将被销毁时调用
	
	//禁用脚本元素
	//随着jsp2.0对表达式语言的加强，推荐的实践是：在jsp页面中使用el表达式访问服务器端对象且不写java代码，因此在部署描述符中可以使用<jsp-property-group>元素中定义一个scripting-invalid,来禁用脚本元素
		/*
		 * <jsp-property-group>
				<url-pattern>*.jsp</url-pattern>
				<scripting-invalid>true</scripting-invalid>
		   </jsp-property-group>	
	    */
	
	//动作
	//动作是第三种类型的语法元素（1，指令：<%@ page %>,<%@ include%>..2脚本元素：<% %>(java脚本),<%= %>(表达式),<%! %>(声明)）
	//他们被转换为java代码来执行操作：如访问一个java对象或调用方法，现讨论jsp容器支持的标准动作，除此之外还可以创建自定义标签执行某些操作
		//useBean:创建一个关联java对象的脚本变量<jsp:useBean id="today" class="java.util.Date">  <%=today%>
		//setProperty/getProperty:可以对一个Java对象设置/输出属性:<jsp:setProperty name="employee" property="age"/>
		//include:用来动态的引入另一个资源，可以引入一个jsp页面，也可以引入一个servlet或一个静态的html:<jsp:include page="include/footer.jsp"/>
			//理解include指令和include动作十分重要：
				//1：对于include指令，资源引入发生在页面转换时，即当jsp容器将页面转换为servlet时，（也成为静态的include）
				//2：对于include动作，资源引入发生在请求页面时，因此使用include动作是可以传递参数的，而include指令不行（动态的include）
				//3：include指令对引入的文件扩展名不做特殊要求，include动作若引入的文件需要以jsp页面处理，则其文件扩展名必须为jsp,若是使用jspf,该页面会被当成静态文件
	
	//forward
	//forward将当前页面转向其他资源：<jsp:forward page="footer.jsp"><jsp:param name="text" value="hahah"/></jsp:forward>			
	
	//错误处理
	//jsp提供了很好的错误处理能力，除了在java脚本中执行try-catch，还可以指定一个特殊的页面，当引用页面遇到异常时，使用错误页面展示信息
		//使用page指令的isErrorPage属性（属性值为true）指定错误页面
		//其他需要防止未捕获异常的页面使用page指令的errorPage指向错误处理页面
	
	
	//小结：jsp是构建javaweb应用程序的第二种技术（第一种为servlet）,是servlet技术的补充，了解jsp的9大隐式对象，了解jsp页面的三个语法元素
	//1：指令--page/include 2:脚本元素--<% %>java脚本/   <%= %>java表达式/    <%! %>java变量。方法声明
	
	
}
