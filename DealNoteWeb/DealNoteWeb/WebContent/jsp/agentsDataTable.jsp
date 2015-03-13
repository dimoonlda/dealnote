<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<spring:url value="/agents/" var="showAgentUrl"/>        
	<div>
		<sec:authorize access="hasRole('ROLE_USER')">
			<a href="${showAgentUrl}/new"><spring:message code="agents.dataTable.button.addAgent" /></a>
		</sec:authorize>
	</div><br>
        <script type="text/javascript">
        $(document).ready(function () {
            $("#agents").dataTable({
                "bServerSide": true,
                "sAjaxSource": '${showAgentUrl}/listgrid',
                "bProcessing": true //,
                //"dom": '<"toolbar">frtip'
            });
            //$("div.toolbar").html('<a href="${showAgentUrl}/new"><spring:message code="agents.dataTable.button.addAgent" /></a>');
        });
        </script>

	<table id="agents" class="display" cellspacing="0" width="100%">
    <thead><tr>
	<th width="10%"><spring:message code="agents.dataTable.header.id"/></th>
	<th width="70%"><spring:message code="agents.dataTable.header.name"/></th>
	<th width="10%"><spring:message code="agents.dataTable.header.clientCount"/></th>
	<th width="5%"><spring:message code="agents.dataTable.header.active"/></th>
	<th width="5%"><spring:message code="agents.dataTable.header.action"/></th>
	</tr></thead><tbody>
	</tbody></table>
