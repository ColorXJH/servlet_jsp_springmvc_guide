<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:useBean id="today" class="java.util.Date"/>
	<jsp:useBean id="employee" class="example03_jsp.Employee"/>
	<jsp:setProperty name="employee" property="name" value="xjh"/>
	<jsp:setProperty name="employee" property="age" value="26"/>
	name:<jsp:getProperty name="employee" property="name"/>
	<br/>
	age:<jsp:getProperty name="employee" property="age"/>
	<%=today %>
</body>
</html>