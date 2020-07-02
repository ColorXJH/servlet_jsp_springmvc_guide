package example00_tomcat;

public class QuickStartAtTomcat {
		//本实例需要tomcat7及以上servlet/jsp容器才能运行
		
	//1：下载和配置tomcat
		//官网下载之后进行解压：
			//bin目录中有启动和终止tomcat的程序，webapps目录很重要，那里可以部署应用程序，conf目录包含了配置文件
			//包括servlet.xml和tomcat-users.xml
			//lib目录包含了编译servlets和定制标签所需要的servlet和jsp api
	//2:启动和终止tomcat
		//下载并解压好tomcat的二进制文件之后，可以运行bin目录下的startup.bat文件来启动tomcat,默认情况下tomcat在8080端口运行
		//浏览器中输入localhost:8080
		//终止tomcat时，运行bin目录下的shutdown.bat文件
	//定义上下文
		//要将servlet/jsp应用程序部署到tomcat时，需要显式或隐式定义一个tomcat上下文，在tomcat中，每个tomcat上下文都表示一个web应用程序
		//显式定义包含两种方法：
			//1：在tomcat的conf/Catalina/localhost目录下创建一个xml文件
			//2：在tomcat的conf/server.xml文件中添加一个Context元素
		
		//如果决定给每个上下文，那么这个文件名就很重要，因为上下文路径是从文件名衍生得到的，例如把一个commerce.xml文件放在conf/Catalina/localhost目录下
		//那么应用程序的上下文就是commerce，并且可以利用如下url调用这一个资源：
			//http:localhost:8080/commerce/resourceName
		//上下文文件中必须包含一个context元素。作为它的根元素，这个元素大多没有子元素，他是该文件中的唯一元素，下面就是一个上下文文件，其中只包含一行代码
			//<Context docBase="c:/app/comerce" reloadable="true"/>
			//这里唯一必要的是docBase属性，它用来定义应用程序的位置，reloadable属性是可选的，但是如果存在并且它的值为true,那么应用程序中一旦java类文件或
			//其他资源有任何增加减少，tomcat都会侦察到变化，tomcat就会重新加载应用程序，在部署期间建议将其设置为true,在生产期间不建议设置为true	
		//当把上下文文件添加到指定目录时，tomcat就会自动加载应用程序，当删除这个文件时，tomcat就会卸载程序
	
		//定义上下文的另一种方法是，在conf/server.xml文件中添加元素Context:打开此文件，在host元素下创建context元素
		//此处定义上下文需要给上下文路径定义path属性：例如
		//<host name="localhost" appBase="webapps" unpackWARs="true" autoDeploy="true">
			//<Context path="/connerce" docBase="c:/app/commerce" reloadable="true"/>
		//</host>
		
		//一般来说不建议使用server.xml来管理上下文，因为只有重启tomcat之后才会生效，但是如果有很多程序需要测试，可以在一个文件中管理所有应用
		//最后通过将一个WAR文件或整个应用程序赋值到tomcat的webapps目录下，还可以隐式的部署应用程序
	
	
	
	
	
	
}
