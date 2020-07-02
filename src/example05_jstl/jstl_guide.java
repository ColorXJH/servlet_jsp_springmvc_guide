package example05_jstl;

public class jstl_guide {
		//jsp标准标签库（java server page standard tag library JSTL）是一个定制标签库的集合，用来解决想遍历map或集合，条件测试，xml处理，甚至数据库访问和数据库操作等常见问题
	
		//下载jstl  ：http://jstl.java.net
			//jstl api和jstl实现这两个jar必须下载，并放在web-inf/lib目录下
	
		//jstl库
			//JSTL是标准标签库，但是它通过多个标签库来暴露行为，jstl 1.2中的标签可以分为5类区域
				//核心(变量支持，流控制，url管理，其他):http://java.sun.com/jsp/jstl/core      		c
				//xml(核心，流控制，转换):http://java.sun.com/jsp/jstl/xml						x
				//国际化(语言取域，消息格式化，数字和日期格式化):http://java.sun.com/jsp/jstl/fmt		fmt
				//数据库(sql):http://java.sun.com/jsp/jstl/sql								sql
				//函数(集合长度，字符串操作):http://java.sun.com/jsp/jstl/functions				fn
		//在jsp页面使用jstl标签库，必须通过以下格式使用taglib指令：
			//<%@ taglib uri="..." prefix="..."%>
				//前缀是任意的，但是建议使用默认前缀
		//标签的属性rtexprvalue为true时，标识该属性可以赋值静态字符串或动态值(java表达式，el表达式，或者通过<jsp:attribute>设置的值)
		//为false表示只能符静态字符串的值，
		//jstl标签的body content可以为empty.jsp，或tagdependent
	
	//一般行为：out set remove
		//out:out标签在运算表达式时，是将结果输出到当前的jspwriter,out语法有两种形式，即有body content和没有body content
			//1:<c:out value="value1" [escapeXml="{true|false}"] [default="defaultValue"]>
			//<c:out value="value1" [escapeXml="{true|false}"]>defalueValue</c:out>
				//out的body content为jsp,属性如下(*,必须属性，+可选属性)
					//value:(*+)   对象类型    要计算的表达式
					//escapeXml(+) 布尔            表示结果中的字符串<,>,&,',"将会转化成相应的实体码，如<被转换为lt;等等
					//default(+)   对象             默认值    (可以赋动态值)
		
		//set:利用set标签，可以完成以下工作
			//1：创建一个字符串和一个引用该字符串的有界变量
			//2：创建一个引用先存有界对象的有界变量
			//3：创建有界对象的属性
		//如果使用set创建有界变量，那么在标签出现后的整个jsp页面都可以使用该变量
		//set标签的语法有4中形式
			//1：创建有界变量，var定义一个引用名称，并用value属性定义一个要创建的值或现存有界对象，scope指定有界变量的范围,不写默认为page
				//<c:set value="value1"  var ="varname" [scope="{page|request|session|application}"]/>
				//<c:set var="job" value="${requestScope.position}" scope="page"/>
					//这个例子可能让人优点费解，因为它创建了一个引用请求范围对象的页面范围变量，如果清楚有界对象本身并不是真的存在于HttpServletRequest里面
					//就不难明白了，引用(名为position)其实是指引用该对象
			//2：与第一种类似，只是要创建的字符串或者要引用的有界对象是作为body content赋值的
				//<c:set var="varname" [scope="{page|request|session|application}"]>body content</c:set>
				//第二种形式允许在body content中有jsp代码
			//3：设置有界对象的属性值，target属性定义有界对象，以及有界对象的property属性，对该属性的赋值是通过value属性进行的
				//<c:set target="myTarget" property="myProperty" value="value1"/>
				//上面表示：将value1赋值给有界对象myTarget的myProperty属性,target属性中需要用el表达式来引用一个有界对象
			//4：与第三种相似，只是赋值是作为body content完成的
				//<c:set target="myTarget" property="myProperty">value1</c:set>
					//set标签的属性
						//1：value(+)  对象				要创建的字符串，或者要引用的有界对象，或者新的属性值
						//2：var 	  字符串                                       要创建的有界变量
						//3：scope	 字符串				新创建的有界变量的范围
						//4：target(+) 对象				其属性要被赋予新值的有界对象，这必须是一个javabean实例或者java.util.map对象
						//5：property(+)字符串				要被赋予新值的属性名称
		
