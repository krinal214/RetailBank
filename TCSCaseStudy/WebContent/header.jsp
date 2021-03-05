
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.bean.User"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ABC Bank</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
 <link rel="stylesheet" href="navbar.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<h1 style="text-align:center;">
ABC Bank-India's  Largest Bank
</h1>
	<c:if test="${user!=null}">
		<p style="font-size:20px;text-align:right;padding-right:16px;">
		Hello, ${user.userId}<br>
		Logged In Since :
		<fmt:formatDate  value = "${user.lastLogin}"  pattern="MMM dd, yyyy h:mm:ss a"/>
		</p>
    </c:if>
    
    <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="/TCSCaseStudy">Home</a>
    </div>
    
    <ul class="nav navbar-nav">

      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Customer Service
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="customerController?mode=create">Create Customer</a></li>
          <li><a href="customerController?mode=search">Search Customer</a></li>
			<li><a href="customerController?mode=update">Update Customer</a></li>
			<li><a href="customerController?mode=delete">Delete Customer</a></li>
			<li><a href="customerStatusController?page=1">View Customers</a></li>
        </ul>
      </li>
      
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Account Service
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="accountController?mode=create">Create Account</a></li>
			<li><a href="accountController?mode=search&view=query">Search Account</a></li>
			<li><a href="accountController?mode=delete&view=query">Delete Account</a></li>
			<li><a href="accountStatementController">View Account Statement</a></li>
			<li><a href="accountStatusController?page=1">View Accounts</a></li>
        </ul>
      </li>
      
      <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#">Transaction Service
        <span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="accountController?mode=search&view=query">Withdraw Money</a></li>
			<li><a href="accountController?mode=search&view=query">Deposit Money</a></li>
			<li><a href="accountController?mode=search&view=query">Transfer Money</a></li>
        </ul>
      </li>
      <li>
		    <c:if test="${user==null}">
			<a href="loginController">Login</a>
			</c:if>
		    <c:if test="${user!=null}">
			<a href="logoutController">Logout</a>
			</c:if>
	  </li>
    </ul>
    
    
  </div>
</nav>
</body>
</html>
