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
		var password = document.getElementById("password").value;
		var userName = document.getElementById("userName").value;

		if (password == "") {
			document.getElementById("passwordError").innerHTML = "Password Cannot be Empty";
			return false;
		} else if (userName == "") {
			document.getElementById("userNameError").innerHTML = "User Name Cannot be Empty";
			return false;
		} else
			return true;
	}
</script>
<title>Login</title>
</head>
<body style="background-color: lavendar; overflow: hidden">
	<jsp:include page="header.jsp" />
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
	<div class="container">
		<div class="row">
			<div class="col-md-5">
				<div class="jumbotron">
					<h4>Admin Registration</h4>
					<p>If you are an admin please register here</p>
					<a href="adminsignup.get" class="btn btn-primary">Admin
						Registration</a>
				</div>
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-5">
				<div class="jumbotron">
					<form:form method="post" onsubmit="return validateForm()"
						action="adminLogin.action" commandName="user">
						<h4>Admin Login</h4>
						<div class="form-group">
							<label for="name">Enter your User Name</label>
							<form:input type="text" id="name" class="form-control"
								path="userName" />
							<form:errors path="userName" class="error" />
						</div>
						<div class="form-group">
							<label for="phone">Enter your password</label>
							<form:input type="password" id="password" class="form-control"
								path="password" />
							<form:errors path="password" class="error" />
						</div>
						<div class="form-group">
							<input type="submit" id="submit"
								class="form-control btn btn-primary" value="Login">
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