<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%! 
	int x=6;
%>
		<% 
		request.setAttribute("xjh", 26);
		session.setAttribute("xjh", "hahaha");
		response.setHeader("dearbear", "26");
		application.setAttribute("xjh", "xixixi------<br/>");
		%>
		${pageContext.request}
		<br/>
		${pageContext.request.method }
		<br/>
		${pageContext.session.id }
		<br/>
		${pageContext.out }
		<br/>
		${pageContext.response }
		<br/>
		${cookie }
		<br/>
		${requestScope }
		<br/>
		${header }
		<br/>
		<br/>
		${param }
		<br/>
		${paramValues.xjh[0] }
		<br/>
		${initParam }
		<br/>
		-----------------------------------------------------1111111111111111111111111111111--------------------------
		<br/>
		${sessionScope }
		<br/>
		-----------------------------------------------------1111111111111111111111111111111--------------------------
		<br/>
		${applicationScope }
		<br/>
		-----------------------------------------------------1111111111111111111111111111111--------------------------
		<br/>
		${pageScope }
		<br/>
		${empty k }
</body>
</html>