<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agent's GPS coordinates</title>
</head>
<body>
<c:if test="${requestScope.locations != null }" var="isLocations"/>
<table width="100%">
<tr>
	<td><!-- header -->
		<jsp:include page="fragments/header.jsp"/>
			<script type="text/javascript">
						$(function(){
							$("#CalendarDatepiker").datepicker({
								// Полные имена дней недели
								dayName: ['Воскресенье', 'Понедельник', 'Вторник', 'Среда', 'Четверг', 'Пятница', 'Суббота'],
			        			// Сокращенные имена дней недели
								dayNamesMin: ['Вс', 'Пн', 'Вт', 'Ср', 'Чт', 'Пт', 'Сб'],
			        			// Название месяцев
								monthNames: ['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль', 'Август', 'Сентябрь', 'Октябрь', 'Ноябрь', 'Декабрь'],
								// Сокращенные названия месяцев
			        			monthNamesShort: ['Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь', 'Июль', 'Август', 'Сентябрь', 'Октябрь', 'Ноябрь', 'Декабрь'],
			        			// Формат даты, например 27.04.2011
								dateFormat: 'dd.mm.yy',
			        			// Первый день недели, 0 - воскресенье.
								firstDay: 1
							});
						});	
			</script>
	</td>
</tr>
<tr>
	<td><!-- body --> 
<center><form id="fmFilter" method="POST" action='<c:url value="/gps/ShowAgentGPS.do"/>' >
	<table border="0" cellspacing="5">
    <tr>
	<th  align="right">Choose agent:</th>
	<td align="left">
		<jsp:include page="agentsList.jsp">
			<jsp:param value='${param["agentid"]}' name="agentid"/>
		</jsp:include>
	</td></tr>
	<tr>
	 	<th  align="right">Set date:</th>
		<td align="left">
			<input type="text" name="agentdate" id="CalendarDatepiker" value="${param['agentdate'] }" />
		</td>
	</tr>
    <tr >
    	<td colspan="2" align="right">
    		<table width="100%"><tr align="right">
    			<td><input type="submit" name="showgpsloc" value="Show coordinates"></td>
    			<td><input formtarget="_blank" type="submit" name="gengpsmap" value="Show map"></td>
    			<td><input type="reset"></td>
    		</tr></table>
    	</td>
    </tr>	
	</table>
</form></center>		
	</td>
</tr>
<c:if test="${param['agentdate'] != null && param['agentid'] != null}">
	<tr>
		<td>
			<jsp:include page="agentsLocationDataTable.jsp"/>
		</td>
	</tr>
</c:if>
<tr>
	<td><!-- footer -->
		<jsp:include page="fragments/footer.jsp"/>
	</td>
</tr>
</table>
</body>
</html>