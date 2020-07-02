<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <c:set  var="ctx" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>file-jsp</title>
<link rel="stylesheet" href="${ctx }/css/main.css" />
<!-- <style type="text/css">@import url("<c:url value="/css/main.css"/>");</style> -->
</head>
<body>
		<form:form commandName="book2"  action="${ctx }/saveFile" type="post" enctype="multipart/form-data"	>
			<fieldset>
				<p>
				<label for="name">BookName: </label>
				<form:input path="name" id="name" cssErrorClass="error"/>
				</p>
				<p>
				<label for="age">Age: </label>
				<form:input path="age" id="age"/>
				<form:errors path="age" cssClass="error"/>
				</p>
				<p>
				<label>File: </label>
				<input type="file" name="images[0]"/><!--<input type="file" name="images" multiple/>为多文件上传 -->
				</p>
				<p>
					<label><input type="submit" value="submit"/></label>
				</p>
			</fieldset>
		</form:form>
</body>
</html>