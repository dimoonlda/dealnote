<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="fragments/staticFiles.jsp" />

<html>
<title><spring:message code="serviceClient.showForm.title" /></title>
<body>
	<jsp:include page="fragments/header.jsp" />
	<div class="container">
		<table width="100%">
			<tr>
				<td><spring:url value="/serviceClients/" var="showServiceClientUrl" />
					<div>
						<sec:authorize access="hasRole('ROLE_USER')">
							<a href="${showServiceClientUrl}new"><spring:message
									code="serviceClient.dataTable.button.addClient" /></a>
						</sec:authorize>
					</div>
					<br> <script type="text/javascript">
						$(document).ready(function() {
							$("#serviceClient").dataTable({
								"bServerSide" : true,
								"sAjaxSource" : '${showServiceClientUrl}/listgrid',
								"bProcessing" : true,
								"stateSave" : true,
						        //this function change cell's value before showing
						        "fnRowCallback": function( nRow, aData, iDisplayIndex ) {
		                            var $cell=$('td:eq(2)', nRow);
		                            var $recId = aData[0];
		                            var $clientName = aData[2];
		                            var $clientUrl = aData[3];
		                            //you can use $cell.text and set only text value 
		                            $cell.html('<a href="${showServiceClientUrl}' + $recId + '">' + $clientName + '</a>');
		                            $cell=$('td:eq(3)', nRow);
		                            $cell.html('<a href="' + $clientUrl + '">' + $clientUrl + '</a>');
		                            $cell=$('td:eq(6)', nRow);
		                            $cell.html('<a href="${showServiceClientUrl}' + $recId + '/delete">D</a>' + ' <a href="${showServiceClientUrl}' + $recId + '/edit">E</a>');
		                            return nRow;
		                    	}
							});
						});
					</script>

					<table id="serviceClient" class="display" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th width="10%"><spring:message
										code="serviceClient.dataTable.header.id" /></th>
								<th width="10%"><spring:message
										code="serviceClient.dataTable.header.typeCode" /></th>
								<th width="15%"><spring:message
										code="serviceClient.dataTable.header.name" /></th>
								<th width="30%"><spring:message
										code="serviceClient.dataTable.header.url" /></th>
								<th width="15%"><spring:message
										code="serviceClient.dataTable.header.fileName" /></th>
								<th width="5%"><spring:message
										code="serviceClient.dataTable.header.version" /></th>
								<th width="5%"><spring:message
										code="serviceClient.dataTable.header.action" /></th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table></td>
			</tr>
		</table>
		<jsp:include page="fragments/footer.jsp" />
	</div>
</body>
</html>