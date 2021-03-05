<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Customer</title>
</head>
<body>
<jsp:include page="header.jsp" />
<c:if test="${user!=null}">
<h1>Update Customer</h1>
<form action="" method="post">
<table border="1">
	<tr>
		<td>Customer SSN ID:</td>
		<td><input type="text" name="ssn" value="${customer.ssn}" readonly></td>
	</tr>
	<tr>
		<td>Customer ID:</td>
		<td><input type="text" name="custId" value="${customer.custId}" readonly></td>
	</tr>
	<tr>
		<td>Customer Name:</td>
		<td><input type="text" name="name" value="${customer.name}" pattern="[a-zA-Z ]+" title="Please enter proper name" required></td>
	</tr>
	<tr>
		<td>Customer Status:</td>
		<td><input type="text" name="name" value="${customer.status}" readonly></td>
	</tr>
	<tr>
		<td>Customer Age:</td>
		<td><input type="text" name="age" value="${customer.age}" pattern="[0-9]{1,3}" title="Please enter proper age" required></td>
	</tr>
	<tr>
		<td>Customer Address:</td>
		<td><input type="text" name="address" value="${customer.address}" required></td>
	</tr>
	<tr>
		<td>Customer City:</td>
		<td><input type="text" name="city" value="${customer.city}" pattern="[a-zA-Z]+" title="Please enter proper city" required></td>
	</tr>
	<tr>
		<td>Customer State:</td>
		<td><input type="text" name="state" value="${customer.state}" pattern="[a-zA-Z]+" title="Please enter proper state" required></td>
	</tr>
</table>
<br>
<div class="row">
  <div class="col-md-12 text-center">
   
<button type="submit"  class="btn btn-primary" formmethod="post" formaction="customerController?mode=update&confirm=true">Update Customer</button>
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