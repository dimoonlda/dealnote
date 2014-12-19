<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agents</title>
</head>
<body>
<table width="100%">
<tr>
	<td><!-- header -->
		<jsp:include page="header.jsp"/>
	</td>
</tr>
<tr>
	<td><!-- body --> 		
	</td>
</tr>
	<tr>
		<td>
			<jsp:include page="agentsDataTable.jsp"/>
		</td>
	</tr>
<tr>
	<td><!-- footer -->
		<jsp:include page="footer.jsp"/>
	</td>
</tr>
</table>
</body>
</html>