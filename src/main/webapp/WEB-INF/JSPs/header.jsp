<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=100%, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<spring:url value="/resources/style.css" var="navstyle" />
<link rel="stylesheet" href="${navstyle}">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Registration</title>
</head>
<body>
	<div class="container.fluid" style="overflow: hidden;">
		<nav class="navbar navbar-custom">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="home">Socail App</a>
				</div>
				<ul class="nav navbar-nav">
					<li><a href="registration.get">Registration</a></li>
					<li><a href="viewevents.get">Events Information</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="adminsignup.get"><span
							class="glyphicon glyphicon-user"></span> Admin Registration</a></li>
					<li><a href="adminlogin.get"><span
							class="glyphicon glyphicon-log-in"></span> Admin Login</a></li>
					<li><a href="aboutus.get"><span
							class="glyphicon glyphicon-info-sign"></span> About
							us&nbsp;&nbsp;</a></li>
				</ul>
			</div>
		</nav>
	</div>
</body>
</html>