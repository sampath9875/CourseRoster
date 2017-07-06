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
<script type="text/javascript">
function validateForm(){
	var name = document.getElementById("name").value;
	var details = document.getElementById("details").value;
	var location = document.getElementById("location").value;
	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;
	var startHour = document.getElementById("startHour").value;
	var startMin = document.getElementById("startMin").value;
	var endHour = document.getElementById("endHour").value;
	var endMin = document.getElementById("endMin").value;
	var recurrence = document.getElementById("Recurrence").value;

	var nameCheck = /[a-zA-Z 0-9]{3,20}$/;
	var detCheck = /[a-zA-Z ,]$/;

	if(name == ""){
		document.getElementById("nameError").innerHTML = "Name cannot be Empty";
		document.getElementById("detError").innerHTML = "";
		document.getElementById("locationError").innerHTML = "";
		document.getElementById("startDateError").innerHTML = "";
		document.getElementById("endDateError").innerHTML = "";
		document.getElementById("recurrenceError").innerHTML = "";
		document.getElementById("timeError").innerHTML = "";
		return false;
	} else if (details == ""){
		document.getElementById("nameError").innerHTML = "";
		document.getElementById("detError").innerHTML = "Please add a description of the course";
		document.getElementById("locationError").innerHTML = "";
		document.getElementById("startDateError").innerHTML = "";
		document.getElementById("endDateError").innerHTML = "";
		document.getElementById("recurrenceError").innerHTML = "";
		document.getElementById("timeError").innerHTML = "";
		return false;
	} else if (location == 0){
		document.getElementById("nameError").innerHTML = "";
		document.getElementById("detError").innerHTML = "";
		document.getElementById("locationError").innerHTML = "Please select a location for the course";
		document.getElementById("startDateError").innerHTML = "";
		document.getElementById("endDateError").innerHTML = "";
		document.getElementById("recurrenceError").innerHTML = "";
		document.getElementById("timeError").innerHTML = "";
		return false;
	} else if (startDate == ""){
		document.getElementById("nameError").innerHTML = "";
		document.getElementById("detError").innerHTML = "";
		document.getElementById("locationError").innerHTML = "";
		document.getElementById("startDateError").innerHTML = "Please select a start date for the course";
		document.getElementById("endDateError").innerHTML = "";
		document.getElementById("recurrenceError").innerHTML = "";
		document.getElementById("timeError").innerHTML = "";
		return false;
	} else if (endDate == ""){
		document.getElementById("nameError").innerHTML = "";
		document.getElementById("detError").innerHTML = "";
		document.getElementById("locationError").innerHTML = "";
		document.getElementById("startDateError").innerHTML = "";
		document.getElementById("endDateError").innerHTML = "Please select a end date for the course";
		document.getElementById("recurrenceError").innerHTML = "";
		document.getElementById("timeError").innerHTML = "";
		return false;
	} else if (recurrence == "0"){
		document.getElementById("nameError").innerHTML = "";
		document.getElementById("detError").innerHTML = "";
		document.getElementById("locationError").innerHTML = "";
		document.getElementById("startDateError").innerHTML = "";
		document.getElementById("endDateError").innerHTML = "";
		document.getElementById("recurrenceError").innerHTML = "Please select a recurrence type for the course";
		document.getElementById("timeError").innerHTML = "";
		return false;
	} else if(checkDates(startDate, endDate)) {
		document.getElementById("nameError").innerHTML = "";
		document.getElementById("detError").innerHTML = "";
		document.getElementById("locationError").innerHTML = "";
		document.getElementById("startDateError").innerHTML = "";
		document.getElementById("recurrenceError").innerHTML = "";
		document.getElementById("endDateError").innerHTML = "Please select an end date greater than or equal to the start date";
		document.getElementById("timeError").innerHTML = "";
		return false;
	} else if(checkTime(startHour, startMin, endHour, endMin)){
		document.getElementById("nameError").innerHTML = "";
		document.getElementById("detError").innerHTML = "";
		document.getElementById("locationError").innerHTML = "";
		document.getElementById("startDateError").innerHTML = "";
		document.getElementById("recurrenceError").innerHTML = "";
		document.getElementById("endDateError").innerHTML = "";
		document.getElementById("timeError").innerHTML = "Enter valid timings for the course";
		return false;
	} else if(!nameCheck.test(name)){
		document.getElementById("nameError").innerHTML = "Please enter a valid Course Name";
		document.getElementById("detError").innerHTML = "";
		document.getElementById("locationError").innerHTML = "";
		document.getElementById("startDateError").innerHTML = "";
		document.getElementById("recurrenceError").innerHTML = "";
		document.getElementById("endDateError").innerHTML = "";
		document.getElementById("timeError").innerHTML = "";
		return false;
	} else if(!detCheck.test(details)){
		document.getElementById("nameError").innerHTML = "";
		document.getElementById("detError").innerHTML = "Please enter a proper course description";
		document.getElementById("locationError").innerHTML = "";
		document.getElementById("startDateError").innerHTML = "";
		document.getElementById("recurrenceError").innerHTML = "";
		document.getElementById("endDateError").innerHTML = "";
		document.getElementById("timeError").innerHTML = "";
		return false;
	} else
		return true;
	};

	function checkDates(startDate, endDate){
		var date1= new Date(startDate);
		var date2 = new Date(endDate);
		if(date1<=date2)
			return false;
		else
			return true;
	};

	function checkTime(startHour, startMin, endHour, endMin){
		var hourGap = endHour - startHour;
		var minGap = endMin - startMin;
		if(hourGap == 0){
			if(minGap<15)
				return true;
		} else if(hourGap<0)
			return true;
		else
			return false;
	};