		//remove标签
			//remove标签用于删除有界变量，语法如下
				//<c:remove var="varname" scope="{page|request|session|application}"/>
					//注意：有界变量引用的对象不能删除，因此如果另一个对象也引用了同一个对象，它任然可以通过另一个有界变量访问该对象
						//remove标签的属性
							//1：var 字符串  要删除的有界变量的名称
							//2：scope 字符串 要删除的有界变量的范围
	
		//条件行为：jstl中执行条件行为的有4个标签，即if,choose,when,otherwise
			//if标签：有两种形式
				//1：<c:if test="testCondition" var="varname" scope="{page|request|session|application}"/>
					//var定义的有界对象一般是通过其他标签在同一个jsp的后续阶段再进行测试
				//2:<c:if test="testCondition" [var="varname"] [scope="{page|request|session|application}"]>body content</c:if>
					//if标签属性
						//test(+) 布尔				决定是否处理任何现有body content的测试条件
						//var 字符串					引用测试条件值的有界变量名称，var 的类型为boolean
						//scope 字符串				var定义的有界变量的范围
	
		//choose-when-otherwise
			//他们是为互相排斥的条件执行提供上下文的，choose标签中必须有一个或多个when标签，otherwise标签用于默认的条件块，一般放在最后，choose和otherwise标签没有属性，when带有test属性
			/*<c:choose>
			 * 		<c:when test="${param==5}">this is 5</c:when>
			 * 		<c:otherwise>this is not 5</c:otherwise>
			</c:choose>*/
	
	//遍历行为：jstl提供了forEach和forTokens两执行遍历行为的标签
		//forEach:利益遍历Collection/map/对象数组/Iterator/Enumeration
		//forEach有2中形式
			//1：<c:forEach [var="varname"] begin="begin" end="end"  step="step" >body content</c:forEach>
				//表示固定次数的执行body content
			//2:<c:forEach items="Collection" [var="varname"] [varStatus="varStatusName"] [begin="begin"] [end="end"] [step="step"]>body content</c:forEach>
				//用于遍历集合对象
					//forEach标签的属性
						//var 字符串				引用遍历的当前项的有界变量名称
						//items(+) 支持的任意类型	遍历的对象集合
						//varStatus 字符串		保存状态的有界变量名称，类型值为Javax.servlet.jsp.jstl.core.LoopTagStatus
							//它包含以下属性
								//current：当前这次迭代的（集合中的）项
								//index：当前这次迭代从 0 开始的迭代计数
								//count：当前这次迭代从 1 开始的迭代计数
								//first：用来表明当前这轮迭代是否为第一次迭代的标志，返回true/false
								//last：用来表明当前这轮迭代是否为最后一次迭代的标志,返回true/false
						//begin(+)  整数			如果指定items,遍历将从指定索引出开始，如果指定，其值必须大于等于0
						//end(+)    整数			如果指定items,遍历将在（含）指定索引出处结束
						//step(+)	整数			遍历将只处理间隔指定step的项目，从第一项开始，step必须大于或等一1
				/*<c:forEach var="x" begin="1" end="5">
				 * 		<c:out value="${x}"/>
				</c:forEach>*/
				//对于每一次遍历，forEach标签都会创建一个有界变量，名称var定义，，变量只存在于forEach标签之间。一旦关闭forEach标签，它就会被删除
	
			//forTokens:用于遍历以特定分隔符隔开的令牌，其语法如下
				/*<c:forTokens items="stringOfTokens" delims="delimiters" [var="varname"] [varStatus="varstatusname"] [begin="begin"] [end="end"] [step="step"]>
				 * 		body content
				</c:forTokens>*/	
				//body content 是jsp
					//delims(+) 字符串				一组分隔符
				
