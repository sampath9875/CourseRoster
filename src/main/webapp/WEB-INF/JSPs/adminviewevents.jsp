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
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />
<script type="text/javascript">
	function validateForm1() {
		var loc = document.getElementById("location1");
		var location = loc.options[loc.selectedIndex].value;
		var eventDate = document.getElementById("eventDate").value;
		if (location == "0") {
			alert("Please select a location ");
			return false;
		} else if (eventDate == "") {
			alert("Please select a Date ");
			return false;
		} else {
			return true;
		}
	};

	function validateForm2() {
		var loc = document.getElementById("location2");
		var location = loc.options[loc.selectedIndex].value;
		var courseDate = document.getElementById("courseDate").value;
		if (location == "0") {
			alert("Please select a location ");
			return false;
		} else if (courseDate == "") {
			alert("Please select a Date ");
			return false;
		} else {
			return true;
		}
	};
</script>
<title>View Events</title>
</head>
<body style="background-color: lavendar; overflow: hidden">
	<jsp:include page="adminheader.jsp" />
	<jstl:if test="${message!=null}">
		<div class="container">
			<div class="row">
				<div class="jumbotron">
					<div id="errorMessage">
						<jstl:out value="${message}"></jstl:out>
					</div>
				</div>
			</div>
		</div>
	</jstl:if>
	<div class="container" style="height: 100%;">
		<div class="row">
			<div class="col-md-5">
				<div class="jumbotron">
					<p>Register Events</p>
					<h4>Click here to register Events</h4>
					<a href="registerevents.get" class="btn btn-primary">Register
						Events</a>
				</div>
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-5">
				<div class="jumbotron">
					<p>Register a Course</p>
					<h4>Click here to register Courses</h4>
					<a href="registercourse.get" class="btn btn-primary">Register
						Course</a>
				</div>
			</div>
			<div class="col-md-1"></div>
		</div>
		<div class="row">
			<div class="col-md-5">
				<div class="jumbotron">
					<form:form method="post" onsubmit="return validateForm1();"
						action="getEvents.action" commandName="event">
						<h4>Search Events</h4>
						<div class="form-group">
							<label for="email">Select Event location *</label>
							<form:select path="location.locationId" id="location1"
								class="form-control" onchange="display(this.value)">
								<form:option value="0">Select location..</form:option>
								<jstl:forEach items="${locations}" var="location">
									<form:option value="${location.locationId}"> ${location.locationDetails} </form:option>
								</jstl:forEach>
							</form:select>
							<form:errors path="location.locationId" cssClass="error" />
							<span id="location1Error"></span>
						</div>
						<div class="form-group">
							<label for="phone">Select date *</label>
							<div class="input-group date" data-provide="datepicker" id="datepicker1">
								<form:input type="text" class="form-control" id="eventDate"
									readonly="readonly" path="eventDate" />
								<span id="dateError"></span>
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-th"></span>
								</div>
							</div>
							<script type="text/javascript">
								var nowDate = new Date();
								var today = new Date(nowDate.getFullYear(),
										nowDate.getMonth(), nowDate.getDate(),
										0, 0, 0, 0);
								$('#datepicker1').datepicker({
									todayHighlight : 1,
									startDate : today,
									value : today,
									daysOfWeekDisabled : [ 0, 6 ],
									autoclose: true
								});
							</script>
						</div>
						<div class="form-group">
							<input type="submit" id="submit"
								class="form-control btn btn-primary" value="Search">
						</div>
					</form:form>
				</div>
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-5">
				<div class="jumbotron">
					<form:form method="post" onsubmit="return validateForm2();"
						action="getCourses.action" commandName="course">
						<h4>Search Courses</h4>
						<div class="form-group">
							<label for="email">Select Course location *</label>
							<form:select path="location.locationId" id="location2"
								class="form-control" onchange="display(this.value)">
								<form:option value="0">Select location..</form:option>
								<jstl:forEach items="${locations}" var="location">
									<form:option value="${location.locationId}"> ${location.locationDetails} </form:option>
								</jstl:forEach>
							</form:select>
							<form:errors path="location.locationId" cssClass="error" />
							<span id="location2Error"></span>
						</div>
						<div class="form-group">
							<label for="phone">Select date *</label>
							<div class="input-group date" data-provide="datepicker"
								id="datepicker2">
								<form:input type="text" class="form-control" id="courseDate"
									readonly="readonly" path="startDate" />
								<span id="dateError"></span>
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-th"></span>
								</div>
							</div>
							<script type="text/javascript">
								var nowDate = new Date();
								var today = new Date(nowDate.getFullYear(),
										nowDate.getMonth(), nowDate.getDate(),
										0, 0, 0, 0);
								$(document).ready(function() {
									$('#datepicker2').datepicker({
										todayHighlight : 1,
										startDate : today,
										value : today,
										daysOfWeekDisabled : [ 0, 6 ],
										autoclose: true
									});
								});
							</script>
						</div>
						<div class="form-group">
							<input type="submit" id="submit"
								class="form-control btn btn-primary" value="Search">
						</div>
					</form:form>
				</div>

			</div>
			<div class="col-md-1"></div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>