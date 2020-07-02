package example22_i18n;

public class I18n_Guide {
		//国际化
			//需要了解两个术语：1：国际化：i18n(internationalization),国际化是开发支持多语言和数据格式的程序技术，无需重复编写逻辑
							  //2：本地化：i10n(localization)这是将国际化应用程序改成支持特定语言区域的技术，语言区域是指一个特定的地理，政治，或文化区域，本地化被称为i10n
			
			//JAVA为字符和字符串提供了unicode支持，国际化应用程序的具体方式取决于有多少静态数据需要以不同的语言显示出来，这里有两种方法
				//1：如果大量数据时静态的，就要针对每个语言区域单独创建一个资源版本，这种方法一般适用于有大量静态html页面的web应用程序
				//2：如果需要国际化的静态资源数量有限，就可以将文本元素，如元件标签和错误消息隔离成为文本文件，每个文本文件中都保存着一个语言区域的所有文本元素译文，随后应用程序会自动获取每一个元素
		
			//语言区域
				//java.util.Locale类表示一个语言区域，一个Locale对象包含三个主要元件：language,country,variant
				//language无疑是最重要的一部分，在相同的language下就需要指定country,例如美国和英国都说英语，需要使用国家区别他们说的不同英语
				//variant是一个特定于供应商或者浏览器的代号，例如win代表windows,mac代表Macintosh,POSIX代表POSIX,两个variant之间用一个下划线隔开
				//并将最重要的部分放在前面，用language,country,variant参数构造一个locale分别是：
					//es  ES  Traditional_WIN
				//Locale对象的构造器如下：
					//public Locale(String language)
					//public Locale(String language,String country)
					//public Locale(String language,String country,String variant)
				//ISO 639语言码规范(部分)
					//de	德语
					//el	希腊语
					//en	英语
					//es	西班牙语
					//fr	法语
					//hi	印地语
					//it	意大利语
					//ja	日语
					//nl	荷兰语
					//Pt	葡萄牙语
					//ru	俄语
					//zh 	汉语
				//ISO 3166国家码规范(部分)
					//AU	澳大利亚
					//BR	巴西
					//CA	加拿大
					//CN	中国
					//EG	埃及
					//FR	法国
					//DE	德国
					//IN	印度
					//MX	墨西哥
					//CH	瑞士
					//TW	台湾
					//GB	英国
					//US	美国
			//Locale locale=new Locale("en","CA");//加拿大所用英语的locale对象
			//Locale类也有static final域：用来返回特定的国家或语言区域
				//Locale locale=Locale.CHINA;
			//静态的getDefault()方法会返回用户计算机的语言区域
				//Locale default=Locale.getDefault()
	
		//国际化springMVC应用程序
			//国际化和本地化应用程序时，需要具备以下条件：
				//1：将文本元件隔离成属性文件
				//2：要能够选择和读取正确的属性文件
		
			//1：将文本元件隔离成属性元件
				//每一个语言区域的文本元素都单独保存在一个独立的属性文件中，每个问价你都包含k/v对，每个key在属性文件中是唯一的
				//java.util.ResourceBoundle类
					//使用该类可以轻松的选择和读取特定用户语言区域的属性文件，以及查找值，该类是一个抽象类，需要返回一个具体子类的实例
					//ResourceBoundle有一个基准名，它可以是任意名称，但是为了让ResourceBoundle正确的选择属性文件，这个文件名中最好包含基准名ResourceBoundle,后面再接下划线，语言码，还可以再接一条下划线加国家码
						//basename_languageCode_conutryCode
						//假设基准名为：MyResources,并且定义了三个语言区域：US_en DE_de CN_zh,那么就会得到下面三个属性文件
							//MyResources_en_US.properties
							//MyResources_de_DE.properties
							//MyResources_zh_CN.properties
			//2:选择和读取正确的属性文件
				//ResourceBoundle是一个抽象类，可以通过getBoundle方法来获得一个ResourceBoundle实例
					//public static ResourceBoundle getBoundle(String basename)
					//public staTic ResourceBoundle getBoundle(String basename,Locale locale)
						//ResourceBoundle rb=ResourceBoundle.getBoundle("MyResources",Locale.CHINA);
							//这样将会加载ResourceBoundle在相应属性文件中的值
							//如果没有找到相应的属性文件，ResourceBoundle会返回到默认的属性文件，默认的属性文件为基准名及一个扩展名properties:MyResources.properties
							//如果没找到默认文件则抛出异常
						//随后，读取值,ResourceBoundle类的getString方法传入一个key
							//public String getString(String key)
							//如果没有找到指定的key,则抛出异常
			