		//格式化行为：jstl提供了格式化和解析数字与日期的标签：formatNumber,formatDate,timeZone,setTimeZone,parseNumber,parseDate
			//formatNumber标签
				//用于格式化数字，利用它的属性获得自己想要的格式，格式有两种
					//1：没有body content
					/*<fmt:formatNumber value="numericValue"
					[type="{number|currency|percent}"]
					[pattern="customPattern"]
					[currencyCode="currencyCode"]
					[currencySymbol="currencySymbol"]
					[groupingUsed="{true|false}"]
					[maxIntegerDigits="maxIntegerDigits"]
					[minIntegerDigits="minIntegerDigits"]
					[maxFractionDigits="maxFractionDigits"]
					[minFractionDigits="minFractionDigits"]
					[var="varName"]
					[scope="{page|request|session|application}"]
					/>*/
					//2:有 body content
					/*<fmt:formatNumber [type="{number|currency|percent}"]
					[pattern="customPattern"]
					[currencyCode="currencyCode"]
					[currencySymbol="currencySymbol"]
					[groupingUsed="{true|false}"]
					[maxIntegerDigits="maxIntegerDigits"]
					[minIntegerDigits="minIntegerDigits"]
					[maxFractionDigits="maxFractionDigits"]
					[minFractionDigits="minFractionDigits"]
					[var="varName"]
					[scope="{page|request|session|application}"]>
					numeric value to be formatted
					</fmt:formatNumber>*/
	/*
	value+				字符串或数字				要格式化的数字化值
	type+    			字符						说明该值是要被格式化成数字、货币，还是百分比。这个属性值有number、currency、percent
	pattern+ 			字符串 					定制格式化样式
	currencyCode+ 		字符串					ISO 4217码，如表5.11所示
	CurrencySymbol+ 	字符串 					货币符号
	groupingUsed+ 		布尔 						说明输出结果中是否包含组分隔符
	maxIntegerDigits+ 	整数 						规定输出结果的整数部分最多几位数字
	minIntegerDigits+ 	整数 						规定输出结果的整数部分最少几位数字
	maxFractionDigits+ 	整数 						规定输出结果的小数部分最多几位数字
	minFractionDigits+ 	整数 						规定输出结果的小数部分最少几位数字
	var 				字符串 					将输出结果存为字符串的有界变量名称
	scope 				字符串 					var的范围。如果有scope属性，则必须指定var属性
	*/
	
			/*//部分货币代码
			币别 			ISO 4217码 		大单位名称 		小单位名称
			加拿大元 		CAD 			加元 				分
			人民币 		CNY 			元 				角
			欧元 			EUR 			欧元 				分
			日元 			JPY 			日元 				钱
			英磅 			GBP 			英磅 				便士
			美元 			USD 			美元 				美分*/
	//例子
	/*<fmt:formatNumber value="12" type="number"/><br/>
	<fmt:formatNumber value="12" type="number" minIntegerDigits="3"/><br/>
	<fmt:formatNumber value="12" type="number" minFractionDigits="2"/><br/> 
	<fmt:formatNumber value="123456.78" pattern=".000"/><br/> 
	<fmt:formatNumber value="123456.78" pattern="#,#00.0#"/><br/>
	<fmt:formatNumber value="12" type="currency"/> <br/>
	<fmt:formatNumber value="12" type="currency" currencyCode="GBP"/><br/>
	<fmt:formatNumber value="0.12" type="percent"/><br/>
	<fmt:formatNumber value="0.125" type="percent" minFractionDigits="2"/><br/>*/
	//如果没有定义currencyCode属性，就使用浏览器的locale。
	
	//formatDate标签：用于格式化日期。语法如下
		//<fmt:formatDate value="date" 
		//                [type="{time|date|both}"] [dateStyle="{default|short|medium|long|full}"]
		//				  [timeStyle="{default|short|medium|long|full}"] [pattern="customerPattern"]
		//				  [timeZone="timeZone"] [var="varName"] [scope="{page|request|session|application	}"]
		//	/>	
	/*
	属性 							类型 							描述
	value+ 						java.util.Date 				要格式化的日期和/或时间
	type+ 						字符串						说明要格式化的是时间、日期，还是时间与日期元件
	dateStyle+ 					字符串						预定义日期的格式化样式，遵循java.text.DateFormat中定义的语义
	timeStyle+ 					字符串						预定义时间的格式化样式，遵循java.text.DateFormat中定义的语义
	pattern+ 					字符串 						定制格式化样式
	timezone+					字符串或java.util.TimeZone		定义用于显示时间的时区
	var 						字符串 						将输出结果存为字符串的有界变量名称
	scope 						字符串 						var的范围
	*/
			
		//<fmt:formatDate type="time" value="" timeStyle=""/>
	
	//timeZone标签：定义时区，使其body content的时间信息按指定时区进行格式化或解析，语法如下
		//<fmt:timeZone value="timeZone">body content </fmt:timeZone>	
			//属性值可以是String或者java.util.timeZone的动态值
			/*
				<fmt:timeZone value="GMT+1:00">
					<fmt:formatDate value="${now}" type="both" dateStyle="full"/>
				</fmt:timeZone>
				*/
	//setTimeZone标签：将指定的时区保存在一个有界变量或者时间配置变量中，语法格式如下
		//<fmt:setTimeZone value="timeZone" [var="varName"] [scope="{page|request|session|application}"]/>
			//var： 保存类型为java.util.TimeZone的时区的有界变量
	
