<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="/WEB-INF/mytags.tld" prefix="easy" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<easy:select>
		<input type="text" name="${value }" value="${text }"/>
		<%-- <option value="${value }">${text }</option> --%>
	</easy:select>
	<br/>
	${pageScope }
</body>
</html>