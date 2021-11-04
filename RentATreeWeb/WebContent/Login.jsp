<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import = "db.Login" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>Sign in</title>
	<script src="http://code.jquery.com/jquery-2.2.4.js" type="text/javascript"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/bootstrap.css">
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery-3.6.0.min.js"></script>
</head>

<body class="text-center">
<%@include file="navbar.jsp" %>
<main class="form-signin">
	<form action="Login.jsp" method="POST">
		<!--<img class="mb-4" src="/docs/5.1/assets/brand/bootstrap-logo.svg" alt="" width="72" height="57">-->
		<img class="mb-4" src="static/RentATree-logos.jpeg" alt="" width="80" height="68">
		<h1 class="h3 mb-3 fw-normal">Please sign up</h1>
	<div class="form-floating">
		<input type="text" class="form-control" id="floatingInput" placeholder="Username" name="username" required autofocus>
		<label for="floatingInput" >Username</label>
	</div>
    <br>
	<div class="form-floating">
		<input type="password" class="form-control" placeholder="Password" id="floatingPassword" name="password" required>
		<label for="floatingPassword" >Password</label>

	</div>

	<div class="checkbox mb-3">
		<label>
		<input type="checkbox" value="remember-me" name="remember"> Remember me
		</label>
	</div>
    
	<p><a href="NewUser.jsp">Sign Up</a></p> <!-- need to change url-->
	
	<button class="w-100 btn btn-lg btn-primary" type="submit" value="Submit">Sign in</button>
	<p class="mt-5 mb-3 text-muted">&copy; RentATree 2021</p>
	

	
  </form>
	<%
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Login lObj = new Login(username, password);
		int output = lObj.loginUser();
		if(output==2){
			response.sendRedirect("http://localhost:18080/RentATreeWeb/Admin.jsp");
		}
		if(output==1){
			session.setAttribute("username", username);
			response.sendRedirect("http://localhost:18080/RentATreeWeb/Shop.jsp");
		}
	%>
</main>
</body>

</html>