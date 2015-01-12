<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login form.</title>
</head>
<body>
	<div align="center">
    <strong>Wellcome to the DealNote Web.</strong>
	<form action="j_security_check" method="post">
		  username: <input name="j_username" type="text"><br><br>
		  password: <input name="j_password" type="password"><br>
					<input type="submit" value="Login">
	</form>
    </div>
</body>
</html>