<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 <%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account Statement</title>
</head>
<body>
<jsp:include page="header.jsp" />
<h1>Account Statement</h1>
<c:if test="${user!=null}">
<table border="1">
<tr>
	<th>Transaction Id</th>
	<th>Transaction Type</th>
	<th>Transaction Amount</th>
	<th>Transaction Time</th>
</tr>
<c:forEach items="${transactions}" var="transaction">
<tr>
	<td>${transaction.trxnId}</td>
	<c:if test="${transaction.type=='W'.charAt(0)}">
	<td>Withdraw</td>
	</c:if>
    <c:if test="${transaction.type=='D'.charAt(0)}">
	<td>Deposit</td>
	</c:if>	
	<c:if test="${transaction.type=='T'.charAt(0)}">
	<td>Transfer</td>
	</c:if>
	<td>${transaction.amount}</td>
	<td><fmt:formatDate  value = "${transaction.timestamp}"  pattern="MMM dd, yyyy h:mm:ss a"/></td>
</tr>
</c:forEach>
</table>
<br>


<div class="row">
  <div class="col-md-12 text-center">

<form action="" method="post">
<input type="hidden" name="searchBy" value="${searchBy}"/>
<input type="hidden" name="accId" value="${accId}"/>
<input type="hidden" name="start" value="${start}"/>
<input type="hidden" name="end" value="${end}"/>
<input type="hidden" name="number" value="${number}"/>


<c:if test="${prev!=-1}">
<button type="submit"  class="btn btn-primary" formmethod="post" formaction="accountStatementController?page=${prev}">Prev</button>
</c:if>
<button type="button" class="btn btn-primary" onclick="window.location.href='/TCSCaseStudy';">Home</button>
<c:if test="${next!=-1}">
<button type="submit"  class="btn btn-primary" formmethod="post" formaction="accountStatementController?page=${next}">Next</button>
</c:if>

</form>

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