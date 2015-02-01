<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="fragments/staticFiles.jsp"/>

<html>
<title>${agent.name}</title>
<body>
<jsp:include page="fragments/header.jsp" />
<div class="container">
<h2><spring:message code="agents.showInfoForm.title"/></h2>
<table class="table table-striped" style="width:600px;">
	<tr>
		<th><spring:message code="agents.showInfoForm.lable.id"/>:</th>
		<td>${requestScope['agent'].id}</td>
	</tr>
	<tr>
		<th><spring:message code="agents.showInfoForm.lable.outerId"/>:</th>
		<td>${requestScope['agent'].outerId}</td>
	</tr>
	<tr>
		<th><spring:message code="agents.showInfoForm.lable.name"/>:</th>
		<td>${requestScope['agent'].name}</td>
	</tr>
	<tr>
		<c:if test="${agent.active==1 }">
			<c:set value="checked" var="isChecked"/>
		</c:if>
		<th><spring:message code="agents.showInfoForm.lable.isActive"/>:</th>
		<td><input type="checkbox" ${isChecked } disabled></td>
	</tr>
	<tr>
		<th><spring:message code="agents.showInfoForm.lable.roleName"/>:</th>
		<td>
			<c:choose>
				<c:when test="${agent.roleCode==1 }">
					<spring:message code="agents.createForm.lable.role.agent" var="roleName"/>
				</c:when>
				<c:otherwise>
					<spring:message code="agents.createForm.lable.role.supervisor" var="roleName"/>
				</c:otherwise>
			</c:choose>
			${roleName }
		</td></tr>
	<tr>
		<td colspan="2"><center>
			<input type="button" value='<spring:message code="agents.showInfoForm.button.cancel"/>' 
				onclick="history.go(-1);" class="btn btn-default">
		</center></td>
	</tr>
</table>
</div>
<jsp:include page="fragments/footer.jsp" />
</body>
</html>