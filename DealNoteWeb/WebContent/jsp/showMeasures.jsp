<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="fragments/staticFiles.jsp" />

<html>
<title><spring:message code="measure.showForm.title" /></title>
<body>
	<jsp:include page="fragments/header.jsp" />
	<div class="container">
		<table width="100%">
			<tr>
				<td><spring:url value="/measure/" var="showMeasureUrl" />
					<div>
						<sec:authorize access="hasRole('ROLE_USER')">
							<a href="${showMeasureUrl}new"><spring:message
									code="measure.dataTable.button.addMeasure" /></a>
						</sec:authorize>
					</div>
					<br> <script type="text/javascript">
						$(document).ready(function() {
							$("#measure").dataTable({
								"bServerSide" : true,
								"sAjaxSource" : '${showMeasureUrl}/listgrid',
								"bProcessing" : true
							});
						});
					</script>

					<table id="measure" class="display" cellspacing="0" width="100%">
						<thead>
							<tr>
								<th width="10%"><spring:message
										code="measure.dataTable.header.id" /></th>
								<th width="75%"><spring:message
										code="measure.dataTable.header.name" /></th>
								<th width="10%"><spring:message
										code="measure.dataTable.header.outerId" /></th>
								<th width="5%"><spring:message
										code="measure.dataTable.header.action" /></th>
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