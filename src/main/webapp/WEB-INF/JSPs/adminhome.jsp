<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<title>Home Social App</title>
</head>
<body style="background-color: lavendar; overflow: hidden">
	<jsp:include page="adminheader.jsp" />
	<div class="container">
		<div class="row">
			<div class="jumbotron">
				<h2>Social App</h2>
				<p>Welcome to the Admin Home page</p>
			</div>
		</div>
	</div>
	<jstl:if test="${message!=null}">
		<div class="container">
			<div class="row">
				<div class="jumbotron">
					<div>
						<jstl:out value="${message}"></jstl:out>
					</div>
				</div>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${eventId!=null}">
		<div class="container">
			<div class="row">
				<div class="jumbotron">
					<div>
						<jstl:out
							value="Event Successfully registered with Id: ${eventId}"></jstl:out>
					</div>
				</div>
			</div>
		</div>
	</jstl:if>
	<jstl:if test="${locId!=null}">
		<div class="container">
			<div class="row">
				<div class="jumbotron">
					<div>
						<jstl:out
							value="Location Successfully registered with Id: ${locId}"></jstl:out>
					</div>
				</div>
			</div>
		</div>
	</jstl:if>
	<div class="container">
		<div class="row">
			<div class="col-md-5">
				<div class="jumbotron">
					<p>Register Events</p>
					<h4>Click here to register events</h4>
					<a href="registerevents.get" class="btn btn-primary">Register
						Events</a>
				</div>
			</div>
			<div class="col-md-2"></div>
			<div class="col-md-5">
				<div class="jumbotron">
					<p>View Events</p>
					<h4>Click here to view Events</h4>
					<a href="adminviewevents.get" class="btn btn-primary">View
						Events/Volunteers</a>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>