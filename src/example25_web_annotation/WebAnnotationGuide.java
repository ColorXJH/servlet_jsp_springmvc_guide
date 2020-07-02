package example25_web_annotation;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

import example18_base_on_annotation_controller.domain.Book;

public class WebAnnotationGuide {
	// servlet3.0在javax.servlet.annotation包中引入了一组注解类型，可以注解包括servlet,filter,listener等web对象
	// 1：@HandleTypes:用来声明ServletContainerInitializer可以处理的类，只有一个value属性，其值为可以处理的类
	// 如下表示该initializer可以处理Book
	@HandlesTypes({ Book.class })
	class Myinitializer implements ServletContainerInitializer {
		@Override
		public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
			// TODO Auto-generated method stub

		}

	}
	
	//2:HttpConstraint:表示施加到所有的http协议方法的安全约束，且http协议方法对应的@HttpMethodConstraint没有出现在@ServletSecurity注解中
	//此注解类型必须包含在ServletSecurity注解中
			//属性
				////rolesAllowed:包含授权角色的字符数组
				//value:默认授权
				//transportGuarantee:连接请求所必须满足的数据保护需求，有效值为：ServletSecurity.TransportGuarantee枚举成员(CONFIDENTIAL or NONE)
		//ServletSecurity(@HttpConstraint(rolesAllowed="manager"))
			//声明了该servlet仅能被manager，由于没有定义@HttpMethodConstraint注解，因此该约束应用到所有的http协议
	
	//3：HttpMethodConstraint:声明一个特定的http方法的安全性约束，该注解只能出现在在ServletSecurity中
			//属性：比上面多一个
				//emptyRoleSemantic：当rolesAllowed返回一个空数组，（只）应用的默认授权语义。有效值为ServletSecurity. EmptyRoleSemantic enum（DENY orPERMIT）
		//@ServletSecurity(value=@HttpConstraint(rolesAllowed="manager"),httpMethodConstraints={@HttpMethodConstraint("GET")}	)
			//@HttpConstraint定义可以访问本servlet的角色，@HttpMethodConstraint重写了get约束，去除了rolesAllowed属性，因此，该servlet可以被任何用户通过get
			//方式访问，但是其他的http方法都需要manager角色

		//然而，如果HttpMethodConstraint注解类型的emptyRoleSemantic属性值为EmptyRoleSemantic. DENY时，则限制所有用户访问该方法，如下：
			//@ServletSecurity(value=@HttpConstraint(rolesAllowed="manager"),httpMethodConstraints={@HttpMethodConstraint(value="GET",emptyRoleSemantic=EmptyRoleSemantic.DENY)})
				//该Servlet阻止所有通过Get方法的访问，但允许所有member角色的用户通过其他HTTP方法访问

	//4:MultipartConfig:标注一个servlet来指示该servlet实例能够处理multipart/form-data的MIME类型，在上传文件时会用到
		//属性
			//1：fileSizeThreshold:当文件大小超过指定大小后存入磁盘
			//2：location:文件保存在服务端的路径
			//3：maxFileSize:允许上传的文件最大值，默认为-1，表示没有限制
			//4：maxRequestSize:针对该multipart/form-data请求的最大数量，默认值为-1，表示没有限制
	
	//5：ServletSecurity:标注一个servlet类在Servlet应用中	的安全约束
	
	//6：WebFilter:注解一个filter
		
	//7:WebInitParam:用于传递初始化参数到一个servlet或者过滤器
		
	//8:WebListener:注解一个listener
	
	//9:WebServlet:注解一个servlet
	
	







}
