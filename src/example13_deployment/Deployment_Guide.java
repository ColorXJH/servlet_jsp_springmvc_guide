package example13_deployment;

public class Deployment_Guide {
		//部署
		//部署一个servlet3.0是一个轻而易举的事情，通过servlet注解类型，对于不太复杂的应用程序，可以部署没有描述符的servlet/jsp应用程序
		//在需要更加精细的配置下，部署描述符是必须的，首先部署描述符必须被命名为web.xml并且必须被放在web-inf目录下，java类必须放在web-inf/classes目录下
		//java类库必须放在web-inf/lib目录下，所有的应用程序必须打成一个以.war为后缀的jar文件
	
		//概述：
			//servlet3.0之后，部署描述符不是必须的，因为我们可以使用注解来映射一个url的资源模式，不过在若干场景下，则依然需要部署描述符、
				//1：需要传递初始参数给ServletContext
				//2:有多个过滤器，并且需要指定调用顺序
				//3:需要更改会话超时设置
				//4：要限制资源的访问，并配置用户身份验证方式
		
		//在web.xml中
			//xsi:schemaLocation属性制定了模式文档的位置，以便可以进行验证，version制定了servlet版本
			//可选的metadata-complete属性制定部署描述符是否是完整的，若值为true,则servlet/jsp容器将忽略注解，若值为false或不存在，则容器必须检查类文件的注解并扫描web fragment文件
			//web-app元素是文档的根元素，并且可以有如下子元素，
				//1：servlet声明
				//2：servlet映射
				//3：servletcontext的初始化参数
				//4：会话配置
				//5：监听器类
				//6：过滤器定义和映射
				//7:MIME类型映射
				//8：欢迎文件列表
				//9：错误页面
				//10：jsp特定的设置
				//11：JNDI设置
		
