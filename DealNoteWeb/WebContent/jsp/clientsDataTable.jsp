<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>        

        <script type="text/javascript">
        $(document).ready(function () {
            $("#clients").dataTable({
                "bServerSide": true,
                "sAjaxSource": '<c:url value="/dictionaries/GetClientsInGson.do" />',
                "bProcessing": true,
                "pagingType": "full_numbers",
                "fnServerParams": function ( aoData ) {
                	  aoData.push( { "name": "agentid", "value": "${param['agentid'] }" } );
                	}
            });
        });
        </script>

	<table id="clients" class="display" cellspacing="0" width="100%">
    <thead><tr>
	<th width="10%">ID</th><th width="45%">Name</th><th width="45%">Address of location</th></tr></thead><tbody>
	</tbody></table>
