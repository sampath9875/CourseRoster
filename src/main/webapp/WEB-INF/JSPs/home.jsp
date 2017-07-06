<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=100%, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />
<title>Course Roster</title>
</head>
<body style="background-color: lavendar; overflow: hidden">
	<jsp:include page="header.jsp" />
	<div class="container">
		<div class="row">
			<div class="jumbotron">
				<h2>Course Roster</h2>
				<p>Welcome to the Course Roster. You can register for various
					events and courses here. Also manage them if you are an admin</p>
				<a href="aboutus.get" class="btn btn-primary btn-md">About Us</a>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<div class="jumbotron">
					<p>Registration</p>
					<h5>Please click below to register for an Event/Course</h5>
						<a href="registration.get?entity=event" class="btn btn-primary">Event</a>
						<a href="registration.get?entity=course" class="btn btn-primary">Course</a>
				</div>
			</div>
			<div class="col-md-2"></div>
			<div class="col-md-5">
				<div class="jumbotron">
					<p>Admin Login</p>
					<h5>Click here to go to the admin module</h5>
					<a href="adminlogin.get" class="btn btn-primary">Admin Login</a>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>