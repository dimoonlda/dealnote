<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>        

        <script type="text/javascript">
        $(document).ready(function () {
            $("#locations").dataTable({
                "bServerSide": true,
                "sAjaxSource": '<c:url value="/gps/GetAgentLocationsInGson.do" />',
                "bProcessing": true,
                "sPaginationType": "full_numbers",
                "bJQueryUI": true,
                "fnServerParams": function ( aoData ) {
                	  aoData.push( { "name": "agentdate", "value": "${param['agentdate'] }" } );
                	  aoData.push( { "name": "agentid", "value": "${param['agentid'] }" } );
                	}
            });
        });
        </script>

	<div id="container">
	<div id="demo_jui">
	<table id="locations" class="display">
    <thead><tr>
	<th width="12%">Широта</th><th width="12%">Долгота</th><th width="30%">Время</th><th width="15%">Детали</th><th width="10%">Провайдер</th><th width="11%">Время поиска, сек</th><th width="10%">Батарея</th></tr></thead><tbody>
	</tbody></table>
	</div></div>
