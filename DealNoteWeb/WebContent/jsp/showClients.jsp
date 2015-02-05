<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
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
	<form:form modelAttribute="agent" class="form-inline" method="POST">
		<div class="form-group">
			<form:label path="id">
				<spring:message code="clients.showForm.agentsList.label"/>:	</form:label>
			<form:select path="id" id="agentId" items="${agentsList}" itemLabel="name" itemValue="id" class="form-control"/>
		</div>
		<button type="submit" class="btn btn-default">
			<spring:message code="clients.showForm.button.showClients" /></button>
		
	</form:form>
	</td>
</tr>
<c:if test="${agent.id != null}">
	<tr>
		<td>
			<jsp:include page="clientsDataTable.jsp">
				<jsp:param value="${agent.id }" name="agentId"/>
			</jsp:include>
		</td>
	</tr>
</c:if>
</table>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>