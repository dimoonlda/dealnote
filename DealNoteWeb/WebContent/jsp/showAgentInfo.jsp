<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agents</title>
</head>
<body>
	<table width="100%">
		<tr>
			<td>
				<!-- header --> <jsp:include page="header.jsp" />
			</td>
		</tr>
		<tr>
			<td>
				<!-- body -->
			</td>
		</tr>
		<tr>
			<td> 
				<form action="../dictionaries/EditAgentInfo.do" method="post">
					<table width="100%">
						<tr>
							<th align="right">Outer ID:</th>
							<td><input name="outerId" type="number" size="15" value="${requestScope.agent.outerId }"></td>
						</tr>
						<tr>
							<th align="right">Name:</th>
							<td><input name="agentName" size="50" value="${requestScope.agent.name }"></td>
						</tr>
						<tr>
							<th align="right">Is active:</th>
							<td><input name="isActive" type="checkbox" value="1" <c:if test='${requestScope.agent.activeAsBoolean }'>checked</c:if>></td>
						</tr>
						<tr>
							<th align="right">Role:</th>
							<td><select name="agentRole">
									<option value="1" <c:if test='${requestScope.agent.roleCode == 1 }'>selected</c:if>>Agent</option>
									<option value="2" <c:if test='${requestScope.agent.roleCode == 2 }'>selected</c:if>>Supervisor</option>
							</select><input type="hidden" name="agentId" value="${requestScope.agent.id }"></td>
						</tr>
						<tr>
							<td align="right"><input type="submit" name="saveAgent"
								value="Save"></td>
							<td align="left"><input type="submit" name="cancelAgent"
								value="Cancel"></td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<!-- footer --> <jsp:include page="footer.jsp" />
			</td>
		</tr>
	</table>
</body>
</html>