<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 <%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Status</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$("#custtable .refresh").click(function(){ 
		
	 let custid=$(this).attr('id');

	 $.ajax({
		 type:'GET',
		 url:'customerController',
		 data:{
			 mode:'fetch',
			 custId:custid,
		 },
		 success:function(responseText){

		 if(responseText.custId!==undefined){	 
			 let temp="row"+custid;

			 let cell=document.getElementById(temp).cells;
		  
		   cell[0].innerHTML=responseText.custId;
		   cell[1].innerHTML=responseText.ssn;
		   if(responseText.status==='A')
		   cell[2].innerHTML="Active";
		   else if(responseText.status==='I')
			   cell[2].innerHTML="Inactive";
		   cell[3].innerHTML=responseText.msg;
		   cell[4].innerHTML=responseText.last_update;
		   
		 }
		 }
	 })
		
		});
});
</script>

		
</head>
<body>
<jsp:include page="header.jsp" />

<c:if test="${user!=null}">
<h1>Customer Status</h1>

<table id="custtable" border="1">
<tr>
	<th>Customer Id</th>
	<th>Customer SSN</th>
	<th>Customer Status</th>
	<th>Status Message</th>
	<th>Last Update</th>
	<th>Refresh</th>
	<th>Profile</th>
</tr>
<c:forEach items="${customers}" var="customer">
<tr id="row${customer.custId}">
	<td >${customer.custId}</td>
	<td >${customer.ssn}</td>
	<c:if test="${customer.status=='A'.charAt(0)}">
	<td >Active</td>
	</c:if>
    <c:if test="${customer.status=='I'.charAt(0)}">
	<td >Inactive</td>
	</c:if>
	<td >${customer.msg}</td>
	<td><fmt:formatDate  value = "${customer.last_update}"  pattern="MMM dd, yyyy h:mm:ss a"/>
	</td>
	<td ><a href="#" id="${customer.custId}" class="refresh">Refresh</a></td>
	<td ><a href="customerController?mode=profile&custId=${customer.custId}" target="_blank">Profile</a></td>
</tr>
</c:forEach>
</table>
<br>

<div class="row">
  <div class="col-md-12 text-center">

<c:if test="${prev!=-1}">
<button type="submit"  class="btn btn-primary" onclick="window.location.href='customerStatusController?page=${prev}';">Prev</button>
</c:if>
<button type="button" class="btn btn-primary" onclick="window.location.href='/TCSCaseStudy';">Home</button>
<c:if test="${next!=-1}">
<button type="submit"  class="btn btn-primary" onclick="window.location.href='customerStatusController?page=${next}';">Next</button>
</c:if>

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