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
<title>Course Information</title>
</head>
<body style="background-color: lavendar; overflow: hidden">
	<jsp:include page="adminheader.jsp" />
	<div class="container">
		<h4>Courses</h4>
		<jstl:choose>
			<jstl:when test="${fn:length(coursesSearch) gt 0}">
				<table class="table table-striped">
					<thead>
						<tr class="info">
							<th>Course Name</th>
							<th>Starts On</th>
							<th>Ends On</th>
							<th>Course Location</th>
							<th>Registration Link</th>
							<th>Calendar Link</th>
						</tr>
					</thead>
					<tbody>
						<jstl:forEach items="${coursesSearch}" var="course">
							<tr>
								<td><jstl:out value="${course.courseName}"></jstl:out></td>
								<td><jstl:out
										value="${course.format(course.getStartDate())}"></jstl:out></td>
								<td><jstl:out value="${course.format(course.getEndDate())}"></jstl:out></td>
								<td><jstl:out value="${course.location.locationDetails}"></jstl:out></td>
								<td><a href="viewVolunteers?courseId=${course.courseId}">Click
										to View Registrations</a></td>
								<td><a
									href="getCalendar?entity=Course&id=${course.courseId}">Click
										here to download the Course Calendar</a>
							</tr>
						</jstl:forEach>
					</tbody>
				</table>
			</jstl:when>
			<jstl:otherwise>
				<div class="jumbotron">
					<h4>There are no courses for the given filters</h4>
					<p>Please click here to go to home page</p>
					<a href="adminHome.get" class="btn btn-primary">Home</a>
				</div>
			</jstl:otherwise>
		</jstl:choose>
		<h4>Other Courses on given location</h4>
		<jstl:choose>
			<jstl:when test="${fn:length(coursesLocation) gt 0}">
				<table class="table table-striped">
					<thead>
						<tr class="info">
							<th>Course Name</th>
							<th>Starts On</th>
							<th>Ends On</th>
							<th>Course Location</th>
							<th>Registration Link</th>
							<th>Calendar Link</th>
						</tr>
					</thead>
					<tbody>
						<jstl:forEach items="${coursesLocation}" var="course">
							<tr>
								<td><jstl:out value="${course.courseName}"></jstl:out></td>
								<td><jstl:out
										value="${course.format(course.getStartDate())}"></jstl:out></td>
								<td><jstl:out value="${course.format(course.getEndDate())}"></jstl:out></td>
								<td><jstl:out value="${course.location.locationDetails}"></jstl:out></td>
								<td><a href="viewVolunteers?courseId=${course.courseId}">Click
										to View Registrations</a></td>
								<td><a
									href="getCalendar?entity=Course&id=${course.courseId}">Click
										here to download the Course Calendar</a>
							</tr>
						</jstl:forEach>
					</tbody>
				</table>
			</jstl:when>
			<jstl:otherwise>
				<h5>There are no Other courses for the given location</h5>
			</jstl:otherwise>
		</jstl:choose>
		<h4>Other Courses on given Date</h4>
		<jstl:choose>
			<jstl:when test="${fn:length(coursesDate) gt 0}">
				<table class="table table-striped">
					<thead>
						<tr class="info">
							<th>Course Name</th>
							<th>Starts On</th>
							<th>Ends On</th>
							<th>Course Location</th>
							<th>Registration Link</th>
							<th>Calendar Link</th>
						</tr>
					</thead>
					<tbody>
						<jstl:forEach items="${coursesDate}" var="course">
							<tr>
								<td><jstl:out value="${course.courseName}"></jstl:out></td>
								<td><jstl:out
										value="${course.format(course.getStartDate())}"></jstl:out></td>
								<td><jstl:out value="${course.format(course.getEndDate())}"></jstl:out></td>
								<td><jstl:out value="${course.location.locationDetails}"></jstl:out></td>
								<td><a href="viewVolunteers?courseId=${course.courseId}">Click
										to View Registrations</a></td>
								<td><a
									href="getCalendar?entity=Course&id=${course.courseId}">Click
										here to download the Course Calendar</a>
							</tr>
						</jstl:forEach>
					</tbody>
				</table>
			</jstl:when>
			<jstl:otherwise>
				<h5>There are no Other courses for the given Date</h5>
			</jstl:otherwise>
		</jstl:choose>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>