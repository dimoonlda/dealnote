<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
	<script type="text/javascript" src="<c:url value='/js/jquery-1.11.1.min.js'/>"></script>	
	
	<link type="text/css" href="<c:url value='/menu/menu.css'/>" rel="stylesheet" />
	<script type="text/javascript" src="<c:url value='/menu/menu.js'/>"></script>
	
    <link href='<c:url value="/css/jquery.dataTables.css" />' rel="stylesheet" type="text/css" />
    <script src='<c:url value="/js/jquery.dataTables.min.js" />' type="text/javascript"></script>
	<div id="menu">
		<ul class="menu">
			<li><a href="<c:url value='/jsp/index.jsp'/>" class="parent"><span>Главная</span></a>
			</li>
			<li><a href='#'><span>Справочники</span></a>
				<div><ul>
					<li><a href='<c:url value="/jsp/showAgents.jsp"/>'><span>Agents</span></a></li>
					<li><a href="#"><span>Товары</span></a></li>
					<li><a href='<c:url value="/dictionaries/ShowClients.do"/>'><span>Clients</span></a></li>
				</ul></div>
			</li>
			<li><a href="#"><span>Анализ</span></a>
			<div><ul>
					<li><a href="<c:url value='/gps/ShowAgentGPS.do'/>"><span>GPS</span></a></li>
					<li><a href="OrderStatServlet"><span>Статистика по заявкам</span></a></li>			
			</ul></div>
			</li>
			<li class="last"><a href="DTraderLogout"><span>Выход</span></a></li>
		</ul>
	</div>	