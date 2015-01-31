<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="fragments/staticFiles.jsp"/>

<html>
<title><spring:message code="clients.showForm.title"/></title>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="container">
<table width="100%">
<tr>
	<td><!-- body --> 
	<center><form id="fmFilter" method="GET" action='<c:url value="/clients/listClients/"/>' >
	<table border="0" cellspacing="5">
    <tr>
	<th  align="right"><spring:message code="clients.showForm.agentsList.label"/>:</th>
	<td align="left">
		<jsp:include page="agentsList.jsp">
			<jsp:param value='${param["agentid"]}' name="agentid"/>
		</jsp:include>
		<input type="submit" name="showClients" value='<spring:message code="clients.showForm.button.showClients" />'>
	</td></tr>
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
</table>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>