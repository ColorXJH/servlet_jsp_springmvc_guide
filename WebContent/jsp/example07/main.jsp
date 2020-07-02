<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	your referer header is:${header.referer }
	<br/>
	${header.referer }
	<br/>
	<tags:doBodyDemo>
		${header.referer }<!--该值存入了refererp属性中  -->
	</tags:doBodyDemo>
	<a href="viewReferer.jsp">view</a> the referer as a session attribute
</body>
</html>