	//parseNumber标签：用于将以字符串表示的数字，货币或者百分比解析成数字，有两种格式
		//1：没有body
			//<fmt:parseNumber vlaue="numberValue" [type="{number|currency|percent}"] [pattern="customPattern"] [parseLocale="parseLocale"] [IntegerOnly="true|false"] [var="varName"]  [scope="{page|request|session|application}"]/>
		//2:有body
			//同第一种相似，只不过是将value值放在标签之间
		//属性：parseLocale:字符串或者java.util.Locale			定义Locale,再解析操作期间将其默认为格式化样式，或者将pattern属性定义的样式引用其中
	
	//parseDate标签：以区分地域的格式解析以字符串表示的日期和时间，有两种格式	
		//1:无 body
			//<fmt:parseDate value="dateString" [type="{time|date|both}"] [dateStyle="default|short|medium|long|full"]
			// [timeStyle="{default|short|medium|long|full}"] [pattern="customPattern"] [timeZone="timeZone"] [parseLocale="parseLocale"]
			// [var="varName"] [scope="{page|request|session|application}"]
			// />
		//2:有body
		/*
			<fmt:parseDate [type="{time|date|both}"]
			[dateStyle="{default|short|medium|long|full}"]
			[timeStyle="{default|short|medium|long|full}"]
			[pattern="customPattern"]
			[timeZone="timeZone"]
			[parseLocale="parseLocale"]
			[var="varName"]
			[scope="{page|request|session|application}"]>
			date value to be parsed
			</fmt:parseDate>*/
				
		
			//函数
				//jstl 1.1 1.2定义了一套可以在el表达式中使用的标准函数，这些函数都集中放置再function标签库中，使用前需要导入函数库
					//<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
				//使用时：要以下列格式调用一个el:${fn:functionName}
			//大部分函数用于字符串操作，例如length函数：用于字符串和集合，返回集合或数组的项目数，或者返回一个字符串的字符数
				
				//contains函数：测试一个字符串是否包含指定子字符串，返回boolean值：${fn:contains("string","substring")}
				//containsIgnoreCase:同上，但是不区分大小写，返回boolean     ${fn:containsIgnoreCase("HAHAHAS","as")}
				//endsWith:测试一个字符串是否以指定的后缀结尾。返回boolean ${fn:endsWith("helloword","rd")}
				//escapeXml:用于给String编码，与out标签的escapeXml属性设置为true一致  ${fn:escapeXml("User <br/> to")}-->User &lt; br &gt to;
				//indexOf:返回指定子字符串在某字符串中第一次出现时的索引，如果没有找到则返回-1  ${fn:indexOf("	123HSAS","S")}  //4
				//join:将一个String数组中的所有元素合成一个字符串，并用指定的分隔符分开 ${fn:join(array,"\")}
				//length:返回集合或数组中的项目数，或者字符串中的字符数${fn:length("hahaha")}
				//replace:将字符串中出现的指定字符全部用其他字符代替${fn:replace(string,sptr,replacestr)}
				//split函数：将一个字符串分割成子字符串数组，作用于join相反:${fn:split(string,replace)}
				//startsWith:用于测试一个字符串是否以指定的前缀开头${fn:startsWith(string,s)}
				//substring:返回一个从指定基于0的起始索引（包含）到指定基于0的终止索引（不包含）的子字符串：${fn:substring(str,beginindex,endindex)}
				//substringAfter:返回指定子字符串第一次出现后的字符串部分：${fn:substringAfter(string,substring)}
				//substringBefore:返回指定字符串第一次出现前的字符串部分:${fn:substringBefore(string,substring)}
				//toLowerCase:将一个字符串转化为小写版本：${fn:toLowerCase("xiajinhui")}
				//toUpperCase:将一个字符串转化为大写版本:$(fn:toUpperCase("xiajinhui"))
				//trim：删除一个字符串开头和结尾的空白：${fn:trim("   xkj   ")}
		
	//小结：jstl可以完成一般的任务：遍历，集合，条件，处理xml文档，格式化文本，访问数据库以及操作数据：本章介绍了一些重要的标签：操作有界对象的标签：out/set/remove
	//执行条件测试的标签：if/choose.when/otherwise  遍历集合或tokens的标签forEach/forTokens  解析和格式化日期与数字的标签，parseNumber/parseDate/formatNumber/formatDate以及常用函数

















}
