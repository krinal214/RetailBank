<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete Account</title>
</head>
<body>
<jsp:include page="header.jsp" />
<c:if test="${user!=null}">
<h1>Delete Account</h1>
<table border="1">
	<tr>
		<td>Account ID:</td>
		<td>${account.accountId}</td>
	</tr>
	<tr>
		<td>Customer ID:</td>
		<td>${account.custId}</td>
	</tr>
	<tr>
		<td>Account Type:</td>
		<td>${account.accountType}</td>
	</tr>
	<tr>
		<td>Account Balance:</td>
		<td>${account.balance}</td>
	</tr>
	<tr>
		<td>Account Status:</td>
		<td>${account.status}</td>
	</tr>
	<tr>
		<td>Account Creation Date</td>
		<td>${account.creationDate}</td>
	</tr>
</table>
<br>
<form action="" method="post">
<input type="hidden" name="accountId" value="${account.accountId}" />
<div class="row">
  <div class="col-md-12 text-center">
    

<button type="submit"  class="btn btn-primary" formmethod="post" formaction="accountController?mode=delete&confirm=true">Delete Account</button>
<button type="button" class="btn btn-primary" onclick="window.location.href='/TCSCaseStudy';">Cancel</button><br>

</div>
</div>
</form>

</c:if>
<c:if test="${user==null}">
<h1>
Please Login to access this page
</h1>
<br>
<div class="row">
  <div class="col-md-12 text-center">
<button type="button" class="btn btn-primary" onclick="window.location.href='loginController';">Login</button>
</div>
</div>
</c:if>
</body>
</html>