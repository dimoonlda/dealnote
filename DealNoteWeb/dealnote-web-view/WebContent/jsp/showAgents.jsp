<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="fragments/staticFiles.jsp" />

<html>
<title><spring:message code="agents.showForm.title" /></title>
<body>
	<jsp:include page="fragments/header.jsp" />
	<div class="container">
		<table width="100%">
			<tr>
				<td><spring:url value="/agents/" var="showAgentUrl" />
					<div>
						<sec:authorize access="hasRole('ROLE_USER')">
							<a href="${showAgentUrl}/new"><spring:message
									code="agents.dataTable.button.addAgent" /></a>
						</sec:authorize>
					</div>
					<br> <script type="text/javascript">
						$(document).ready(function() {
							$("#agents").dataTable({
								"bServerSide" : true,
								"sAjaxSource" : '${showAgentUrl}/listgrid',
								"bProcessing" : true,
								"stateSave" : true,
						        //this function change cell's value before showing
						        "fnRowCallback": function( nRow, aData, iDisplayIndex ) {
		                            var $cell=$('td:eq(1)', nRow);
		                            var $recId = aData[0];
		                            var $agentName = aData[1];
		                            //you can use $cell.text and set only text value 
		                            $cell.html('<a href="${showAgentUrl}' + $recId + '">' + $agentName + '</a>');
		                            $cell=$('td:eq(4)', nRow);
		                            $cell.html('<a href="${showAgentUrl}' + $recId + '/delete">D</a>' + ' <a href="${showAgentUrl}' + $recId + '/edit">E</a>');
		                            return nRow;
		                    	}
							});
						});
					</script>

					<table id="agents" class="display" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th width="10%"><spring:message
										code="agents.dataTable.header.id" /></th>
								<th width="70%"><spring:message
										code="agents.dataTable.header.name" /></th>
								<th width="10%"><spring:message
										code="agents.dataTable.header.clientCount" /></th>
								<th width="5%"><spring:message
										code="agents.dataTable.header.active" /></th>
								<th width="5%"><spring:message
										code="agents.dataTable.header.action" /></th>
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