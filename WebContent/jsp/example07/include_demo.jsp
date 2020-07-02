<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<mytag:include_tag_file />
	<br />
	<mytag:encode input="<br/><h1></h1>  can change ?" />
	<br />
	<mytag:varDemo>
	in longDate:${longDate }<br />
	in shortDate:${shortDate }<br />
	</mytag:varDemo>
	<mytag:invokeDemo>
		<jsp:attribute name="productDetail">
			<table width="220" border="1">
		<tr>
			<td><b>Product Name</b></td>
			<td>${productName}</td>
		</tr>
		<tr>		
			<td><b>Description</b></td>
			<td>${description}</td>
		</tr>
		<tr>
			<td><b>Price</b></td>
			<td>${price}</td>
		</tr>
			</table>
		
		</jsp:attribute>


	</mytag:invokeDemo><!--表格带的过去（因为他是一个fragment）  -->
		
	<mytag:doBodyDemo><!--表格带不过去  -->
		<table>
			<tr>
				<td>111</td>
			</tr>
		</table>
	</mytag:doBodyDemo>
	<br/>
		${requestScope.invok }
	<a href="viewReferer.jsp">haha</a>
</body>
</html>