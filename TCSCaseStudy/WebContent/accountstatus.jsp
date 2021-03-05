<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 <%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Account Status</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$("#acctable .refresh").click(function(){ 
		
	 let accid=$(this).attr('id');

	 $.ajax({
		 type:'GET',
		 url:'accountController',
		 data:{
			 mode:'fetch',
			 accId:accid,
		 },
		 success:function(responseText){

		 if(responseText.accountId!==undefined){	 
			 let temp="row"+accid;

			 let cell=document.getElementById(temp).cells;
		  
		   cell[0].innerHTML=responseText.custId;
		   cell[1].innerHTML=responseText.accountId;
		   if(responseText.status==='C')
			  cell[2].innerHTML="Current";
		   else if(responseText.status==='S')
			   cell[2].innerHTML="Saving";
		   if(responseText.status==='A')
		   cell[3].innerHTML="Active";
		   else if(responseText.status==='I')
			   cell[3].innerHTML="Inactive";
		   cell[4].innerHTML=responseText.balance;
		   cell[5].innerHTML=responseText.msg;
		   cell[6].innerHTML=responseText.lastUpdate;
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
<h1>Account Status</h1>
<table id="acctable" border="1">
<tr>
	<th>Customer Id</th>
	<th>Account Id</th>
	<th>Account Type</th>
	<th>Account Balance</th>
	<th>Account Status</th>
	<th>Account Message</th>
	<th>Last Updated</th>
	<th>Refresh</th>
</tr>
<c:forEach items="${accounts}" var="account">
<tr id="row${account.accountId}">
	<td >${account.custId}</td>
	<td>${account.accountId}</td>
	<c:if test="${account.accountType=='C'.charAt(0)}">
	<td >Current</td>
	</c:if>
    <c:if test="${account.accountType=='S'.charAt(0)}">
	<td >Saving</td>
	</c:if>
	
	
	<c:if test="${account.status=='A'.charAt(0)}">
	<td >Active</td>
	</c:if>
    <c:if test="${account.status=='I'.charAt(0)}">
	<td >Inactive</td>
	</c:if>
	<td >${account.balance}</td>
	<td >${account.msg}</td>
	<td><fmt:formatDate  value = "${account.lastUpdate}"  pattern="MMM dd, yyyy h:mm:ss a"/>
	<td ><a href="#" id="${account.accountId}" class="refresh">Refresh</a></td>
</tr>
</c:forEach>
</table>
<br>

<div class="row">
  <div class="col-md-12 text-center">

<c:if test="${prev!=-1}">
<button type="submit"  class="btn btn-primary" onclick="window.location.href='accountStatusController?page=${prev}';">Prev</button>
</c:if>
<button type="button" class="btn btn-primary" onclick="window.location.href='/TCSCaseStudy';">Home</button>
<c:if test="${next!=-1}">
<button type="submit"  class="btn btn-primary" onclick="window.location.href='accountStatusController?page=${next}';">Next</button>
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