<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="fragments/staticFiles.jsp"/>

<html>
<c:choose>
	<c:when test="${measure['new'] }">
		<c:set var="method" value="post"/>
		<spring:message code="measure.createForm.title.addMeasure" var="title"/>
		<spring:message code="measure.createForm.lable.button.add" var="addOrUpdateButton"/>
	</c:when>
	<c:otherwise>
		<c:set var="method" value="put"/>
		<spring:message code="measure.createForm.title.updateMeasure" var="title"/>
		<spring:message code="measure.createForm.lable.button.update" var="addOrUpdateButton"/>
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
				<form:form modelAttribute="measure" method="${method }" id="add-measure-form" class="form-horizontal">
							<div class="form-group">
								<form:label path="outerId" class="col-sm-2 control-label">
									<spring:message code="measure.createForm.lable.outerId"/>:
								</form:label>
								<div class="col-xs-2"><form:input path="outerId" size="15" class="form-control"/></div>
							</div>
							<div class="form-group">
								<form:label path="name" class="col-sm-2 control-label">
									<spring:message code="measure.createForm.lable.name"/>:
								</form:label>
								<div class="col-xs-5"><form:input path="name" class="form-control"/></div>
								<form:errors path="name" cssClass="form_error_message"/>
							</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="submit" value='${addOrUpdateButton }' name="saveMeasure" class="btn btn-default">
								<input type="button" onClick="history.go(-1);" class="btn btn-default" 
									 name="cancelMeasure" value='<spring:message code="measure.createForm.lable.button.cancel"/>'>
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