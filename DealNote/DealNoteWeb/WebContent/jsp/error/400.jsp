<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../fragments/staticFiles.jsp" />

<html>
<title><spring:message code="label.400.title" /></title>
<body>
	<jsp:include page="../fragments/header.jsp" />
	<div class="container">
			<h1><spring:message	code="label.error" />&nbsp;400</h1>
			<spring:message code="label.400.detail" />
			<br />
			<spring:message code="label.400.checkRequest" />
			&nbsp;<a href="${pageContext.request.contextPath}/">
			<spring:message	code="label.back2main" /></a>
	</div>
	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>