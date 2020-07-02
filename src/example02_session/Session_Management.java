package example02_session;

public class Session_Management {
	//由于http的无状态性，使得会话管理或会话跟踪称为web应用开发的一个无可避免的主题，默认情况下，一个web服务器无法区分一个http请求是否为第一次访问
	//例如一个web邮件应用要求用户登陆后才能查看邮件，当用户登陆后不应该再次提示用户需要登陆，应用必须记住那些登陆过的用户，换句话说应用需要管理用户的会话
	
	//四种不同的状态保持技术
		//1：url重写
		//2：隐藏域
		//3：cookies
		//4:httpsession对象
	
	//URL重写
		//他是一种会话跟踪技术，它将一个或多个token添加到url的查询字符串中每个token通常为key=value形式：如下：
			//url?key1=value1&key2=value2&key3=value3..keyn=valuen
		//注意：url与token之间使用?分割，token之间使用&分割
		//url重写适合于token无需再太多url之间传递的情况下，然而他有如下限制：
			//1：url再某些浏览器中最长为2000字符串
			//2：若要传递值到下一个资源，需要将值插入到链接中，换句话说，静态页面很难传值
			//3：url重写需要再服务端上完成，所有的连接都必须带值，因此当一个页面存在很多链接时，处理过程是一个不小的挑战
			//4：某些字符，如空格，与，问号，等必须使用base64编码
			//5：所有信息都是可见的，某些情况下不合适
		//适合信息仅仅在少量页面间传递，且信息本身不敏感的情况下
		//在一个servlet写html到响应时，如果url为<href="?city=paris">表示此处为相对url,即url没有协议部分，相对会当前页面：http://localhost:8080/context/urlPattern
		//url重写一般用来传递参数
	
	//隐藏域
	//使用隐藏域来保存状态类似于url重写，是将值写道表单的hidden域，隐藏域仅对表单有效，优势在于：
		//1：没有字符数限制
		//2：无需额外的编码
	//缺点：不适合跨越多个界面
	
	//Cookies
		//url重写和隐藏域仅适合保存无需跨越太多页面的信息，如果需要在多个页面间传递信息，可以使用Cookies技术
		//Cookies是一个很少的信息片段，可自动的在浏览器和web服务器间交互，因此cookies可以存储在多个页面间传递信息，cookies作为httpheader的一部分
		//其传输由http协议控制，此外你可以控制cookies的有效时间，浏览器通常支持每个网站多达20个cookies
		//cookies的问题在于：用户可以通过改变浏览起的设置来拒绝接收cookies
		//Cookie c=new Cookie(name,value);
			//创建完一个cookie对象后，可以设置domain,path,maxAge属性，maxAge决定其何时过期
		//将cookie发送到浏览器需要调用HttpServletResponse.addCookie(cookie)
			//浏览器在访问同一台服务器时，会讲之前收到的cookie一并发送
		//除此之外，cookie也可以通过客户端的javascript脚本创建和删除，
		//服务端若要读取客户端提交的cookie:HttpServletRequest.getCookies(),该方法返回一个cookie数组，没有则返回null
		//需要遍历数组通过getName对比名称，目前还没有直接的通过名称选择cookie的方法，也米有直接的删除cookie的方法
			//只能创建一个同名cookie,并将maxAge设置为0，并添加到httpservletresponse接口中
			//Cookie c=new Cookie("userName","");
			//c.setMaxAge(0);
			//response.addCookie(c);
	
	//httpsession
	//在所有的会话跟踪技术中，httpsession是最强大和通用的，一个用户有且只有以恶搞httpsession,且不会被其他用户访问到
	//httpsession对象在第一次访问网站的时候自动被创建，可以获得：httpServletRequest.getSession方法获得
		//有两个重载方法：Httpsession getSession(),httpsession getSession(boolean create)
		//第一个方法返回当前的httpsession，如果没有则创建一个，第二个方法看boolean值而定
	//httpsession的setAttribute(name,value)将值放入httpsession---- 不同于url重写，隐藏域，cookie,放入httpsession的值是存在内存中的
		//存放如httpsession的值不限于string,可以是任意实现了java.io.serializable的java对象，当name相同时会覆盖旧值
	//java.util.Enumeration<String>getAttributeNames();//获取存在httpsession中的所有值
	//所有保存在httpsession的数据不会被发送到客户端，不同于其他会话管理技术，servlet容器为每个httpsession生成唯一的标识，并将该标识发送给浏览器，或创建一个名为JSESSIONID的cookie
	//或者在url后附加一个名为jsessionid的参数，在后续的请求中，浏览器会将标识提交给服务端，这样服务端就可以识别该请求由哪个用户发起的，servlet容器会自动选择一种方式传递会话标识，无需开发人员介入
	//可以通过调用httpsession的getId方法来获得该标识：java.lang.String getId()
	//httpsession还定义了一个名为invalidate的方法，该方法强制会话过期，并清空其保存的对象，默认情况下httpsession会在用户不活动一段时间后过期，时间可以通过部署描述符中的session-timeout配置
	//getMaxInactiveInterval方法查看会话多久过期
	//setMaxInactiveInterval单独对某个httpsession设定超时时间，若设置为0，则永不过期
	
	//小结：url重写和隐藏域是轻量级的会话跟踪技术，适用于那些仅跨少量页面的数据，cookie和httpsession更加灵活但也有限制，尤其是httpsession会消耗内存
	
	
	
}
