<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	overriding jspInit and jspDestory
	<%!
		public void jspInit(){
			System.out.println("jspInit...");
	}
	public void jspDestory(){
		System.out.println("jspDestory...");
	}
			
	%>
</body>
</html>