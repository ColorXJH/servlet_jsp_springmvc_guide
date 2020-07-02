package example16_model2_and_mvcModel;

public class Model2AndMVCModel_Guide {
		//javaWeb应用开发中有两种设计模型，为了方便分别称为模型1和模型2
		//模型1是页面中心，适用于小型应用开发
		//模型2是MVC模式，是javaWeb应用的推荐架构
		
		//模型1介绍：
			//第一次学习jsp,通常通过链接的方式进行jsp页面间的跳转，这种方法非常直接，但是在大中型应用中会带来维护问题
		//模型2介绍：
			//模型2是基于模型-视图-控制器（MVC）模式，该模式是smalltalk-80用户交互的核心概念，但是还没有设计模式的说法，称为mvc范式
			//一个实现mvc的应用包含模型，视图，和控制器三个模块，视图负责应用的展示，模型封装了应用的数据和业务逻辑，控制器负责接收用户输入，改变模型以及调整视图显示
			//模型2中，servlet或filter都可以充当控制器，几乎所有的现代web框架都是模型2的实现，
			//springMVC和struts1使用一个servlet作为控制器，而struts2使用一个filter作为控制器，大部分都采用jsp页面作为视图，当然也有其他技术
			//模型是采用POJO(Plain Old Java Object),不同于EJB(enterprise Java bean),POJO是一个普通的对象，实践中会采用一个javabean来持有模型状态
			//并将业务逻辑放到一个Action类中，一个javaBean必须有一个无参构造器，通过get/set方法来访问参数，同时支持持久化
		//模型2架构图如下：					
						/*					web容器
						请求					Servlet/Filter(控制器)
			web浏览器《---------》										《--------》数据库
						响应				JSP场面(视图)   javaBean(模型)*/
	
			//每个http请求都发送给控制器，请求中的url标识出对应的action,action代表了应用应用可以执行的一个操作，一个提供了action
			//的java对象称为action对象，一个action类可以支持多个actions（springmvc和struts2中），或者一个action(struts1)
			//看似简单的操作可能需要多个action,例如像数据库添加一个商品，需要两个action
				//1：显示一个添加产品的表单
				//2将表单数据保存到数据库
			//控制器会解析url并调用相应的action,然后将模式对象放到视图可以访问的区域，最后控制器通过RequestDispatcher跳转到视图，在jsp页面中显示
				//注意：调用RequestDispatcher.forward方法并不会停止执行剩余的代码，因此若该方法不是最后一行代码，则应该显示的返回
		
		//模型2之Servlet控制器
			
		//可以通过如下几种方式避免通过浏览器直接访问jsp页面
			//1：将jsp页面放入WEB-INF目录下
			//2：利用一个servlet filter过滤jsp页面
			//3：在部署描述符中为jsp页面增加安全限制
	
		//可以将servlet控制器作为默认主页，这是一个非常重要的特性，
		//在web.xml部署描述符中的welcome-file-list下的第一个welcome-file元素中设置一个可以被servlet拦截到的url,这个url不会显示在浏览器的url中，但是它默认会被
		//标记了相应的url-pattern的servlet拦截(浏览器一般访问doget方法)，可以在其中通过判断url的特性来让这个请求转发到特定的页面（request.getRequestDispatcher(url).forward(req,resp)）
	
		
		//解耦控制器代码
			//业务逻辑都写在了servlet控制器中，会随着业务不断的膨胀，为了避免此问题，我们将业务逻辑代码提取到独立的被称为controller的类中
	
		//校验器
			//因为校验工作如此重要，Java社区专门发布了JSR 303 Bean Validation以及JSR 349 Bean Validation 1.1版本
			//现代的MVC框架通常同时支持编程式和申明式两种校验方法。在编程式中，需要通过编码进行用户输入校验，而在声明式中，则需要提供包含校验规则的XML文档或者属性文件。	
		//后端
			//应用MVC，可以在Controller类中调用后端业务逻辑。通常，需要若干封装了后端复杂逻辑的Service类。在Service类中，可以实例化一个DAO类来访问数据库。
			//在Spring环境中，Service对象可以自动被注入到Controller实例中，而DAO对象可以自动被注入到Service对象中
	
	
		//小结：本章我们学习了MVC模式的模型2架构
}
