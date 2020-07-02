<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Login-Page</title>
</head>
<body>
		<form action='j_security_check' method='post'>
			name:<input type="text" name="j_username"/><br/>
			password:<input type="password" name="j_password"/><br/>
			<input type="submit" value="login"/>
		</form>
		
		
</body>
</html>