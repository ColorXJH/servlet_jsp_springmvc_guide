package example07_tag_file;

public class Tag_File_Guide {
	//编写自定义标签需要编写标签处理器和在标签库描述文件中定义标签
	//从jsp2.0开始，通过tag file形式，无需编写标签处理类和标签库描述文件
	
	//tag file简介
		//tag file 从两个方面简化了自定义标签的开发
			//1：tag file 无需提前编译，直到第一次调用时才会被编译，除此之外仅仅使用jsp语法就可以完成标签的扩展定义
			//2：标签库描述文件不再需要，原先需要在描述文件中定义标签元素的名字，以及它所对应的action（一般是指对应的类）,使用tag file方式，tag file名和action相同，因此不再需要描述文件
		//jsp容器提供多种方式将tag file 编译成java的标签处理类，tomcat将tag file 翻译成继承于javax.servlet.jsp.tagext.SimpleTag接口的标签处理类
		//一个tag file和jsp页面一样，它拥有指令，脚本，el表达式，动作元素以及自定义标签，一个tag file以tag/tagx为后缀，他们可以包含其他资源文件，一个被其他文件包含的tag file应该以tagf为后缀
		//tag file 必须放在web-inf/tags目录下才能生效，和标签处理类一样，tag文件可以被打到jar包里
		//tag file中也有一些隐藏对象，通过脚本或el表达式可以访问这些隐藏对象，可用的隐藏对象如下：
			//1：request				javax.servlet.http.HttpServletRequest
			//2:response			javax.servlet.http.HttpServletResponse
			//3:out					javax.servlet.jsp.JspWriter
			//4:session				javax.servlet.http.HttpSession
			//5:application			javax.servlet.ServletContext
			//6:config				javax.servlet.ServletConfig
			//7:jspContext			javax.servlet.jsp.JspContext
		
		//第一个tag file
			//tag file 名称和标签名称是一样的，例如：firstTag.tag的tag file对应的标签名即为 firstTag
	
		//tag file指令
			//和jsp页面一样，tag file可以使用指令来指挥jsp容器如何编译这个tag file,tag file指令语法和jsp指令语法相同：
				//<%@ directive (attribute=value)*%>
				//属性必须被单引号或者双引号包围，除了page指令，其他所有的jsp指令都可以用于tag file,在 tag file 中，可以使用tag指令代替page指令，另外你还可以使用两个新指令：attribute,variable
				//以下是可以在tag file中使用的指令：
					//1：tag				作用于jsp页面的page指令类似
					//2：include			用于将其他资源导入 到tag file中
					//3：taglib			用于将自定义标签库导入到tag file中
					//4：attribute		用于将自定义标签库导入到tag file中
					//5：variable		用于将自定义标签库导入到tag file中
	
		//tag指令
			//类似于jsp页面中的page指令，tag 指令的属性如下：
				//1：display-name				在xml工具中显示的名称，默认值是不包含后缀的tag file名
				//2：body-content				指定标签body的类型，body-content的属性有：empty/tagdependent/scriptless,默认值是scriptless
				//3:dynamic-attributes			指定tag file动态属性的名称，当dynamic-attributes值被设定时，会产生一个map来存放这些动态属性名和对应的值
				//4：small-icon					制定一个图片路径，用于在xml工具上显示小图标，一般用不到
				//5:large-icon					制定一个图片路径，用于在xml工具上显示大图标，一般用不到
				//6：description					标签的描述信息
				//7：example						标签使用实例的描述
				//8：language					tag file中使用的脚本语言类型，当前版本的jsp中必须设置为java
				//9:import						用于导入一个java类型，和jsp页面的import相同
				//10：pageEncoding				指定tag file的编码格式，可以使用"charset"中的值，和jsp页面的pageEncoding相同
					//注意，以上属性大偶是在tag file文件中的tag指令的属性（该文件时web-inf/tag目录下的）
	
	
		//除了import属性，其他所有的属性只能在tag file中最多出现一次
			//<%@ tag display-name="first tag file" body-content="scriptless"%> <%@ tag body-content="scriptless"%>//这两个tag指令在一个tag file中无效，body-content出现了两次
			//<%@ tag import="java.util.Date" import="java.util.Calendar"%>//有效，import可以出现多次
		
