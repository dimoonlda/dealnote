<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${requestScope['client'].name}</title>
</head>
<body>
<table width="100%">
	<tr><td align="right" width="50%">Id:</td><td width="50%"><b>${requestScope['client'].id}</b></td></tr>
	<tr><td align="right">Full name:</td><td><b>${requestScope['client'].name}</b></td></tr>
	<tr><td align="right">Phone:</td><td><b>${requestScope['client'].phone}</b></td></tr>
	<tr><td align="right">Tax Code:</td><td><b>${requestScope['client'].taxCode}</b></td></tr>
	<tr><td align="right">Agent:</td><td><b>${requestScope['client'].agent.name}</b></td></tr>
	<tr><td align="right">Address location:</td><td><b>${requestScope['client'].addressLocation}</b></td></tr>
	<tr><td align="right">OKPO:</td><td><b>${requestScope['client'].okpo}</b></td></tr>
	<tr><td align="right">Bank account:</td><td><b>${requestScope['client'].bankAccount}</b></td></tr>
	<tr><td align="right">Dog. number:</td><td><b>${requestScope['client'].dogNum}</b></td></tr>
	<tr><td align="right">Address law:</td><td><b>${requestScope['client'].addressLaw}</b></td></tr>
	<tr><td colspan="2"><center><input type="button" value="Close" onclick="self.close()"></center></td></tr>
</table>
</body>
</html>