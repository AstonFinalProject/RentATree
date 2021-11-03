<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "db.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>Sign up</title>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js /bootstrap.bundle.min.js" integrity="sha384 -gtEjrD/ SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
	<link href ="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity ="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin ="anonymous">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/bootstrap.css">
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery-3.6.0.min.js"></script>
</head>

<body class="text-center">
<%@include file="navbar.jsp" %>
<main class="form-signin">
	<form action="NewUser.jsp" method="POST">
		<!--<img class="mb-4" src="/docs/5.1/assets/brand/bootstrap-logo.svg" alt="" width="72" height="57">-->
		<img class="mb-4" src="static/rentATreeLogo.PNG" alt="" width="75" height="62">
		<h1 class="h3 mb-3 fw-normal">Sign Up</h1>
		<div id=FormFill>
		<p>Please fill in this form to create an account</p>
		</div>
	
	<div class="form-floating">
		<input class="form-control" id="floatingInput" placeholder="Username" name="username">
		<label for="floatingInput">Username</label>
	</div>
	
	<div class="form-floating">
		<input type="email" class="form-control" id="floatingInput" placeholder="Email" name="email">
		<label for="floatingInput">Email address</label>
	</div>
	
	<div class="form-floating">
		<input class="form-control" id="floatingInput" placeholder="First Name" name="fname">
		<label for="floatingInput">First Name</label>
	</div>
	
	<div class="form-floating">
		<input  class="form-control" id="floatingInput" placeholder="Last Name" name="lname">
		<label for="floatingInput">Last Name</label>
	</div>
	
	<div class="form-floating">
		<input class="form-control" id="floatingInput" placeholder="Telephone" name="phone">
		<label for="floatingInput">Telephone</label>
	</div>
	
	<div class="form-floating">
		<input type="password" class="form-control" placeholder="Password" id="floatingPassword" name="pass">
		<label for="floatingPassword">Password</label>
	</div>
	
	<div class="form-floating">
		<input type="password" class="form-control" placeholder="Confirm Password" id="floatingPassword" name="cpass">
		<label for="floatingPassword">Confirm Password</label>
	</div>
	
	<div class="checkbox1 mb-3">
		<label>
		<input type="checkbox" value="Terms" name="terms"> I accept the Terms of Use and Privacy Policy
		</label>
	</div>

	<p><a href="Login.jsp">Sign In</a></p> <!-- need to change url-->
	
	<button class="w-100 btn btn-lg btn-primary" value="submit" type="submit">Sign Up</button>
	<p class="mt-5 mb-3 text-muted">&copy; RentATree 2021</p>
	
	<%
		String username = request.getParameter("username");
		String pass = request.getParameter("pass");
		String cpass = request.getParameter("cpass");
		String phone = request.getParameter("phone");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String terms = request.getParameter("terms");
		String submit = request.getParameter("Submit");
	
		if(pass!=null && pass.equals(cpass)){
			String[] params = {username, email, fname, lname,phone, pass};
			NewUser nu = new NewUser(params);
			int output = nu.createUser();
			if(output>=1){
				response.sendRedirect("http://localhost:18080/RentATreeWeb/Login.jsp");
			}
		}
	%>
  </form>

</main>
</body>

</html>