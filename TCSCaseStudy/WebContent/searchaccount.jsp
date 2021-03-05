<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Search Account</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
$("input[type='radio']").change(function(){ 
if($(this).val()=="accId"){
	$("#accId").show();
	$("#custId").hide();
	$("#accId").prop("required",true);
	$("#custId").prop("required",false);
	}
else{
	$("#accId").hide();
	$("#custId").show();
	$("#accId").prop("required",false);
	$("#custId").prop("required",true);
}	
});
});
</script>

</head>

<body>
<jsp:include page="header.jsp" />
<c:if test="${user!=null}">
<h1>Search Account</h1>

<form action="" method="get">

<input type="hidden" name="mode" value="${operation}">
<input type="hidden" name="view" value="list" >
<table>
<tr>
<td>
<input type="radio" name="searchBy" value="accId" required checked>Search By AccountId
<input type="text" name="accId" id="accId" pattern="[0-9]{9}" title="Account id should have 9 digits" style="display:inline;"><br>
</td>
</tr>
<tr>
<td>
<input type="radio" name="searchBy" value="custId" required>Search By Customer Id
<input type="text" name="custId" id="custId" pattern="[0-9]{9}" title="Account id should have 9 digits" style="display:none;"><br>
</td>
</tr>
</table>
<br>
<div class="row">
  <div class="col-md-12 text-center">
    

<button type="submit"  class="btn btn-primary" formmethod="get" formaction="accountController">Submit</button>
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