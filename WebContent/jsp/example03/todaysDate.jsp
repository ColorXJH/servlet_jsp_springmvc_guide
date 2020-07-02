<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
		<% 
			DateFormat  dateFormat=DateFormat.getDateInstance(DateFormat.LONG);
			String s=dateFormat.format(new Date());
			out.println("today is "+s);
			String name=request.getParameter("username");
			out.println(name);
		%>
</body>
</html>