		//include 指令
			//同jsp页面的include指令，使用该指令将外部文件导入到tag file中，这个公共文件可以是静态文件（html）,也可以是动态文件（如其他tag file）(注意被导入的tag file 后缀名必须为。tagf)
	
		//taglib 指令
			//可以通过taglib指令在tag file中使用自定义标签：
				//<%@ taglib uri="tagLibraryURI" prefix="tagPrefix"%>
					//uri:指定与前缀描述符相关联的标签库描述文件的绝对路径或相对路径
			//使用taglib指令，可以有两种方式使用自定义标签
				//1：<prefix:tagName/>//不包含content-body
				//2:<prefix:tagName>body</prefix:tagName>//包含content-body
			//tag file 中的taglib指令和jsp中的taglib指令一样
				//可参考jsp自定义标签使用
					//<%@ taglib uri="/web-inf/tags"%>
	
		//attribute指令
			//用于设定tag file中标签的属性，他和标签库描述符文件的attribute元素等效，语法如下
				//<%@ attribute (attribute="value")*%>	,attribute指令的属性如下：
					//1：name				用于设定该属性的名称，在一个tag file中，每个属性的名称必须是唯一的
					//2：required			用于设定该属性是否必须，默认为false
					//3:fragment			用于设定该属性是否为fragment,默认false
					//4:rtexprvalue			用于设定该属性值是否在运行时被动态计算，默认为true
					//5:type				用于设定该属性的类型，默认为java.lang.String
					//6:description			用于设定该属性的描述性息
	
		//variable指令
			//有时候我们需要将tag file中的一些值传递到jsp页面，这时候可以通过variable来完成，tag file中的variable指令和标签描述符中的variable元素类似，它用于定义那些需要传递到jsp页面的变量、
			//tag file支持多个variable指令，这意味着可以传递多个值到jsp页面，相对而言，attribute的作用与variable恰恰相反，它用于将值从jsp页面传递到tag file
				//<%@ variable (attribute="value")*%>,		variable指令的属性如下：
					//1：name-given:变量名，在jsp页面的脚本和el表达式中，可以使用该变量名，如果指定了name-from-attribute属性，，那么此属性就不能再出现了，反之亦然，它的值不能和同一个tag file中的属性名重复
					//2：name-from-attribute:和name-given类似，由标签属性的值来决定标签变量名
	
		
	
	//doBody
		//doBody动作元素只能在tag file中使用，它用来调用一个标签的本体内容
		//doBody动作元素也可以有属性，你可以通过这些属性来指定某个变量接收主题内容，如果不使用这些指令，doBody动作元素会把主体内容写道jsp页面的jspWriter上
		//doBody动作元素的属性如下：
			//1：var				用于保存标签主体内容的变量值，内容会以java.lang.String的类型保存这个变量值，var和varReader只能出现一个
			//2：varReader		同上，但是内容会以java.io.Writer的类型保存在变量值中
			//3：scope			变量保存到的作用域
	
	
	//invoke
		//invoke动作元素和doBody类似，再tag file中，可以使用它来调用一个fragment,再tag file定义属性的attribute指令中有一个fragment属性，如果该值为true,那么这个属性就是一个fragment
		//这意味着可以从tag file中多次调用，invoke动作元素也有多个属性，如下：
			//1：fragment				要调用的fragment名称
			//2；var						用于保存片段主体内容的变量值，主体内容以java.lang.String的类型保存这个变量，var和varReader只能出现一个
			//3：varReader				同上，主体内容以java.io.Reader的类型保存这个变量
			//4：scope					变量保存都的作用域
	
	
	//小结：本章介绍如何使用tag file更加简单的进行标签的自定义，，通过tag file 无需编写标签库描述文件和标签处理类
	//同时本章还介绍了如何使用invoke和doBody动作元素
	
	
	
	
	
	
}
