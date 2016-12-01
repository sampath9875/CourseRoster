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
<script type="text/javascript">
	var validUser = false;
	function validateForm() {
		var password1 = document.getElementById("password").value;
		var password2 = document.getElementById("password1").value;
		var userName = document.getElementById("userName").value;
		var name = document.getElementById("name").value;
		var emailId = document.getElementById("email").value;

		var emailCheck = /[\w-\.+]@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
		var nameCheck = /[a-zA-Z ]{3,20}$/;

		if (name == "") {
			document.getElementById("nameError").innerHTML = "Name Cannot be Empty";
			return false;
		} else if (password1 == "") {
			document.getElementById("passwordError").innerHTML = "Password Cannot be Empty";
			return false;
		} else if (password2 == "") {
			document.getElementById("password1Error").innerHTML = "Please Re Enter Your Password";
			return false;
		} else if (userName == "") {
			document.getElementById("userNameError").innerHTML = "User Name Cannot be Empty";
			return false;
		} else if (emailId == "") {
			document.getElementById("emailError").innerHTML = "Email Cannot be Empty";
			return false;
		} else if (!emailCheck.test(emailId)) {
			document.getElementById("emailError").innerHTML = "Please enter a valid email Id";
			return false;
		} else if (!nameCheck.test(name)) {
			document.getElementById("nameError").innerHTML = "Please enter only alphabets";
			return false;
		} else if (!validUser) {
			document.getElementById("userNameError").innerHTML = "User Name already taken. Please choose a different one";
			return false;
		} else {
			return validatePassword();
		}
	};
	function validUserName(userName) {
		$.ajax({
			type : "get",
			contentType : "application/text",
			url : "validateUserName",
			data : {
				"userName" : userName.value
			},
			success : function(data) {
				console.log(data);
				if (data.toUpperCase() === "inValid".toUpperCase())
					validUser = false;
				else if (data.toUpperCase() === "valid".toUpperCase())
					validUser = true;
				else
					validUser = false;
			},
			error : function() {
				alert("fail");
			}
		});
	}

	function validatePassword() {
		var password1 = document.getElementById("password").value;
		var password2 = document.getElementById("password1").value;

		if ((password1 == "")) {
			document.getElementById("passwordError").innerHTML = "Fields cannot be empty";
			return false;
		} else if (password2 == "") {
			document.getElementById("password1Error").innerHTML = "Fields cannot be empty";
			return false;
		} else if (password1 != password2) {
			document.getElementById("password1Error").innerHTML = "Passwords DO Not Match";
			return false;
		} else {
			document.getElementById("password1Error").innerHTML = "";
			return true;
		}
	}
</script>
<title>User Registration</title>
</head>
<body style="background-color: lavendar; overflow: hidden">
	<jsp:include page="header.jsp" />
	<form:form class="form-horizontal" method="post"
		onsubmit="return validateForm()" action="adminSignUp.action"
		commandName="user">
		<div class="container">
			<div class="jumbotron">
				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<div class="col-sm-3">
							<h4>Admin Registration</h4>
						</div>
						<div class="col-sm-7"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<label class="col-sm-2" for="name">Enter your name</label>
						<div class="col-sm-5">
							<form:input type="text" id="name" class="form-control"
								path="name" />
							<form:errors path="name" class="error" />
							<span id="nameError"></span>
						</div>
						<div class="col-sm-3"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<label class="col-sm-2" for="emailId">Enter your email</label>
						<div class="col-sm-5">
							<form:input type="email" id="email" class="form-control"
								path="emailId" />
							<form:errors path="emailId" class="error" />
							<span id="emailError"></span>
						</div>
						<div class="col-sm-3"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<label class="col-sm-2" for="userName">Enter your user
							name</label>
						<div class="col-sm-5">
							<form:input type="text" id="userName" class="form-control"
								path="userName" onblur="validUserName(this)" />
							<form:errors path="userName" class="error" />
							<span id="userNameError"></span>
						</div>
						<div class="col-sm-3"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<label class="col-sm-2" for="password">Enter your password</label>
						<div class="col-sm-5">
							<form:input type="password" id="password" class="form-control"
								path="password" />
							<form:errors path="password" class="error" />
							<span id="passwordError"></span>
						</div>
						<div class="col-sm-3"></div>
					</div>
				</div>
				<div class="form-group">
					<div class="row">
						<div class="col-sm-2"></div>
						<label class="col-sm-2" for="phone">Re-enter your password</label>
						<div class="col-sm-5">
							<input type="password" id="password1" class="form-control"
								onkeyup="validatePassword()" /> <span id="password1Error"></span>
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
								class="form-control btn btn-primary" value="Register">
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