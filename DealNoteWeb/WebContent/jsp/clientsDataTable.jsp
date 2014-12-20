<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>        

        <script type="text/javascript">
        $(document).ready(function () {
            $("#clients").dataTable({
                "bServerSide": true,
                "sAjaxSource": '<c:url value="/dictionaries/GetClientsInGson.do" />',
                "bProcessing": true,
                "sPaginationType": "full_numbers",
                "bJQueryUI": true,
                "fnServerParams": function ( aoData ) {
                	  aoData.push( { "name": "agentid", "value": "${param['agentid'] }" } );
                	}
            });
        });
        </script>

	<div id="container">
	<div id="demo_jui">
	<table id="clients" class="display">
    <thead><tr>
	<th width="10%">ID</th><th width="45%">Name</th><th width="45%">Address of location</th></tr></thead><tbody>
	</tbody></table>
	</div></div>
