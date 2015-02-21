<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="fragments/staticFiles.jsp"/>

<html>
<c:choose>
	<c:when test="${priorityColor['new'] }">
		<c:set var="method" value="post"/>
		<spring:message code="priorityColor.createForm.title.addPriorityColor" var="title"/>
		<spring:message code="priorityColor.createForm.lable.button.add" var="addOrUpdateButton"/>
	</c:when>
	<c:otherwise>
		<c:set var="method" value="put"/>
		<spring:message code="priorityColor.createForm.title.updatePriorityColor" var="title"/>
		<spring:message code="priorityColor.createForm.lable.button.update" var="addOrUpdateButton"/>
	</c:otherwise>
</c:choose>

<title>${title}</title>

<body>
	<jsp:include page="fragments/header.jsp" />
<div class="container">
<p/>
	<table width="100%">
		<tr>
			<td> 
				<form:form modelAttribute="priorityColor" method="${method }" id="add-priorityColor-form" class="form-horizontal">
							<div class="form-group">
								<form:label path="colorCode" class="col-sm-2 control-label">
									<spring:message code="priorityColor.createForm.lable.colorCode"/>:
								</form:label>
								<div class="col-xs-2">
									<form:input path="colorCode" size="15" class="form-control colorpicker1"/>
									<script>
										$(function(){
    										$('.colorpicker1').colorpicker();
    												//${priorityColor['colorCode']});
										});
									</script>
								</div>
								<form:errors path="colorCode" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<form:label path="description" class="col-sm-2 control-label">
									<spring:message code="priorityColor.createForm.lable.description"/>:
								</form:label>
								<div class="col-xs-5"><form:input path="description" class="form-control"/></div>
								<form:errors path="description" cssClass="form_error_message"/>
							</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="submit" value='${addOrUpdateButton }' class="btn btn-default">
								<input type="button" onClick="history.go(-1);" class="btn btn-default" 
									 value='<spring:message code="priorityColor.createForm.lable.button.cancel"/>'>
							</div>
						</div>
				</form:form>
			</td>
		</tr>
	</table>
</div>
	<jsp:include page="fragments/footer.jsp" />
</body>
</html>