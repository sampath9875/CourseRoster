<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<title>Event Information</title>
</head>
<body style="background-color: lavendar; overflow: hidden">
	<jsp:include page="adminheader.jsp" />
	<div class="container">
		<table class="table table-striped">
			<tr>
				<td><jstl:out value="${event.eventName}"></jstl:out></td>
				<td><jstl:out value="${event.format(event.getEventDate())}"></jstl:out></td>
				<td><jstl:out value="${event.location.locationDetails}"></jstl:out></td>
			</tr>
		</table>
		<jstl:choose>
			<jstl:when test="${fn:length(registrations) gt 0}">
				<table class="table table-striped">
					<thead>
						<tr class="info">
							<th>Volunteer Name</th>
							<th>Volunteer Id</th>
							<th>Volunteer Email</th>
							<th>Volunteer Phone No.</th>
						</tr>
					</thead>
					<tbody>
						<jstl:forEach items="${registrations}" var="registration">
							<tr>
								<td><jstl:out value="${registration.volunteerName}"></jstl:out></td>
								<td><jstl:out value="${registration.volunteerId}"></jstl:out></td>
								<td><jstl:out value="${registration.volunteerEmail}"></jstl:out></td>
								<td><jstl:out value="${registration.volunteerPhoneno}"></jstl:out></td>
							</tr>
						</jstl:forEach>
					</tbody>
				</table>
			</jstl:when>
			<jstl:otherwise>
				<div class="jumbotron">
					<h4>There are no registrations for the given event</h4>
					<p>Please click here to go to home page</p>
					<a href="adminHome.get" class="btn btn-primary">Home</a>
				</div>
			</jstl:otherwise>
		</jstl:choose>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>