			//在spring MVC中使用国际化
				//在springMVC中不直接使用ResourceBoundle，而是利用messageSource bean告诉springMVC要将属性文件保存在哪里
				//如下：
					//<bean id="messageSource" class="org.springframework.context.support.ReloadableResourceBoundleMessageSouece">
						//<property name="basenames">
							//<list>
								//<value>resource/messages</value>
								//<value>resource/labels</value>
							//</list>
						//</property>
					//</bean>
					//上面的bean定义中用ReloadableResourceBoundleMessageSource类作为实现,可以将这个设置为可以重新加载，使用这个实现类时，狮子啊应用程序目录下搜索这些属性文件
					//另一个实现类是ResourceBpundleMessageSource,他是不能重新加载的，在属性文件中修改值时需要重启jvm，使用这个类时，属性文件必须放在类路径下，及web-inf/class目录下
					//注意如果只有一组属性文件，可以使用basename代替basenames
	
			//告诉springMVC使用哪个语言区域
				//为用户选择语言区域时，最常用的方法或许是通过读取用户浏览器的accept-language标题值，accept-language标题提供了关于用户偏好哪种语言的信息
				//选择语言区域的其他方法还包括读取某个session属性或cookie
				
				//在springMVC中选择语言区域，可以使用语言区域解析bean,他有如下几个实现：
					//1:AcceptHeaderLocaleResolver
					//2:SessionLocaleResolver
					//3:CookieLocaleResolver
				//所有这些实现都是org.springframework.web.servlet.i18n包的组成部分，AcceptHeaderLocaleResolver或许是其中最容易使用的一个
				//如果使用这个语言解析器，springMVC将将会读取浏览器的accept-language标题，来确定浏览器接受哪个（些）语言区域，如果浏览器的某个语言区域与springMVC应用程序支持的某个语言区域相匹配，就会使用这个语言区域，如果没有找到匹配的语言区域，就是用默认的语言区域
					
				//1：下面是AcceptHeaderLocaleResolver的bean定义：（不需要多余配置）
					//<bean id="localeResolver" class="org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver"></bean>
				
				//2：使用SessionLocaleResolver定义bean 
					//<bean id="localeResolver" class="org.springframework.web.servlet.i18n.SessionLocaleResolver"></bean>
					//需要额外配置国际化操作拦截器（请求/session/cookie）则必须配置
						//<mvc:interceptors>
							//<bean class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor">
								// <!-- 通过这个参数来决定获取那个配置文件 同页面?lang -->
								//<property name="paramName" value="lang"/>
							//</bean>
						//<mvc:interceptors>
	
				//3：使用CookieLocaleResolver定义bean
					//<bean id="localeResolver" class="org.springframework.web.servlet.i18n.CookieLocaleResolver">
	 					//<!-- cookie变量的名称 -->
	 					//<property name="cookieName" value="lang" />
	 					//<!-- cookie超时时间 -->
	 					//<property name="cookieMaxAge" value="20" />
	 					//<!-- 默认使用简体中文 -->
	 					//<property name="defaultLocale" value="zh_CN" />
					//</bean>
					//需要额外配置国际化操作拦截器（请求/session/cookie）则必须配置
						//<mvc:interceptors>
							//<bean class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor">
								// <!-- 通过这个参数来决定获取那个配置文件 同页面?lang -->
								//<property name="paramName" value="lang"/>
							//</bean>
						//<mvc:interceptors>
			//使用message标签
				//在sprinMVC种显示本地化消息的最简便方式就是使用spring的message标签，为了使用这个标签，需要在jsp页面最前面声明这个taglib指令：
				//<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	
				//message标签的属性如下，所有这些属性都是可选的：
					//1：arguments				该标签的参数写成一个有界的字符串，一个对象数组或者单个对象
					//2:argumentSeparator		用来分割该标签参数的字符
					//3:code					获取消息的key
					//4:htmlEscape				接受true或者false，表示被渲染的文本是否进行html转移
					//5:javaScriptEscape		接受true或者false，表示被渲染的文本是否进行javascript转移
					//6:message					MessageSourceResolvable参数
					//7:scope					保存var属性种定义的变量范围
					//8:text					如果code属性不存在，或者指定码无法获得消息时，所显示的默认文本、
					//9:var						用于保存消息的有界变量
					
		//小结：spring种的三种国际化的方式，以及子啊java种的ResourceBoundle资源类和Locale类
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
		
}
