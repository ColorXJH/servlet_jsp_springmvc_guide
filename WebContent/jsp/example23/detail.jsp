<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Detail-Jsp</title>
</head>
<body>
		bookName:${book3.name }<br/>
		bookAge:${book3.age }<br/>
		bookImages:
		<c:forEach items="${book3.images }" var="image">
			<li>
			${image.originalFilename }
			<img width="100" src='<c:url value="/images/"/>${image.originalFilename}'/>
			</li>
		</c:forEach>
</body>
</html>