<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="fragments/staticFiles.jsp"/>

<html>
<c:choose>
	<c:when test="${client['new'] }">
		<c:set var="method" value="post"/>
		<spring:message code="clients.createForm.title.addClient" var="title"/>
		<spring:message code="clients.createForm.lable.button.add" var="addOrUpdateButton"/>
	</c:when>
	<c:otherwise>
		<c:set var="method" value="put"/>
		<spring:message code="clients.createForm.title.updateClient" var="title"/>
		<spring:message code="clients.createForm.lable.button.update" var="addOrUpdateButton"/>
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
				<form:form modelAttribute="client" method="${method }" id="add-client-form" class="form-horizontal">
							<div class="form-group">
								<form:label path="outerId" class="col-sm-2 control-label">
									<spring:message code="clients.createForm.lable.outerId"/>:
								</form:label>
								<div class="col-xs-2"><form:input path="outerId" size="15" class="form-control"/></div>
								<label class="checkbox-inline">
									<form:checkbox path="isNotActive" value="1"/>
									<form:label path="isNotActive">
										<spring:message code="clients.createForm.lable.notActive"/>
									</form:label>
								</label>
								<label class="checkbox-inline">
									<form:checkbox path="stopShipment" value="1"/>
									<form:label path="stopShipment">
										<spring:message code="clients.createForm.lable.stopShipment"/>
									</form:label>
								</label>
							</div>
							<div class="form-group">
								<form:label path="name" class="col-sm-2 control-label">
									<spring:message code="clients.createForm.lable.name"/>:
								</form:label>
								<div class="col-xs-5"><form:input path="name" class="form-control"/></div>
								<form:errors path="name" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<form:label path="group" class="col-sm-2 control-label">
									<spring:message code="clients.createForm.lable.group"/>:
								</form:label>
								<div class="col-xs-3">
									<form:select path="group" items="${groupsList}" itemLabel="name" itemValue="id" class="form-control"/></div>
							</div>
							<div class="form-group">
								<form:label path="phone" class="col-sm-2 control-label">
									<spring:message code="clients.createForm.lable.phone"/>:
								</form:label>
								<div class="col-xs-3"><form:input path="phone" class="form-control"/></div>
							</div>
							<div class="form-group">
								<form:label path="taxCode" class="col-sm-2 control-label">
									<spring:message code="clients.createForm.lable.taxCode"/>:
								</form:label>
								<div class="col-xs-3"><form:input path="taxCode" class="form-control"/></div>
							</div>
							<div class="form-group">
								<form:label path="agent" class="col-sm-2 control-label">
									<spring:message code="clients.createForm.lable.agent"/>:
								</form:label>
								<div class="col-xs-3">
									<form:select path="agent" items="${agentsList}" itemLabel="name" itemValue="id" class="form-control"/></div>
							</div>
							<div class="form-group">
								<form:label path="addressLocation" class="col-sm-2 control-label">
									<spring:message code="clients.createForm.lable.addressLocation"/>:
								</form:label>
								<div class="col-xs-5"><form:input path="addressLocation" class="form-control"/></div>
								<form:errors path="addressLocation" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<form:label path="route" class="col-sm-2 control-label">
									<spring:message code="clients.createForm.lable.route"/>:
								</form:label>
								<div class="col-xs-3">
									<form:select path="route" class="form-control">
										<spring:message code="clients.createForm.message.selectRoute" var="defaultRoute"/>
										<form:option value="-1" label="${defaultRoute}"/>
										<form:options items="${routesList}" itemLabel="name" itemValue="id"/>
									</form:select>
								</div>
							</div>
							<div class="form-group">
								<form:label path="okpo" class="col-sm-2 control-label">
									<spring:message code="clients.createForm.lable.okpo"/>:
								</form:label>
								<div class="col-xs-3"><form:input path="okpo" class="form-control"/></div>
							</div>
							<div class="form-group">
								<form:label path="mfo" class="col-sm-2 control-label">
									<spring:message code="clients.createForm.lable.mfo"/>:
								</form:label>
								<div class="col-xs-3"><form:input path="mfo" class="form-control"/></div>
							</div>
							<div class="form-group">
								<form:label path="bankName" class="col-sm-2 control-label">
									<spring:message code="clients.createForm.lable.bankName"/>:
								</form:label>
								<div class="col-xs-5"><form:input path="bankName" class="form-control"/></div>
							</div>
							<div class="form-group">
								<form:label path="bankAccount" class="col-sm-2 control-label">
									<spring:message code="clients.createForm.lable.bankAccount"/>:
								</form:label>
								<div class="col-xs-3"><form:input path="bankAccount" class="form-control"/></div>
							</div>
							<div class="form-group">
								<form:label path="dogNum" class="col-sm-2 control-label">
									<spring:message code="clients.createForm.lable.contract"/>:
								</form:label>
								<div class="col-xs-3"><form:input path="dogNum" class="form-control"/></div>
							</div>
							<div class="form-group">
								<form:label path="addressLaw" class="col-sm-2 control-label">
									<spring:message code="clients.createForm.lable.addressLaw"/>:
								</form:label>
								<div class="col-xs-5"><form:input path="addressLaw" class="form-control"/></div>
							</div>
							<div class="form-group">
								<form:label path="defaultDiscount" class="col-sm-2 control-label">
									<spring:message code="clients.createForm.lable.defaultDiscount"/>:
								</form:label>
								<div class="col-xs-1"><form:input path="defaultDiscount" class="form-control"/></div>
							</div>
							<div class="form-group">
								<form:label path="longitude" class="col-sm-2 control-label">
									<spring:message code="clients.createForm.lable.longitude"/>:
								</form:label>
								<div class="col-xs-3"><form:input path="longitude" class="form-control"/></div>
							</div>
							<div class="form-group">
								<form:label path="latitude" class="col-sm-2 control-label">
									<spring:message code="clients.createForm.lable.latitude"/>:
								</form:label>
								<div class="col-xs-3"><form:input path="latitude" class="form-control"/></div>
							</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="submit" name="saveClient"
									value='${addOrUpdateButton }' class="btn btn-default">
								<input TYPE="button" onClick="history.go(-1);" class="btn btn-default" 
									name="cancelClient" value='<spring:message code="clients.createForm.lable.button.cancel"/>'>
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