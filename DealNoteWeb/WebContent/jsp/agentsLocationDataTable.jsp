<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    

        <script type="text/javascript">
        $(document).ready(function () {
            $("#locations").dataTable({
                "bServerSide": true,
                "sAjaxSource": '<c:url value="/locations/listgrid" />',
                "bProcessing": true,
                "pagingType": "full_numbers"
            });
        });
        </script>

	<table id="locations" class="display" cellspacing="0" width="100%">
    <thead><tr>
		<th width="12%"><spring:message code="locations.dataTable.header.latitude"/></th>
		<th width="12%"><spring:message code="locations.dataTable.header.longitude"/></th>
		<th width="30%"><spring:message code="locations.dataTable.header.time"/></th>
		<th width="15%"><spring:message code="locations.dataTable.header.details"/></th>
		<th width="10%"><spring:message code="locations.dataTable.header.provider"/></th>
		<th width="11%"><spring:message code="locations.dataTable.header.searchTime"/></th>
		<th width="10%"><spring:message code="locations.dataTable.header.battery"/></th>
	</tr></thead><tbody></tbody></table>
