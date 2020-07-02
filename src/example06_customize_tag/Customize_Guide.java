package example06_customize_tag;

public class Customize_Guide {
	//扩展javax.servlet.jsp.tagext包中的成员，就可以实现自定义标签
	//自定义标签允许访问jsp中的隐藏的对象和他们的属性
	//jsp2.0增加了两个新特性用于改善自定义标签的实现
		//1：增加了SimpleTag接口
		//2：标签文件中定义标签的机制
	//自定义标签的实现叫做标签处理器，而简单标签处理器是指继承SimpleTag实现的标签处理器
	//简单标签处理器不再被jsp缓存了，但是并不意味之会比之前的慢（缓存标签处理器并不能带来较好的性能优化，而且还会带来更多的潜在错误）
	
	
	//实现SimpleTag的标签处理器叫做简单标签处理器，实现Tag,IterationTag,BodyTag的标签处理器叫做经典表i请安处理器
	//简单标签处理器有着简单的声明周期，比经典标签处理器更容易实现，SimpleTag中用于标签触发的方法只有一个：doTag,该方法只会执行一次
	//简单标签处理器的页面内容都在jspFragement类的实现中体现
	
	//声明周期如下：
		//1：jsp容器通过简单白标签处理器的无参构造器创建它的实例，因此简单标签处理器必须有无参构造器
		//2：jsp容器通过setJspContext方法(public void setJspContext(JspContext context))，传入jspContext对象，该对象中最重要的方法是getOut，它能返回jspWriter,通过jspWriter就可以把响应返回前端了，通常情况下都需要把传入的jspContext指定为类的成员变量供以后使用
		//3：如果自定义标签被另一个自定义所嵌套，jsp容器就会调用setParent的方法：public void setParent(jspTag parent)
		//4:jsp容器调用该标签中所定义的每个属性的set方法
		//5:如果需要处理页面内容，jsp容器还会调用simpletag接口的setjspBody方法，把使用jspFragment封装的页面内容传递过来，，如果没有页面类容，jsp容器就不会调用该方法
	
	//javax.servlet.jsp.tagext包中包含一个simpletag的基础类：SimpleTagSupport,它提供了simpletag所有方法 的默认实现，其getJspContext方法返回jspContext实例，这个实例在jsp容器调用simpleTag的setJspContext方法时传入
	
	//注册标签
		//在标签处理器能被jsp页面使用之前，他需要在标签库描述器中注册一下，描述器是以.tld结尾的xml文件，这个文件必须放在web-inf目录下
		//在标签描述文件中最主要的节点是tag,用于定义一个标签，包含一个name节点和tag-class的节点，一个标签库描述器中可以包含多个标签，
		//此外在标签描述器中还有其他节点，description节点用于说明这个标签的名称，tlib-version指定自定义标签版本，short-name指这些标签的简称
	
	//使用标签
		//要使用自定义标签就要使用taglib指令，其中的uri属性指定标签描述器的绝对路径或相对路径，如果使用的是jar包中的标签库就要使用绝对路径
	
	//访问标签内容
		//在simpletag中，可以通过jsp容器传入的jspfragment来访问标签内容，jspfragment类提供了多次访问jsp中这部分代码的能力，jsp片段的定义不能包含脚本或者脚本表达式，他只能是文件模板或者jsp标准节点
		//jspfragment类中有两个方法：getJspContext,invoke
			//1:public abstract JspContext getJspContext()
			//2:public abstract void invoke(java.io.Writer writer) throws JspException,java.io.IOException
				//getJspContext方法返回这个jspFragment关联的jspContext对象，可以通过invoke方法执行这个片段，然后通过指定的writer对象将它直接输出，如果把null传入invoke,
				//那么这个writer将会被jspfragment所关联的jspcontext中的getOut方法返回的writer所接管

	
	//编写el函数
		//可以自定义实现通过表达式语言触发的函数
		//编写el函数需要2个步骤：
			//1：创建一个包含静态方法的public类，每个类的静态方法表示一个el函数，这个类可以不需要实现任何接口或继承任何特定的类，可以像发布其他类一样发布这个类，这个类必须放在web-inf/classes目录或它的子目录下
			//2：用function节点在标签库描述器中注册这个函数
				//function节点是taglib节点的下级节点，它有如下节点	
					//1：description:可选				标签说明
					//2：display-name:				在xml工具中显示的缩写名字
					//3：icon:可选					在xml工具中使用的icon节点
					//4：name:						函数的唯一名字
					//5：function-class				该函数对应实现的java静态方法
					//6：example:可选					使用该函数的示例说明
					//7：function-extension			可以是一个或多个节点，在xml工具中使用，用于提供该函数的更多细节
		//要使用这个函数，需要将taglib指令中的uri属性指向标签库描述，并指明使用前缀，然后使用如下：
			//${prefix:functionName(parameterList)}


	//发布自定义标签
		//可以把自定义标签处理器以及标签描述器打包到jar包里，这样就可以给被人使用了，就像jstl一样，这种情况下需要包含其所有的标签处理器以及tld文件，此外还需要在描述器中指定绝对的url

	//小结
		//自定义标签是解决javabean中前端展示和后端分离的好方法，编写自定义标签，需要创建标签处理器，并在标签库描述器中注册它
		//在jsp2.3中，有两种标签处理器可以使用，经典标签处理器和简单标签处理器，前者需要实现Tag,IterationTag,BodyTag接口或者扩展TagSupport,BodyTagSupport这两个基类
		//简单标签处理器只需要实现SimpleTag或者SimpleTagSupport,在jar包中可以发布自己的自定义标签以及el函数以便他人使用
	





}
