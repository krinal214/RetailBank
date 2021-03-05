<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>customer Information</title>
</head>
<body>
<jsp:include page="header.jsp" />
<c:if test="${user!=null}">
<h1>Customer Information</h1>
<table border="1">
	<tr>
		<td>Customer SSN ID:</td>
		<td>${customer.ssn}</td>
	</tr>
	<tr>
		<td>Customer ID:</td>
		<td>${customer.custId}</td>
	</tr>
	<tr>
		<td>Customer Name:</td>
		<td>${customer.name}</td>
	</tr>
	<tr>
		<td>Customer Name:</td>
		<td>${customer.status}</td>
	</tr>
	<tr>
		<td>Customer Age:</td>
		<td>${customer.age}</td>
	</tr>
	<tr>
		<td>Customer Address:</td>
		<td>${customer.address}</td>
	</tr>
	<tr>
		<td>Customer City:</td>
		<td>${customer.city}</td>
	</tr>
	<tr>
		<td>Customer State:</td>
		<td>${customer.state}</td>
	</tr>
</table>
<br>
<div class="row">
  <div class="col-md-12 text-center">

<button type="button" class="btn btn-primary" onclick="window.location.href='/TCSCaseStudy';">Home</button><br>
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