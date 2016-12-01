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
		var nameCheck = /[a-zA-Z ,]{5,25}$/;

		if (name == "") {
			document.getElementById("nameError").innerHTML = "Name Cannot be Empty";
			return false;
		}  else if (name.length<5 || name.length > 25) {
			document.getElementById("nameError").innerHTML = "Location length should be between 5 - 25 characters";
			return false;
		}else if (!nameCheck.test(name)) {
			document.getElementById("nameError").innerHTML = "Please enter name like Global Village,Bangalore";
			return false;
		} else {
			return true;
		}
	};
</script>
<title>Add Location</title>
</head>
<body style="background-color: lavendar; overflow: hidden">
	<jsp:include page="adminheader.jsp" />
	<form:form class="form-horizontal" method="post"
		onsubmit="return validateForm();" action="location.action"
		commandName="location">
		<div class="container">
			<div class="jumbotron">
				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<div class="col-sm-3">
							<h4>Register Location here</h4>
						</div>
						<div class="col-sm-7"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<label class="col-sm-2" for="name">Enter Location</label>
						<div class="col-sm-5">
							<form:input type="text" id="name" class="form-control"
								path="locationDetails"
								placeHolder="E.g: Global Village, Bangalore" />
							<form:errors path="locationDetails" class="error"></form:errors>
							<span id="nameError"></span>
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
								class="form-control btn btn-primary" value="Register Location">
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