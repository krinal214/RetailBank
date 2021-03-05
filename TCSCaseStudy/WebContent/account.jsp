<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Information</title>

</head>
<body>
<jsp:include page="header.jsp" />
<c:if test="${user!=null}">
<h1>Customer Information</h1>

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
<div class="row">
  <div class="col-md-12 text-center">

<c:if test="${account.status=='A'.charAt(0)}">
      <button type="button" class="btn btn-primary" onclick="window.location.href='transactionController?mode=withdraw&accountId=${account.accountId}';">Withdraw</button>
      <button type="button" class="btn btn-primary" onclick="window.location.href='transactionController?mode=deposit&accountId=${account.accountId}';">Deposit</button>
      
      <button type="button" class="btn btn-primary" onclick="window.location.href='transactionController?mode=transfer&accountId=${account.accountId}';">Transfer</button>
</c:if>      
      <button type="button" class="btn btn-primary" onclick="window.location.href='/TCSCaseStudy';">Home</button>
   </div>
  </div>


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