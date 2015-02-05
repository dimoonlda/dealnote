<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>        

<spring:url value="/clients/" var="showClientUrl"/>

        <script type="text/javascript">
        $(document).ready(function () {
            $("#clients").dataTable({
                "bServerSide": true,
                "sAjaxSource": "${showClientUrl }/listgrid/${param['agentId'] }",
                "bProcessing": true,
                "pagingType": "full_numbers"
            });
        });
        </script>
	<div>
		<sec:authorize access="hasRole('ROLE_USER')">
			<a href="${showClientUrl}/new"><spring:message code="clients.dataTable.button.addClient" /></a>
		</sec:authorize>
	</div><br>

	<table id="clients" class="display" cellspacing="0" width="100%">
    <thead><tr>
	<th width="10%"><spring:message code="clients.dataTable.header.id"/></th>
	<th width="40%"><spring:message code="clients.dataTable.header.name"/></th>
	<th width="45%"><spring:message code="clients.dataTable.header.addressLocation"/></th>
	<th width="5%"><spring:message code="clients.dataTable.header.action"/></th>
	</tr></thead><tbody>
	</tbody></table>
