<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="fragments/staticFiles.jsp"/>

<html>
<title>${client.name}</title>
<body>
<jsp:include page="fragments/header.jsp" />
<div class="container">
<h2><spring:message code="clients.showInfoForm.title"/></h2>
<table class="table table-striped" style="width:600px;">
	<tr>
		<th><spring:message code="clients.showInfoForm.lable.id"/>:</th>
		<td>${requestScope['client'].id}</td></tr>
	<tr>
		<th><spring:message code="clients.showInfoForm.lable.outerId"/>:</th>
		<td>${requestScope['client'].outerId}</td></tr>
	<tr>
		<th><spring:message code="clients.showInfoForm.lable.fullName"/>:</th>
		<td>${requestScope['client'].name}</td></tr>
	<tr>
		<th><spring:message code="clients.showInfoForm.lable.clientGroup"/>:</th>
		<td>${requestScope['client'].group.name}</td></tr>
	<tr>
		<th><spring:message code="clients.showInfoForm.lable.phone"/>:</th>
		<td>${requestScope['client'].phone}</td></tr>
	<tr>
		<th><spring:message code="clients.showInfoForm.lable.taxCode"/>:</th>
		<td>${requestScope['client'].taxCode}</td></tr>
	<tr>
		<th><spring:message code="clients.showInfoForm.lable.agentName"/>:</th>
		<td>${requestScope['client'].agent.name}</td></tr>
	<tr>
		<th><spring:message code="clients.showInfoForm.lable.addressLocation"/>:</th>
		<td>${requestScope['client'].addressLocation}</td></tr>
	<tr>
		<th><spring:message code="clients.showInfoForm.lable.route"/>:</th>
		<td>${requestScope['client'].route.name}</td>
	</tr>
	<tr>
		<th><spring:message code="clients.showInfoForm.lable.okpo"/>:</th>
		<td>${requestScope['client'].okpo}</td></tr>
	<tr>
		<th><spring:message code="clients.showInfoForm.lable.bankAccount"/>:</th>
		<td>${requestScope['client'].bankAccount}</td></tr>
	<tr>
		<th><spring:message code="clients.showInfoForm.lable.dogNumber"/>:</th>
		<td>${requestScope['client'].dogNum}</td></tr>
	<tr>
		<th><spring:message code="clients.showInfoForm.lable.addressLaw"/>:</th>
		<td>${requestScope['client'].addressLaw}</td></tr>
	<tr>
		<td colspan="2"><center>
			<input type="button" value='<spring:message code="clients.showInfoForm.button.cancel"/>' 
				onclick="history.go(-1);" class="btn btn-default">
		</center></td>
	</tr>
</table>
</div>
<jsp:include page="fragments/footer.jsp" />
</body>
</html>