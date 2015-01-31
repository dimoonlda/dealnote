<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<head>
	<spring:url value="/css/bootstrap.min.css" var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>

	<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>" ></script>	
	<link type="text/css" href="<c:url value='/css/jquery-ui.css'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/js/jquery-ui.js'/>" ></script>

	<spring:url value="/js/bootstrap.js" var="bootstrapJs"/>
	<script type="text/javascript" src="${bootstrapJs} " ></script>

    <link href='<c:url value="/css/jquery.dataTables.css" />' rel="stylesheet" type="text/css" />
    <script src='<c:url value="/js/jquery.dataTables.min.js" />' type="text/javascript" ></script>
    
	<link href='<c:url value="/css/dealnote.css" />' rel="stylesheet" type="text/css" />
</head>	