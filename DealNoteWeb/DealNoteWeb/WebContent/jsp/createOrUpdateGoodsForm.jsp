<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="fragments/staticFiles.jsp"/>

<html>
<c:choose>
	<c:when test="${goods['new'] }">
		<c:set var="method" value="post"/>
		<spring:message code="goods.createForm.title.addGoods" var="title"/>
		<spring:message code="goods.createForm.lable.button.add" var="addOrUpdateButton"/>
	</c:when>
	<c:otherwise>
		<c:set var="method" value="put"/>
		<spring:message code="goods.createForm.title.updateGoods" var="title"/>
		<spring:message code="goods.createForm.lable.button.update" var="addOrUpdateButton"/>
	</c:otherwise>
</c:choose>

<title>${title}</title>

<body>
	<jsp:include page="fragments/header.jsp" />
<div class="container">
<p/>
	<table width="100%">
		<tr>
			<td> 
				<form:form modelAttribute="goods" method="${method }" id="add-goods-form" class="form-horizontal">
							<div class="form-group">
								<form:label path="outerId" class="col-sm-2 control-label">
									<spring:message code="goods.createForm.lable.outerId"/>:
								</form:label>
								<div class="col-xs-2"><form:input path="outerId" size="15" class="form-control"/></div>
								<label class="checkbox-inline">
									<form:checkbox path="isActive" value="1"/>
									<form:label path="isActive">
										<spring:message code="goods.createForm.lable.isActive"/>
									</form:label>
								</label>
							</div>
							<div class="form-group">
								<form:label path="fName" class="col-sm-2 control-label">
									<spring:message code="goods.createForm.lable.fName"/>:
								</form:label>
								<div class="col-xs-7"><form:input path="fName" class="form-control"/></div>
								<form:errors path="fName" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<form:label path="name" class="col-sm-2 control-label">
									<spring:message code="goods.createForm.lable.name"/>:
								</form:label>
								<div class="col-xs-5"><form:input path="name" class="form-control"/></div>
								<form:errors path="name" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<form:label path="goodsGroup" class="col-sm-2 control-label">
									<spring:message code="goods.createForm.lable.goodsGroup"/>:
								</form:label>
								<div class="col-xs-3">
									<form:select path="goodsGroup" items="${goodsGroupList}" itemLabel="name" itemValue="id" class="form-control"/></div>
								<form:errors path="goodsGroup" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<form:label path="weight" class="col-sm-2 control-label">
									<spring:message code="goods.createForm.lable.weight"/>:
								</form:label>
								<div class="col-xs-2"><form:input path="weight" class="form-control"/></div>
								<form:errors path="weight" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<form:label path="measure" class="col-sm-2 control-label">
									<spring:message code="goods.createForm.lable.measure"/>:
								</form:label>
								<div class="col-xs-2">
									<form:select path="measure" items="${measureList}" itemLabel="name" itemValue="id" class="form-control"/></div>
								<form:errors path="measure" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<form:label path="priorityColor" class="col-sm-2 control-label">
									<spring:message code="goods.createForm.lable.priorityColor"/>:
								</form:label>
								<div class="col-xs-3">
									<form:select path="priorityColor" items="${priorityColorList}" itemLabel="colorCode" itemValue="id" class="form-control"/></div>
								<form:errors path="priorityColor" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<form:label path="sortPos" class="col-sm-2 control-label">
									<spring:message code="goods.createForm.lable.sortPos"/>:
								</form:label>
								<div class="col-xs-2"><form:input path="sortPos" class="form-control"/></div>
								<form:errors path="sortPos" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<form:label path="assortment" class="col-sm-2 control-label">
									<spring:message code="goods.createForm.lable.assortment"/>:
								</form:label>
								<div class="col-xs-2"><form:input path="assortment" class="form-control"/></div>
								<form:errors path="assortment" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<form:label path="vatType" class="col-sm-2 control-label">
									<spring:message code="goods.createForm.lable.vatType"/>:
								</form:label>
								<div class="col-xs-2"><form:input path="vatType" class="form-control"/></div>
								<form:errors path="vatType" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<form:label path="priority" class="col-sm-2 control-label">
									<spring:message code="goods.createForm.lable.priority"/>:
								</form:label>
								<div class="col-xs-2"><form:input path="priority" class="form-control"/></div>
								<form:errors path="priority" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<form:label path="clientTypeMask" class="col-sm-2 control-label">
									<spring:message code="goods.createForm.lable.clientTypeMask"/>:
								</form:label>
								<div class="col-xs-2"><form:input path="clientTypeMask" class="form-control"/></div>
								<form:errors path="clientTypeMask" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<form:label path="sertificat" class="col-sm-2 control-label">
									<spring:message code="goods.createForm.lable.sertificat"/>:
								</form:label>
								<div class="col-xs-5"><form:input path="sertificat" class="form-control"/></div>
								<form:errors path="sertificat" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<form:label path="ekka" class="col-sm-2 control-label">
									<spring:message code="goods.createForm.lable.ekka"/>:
								</form:label>
								<div class="col-xs-3"><form:input path="ekka" class="form-control"/></div>
								<form:errors path="ekka" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<form:label path="class1" class="col-sm-2 control-label">
									<spring:message code="goods.createForm.lable.class1"/>:
								</form:label>
								<div class="col-xs-2"><form:input path="class1" class="form-control"/></div>
								<form:errors path="class1" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<form:label path="goodsDataAsString" class="col-sm-2 control-label">
									<spring:message code="goods.createForm.lable.goodsData"/>:
								</form:label>
								<div class="col-xs-5"><form:textarea path="goodsDataAsString" rows="3" class="form-control"/></div>
								<form:errors path="goodsDataAsString" cssClass="form_error_message"/>
							</div>
							<div class="form-group">
								<form:label path="goodsImage" class="col-sm-2 control-label">
									<spring:message code="goods.createForm.lable.goodsImage"/>:
								</form:label>
								<div class="col-xs-5" id="result">
									<img src='<spring:url value="/goods/${requestScope['goods'].id}/image"/>' 
										alt="${requestScope['goods'].fName}" 
										width="300px" height="300px" 
										class="img-thumbnail"></div>
							</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="submit" value='${addOrUpdateButton }' class="btn btn-default">
								<input TYPE="button" onClick="history.go(-1);" class="btn btn-default" 
									value='<spring:message code="goods.createForm.lable.button.cancel"/>'>
							</div>
						</div>
				</form:form>
<script type="text/javascript">
	//using FormData() object
	function uploadFormData() {
		//$('#result').html('');
		var oMyForm = new FormData();
		oMyForm.append("fileImage", fileImage.files[0]);
		$.ajax({
					url : '<spring:url value="/goods/uploadimage"/>',
					data : oMyForm,
					dataType : 'text',
					processData : false,
					contentType : false,
					type : 'POST',
					success : function(data) {
						$('#result').html("<img src='" + data + "' width='300px' height='300px' class='img-thumbnail'/>");
					}
				});
	}
</script>
									<div class="form-group">
										<label class="col-sm-2 control-label">
											<spring:message code="goods.createForm.lable.chooseImage"/>:
										</label>
										<div class="col-xs-5"><input type="file" id="fileImage" name="fileImage"/></div>
									</div>
								 <button onclick="uploadFormData()" value="submitImage" class="btn btn-default">
											<spring:message code="goods.createForm.lable.button.submitImage"/>
								</button>
			</td>
		</tr>
	</table>
</div>
	<jsp:include page="fragments/footer.jsp" />
</body>
</html>