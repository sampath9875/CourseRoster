<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=100%, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script> -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${navstyle}">
</head>
<body style="background-color: lavendar">
	<div class="container.fluid" style="overflow: hidden;">
		<nav class="navbar navbar-custom">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="adminHome.get">Course Roster</a>
				</div>
				<ul class="nav navbar-nav">
					<li><a href="registerevents.get">Register Events</a></li>
					<li><a href="registercourse.get">Register Courses</a></li>
					<li><a href="location.get">Add Location</a></li>
					<li><a href="adminviewevents.get">View Events/Courses/Volunteers</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="logout.action"><span
							class="glyphicon glyphicon-log-out"></span> Logout&nbsp;&nbsp;</a></li>
				</ul>
			</div>
		</nav>
	</div>
</body>
</html>