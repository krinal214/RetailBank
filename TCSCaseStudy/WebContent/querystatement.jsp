<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Query Statement</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$("input[type='radio']").change(function(){ 
		if($(this).val()=="number"){
			$("#number").show();
			$("#start").hide();
			$("#end").hide();
			$("#number").prop("required",true);
			$("#start").prop("required",false);
			$("#end").prop("required",false);
			
			}
		else{
			$("#number").hide();
			$("#start").show();
			$("#end").show();
			$("#number").prop("required",false);
			$("#start").prop("required",true);
			$("#end").prop("required",true);
		}	
		});
});
</script>
</head>
<body>
<jsp:include page="header.jsp" />
<c:if test="${user!=null}">
<h1>Account Statement</h1>
<form action="accountStatementController" method="post">
<input type="hidden" name="page" value="1" />
<table>

<tr>
	<td >Account Id:</td>
	<td><input type="text" name="accId" title="Account id should have 9 digits" required></td>
</tr>

<tr>
<td colspan="2">
<input type="radio" name="searchBy" value="number" required checked/>Last Number of Transaction : 
<input type="text" name="number" id="number" style="display:none;">
</td>
</tr>


<tr>
<td colspan="2">
<input type="radio" name="searchBy" value="date" required checked/>Start-End Date :
<input type="date" name="start" id="start" style="display:inline;"/>
<input type="date" name="end" id="end" style="display:inline;"/>
</td>

</tr>
</table>
<br>
<div class="row">
  <div class="col-md-12 text-center">
    

<button type="submit" class="btn btn-primary"  formmethod="post" formaction="accountStatementController">Search</button>
<button type="submit" class="btn btn-primary"  formmethod="post" formaction="accountStatementController?mode=download">Download</button>
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