<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
<jsp:include page="header.jsp" />
<h1>Login Page</h1><br>

<form action="" method="post">
<table>
<tr>
<td>
UserName : 
</td>
<td>
<input type="text" name="username" required />
</td>
</tr>
<tr>
<td>
Password : 
</td>
<td>
<input type="password" name="password" required />
</td>
</tr>
</table>
<br>
<div class="row">
  <div class="col-md-12 text-center">
    

<button type="submit"  class="btn btn-primary" formmethod="post" formaction="loginController">Log In</button>

</div>
</div>
</form>
</body>
</html>