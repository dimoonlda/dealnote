<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>        

        <script type="text/javascript">
        $(document).ready(function () {
            $("#agents").dataTable({
                "bServerSide": true,
                "sAjaxSource": '<c:url value="/dictionaries/GetAgentsInGson.do" />',
                "bProcessing": true,
                "dom": '<"toolbar">frtip'
            });
            $("div.toolbar").html('<a href="../dictionaries/AddAgent.do">+Add agent</a>');
        });
        </script>

	<table id="agents" class="display" cellspacing="0" width="100%">
    <thead><tr>
	<th width="10%">ID</th><th width="70%">Name</th><th width="10%">Clients count</th><th width="5%">Active</th><th width="5%">Action</th></tr></thead><tbody>
	</tbody></table>
