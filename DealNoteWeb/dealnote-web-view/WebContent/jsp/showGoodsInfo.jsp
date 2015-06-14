<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="fragments/staticFiles.jsp"/>

<html>
<title>${goods.name}</title>
<body>
<jsp:include page="fragments/header.jsp" />
<div class="container">
<h2><spring:message code="goods.showInfoForm.title"/></h2>
<table class="table table-striped" style="width:600px;">
	<tr>
		<th><spring:message code="goods.showInfoForm.lable.id"/>:</th>
		<td>${requestScope['goods'].id}</td></tr>
	<tr>
		<th><spring:message code="goods.showInfoForm.lable.outerId"/>:</th>
		<td>${requestScope['goods'].outerId}</td></tr>
	<tr>
		<th><spring:message code="goods.showInfoForm.lable.fullName"/>:</th>
		<td>${requestScope['goods'].fName}</td></tr>
	<tr>
		<th><spring:message code="goods.showInfoForm.lable.name"/>:</th>
		<td>${requestScope['goods'].name}</td></tr>
	<tr>
		<c:if test="${goods.isActive==1 }">
			<c:set value="checked" var="isChecked"/>
		</c:if>
		<th><spring:message code="goods.showInfoForm.lable.isActive"/>:</th>
		<td><input type="checkbox" ${isChecked } disabled></td></tr>
	<tr>
		<th><spring:message code="goods.showInfoForm.lable.goodsGroup"/>:</th>
		<td>${requestScope['goods'].goodsGroup.name}</td></tr>
	<tr>
		<th><spring:message code="goods.showInfoForm.lable.weight"/>:</th>
		<td>${requestScope['goods'].weight}</td></tr>
	<tr>
		<th><spring:message code="goods.showInfoForm.lable.measure"/>:</th>
		<td>${requestScope['goods'].measure.name}</td></tr>
	<tr>
		<c:if test="${goods.priorityColor.colorCode!='' }">
			<c:set value="${goods.priorityColor.colorCode}" var="backgroundColor"/>
		</c:if>
		<th><spring:message code="goods.showInfoForm.lable.priorityColor"/>:</th>
		<td bgcolor="${backgroundColor }">${requestScope['goods'].priorityColor.colorCode}</td></tr>
	<tr>
		<th><spring:message code="goods.showInfoForm.lable.sortPos"/>:</th>
		<td>${requestScope['goods'].sortPos}</td></tr>
	<tr>
		<th><spring:message code="goods.showInfoForm.lable.vatCoef"/>:</th>
		<td>${requestScope['goods'].vatCoef}</td></tr>
	<tr>
		<th><spring:message code="goods.showInfoForm.lable.sertificat"/>:</th>
		<td>${requestScope['goods'].sertificat}</td></tr>
	<tr>
		<th><spring:message code="goods.showInfoForm.lable.goodsData"/>:</th>
		<td>${requestScope['goods'].goodsDataAsString}</td></tr>
	<tr>
		<th><spring:message code="goods.showInfoForm.lable.goodsImage"/>:</th>
		<td><img src='<spring:url value="/goods/${requestScope['goods'].id}/image"/>' 
				alt="${requestScope['goods'].fName}" width="300px" height="300px" class="img-thumbnail"></td></tr>
	<tr>
		<td colspan="2"><center>
			<input type="button" value='<spring:message code="goods.showInfoForm.button.cancel"/>' 
				onclick="history.go(-1);" class="btn btn-default">
		</center></td>
	</tr>
</table>
</div>
<jsp:include page="fragments/footer.jsp" />
</body>
</html>