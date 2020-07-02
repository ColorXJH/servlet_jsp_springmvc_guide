<%@page import="java.util.Enumeration,java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>隐式对象</title>
</head>
<body>
		<%  
		//response.setContentType("text/json");//默认为“text/html”
		pageContext.setAttribute("XJH", "HAHA",pageContext.PAGE_SCOPE);
		request.setAttribute("XJH", "BEAR");/*前两句的效果是相同的  */
		out.println(request.getAttribute("XJH"));
		for(Enumeration<String>e=request.getHeaderNames();e.hasMoreElements();){
			String header=e.nextElement();
			out.println(header+"<br/>");
		}
		
		out.println(session.getId()+"<br/>");
		out.println(response.getBufferSize()+"<br/>");
		out.println(config.getServletName()+"<br/>");
		out.println(application.getContextPath()+"<br/>");
		out.println(application.getServerInfo()+"<br/>");
		%>
</body>
</html>