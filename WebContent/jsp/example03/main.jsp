<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	include指令
	<br/>
	<%@ include file="copyright.jspf" %>
	<br/>
	<%@ include file="footer.jsp" %>
	<jsp:include page="footer.jsp"/>
</body>
</html>