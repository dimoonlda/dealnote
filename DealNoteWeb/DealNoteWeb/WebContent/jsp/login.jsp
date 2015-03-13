<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="fragments/staticFiles.jsp"/>
<link type="text/css" href="<c:url value='/css/signin.css'/>" rel="stylesheet" />
<title><spring:message code="loginForm.title"/></title>
<body>
<jsp:include page="fragments/header.jsp"/>

    <div class="container">
    <c:if test="${message.hasMessage }">
    	<div class="alert alert-danger" role="alert">${message.message}</div>
    </c:if>
    <spring:url value="/j_spring_security_check" var="loginUrl"/>
      <form class="form-signin" action="${loginUrl} " method="post">
        <h2 class="form-signin-heading"><spring:message code="loginForm.welcome"/></h2>
        <input name="j_username" type="text" class="form-control" placeholder='<spring:message code="loginForm.lable.username"/>' required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" name="j_password" id="inputPassword" class="form-control" placeholder='<spring:message code="loginForm.lable.password"/>' required>
        <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="loginForm.button.lable.login"/></button>
      </form>

    </div> <!-- /container -->
<jsp:include page="fragments/footer.jsp"/>    
</body>
</html>