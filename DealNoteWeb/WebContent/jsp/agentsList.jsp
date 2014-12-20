<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
		<select name="agentid">
		<option disabled>Choose agent</option>
		<c:forEach items="${requestScope['agentsList'] }" var="agent">
			<c:choose>
				<c:when test="${param['agentid'] == agent.id }">
					<option selected value="${agent.id }"> ${agent.name } </option>
				</c:when>
				<c:otherwise>
					<option value="${agent.id }"> ${agent.name } </option>
				</c:otherwise>				
			</c:choose>
		</c:forEach>
		</select>