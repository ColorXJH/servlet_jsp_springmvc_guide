package example04_eepression_language;

public class Expression_Language_Guide {
	//jsp2.0最重要的核心特征就是表达式语言(el),jsp用户可以用他们来访问应用程序数据，el设计成可以轻松编写免 脚本(jsp声明/表达式/scriptlet)的jsp页面
	//jsp2.0最初是将el应用在jsp标准标签库(jstl)1.0规范中
	//jsp2.1和jsp2.2中的el要将jsp2.0中的el与jsf(java server faces)中定义的el统一起来
		//jsf是java中快速构建web应用程序的框架，构建在jsp1.2之上
	
	//表达式语言的语法
		//el表达式以“${”开头，以“}”结尾：${expression}
		//它也常用来拦截两个表达式：计算结果类型为String:${c+d}(8),${a+b}(10),${c+d}${a+b}(810)
		//如果在定制标签的属性中使用el,<my:tag someAttribute="${expression}"/>,取值会是expression的属性值，如果只是需要文本值，需要转义el:<mg:tag someAttribute="\${expressiom\}"/>

	//关键字
		//下列是关键字，不能用作标识符
		//and eq lt gt true instanceof or ne false empty not le ge null div mod
	
	//[]和.运算符
		//el表达式可以返回任意类型的值，如果el表达式是一个带有属性的对象，则可以使用[]或.运算符来访问该属性
		//${object.property}/${object["property"]}
		//如果属性不是一个有效的java变量名，则只能使用[]
		//如果对象的属性也是一个对象，则可以接着访问(类似于导航语言)
	
	//取值规则：
		//el表达式的取值是从左到右进行的，对于expr-a[expr-b]
			//1:先计算expr-a得到value-a
			//2:如果value-a为null则返回null
			//3:然后计算expr-b得到value-b
			//4：如果value-b为null,则返回null
			//5:如果value-a为map,则会查看nvalue-b是否为map的一个key,若是则返回value-a.get(value-b),否则返回null
			//6:如果value-a为list/array
				//1:强制value-b为int,如果失败则抛出异常，并返回null
				//2:否则，则返回value-a.get(value-b)
			//7:如果value-a不是map/list/array,则value-a必须是一个javabean,必须强制value-b为string,如果value-b是可读的则调用getter，如果getter抛出异常，认为该表达式无效，否则有效
	
	//访问javabean
		//    ./[]
		//${javabean.property}   ${javabean["property"]}
	
	//el隐式对象
		//1：pageContext			这是当前jsp的javax.servlet.jsp.PageContext
			//包含了所有其他jsp隐式对象（request/response/out/config/pageContext/page/session/application/exception）
				//${pageContext.request/response/out/session}
		//2:initParam			这是一个包含所有环境初始化参数，并用参数名作为key的map
			//是整个servletcontext的参数，例如：web.xml中配置的<context-param>中的k/v
		//3:param				这是一个包含所有请求参数，并用参数名作为key的map,每个key的值就是指定名称的第一个参数值，因此如果两个请求参数同名，则只有一个可以利用param获取值，要想访问同名参数的所有值，需要用params代替
			//url请求中的参数
		//4:paramValues			这是一个包含所有请求参数，并用参数名作为key的map,每个key的值是一个字符串数组，其中包含了指定参数名的所有参数值
			//url请求中的参数（数组）
		//5:header				这是一个包含请求标题，并用标题名作为key的map,每个key的值，就是指定标题名称的第一个标题，如果一个标题的值不止一个，需要用headerValues
			//请求头中的参数
		//6:headerValues		这是一个包含请求标题，并用标题名作为key的mao,每个key的值是一个字符串数组
			//请求头中的参数（数组）
		//7:cookie				这是一个包含了当前请求对象中所有cookie对象的map,cookie名称就是key名称，并且每个key都映射到一个cookie对象
			//cookie对象 
		//8:applicationScope    这是一个包含了servletcontext对象中所有属性的map,并用属性名称作为key
			//应用程序范围内的属性
		//9:sessionScope		这是一个包含了httpsession对象中所有属性的map,并用属性名称作为key
			//session范围内的属性
		//10:requestScope		这是一个map,其中包含了当前httpservletrequest对象中的所有属性，并用属性名称作为key
			//请求范围内的属性
		//11:pageScope			这是一个map,其中包含了全页面范围内的所有属性，属性名称就是map的key
			//页面范围内的属性

		
	//注意：在servlet/jsp编程中，有界对象是指在以下对象中作为属性的对象：pagecontext/servletrequest/httpsession/servletcontext
	//隐式对象pagescope/requestscope/sessionscope/applicationscope相似，其范围是page/request/session/application
	//有界对象也可以通过没有范围的el表达式获取，在这种情况下，jsp容器将返回pagecontext,servletrequest,httpsession,servletcontext中第一个同名的对象，执行顺序是从小到大
	
	//el表达式的其他运算符
		//算术运算符，+ - * / % mod
		//逻辑运算符:&&和and  ||和or  !和not
		//关系运算符：== eq,!= ne,> gt,< lt,>= ge,<= le
		//empty运算符：检查某个值是否为null 或者empty:${empty x}
	
	//如何在jsp2.0及更高版本中配置el
		//有了el,javabeans和定制标签，就可以编写免脚本的jsp页面了
		//实现免脚本的jsp页面
			//在web.xml中配置<jsp-config><jsp-property-group><url-pattern>*.jsp</url-pattern><scripting-invalid></scripting-invalid></jsp-property-group></jsp-config>
			//部署描述符中只允许有一个jsp-config元素
		//禁用el计算
			//1：page指令：<%@ page isElIgnored="true"%>
			//2:部署描述符中使用jsp-property-group元素禁用el计算：<jsp-config><jsp-property-group><url-pattern>/noEl.jsp</url-pattern><el-ignored>true</el-ignored></jsp-property-group></jsp-config>
			//部署描述符的优先级高于page指令
	    //此外，如果使用的是与Servlet 2.3及其更低版本兼容的部署描述符，那么EL计算已经默认关闭，即便使用的是JSP 2.0及其更高版本的容器，也一样。



		//小结：如何使用el来访问javabeans和隐式对象(不同于jsp隐式对象，el的pageContext隐式对象直接包含了9个jsp隐式对象)，以及如何使用el预算符
	




















































}