</script>
<title>Event Registration</title>
</head>
<body style="background-color: lavendar; overflow: hidden">
	<jsp:include page="adminheader.jsp" />
	<form:form class="form-horizontal" method="post"
		onsubmit="return validateForm()" action="registerCourse.action"
		commandName="courseDTO">
		<div class="container">
			<div class="jumbotron">
				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<div class="col-sm-4">
							<h4>Register Course Here</h4>
						</div>
						<div class="col-sm-6"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<label class="col-sm-3" for="name">Enter Course name</label>
						<div class="col-sm-5">
							<form:input type="text" id="name" class="form-control"
								path="course.courseName" placeHolder="E.g: Java 201" />
							<form:errors path="course.courseName" class="error"></form:errors>
							<span id="nameError"></span>
						</div>
						<div class="col-sm-2"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<label class="col-sm-3" for="name">Enter Course Details</label>
						<div class="col-sm-5">
							<form:input type="textArea" id="details" class="form-control"
								path="course.courseDetails" placeHolder="E.g: Course Content" />
							<form:errors path="course.courseDetails" class="error"></form:errors>
							<span id="detError"></span>
						</div>
						<div class="col-sm-2"></div>
					</div>
				</div>

				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<label class="col-sm-3" for="email">Enter Course Location</label>
						<div class="col-sm-5">
							<form:select path="course.location.locationId" id="location"
								class="form-control" onchange="display(this.value)">
								<form:option value="0">Select location..</form:option>
								<jstl:forEach items="${locations}" var="location">
									<form:option value="${location.locationId}"> ${location.locationDetails} </form:option>
								</jstl:forEach>
							</form:select>
							<form:errors path="course.location.locationId" cssClass="error" />
							<span id="locationError"></span>
						</div>
						<div class="col-sm-2"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<label class="col-sm-3" for="phone">Select Start date</label>
						<div class="col-sm-5">
							<div class="input-group date" data-provide="datepicker"
								id="datePicker1">
								<form:input type="text" class="form-control" id="startDate"
									readonly="readonly" path="course.startDate" />
								<span id="startDateError"></span>
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-th"></span>
								</div>
							</div>
							<script type="text/javascript">
								var nowDate = new Date();
								var today = new Date(nowDate.getFullYear(),
										nowDate.getMonth(), nowDate.getDate(),
										0, 0, 0, 0);
								$('#datePicker1').datepicker({
									todayHighlight : 1,
									startDate : today,
									value : today,
									daysOfWeekDisabled : [ 0, 6 ],
									autoclose: true
								});
							</script>
						</div>
						<div class="col-sm-2"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<label class="col-sm-3" for="phone">Select End date</label>
						<div class="col-sm-5">
							<div class="input-group date" data-provide="datepicker"
								id="datePicker2">
								<form:input type="text" class="form-control" id="endDate"
									readonly="readonly" path="course.endDate" />
								<span id="endDateError"></span>
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-th"></span>
								</div>
							</div>
							<script type="text/javascript">
								var nowDate = new Date();
								var today = new Date(nowDate.getFullYear(),
										nowDate.getMonth(), nowDate.getDate(),
										0, 0, 0, 0);
								$('#datePicker2').datepicker({
									todayHighlight : 1,
									startDate : today,
									value : today,
									daysOfWeekDisabled : [ 0, 6 ],
									autoclose: true
								});
							</script>
						</div>
						<div class="col-sm-2"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<label class="col-sm-2" for="fromTime">Course Timings</label>
						<div class="col-sm-1" align="right">
							<h6>From:</h6>
						</div>
						<div class="col-sm-1">
							<form:select path="startHour" id="startHour" class="form-control">
								<jstl:forEach items="${hours}" var="hour">
									<form:option value="${hour}">${hour}</form:option>
								</jstl:forEach>
							</form:select>
						</div>
						<div class="col-sm-1">
							<form:select path="startMin" id="startMin" class="form-control">
								<jstl:forEach items="${minutes}" var="min">
									<form:option value="${min}">${min}</form:option>
								</jstl:forEach>
							</form:select>
						</div>
						<div class="col-sm-1" align="right">
							<h6>To:</h6>
						</div>
						<div class="col-sm-1">
							<form:select path="endHour" id="endHour" class="form-control">
								<jstl:forEach items="${hours}" var="hour">
									<form:option value="${hour}">${hour}</form:option>
								</jstl:forEach>
							</form:select>
						</div>
						<div class="col-sm-1">
							<form:select path="endMin" id="endMin" class="form-control">
								<jstl:forEach items="${minutes}" var="min">
									<form:option value="${min}">${min}</form:option>
								</jstl:forEach>
							</form:select>
						</div>
						<span id="timeError"></span>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<label class="col-sm-3" for="email">Select recurrence type</label>
						<div class="col-sm-5">
							<form:select path="course.recurrence" id="Recurrence"
								class="form-control" onchange="display(this)">
								<form:option value="0">Select Recurrence Type</form:option>
								<jstl:forEach items="${recurrenceTypes}" var="recurrence">
									<form:option value="${recurrence}" />
								</jstl:forEach>
							</form:select>
							<form:errors path="course.recurrence" cssClass="error" />
							<span id="recurrenceError"></span>
						</div>
						<div class="col-sm-2"></div>
					</div>
				</div>

				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<label class="col-sm-3"></label>
						<div class="col-sm-5">
							<input type="submit" id="submit"
								class="form-control btn btn-primary" value="Register Course">
						</div>
						<div class="col-sm-2"></div>
					</div>
				</div>
			</div>
		</div>
	</form:form>
	<jsp:include page="footer.jsp" />
</body>
</html>