<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="fragments/staticFiles.jsp"/>

<html>
<title>${priorityColor.colorCode}</title>
<body>
<jsp:include page="fragments/header.jsp" />
<div class="container">
<h2><spring:message code="priorityColor.showInfoForm.title"/></h2>
<table class="table table-striped" style="width:600px;">
	<tr>
		<th><spring:message code="priorityColor.showInfoForm.lable.id"/>:</th>
		<td>${requestScope['priorityColor'].id}</td>
	</tr>
	<tr>
		<c:set value="${requestScope['priorityColor'].colorCode}" var="colorCode"/>
		<th><spring:message code="priorityColor.showInfoForm.lable.colorCode"/>:</th>
		<td bgcolor="${colorCode}">${colorCode}</td>
	</tr>
	<tr>
		<th><spring:message code="priorityColor.showInfoForm.lable.description"/>:</th>
		<td>${requestScope['priorityColor'].description}</td>
	</tr>
	<tr>
		<td colspan="2"><center>
			<input type="button" value='<spring:message code="priorityColor.showInfoForm.button.cancel"/>' 
				onclick="history.go(-1);" class="btn btn-default">
		</center></td>
	</tr>
</table>
</div>
<jsp:include page="fragments/footer.jsp" />
</body>
</html>