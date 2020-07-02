package example23_file_upload;

public class FileUploadGuide {
		//文件上传
			//servlet技术出实现前不久，文件上传的编程任然是一个困难的任务，它涉及在服务器端解析原始的HTTP响应
			//2003年apache 开源了Commons FileUpload元件
			//servlet3.0内置了上传文件特性，开发人员不再需要将commons fileupload元件导入到项目种去了
			
			//为此，springMVC处理文件上传有两种方法
				//1：购买Apache Commons FileUpload元件
				//2：利用servlet3.0以及更高版本的内置特性，如果要将应用程序部署到支持servlet3.0及其更高版本的容器中，只能使用这个方法
			
			//无论使用哪种方法，都要利用相同的API来处理已经上传的文件，下面分别介绍两者
			
			//客户端编程	
				//为了上传文件，必须将html表格的enctype属性值设置为：multipart/form-data,如下：
					//<form action="actionUrl" method="post" enctype="multipart/form-data">
						//<input type="file" name="fieldName"/>
						//<input type="submit" value="submit"/>
					//</form>
				//表格中必须包含一个类型为file的input元素
				//在html5之前，如果想要上传多个文件，必须使用多个input元素，，但是在html5中，通过在input元素中引入multiple属性，使得文件上传变得更加简单
				//以下任意一行都可以生成多文件上传
					//1：<input type="file" name="fieldName" multiple/>
					//2:<input type="file" name="fieldName" multiple="multiple"/>
					//3:<input type="file" name="fieldName" multiple=""/>
	
			//MultipartFile接口
				//在springMVC中处理已经上传得文件十分容易，上传到springMVC应用程序中的文件会被包在一个MultipartFile对象中
				//你唯一的任务就是用类型为MultipartFile的属性编写一个domain类
				//该接口有以下方法：
					//byte[] getBytes()		它以字节数组的形式返回文件的内容
					//String getContentType()	他返回文件的内容类型
					//InputStream getInputStream()	他返回一个InputStream,从中读取文件的内容
					//String getName() 他以多部份的形式返回参数的名称
					//String getOriginalFileName()	它返回客户端本地驱动器中的初始文件名
					//long getSize()	它以字节为单位返回文件的大小
					//boolean isEmpty()	它表示被上传的文件是否为空
					//void transferTo(File destination)	 他将上传的文件保存到目标目录下	
			
			//1：使用commons file upload上传文件
				//只有servlet3.0及其以上的容器支持内置文件上传，如果容器版本低于3.0，则需要使用 commons file upload元件
				//为了让commons file upload元件可以正常工作，它需要commons io元件
				//将这两个元件加入lib,然后在springMVC配置文件中定义multipartResolver bean
					//<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
						//<property name="maxUploadSize" value="200000"/>
					//</bean>
	
			//2:用servlet3.0及更高版本上传文件
				//有了servlet3.0就不需要commons file upload和commons io元件了，servlet3.0及以上版本的容器中进行服务器端文件上传的编程
				//是围绕着注解类型MultipartConfig和javax.servlet.http.Part接口进行的，处理以上传文件的servlets必须以@MultipartConfig进行注解
				//下列是@MultipartConfig注解中出现的属性，他们都是可选的
					//1：maxFileSize:上传文件的最大容量，默认值为-1，表示没有限制，大于指定值的文件将会遭到拒绝
					//2：maxRequestSize:表示多部分http请求允许的最大容量，默认值为-1，表示没有限制
					//3：location:表示在part中调用write方法时，要将已上传的文件保存到磁盘中的位置
					//4：fileSizeThreshold:上传文件超过这个容量限制时，会被写入磁盘
				//springMVC的dispatcherServlet处理大部分或者所有请求，遗憾的是如果不修改源代码，将无法对servlet进行注解，但是庆幸的是，servlet3.0中有一种比较容易的方法
				//能使一个servlet变成一个MultipartConfig Servlet,即给部署描述符(web.xml)中的servlet声明赋值，以下的代码与用@MultipartConfig给DispartcherServlet进行注解的效果一样
					//<servlet>
						//<servlet-name>...</servlet-name>
						//<servlet-class>...</servlet-class>
						//<init-param>
							//<param-name>...</param-name>
							//<param-value>...</param-value>
						//</init-param>
						//这里添加对servlet上传文件的控制==在DispatcherSwrvlet源代码中增加@MultipartConfig效果相同(需要修改源代码)
						//<multipart-config>
							//<max-file-size>20848820</max-file-size>
							//<max-request-size>20848820</max-request-size>
							//<file-size-threshold>1048576</file-size-threshold>
						//</multipart-config>
					//</servlet>
					//此外还需要在springMVC配置文件中使用一个不同的多部份解析器
						//<bean id="multipartResolver" class="org.springframework.web.multipart.support.StandardServletMultipartResolver"></bean>
				//例子：将与上一个上传文件案例的web.xml配置修改一下，把springMVC配置文件修改一下，其他的都相同
	
		//客户端上传
			//虽然servlet3.0的文件上传特性使得文件上传变得很容易，只需要在服务器端编程就可以了，但是这对于提升用户体验毫无用处，但会一个html表单并不能显示进度，或者显示已经上传的文件数量，开发人员常使用不同技术改善用户界面
			//第三方技术               单独用一个浏览器线程对服务器发出请求，以便报告上传进度，或者利用像Java小程序、Adobe Flash、MicrosoftSilverlight这样的第三方技术
			//第三方技术是有效的，但或多或少会有局限性。使用这些技术的第一个缺点在于，所有的主流浏览器对它们都没有内置的支持
			//幸运的是，我们有HTML 5前来支援。
				//HTML 5在其DOM中添加了一个File API。它允许访问本地文件
				//javscript和html5 file api提供报告上传进度的进度条
					//简而言之：我们关注的是html5的input元素的change事件，当input元素的值发生改变时，他就会触发
					//我们还关注html5在XMLHttpRequest对象中添加的progress事件，XMLHttpRequest是ajax的骨架，当异步使用XMLHttpRequest对象上传文件时，就会持续的触发progress事件，直到上传进度完成或取消，或者直到出错而中断
					//通过监听progress事件，就可以轻松的检测文件上传的进度
		
		//小结：
			//在springmvc中处理文件上传
				//1；apache commons file upload
				//2:servlet3.0及以上容器自带实现
			//客户端的优化改进
	
	
	
	
	
	
} 
