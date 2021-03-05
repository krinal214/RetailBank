<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ page isELIgnored="false" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List of Accounts</title>
</head>
<body>
<jsp:include page="header.jsp" />
<h1>List of Accounts</h1>
<c:if test="${user!=null}">
<form action="" method="post">
<table>
<tr>
<td>
Select Account :
</td>
<td>
<select name="account" required>
<c:forEach items="${accounts}" var="account">
<option value="${account.accountId}">${account.accountId}</option>
</c:forEach>
</select >
</td>
</tr>
</table>
<br>
<div class="row">
  <div class="col-md-12 text-center">
    

<button type="submit" class="btn btn-primary" formmethod="post" formaction="accountController?mode=${operation}&confirm=false">Submit</button>
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