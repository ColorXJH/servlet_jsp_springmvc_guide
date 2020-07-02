<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="org.apache.naming.java.javaURLContextFactory"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<%!
		public String getTodaysDate(){
			return new Date().toString();
		}
	%>
	to day is <%=Calendar.getInstance().getTime() %>
	<br/>
	to day is <%=
			Calendar.getInstance().getTime()//结尾无分号
	%>
	<br/>
	to day is <%=getTodaysDate() %>
	<br/>
	<%
		out.print("to day is "+getTodaysDate());
	%>
	<br/>
	<% 
		out.println("to day is "+Calendar.getInstance().getTime());//结尾有分号
	%>
</body>
</html>