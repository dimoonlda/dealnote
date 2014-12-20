<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agent's GPS coordinates</title>
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
<center><form id="fmFilter" method="POST" action='<c:url value="/dictionaries/ShowClients.do"/>' >
	<table border="0" cellspacing="5">
    <tr>
	<th  align="right">Choose agent:</th>
	<td align="left">
		<jsp:include page="agentsList.jsp">
			<jsp:param value='${param["agentid"]}' name="agentid"/>
		</jsp:include>
	</td></tr>
    <tr >
    	<td colspan="2" align="right">
    		<table width="100%"><tr align="right">
    			<td><input type="submit" name="showClients" value="Show clients"></td>
    		</tr></table>
    	</td>
    </tr>	
	</table>
</form></center>		
	</td>
</tr>
<c:if test="${param['agentid'] != null}">
	<tr>
		<td>
			<jsp:include page="clientsDataTable.jsp"/>
		</td>
	</tr>
</c:if>
<tr>
	<td><!-- footer -->
		<jsp:include page="footer.jsp"/>
	</td>
</tr>
</table>
</body>
</html>