		//核心元素
			//web-app的子元素可以以任何顺序出现，有些元素如session-config，jsp-config,login-config只能出现一次，而另一些如Servlet,filter,welcome-file-list可以出现很多次
				//context-param:可用该元素传值给servletcontext，这些值可以被任意servlet/jsp页面读取(init方法中getServletContext().getInitParam(String)/${initParam.name}),context-param由名称/值对构成，可以定义多个context-param
					//其包含子元素：param-name/param-value,还有一个可选元素description
				//distribable:若定义了该元素标识程序部署到分布式的servlet/jsp容器中，该元素必须是空的：<distributable/>
				//error-page:包含一个http错误代码与资源路径或java异常类型与资源路径之间的映射，error-page元素定义容器在特定http错误或异常时应返回的资源路径，error-page元素由如下成分构成
					//error-code:指定一个http错误代码
					//exception-type:指定java的异常类型（全路径名称）
					//location:指定要被显示的资源位置，该元素必须以“/”开始
				//filter:制定一个servlet过滤器，该元素至少包含一个filter-name,filter-class,此外还可以包含icon,display-name,description,init-param,async-supported
					//filter-name:过滤器的名称必须全局唯一
					//filter-class:过滤器的全路径名称
					//init-param:配置过滤器的初始化参数，类似于context-param,一个过滤器可以有多个init-param
				//filter-mapping:过滤器映射元素，制定过滤器要被映射到的资源,可以过滤请求url,也可以过滤servlet
				//listener:注册一个监听器
					//listener-class:监听器的类全路径名称
				//local-encoding-mapping-list和local-encoding-mapping:第一个元素包含多个第二个元素，
					//local-encoding-mapping:定义了local以及编码的映射
						//local:国家语言编码
						//encoding:编码类型
				//login-config:元素包含auth-method,realm-name,form-login-config,每个元素都是可选的
					//auth-method:定义了认证的方式，可选值为：BASIC,DIGEST,FORM,CLLENT-CERT
					//realm-name:定义了用BASIC以及DIGEST认证方式的realm名称
					//form-login-config:定义了用form的方式登陆和错误的页面
						//form-login-page:登陆页面资源路径
						//form-error-page:登陆失败资源路径
				//mime-mapping:用来映射一个mime类型到一个扩展名，由一个extension元素和一个mime-type元素组成，如下
					//<mime-mapping>
						//<extension>txt</extension>
						//<mime-type>text</mime-type>
					//</mime-mapping>
				//security-constraint:该元素允许对一组资源限制访问，有一个可选的display-name,一个或多个web-resource-collection元素，可选的auth-constraint元素，和可选的user-data-constraint元素
					//web-resource-collection:标识一组需要限制访问的资源的集合，这里可以使用url模式和http方法限制
						//web-resource-name:指定受保护的资源名称
						//可选的description
						//零个或多个url-pattern：指定受保护资源的路径
						//零个或多个http-method：指定受保护的http方法
					//auth-constraint:指明哪些角色可以访问受限制的资源，如果没有指定则适用于所有角色
						//可选的description
						//零个或多个role-name:指定角色名称
					//user-data-constraint:用于指示在客户端和servlet/jsp容器传输的数据是否保护
						//一个可选的description
						//一个transport-guarantee:取值范围有NONE(不需要安全传输保障),INTEGRAL(数据在传输过程中不能被篡改),CONFIDENTIAL(必须加密传输数据)
				//security-role:声明用于安全限制的角色
					//description
					//role-name
				//servlet:该元素用来配饰servlet，包括如下子元素
					//一个可选的icon元素
					//一个可选的description元素
					//可选的display-name元素
					//一个servlet-name元素
					//一个servlet-class元素或一个jsp-flie元素
					//0个或多个init-param元素
					//一个可选的load-on-strat元素
					//可选的run-as元素
					//可选的enabled元素
					//可选的async-supported元素
					//可选的multipart-config元素
					//0个或多个security-role-ref元素
						//一个servlet元素必须包含一个servlet-name和servlet-class或者一个servlet-name和jsp-file
						//servlet-name元素的名称必须唯一
						//servlet-class为类的全路径名称
						//jsp-file元素指定jsp页面的路径，为应用程序的相对路径，必须以“/”开头
						//init-param用来传递参数给servlet
						//load-on-startup指示在启动时加载servlet(实例化servlet和调用init方法),可以指定加载顺序，如果指定的是jsp-file则jsp预先被编译为servlet,并加载它
						//run-as:覆盖调用ejb的安全标识，角色名是当前web应用程序定义的安全角色之一
						//security-role-ref:映射在调用servlet的isUserInRole方法时角色名到应用程序定义的安全角色
							//可选的description
							//role-name:
							//role-link:安全角色映射到一个已定义的安全角色，必须包含一个定义在security-role元素中的安全角色
						//async-supported:可选，标识此servlet是否支持异步处理
						//enabled:可选的，值为true/false 设置此元素为False，则禁用这个Servlet。
					//servlet-mapping:映射一个servlet到一个url模式，该元素必须有一个servlet-name和url-pattern子元素
					//session-config:定义了用于javax.servlet.http.HttpSession实例的参数
						//session-timeout:指定会话超时时间，该值必须是整数，，如果为0或负数，则永不超时
						//cookie-config:定义了跟踪会话创建的cookie的配置
						//tracking-mode：定义了跟踪会话模式，其有效值为：COOKIE,URL,SSL
					//welcome-file-list:指定当用户在浏览器输入的url不包含servlet名称或jsp名称或静态资源时显示的文件或servlet
						//多个welcome-file元素，用于查找默认的文件
					//jsp-config:可以指定jsp配置
						//0个或多个taglib:定义了jsp定制标签库
							//taglib-uri:定义了jsp/servlet应用程序所用的标签库的url,其值相当于部署描述符的路径
							//taglib-location:定义TLD文件的位置
						//0个或多个jsp-property-group
					//jsp-property-group:可为一组jsp文件统一配置属性，使用它可以做到以下几点
						//1：指示el显示是否忽略
						//2：指示脚本元素是否允许
						//3：指明页面的编码信息
						//4：指示一个资源时jsp文件
						//5：预包括和代码自动包含	
							//有如下子元素
								//1：一个可选的description
								//2:一个可选的display-name
								//3:一个可选的icon
								//4：一个或多个url-pattern：指定可应用相应属性配置的url模式
								//5：一个可选的el-ignored:默认false
								//6：一个可选的page-encoding:page-encoding的有效值同页面的pageEncoding有效值必须一致
								//7:一个可选的scripting-invalid：默认false
								//8:一个可选的is-xml：True表示匹配URL模式的页面是JSP文件
								//9：零个或多个include-prelude：元素值为相对于Servlet/JSP应用的相对路径。若设定该元素，则匹配URL模式的JSP页面开头处会自动包含给定路径文件（同include指令）
								//10:零个或多个include-code：元素值为相对于Servlet/JSP应用的相对路径，若设定该元素，则匹配URL模式的JSP页面开头处会自动包含给定路径文件（同include指令）
	
					//部署：仅仅需要将原始目录结构压缩成一个WAR文件，war文件必须包含所有库文件，类文件，html文件，jsp页面，图像文件，但不包括java源文件
					
					//web fragment
						//Servlet3.0添加了web fragment特性，用来为已有的web应用部署插件或框架，web fragment被设计成部署描述符的补充，而无需编辑web.xml
						//一个web fragment包含了基本上常用的web对象，如servlet,过滤器，监听器，其他资源如jsp页面和静态图像的包文件（jar文件），一个web fragment也可以有一个描述符，
						//类似部署描述符的xml文档，web fragment描述符必须命名为web-fragment.xml，并位于META-INF目录下，一个web fragment描述符可包含任意可出现在部署描述符web-app元素下的所有元素
						//再加一些web fragment的特定元素，一个应用程序可以有多个web 片段，其可以有metadata-complete属性，如果属性值为true,则包含在web fragment中所有的类注释将被跳过
	
				//小结：本章介绍如何配置和部署jsp/servlet应用程序，首先介绍了部署描述符的结构，
				//发布一个应用程序有2种形式，一种是以目录结构的形式，另一种是以单个war包的形式
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
