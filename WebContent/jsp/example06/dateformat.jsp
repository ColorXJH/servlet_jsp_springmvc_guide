<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/mytags.tld" prefix="easy"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	hello!!<br/>
	<easy:dateFormater header="Country" items="beijing,shanghai,shenzhen,guangzhou"/><!--标签属性 -->
	<br/>
	<easy:dateFormater header="country">
		<jsp:attribute name="items"><!--标准属性  -->
		beijing,shanghai,guangzhou,shenzhen
		</jsp:attribute>
	</easy:dateFormater><br/>
	<jsp:setProperty name="employee" property="age" value="26"/>
	<jsp:setProperty name="employee" value="100">
		<jsp:attribute name="property" ><!-- 表示父标签的属性 -->
			age
		</jsp:attribute>
	</jsp:setProperty>
	name:<jsp:getProperty name="employee" property="age"/>
			
	
	<br/>
	${id }
</body>
</html>