<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  
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
		<td><spring:url value="/clients/" var="showClientUrl" /> <script
							type="text/javascript">
			$(document)
					.ready(
							function() {
								$("#clients")
										.dataTable(
												{
													"bServerSide" : true,
													"sAjaxSource" : "${showClientUrl }listgrid/${agent.id }",
													"bProcessing" : true,
													"pagingType" : "full_numbers",
													"stateSave" : true,
													//this function change cell's value before showing
											        "fnRowCallback": function( nRow, aData, iDisplayIndex ) {
							                            var $cell=$('td:eq(1)', nRow);
							                            var $recId = aData[0];
							                            var $clientName = aData[1];
							                            //you can use $cell.text and set only text value 
							                            $cell.html('<a href="${showClientUrl}' + $recId + '">' + $clientName + '</a>');
							                            $cell=$('td:eq(3)', nRow);
							                            $cell.html('<a href="${showClientUrl}' + $recId + '/delete">D</a>' + ' <a href="${showClientUrl}' + $recId + '/edit">E</a>');
							                            return nRow;
							                    	}
												});
							});
		</script>
						<div>
							<sec:authorize access="hasRole('ROLE_USER')">
								<a href="${showClientUrl}/new"><spring:message
										code="clients.dataTable.button.addClient" /></a>
							</sec:authorize>
						</div>
						<br>

						<table id="clients" class="display" cellspacing="0" width="100%">
							<thead>
								<tr>
									<th width="10%"><spring:message
											code="clients.dataTable.header.id" /></th>
									<th width="40%"><spring:message
											code="clients.dataTable.header.name" /></th>
									<th width="45%"><spring:message
											code="clients.dataTable.header.addressLocation" /></th>
									<th width="5%"><spring:message
											code="clients.dataTable.header.action" /></th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table></td>
	</tr>
</c:if>
</table>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>