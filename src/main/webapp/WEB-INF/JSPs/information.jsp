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
<title>Event Information</title>
</head>
<body style="background-color: lavendar; overflow: hidden">
	<jsp:include page="header.jsp" />
	<div class="container">
		<table class="table table-striped">
			<jstl:if test="${entity == 0 }">
				<thead>
					<tr class="info">
						<th>Event Name</th>
						<th>Event Date</th>
						<th>Event Location</th>
						<th>Event Details</th>
						<th>Registration Link</th>
						<th>Calendar Link</th>
					</tr>
				</thead>
				<tbody>
					<jstl:forEach items="${events}" var="event">
						<tr>
							<td><jstl:out value="${event.eventName}"></jstl:out></td>
							<td><jstl:out value="${event.format(event.getEventDate())}"></jstl:out></td>
							<td><jstl:out value="${event.location.locationDetails}"></jstl:out></td>
							<td><jstl:out value="${event.eventDetails}"></jstl:out></td>
							<td><a href="registration.get?entity=event">Click to
									Register</a></td>
							<td><a href="getCalendar?entity=Event&id=${event.eventId}">Click
										here to download the event Calendar</a>
						</tr>
					</jstl:forEach>
				</tbody>
			</jstl:if>
			<jstl:if test="${entity == 1 }">
				<thead>
					<tr class="info">
						<th>Course Name</th>
						<th>Course Date</th>
						<th>Course Location</th>
						<th>Course Details</th>
						<th>Registration Link</th>
						<th>Calendar Link</th>
					</tr>
				</thead>
				<tbody>
					<jstl:forEach items="${courses}" var="course">
						<tr>
							<td><jstl:out value="${course.courseName}"></jstl:out></td>
							<td><jstl:out
									value="${course.format(course.getStartDate())}"></jstl:out></td>
							<td><jstl:out value="${course.location.locationDetails}"></jstl:out></td>
							<td><jstl:out value="${course.courseDetails}"></jstl:out></td>
							<td><a href="registration.get?entity=course">Click to
									Register</a></td>
							<td><a href="getCalendar?entity=Course&id=${course.courseId}">Click
									here to download the event Calendar</a>
						</tr>
					</jstl:forEach>
				</tbody>
			</jstl:if>
		</table>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>