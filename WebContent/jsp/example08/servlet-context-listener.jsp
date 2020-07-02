<%@page import="example08_minotor.HttpSessionBindingListenerImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
		<!--el表达式中可以取到servletContext中的属性（对应appliationScope）  -->
		those name are display:<br/>
		<c:forEach items="${map }" var="m">
		<li>${m.key }---${m.value }</li>
		</c:forEach>
		<%
		application.setAttribute("add-name", "add1");
		application.setAttribute("add-name", "replace1");
		application.setAttribute("add-name", null);
		config.getServletContext().setAttribute("add-name", "again1");
		Cookie c=new Cookie("xjh","value1");
		response.addCookie(c);
		session.setAttribute("xjh1", new HttpSessionBindingListenerImpl());
		session.setAttribute("xjh1", null);
		request.setAttribute("newbi", "newbi");
		request.setAttribute("newbi", "xinnewbi");
		request.setAttribute("newbi", null);
		
		%>
</body>
</html>