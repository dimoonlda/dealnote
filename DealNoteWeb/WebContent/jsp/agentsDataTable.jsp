<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>        

        <script type="text/javascript">
        $(document).ready(function () {
            $("#agents").dataTable({
                "bServerSide": true,
                "sAjaxSource": '<c:url value="/dictionaries/GetAgentsInGson.do" />',
                "bProcessing": true,
                "sPaginationType": "full_numbers",
                "bJQueryUI": true
            });
        });
        </script>

	<div id="container">
	<div id="demo_jui">
	<table id="agents" class="display">
    <thead><tr>
	<th width="20%">ID</th><th width="70%">Name</th><th width="10%">Active</th></tr></thead><tbody>
	</tbody></table>
	</div></div>
