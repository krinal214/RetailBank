<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Account</title>
</head>
<body>
<jsp:include page="header.jsp" />
<c:if test="${user!=null}">
<h1>Create Account</h1>
<form action="" method="post">
<table border="1">
	<tr>
		<td>Customer ID:</td>
		<td><input type="text" name="custId" pattern="[0-9]{9}" required title="customer id should be 9 digit long"></td>
	</tr>
	<tr>
		<td>Customer Account Type:</td>
		<td>
			<select name="type" required>
				<option value="S">Saving Account</option>
				<option value="C">Current Account</option>
			</select>
		</td>
	</tr>
</table>
<br>
<div class="row">
  <div class="col-md-12 text-center">
    

<button type="submit"  class="btn btn-primary" formmethod="post" formaction="accountController?mode=create">Create Account</button>
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