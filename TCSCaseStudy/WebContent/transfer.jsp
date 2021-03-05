<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transfer Money</title>
</head>
<body>
<jsp:include page="header.jsp" />
<c:if test="${user!=null}">
<h1>Transfer Money</h1>
<form action="" method="post">
<input type="hidden" name="account" value="${account.accountId}"></input>
<table border="1">
	<tr>
		<td>Customer ID:</td>
		<td>${account.custId}</td>
	</tr>
	<tr>
		<td> Source Account ID:</td>
		<td>${account.accountId}</td>
	</tr>
	<tr>
		<td> Target Account ID:</td>
		<td><input type="text" name="destAccId"  pattern="[0-9]{9}" required></td>
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
		<td>Transfer Amount:</td>
		<td><input type="text" name="amount" pattern="[0-9]+" title="Please enter proper amount" required ></td>
	</tr>
</table>
<br>
<div class="row">
  <div class="col-md-12 text-center">
    
<button type="submit"  class="btn btn-primary" formmethod="post" formaction="transactionController?mode=transfer">Transfer Amount</button>
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