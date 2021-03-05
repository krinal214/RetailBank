<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	$("input[type='radio']").change(function(){ 
		if($(this).val()=="ssn"){
			$("#ssn").show();
			$("#id").hide();
			$("#ssn").prop("required",true);
			$("#id").prop("required",false);
			
			}
		else{
			$("#ssn").hide();
			$("#id").show();
			$("#ssn").prop("required",false);
			$("#id").prop("required",true);
		}	
		});
});
</script>
<meta charset="UTF-8">
<title>Search Customer</title>
</head>
<body>
<jsp:include page="header.jsp" />
<c:if test="${user!=null}">
<h1>Search Customer</h1>

<form action="" method="post">
<table>
<tr>
<td>
<input type="radio" name="searchBy"  value="ssn"  required />Search By Customer SSN
<input type="text" name="ssn" id="ssn" style="display:none;"  pattern="[0-9]{9}" title="Customer ssn should have 9 digits"/>
</td>
</tr>
<tr>
<td>
<input type="radio" name="searchBy"  value="id" required checked/>Search By Customer Id
<input type="text" name="id" id="id" style="display:inline" pattern="[0-9]{9}" pattern="[0-9]{9}" title="Customer id should have 9 digits"/>
</td>
</tr>
</table>
<br>
<div class="row">
  <div class="col-md-12 text-center">
    

<button type="submit"  class="btn btn-primary" formmethod="post" formaction="customerController?mode=${operation}&confirm=false">Submit</button>
<button type="button" class="btn btn-primary" onclick="window.location.href='/TCSCaseStudy';">Cancel</button>

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