<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

      <nav class="navbar navbar-default">
        <div class="container-fluid">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href='<c:url value="/"/>'>DealNote Web</a>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><spring:message code="menu.dictionaries" /> <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href='<c:url value="/agents/listAgents"/>'><spring:message code="menu.agents" /></a></li>
                  <li><a href='<c:url value="/goodsgroup/"/>'><spring:message code="menu.goodsGroups" /></a></li>
                  <li><a href='<c:url value="/measure/"/>'><spring:message code="menu.measure" /></a></li>
                  <li><a href='<c:url value="/prioritycolor/"/>'><spring:message code="menu.priorityColor" /></a></li>
                  <li><a href='<c:url value="/goods/"/>'><spring:message code="menu.goods" /></a></li>
                  <li><a href='<c:url value="/clients/listClients"/>'><spring:message code="menu.clients" /></a></li>
                </ul>
              </li>
              <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><spring:message code="menu.analyse" /> <span class="caret"></span></a>
                <ul class="dropdown-menu" role="menu">
                  <li><a href="<c:url value='/locations/'/>"><spring:message code="menu.gps" /></a></li>
                  <li><a href="#"><spring:message code="menu.statisticOnOrders" /></a></li>
                </ul>
              </li>
              <li>
              	<sec:authorize access="isAuthenticated()">
              		<a href='<spring:url value="/j_spring_security_logout"/>'>
              			<sec:authentication property="principal.username"/>, <spring:message code="menu.exit" />
              		</a>
              	</sec:authorize>
              	<sec:authorize access="isAnonymous()">
              		<a href='<spring:url value="/security/login"/>'>
              			<spring:message code="menu.login" />
              		</a>
              	</sec:authorize>
			  </li>	
              
            </ul>
          </div>
        </div>
      </nav>	