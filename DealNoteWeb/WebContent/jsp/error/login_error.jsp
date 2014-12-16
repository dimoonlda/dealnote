<%@ page errorPage="true" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error in login form.</title>
</head>
<body>
<b>Error in login to DealNote Web!</b><br>
${requestScope.j_username }
${pageContext.exception.message }
</body>
</html>