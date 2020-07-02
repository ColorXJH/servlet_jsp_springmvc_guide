<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>spring-taglib</title>
</head>
<body>
	<form:form commandName="book" method="post" action="${ctx}/product_save">
		isbn:<form:input path="isbn" id="isbn"/><br/>
		name:<form:input path="bookName" id="bookName"/><br/>
		price:<form:input path="price" id="price"/><br/>
		birth:<form:input path="birth" id="birth"/><br/>
		<input type="submit" value="submit"/>
		<form:errors path="birth"/>
	</form:form>
</body>
</html>