<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="fragments/staticFiles.jsp"/>

<html>
<spring:message code="file.upload.success" var="uploadSuccess"/>
<spring:message code="file.upload.unsuccess" var="uploadUnSuccess"/>
<c:choose>
	<c:when test="${serviceClient['new'] }">
		<c:set var="method" value="post"/>
		<spring:message code="serviceClient.createForm.title.addClient" var="title"/>
		<spring:message code="serviceClient.createForm.lable.button.add" var="addOrUpdateButton"/>
	</c:when>
	<c:otherwise>
		<c:set var="method" value="put"/>
		<spring:message code="serviceClient.createForm.title.updateClient" var="title"/>
		<spring:message code="serviceClient.createForm.lable.button.update" var="addOrUpdateButton"/>
	</c:otherwise>
</c:choose>

<title>${title}</title>

<body>
	<jsp:include page="fragments/header.jsp" />
	<script type="text/javascript">
	function uploadFormData() {
		var oMyForm = new FormData();
		oMyForm.append("clientFile", clientFile.files[0]);
		$.ajax({
					url : '<spring:url value="/serviceClients/uploadclientfile"/>',
					data : oMyForm,
					dataType : 'text',
					processData : false,
					contentType : false,
					type : 'POST',
					success : function(data) {
						if(data === '1') {
							data = '${uploadSuccess}';
							var newFileName = document.getElementById('clientFile').value;
							$('#fileName').val(newFileName);
						} else {
							data = '${uploadUnSuccess}';
						}
						$('#uploadResult').html('<b>' + data + '</b>');
					}
				});
	}
	</script>
	
<div class="container">
<p/>
	<table width="100%">
		<tr>
			<td> 
				<form:form modelAttribute="serviceClient" method="${method }" id="add-serviceClient-form" class="form-horizontal">
							<div class="form-group">
								<form:label path="typeCode" class="col-sm-2 control-label">
									<spring:message code="serviceClient.createForm.lable.typeCode"/>:
								</form:label>
								<div class="col-xs-2"><form:input path="typeCode" size="15" class="form-control"/></div>
							</div>
							<div class="form-group">
								<form:label path="name" class="col-sm-2 control-label">
									<spring:message code="serviceClient.createForm.lable.name"/>:
								</form:label>
								<div class="col-xs-7"><form:input path="name" class="form-control"/></div>
								<form:errors path="name" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<form:label path="url" class="col-sm-2 control-label">
									<spring:message code="serviceClient.createForm.lable.url"/>:
								</form:label>
								<div class="col-xs-7"><form:input path="url" class="form-control"/></div>
								<form:errors path="url" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<form:label path="fileName" class="col-sm-2 control-label">
									<spring:message code="serviceClient.createForm.lable.fileName"/>:
								</form:label>
								<div class="col-xs-5"><form:input path="fileName" class="form-control" id="fileName"/></div>
								<form:errors path="fileName" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<form:label path="version" class="col-sm-2 control-label">
									<spring:message code="serviceClient.createForm.lable.version"/>:
								</form:label>
								<div class="col-xs-2"><form:input path="version" class="form-control"/></div>
								<form:errors path="version" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">
									<spring:message code="serviceClient.createForm.lable.chooseFile"/>:
								</label>
								<c:if test="${serviceClient['file']!=null }">
									<div class="col-xs-1">	
										<a href="${serviceClient['url'] }"><img src='<c:url value="/images/dealnote/diskette.png" />'></a>
									</div>
								</c:if>
								<div class="col-xs-5">
									<input type="file" id="clientFile" name="clientFile" onchange="uploadFormData()"/>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<p class="help-block" id="uploadResult"></p>
								</div>
							</div>
	
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<input type="submit" value='${addOrUpdateButton }' class="btn btn-default">
									<input TYPE="button" onClick="history.go(-1);" class="btn btn-default" 
										value='<spring:message code="serviceClient.createForm.lable.button.cancel"/>'>
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