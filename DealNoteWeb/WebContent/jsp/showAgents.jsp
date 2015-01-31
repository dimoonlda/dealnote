<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="fragments/staticFiles.jsp"/>

<html>
<title><spring:message code="agents.showForm.title"/></title>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="container">
<table width="100%">
	<tr>
		<td>
			<jsp:include page="agentsDataTable.jsp"/>
		</td>
	</tr>
</table>
<jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>