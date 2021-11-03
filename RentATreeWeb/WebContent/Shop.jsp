<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@page import = "db.*" %>
  <%@page import = "models.*" %>
  <%@page import = "java.util.*" %>
<!DOCTYPE html> 
<html lang="en">

<!--homepage-->

<head>
	<title>Homepage</title>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js /bootstrap.bundle.min.js" integrity="sha384 -gtEjrD/ SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
	<link href ="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity ="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin ="anonymous">

	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/bootstrap.css">
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery-3.6.0.min.js"></script>
			
		<script>
			var slider = document.getElementById("myRange");
			var output = document.getElementById("demo");
			output.innerHTML = slider.value;

			slider.oninput = function() {
			output.innerHTML = this.value;
}


		<!-- second slider script-->	
			var slider1 = document.getElementById("myRange1");
			var output1 = document.getElementById("demo1");
			output1.innerHTML = slider1.value;

			slider1.oninput = function() {
			output1.innerHTML = this.value;
}
</script>
		
</head>

<body>
	<%@include file="navbar.jsp" %>
	<div class = "container">

	
	<div align="center">
		
		<div align=top>
	<marquee behavior="alternate" bgcolor="#4BB060" direction="left" height="" loop="7" scrollamount="1" scrolldelay="2" width="100%">
		<span style="font-size: 20px;color:#FFFFFF">
			Discount Buy one Get the other half price!</span></marquee>
		</div>
	</div>
		<p>Please select your tree type</p>
		<% for(String t: ProductFilter.uniqueTypes()) {%>
		<div class="form-check">
			
			<input type="checkbox" class="form-check-input" id="type<%=t %>" value="<%=t%>">
			<label class="form-check-label" for="type"><%=t %></label>
		</div>
		<%} %>
		<p><br> Please select your tree material</p>
		<% for(String m: ProductFilter.uniqueMaterials()){ %>
		<div class="form-check">
			
			<input type="checkbox" class="form-check-input" id="material<%=m %>" value="<%=m%>">
			<label class="form-check-label" for="type"><%=m %></label>
		</div>
		<%} %>

		<%for(String s: ProductFilter.uniqueSuppliers()){ %>
		<div class="form-check">
		<p><br>Please select your tree supplier</br></p>
			<input type="checkbox" class="form-check-input" id="supplier<%=s%>" value="<%=s%>">
			<label class="form-check-label" for="type"><%=s %></label>
		</div>
		<%} %>
		<div class="slidecontainer">
			<p><br>Please select the max height of the tree (in cm)</br></p>
			<input type="range" min="0" max="400" value="200" class="slider" id="myRange">
			<p>Value: <span id="demo"></span></p>
		</div>
		
		<!-- second slider-->
		<div class="slidecontainer">
			<p><br>Please select the min height of the tree (in cm)</br></p>
			<input type="range" min="0" max="400" value="200" class="slider1" id="myRange1">
			<p>Value: <span id="demo1"></span></p>
		</div>

	</div>

	<br>
  <div class="container marketing">
	<form action = "${pageContext.request.contextPath}/AddToBasket" method="post">
    <!-- Three columns of text below the carousel -->
    <%
    	ArrayList<Product> prods = new ArrayList<Product>();
    	ProductDBHandler h = new ProductDBHandler();
    	prods = h.getProducts();
    	if(session.getAttribute("basket") == null){
    		session.setAttribute("basket", new Basket(prods));
    	}
    	int i = 0;
    	for(Product p: prods){
    %>
   
   	<%if(i==0) {%> 
    <div class="row">
    <%} %>
      <div class="col-lg-4">
        <svg class="bd-placeholder-img rounded-circle" width="140" height="140" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 140x140" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#777"/><text x="50%" y="50%" fill="#777" dy=".3em">140x140</text></svg>
        <h2><%=p.getType() %></h2>
        <p>Height: <%=p.getHeight() %></p>
        <p>Material: <%=p.getMaterial() %></p>
        <p>Supplier: <%=p.getSupplierName()%></p>
        <p>Price: <%=p.getPrice() %>
        
        <p><button type="submit" name="add" class="btn btn-secondary" value=<%=p.getID() %>>Add to basket &raquo;</button></p>
      </div><!-- /.col-lg-4 -->
     
    <%if(i==2) {
    	i=0;%>
    </div>
 
    <%}else{
    	i++;}%>
	<%;}%>

	</form>
</div>

</body>

</html>