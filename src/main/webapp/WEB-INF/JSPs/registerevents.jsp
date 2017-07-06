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
	function validateForm() {
		var name = document.getElementById("name").value;
		var details = document.getElementById("details").value;
		var location = document.getElementById("location").value;
		var eventDate = document.getElementById("eventDate").value;

		/* var dateCheck = /^?[0-2][0-9]{1}+[\/]{1}+?[0-3][0-9]{1}+[\/]{1}+[1-2]{1}[0-9]{3}$/; */
		var nameCheck = /[a-zA-Z ]{3,20}$/;
		var detCheck = /[a-zA-Z ,]$/;
		
		if (name == "") {
			document.getElementById("nameError").innerHTML = "Name Cannot be Empty";
			return false;
		} else if (location == "0") {
			document.getElementById("nameError").innerHTML = "";
			document.getElementById("locationError").innerHTML = "Please select a location";
			return false;
		} else if (eventDate == "") {
			document.getElementById("nameError").innerHTML = "";
			document.getElementById("locationError").innerHTML = "";
			document.getElementById("dateError").innerHTML = "Please select a date";
			return false;
		} else if (!nameCheck.test(name)) {
			document.getElementById("nameError").innerHTML = "Please enter only alphabets";
			return false;
		} else if (details == "") {
			document.getElementById("detError").innerHTML = "Details Cannot be Empty";
			return false;
		}else if (!detCheck.test(details)) {
			document.getElementById("detError").innerHTML = "Please enter only alphabets";
			return false;
		}else {
			return true;
		}
	};
</script>
<title>Event Registration</title>
</head>
<body style="background-color: lavendar; overflow: hidden">
	<jsp:include page="adminheader.jsp" />
	<form:form class="form-horizontal" method="post"
		onsubmit="return validateForm()" action="registerEvent.action"
		commandName="event">
		<div class="container">
			<div class="jumbotron">
				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<div class="col-sm-3">
							<h4>Register Event Here</h4>
						</div>
						<div class="col-sm-7"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<label class="col-sm-2" for="name">Enter Event name</label>
						<div class="col-sm-5">
							<form:input type="text" id="name" class="form-control"
								path="eventName" placeHolder="E.g: One Good Deed" />
							<form:errors path="eventName" class="error"></form:errors>
							<span id="nameError"></span>
						</div>
						<div class="col-sm-3"></div>
					</div>
				</div>

				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<label class="col-sm-2" for="name">Enter Event Details</label>
						<div class="col-sm-5">
							<form:input type="textArea" id="details" class="form-control"
								path="eventDetails" placeHolder="E.g: Serving Lunch" />
							<form:errors path="eventDetails" class="error"></form:errors>
							<span id="detError"></span>
						</div>
						<div class="col-sm-3"></div>
					</div>
				</div>

				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<label class="col-sm-2" for="email">Enter Event location</label>
						<div class="col-sm-5">
							<form:select path="location.locationId" id="location"
								class="form-control" onchange="display(this.value)">
								<form:option value="0">Select location..</form:option>
								<jstl:forEach items="${locations}" var="location">
									<form:option value="${location.locationId}"> ${location.locationDetails} </form:option>
								</jstl:forEach>
							</form:select>
							<form:errors path="location.locationId" cssClass="error" />
							<span id="locationError"></span>
						</div>
						<div class="col-sm-3"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<label class="col-sm-2" for="phone">Select date</label>
						<div class="col-sm-5">
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
						<div class="col-sm-3"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<label class="col-sm-2"></label>
						<div class="col-sm-5">
							<input type="submit" id="submit"
								class="form-control btn btn-primary" value="Register Event">
						</div>
						<div class="col-sm-3"></div>
					</div>
				</div>
			</div>
		</div>
	</form:form>
	<jsp:include page="footer.jsp" />
</body>
</html>