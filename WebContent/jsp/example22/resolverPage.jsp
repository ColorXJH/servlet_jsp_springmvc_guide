<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>I18N-I10N</title>
<style type="text/css">@import url('<c:url value="/css/main.css"/>');</style>
</head>
<body>
		<!-- ?lang=xxx表示在当前页面路径后面添加参数 -->
		language:<a href="?lang=en_US">English</a>  <a href="?lang=zh_CN">Chinese</a>
		<spring:message code="showlog"/>
		<br/>
		Current Locale:${pageContext.response.locale }	<br/>
		accept-language header:${header["accept-language"] }
		<form:form commandName="book" action="product_form_jsp">
				<label for="isbn"><spring:message code="hahah" text="this is the default text"/></label>
				<form:input path="isbn"/>
		</form:form>    
</body>
</html>