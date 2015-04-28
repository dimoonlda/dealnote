<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="fragments/staticFiles.jsp"/>

<html>
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
<title><spring:message code="locations.showForm.title"/></title>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="container">
<table width="100%">
<tr>
	<td><!-- body --> 
	<form:form modelAttribute="agentFilterDto" class="form-inline" method="POST">
		<div class="form-group">
			<form:label path="agent">
				<spring:message code="locations.showForm.agentsList.label"/>:	</form:label>
			<form:select path="agent" items="${agentsList}" itemLabel="name" itemValue="id" class="form-control"/>
		</div>
		<spring:message code='locations.showForm.label.locationDate' var="labelLocationDate"/>
		<form:input path="locationDate" type="text" class="form-control"
			placeholder="${labelLocationDate }" id="CalendarDatepiker"/>
		<button type="submit" class="btn btn-default">
			<spring:message code="locations.showForm.button.showLocations" /></button>
		
	</form:form>
	</td>
</tr>
<c:if test="${agentFilterDto.agent.id != null && agentFilterDto.locationDate != null}">
	<tr>
		<td>
			<script type="text/javascript">
        		$(document).ready(function () {
            		$("#locations").dataTable({
                		"bServerSide": true,
                		"sAjaxSource": '<c:url value="/locations/listgrid" />',
                		"bProcessing": true,
                		"pagingType": "full_numbers",
                		"stateSave" : true
            		});
        		});
        	</script>

			<table id="locations" class="display" cellspacing="0" width="100%">
		    <thead><tr>
				<th width="12%"><spring:message code="locations.dataTable.header.latitude"/></th>
				<th width="12%"><spring:message code="locations.dataTable.header.longitude"/></th>
				<th width="30%"><spring:message code="locations.dataTable.header.time"/></th>
				<th width="15%"><spring:message code="locations.dataTable.header.details"/></th>
				<th width="10%"><spring:message code="locations.dataTable.header.provider"/></th>
				<th width="11%"><spring:message code="locations.dataTable.header.searchTime"/></th>
				<th width="10%"><spring:message code="locations.dataTable.header.battery"/></th>
			</tr></thead><tbody></tbody></table>
		</td>
	</tr>
</c:if>
</table>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>