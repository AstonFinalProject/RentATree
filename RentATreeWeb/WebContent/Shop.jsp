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
			
			function showValS1(newVal){
			    document.getElementById("val1").innerHTML=newVal;
			}
			function showValS2(newVal){
			    document.getElementById("val2").innerHTML=newVal;
			}
			function validateDates(){
				let datemin = document.forms["dates"]["rentfrom"].value;
				let datemax = document.forms["dates"]["rentto"].value;
				if(datemax<datemin){
					alert("Lease end less than Lease start");
					return false;
				}
				const validMonths = [11,0];
				console.log(datemin.getMonth());
				if(validMonths.includes(datemin.getMonth())==false){
					alert("Lease can only be in December or January");
					return false;
				}
				if(!validMonths.includes(datemax.getMonth())==false){
					alert("Lease can only be in December or January");
					return false;
				}
			}
	</script>
		
</head>

<body>
	<%@include file="navbar.jsp" %>
	<div class = "container">

	
	<div align="center">
		
	<div align="top">
	<marquee behavior="alternate" bgcolor="#4BB060" direction="left" height="" loop="7" scrollamount="1" scrolldelay="2" width="100%">
		<span style="font-size: 20px;color:#FFFFFF">
			Discount Buy one Get the other half price! Use PromoCode BOGOHP at checkout</span></marquee>
		</div>
	</div>
	<br>	
			<div class="container">
			<div class = "pb-2 mt-4 mb-2 border-bottom">
			Booking from <%= (String) session.getAttribute("start") %> to <%=(String)session.getAttribute("end") %>
			</div>
			</div>
			<form name="dates" onsubmit="return validateDates()" action = "${pageContext.request.contextPath}/DateFilter" method="post">
				<label>
				  Choose your preferred Rent From date (required, Dec 1st to Jan 14th):
				  <input type="date" name="rentfrom" value="<%=(String)session.getAttribute("start") %>" required>
				  <span class="validity"></span>
				</label>
				<label><br>
				  Choose your preferred Rent To date (required, Dec 1st to Jan 14th):
				  <input type="date" name="rentto" value="<%=(String)session.getAttribute("end") %>"required>
				  <span class="validity"></span>
				</label><br>
				<button type="submit" class="btn btn-primary">Submit Dates</button>
			</form>
	<br>
	<form action = "${pageContext.request.contextPath}/ProductFilterServlet" method="post" name="filters">
		<p>Please select your tree type</p>
		<% for(String t: ProductFilter.uniqueTypes()) {%>
		<div class="form-check">
			
			<input type="checkbox" class="form-check-input" name="type" value="<%=t%>">
			<label class="form-check-label" for="type"><%=t %></label>
		</div>
		<%} %>
		<p><br> Please select your tree material</p>
		<% for(String m: ProductFilter.uniqueMaterials()){ %>
		<div class="form-check">
			
			<input type="checkbox" class="form-check-input" name="material" value="<%=m%>">
			<label class="form-check-label" for="type"><%=m %></label>
		</div>
		<%} %>
		<p><br>Please select your tree supplier</p>
		<%for(String s: ProductFilter.uniqueSuppliers()){ %>
		<div class="form-check">
			<input type="checkbox" class="form-check-input" name="supplier" value="<%=s%>">
			<label class="form-check-label" for="type"><%=s %></label>
		</div>
		<%} %>
		<div class="slidecontainer">
			<p><br>Please select the max height of the tree (in cm)</br></p>
			<input type="range" min="0" max="400" value="200" class="slider" name="myRange" onchange="showval(this.value)" oninput="showval(this.value)">
			<p>Value: <span id="val1"></span></p>
		</div>
		
		<!-- second slider-->
		<div class="slidecontainer">
			<p><br>Please select the min height of the tree (in cm)</br></p>
			<input type="range" min="0" max="400" value="200" class="slider1" name="myRange1" onchange="showval(this.value)" oninput="showval(this.value)">
			<p>Value: <span id="val2"></span></p>
		</div>
		  <br><input class="btn btn-primary" type="submit" value="Submit Filters">
	</div>
	</form>
	<br>
  <div class="container marketing">
	<form action = "${pageContext.request.contextPath}/AddToBasket" method="post">
    <!-- Three columns of text below the carousel -->
    <%
    	ArrayList<Product> prods = new ArrayList<Product>();
    	
    	if(session.getAttribute("products")==null){
    		ProductDBHandler h = new ProductDBHandler();
        	prods = h.getProducts();
        	session.setAttribute("products", prods);
    	}else{
    		prods = (ArrayList)session.getAttribute("products");
    	}
    	if(session.getAttribute("basket") == null){
    		session.setAttribute("basket", new Basket(prods));
    	}
    	Basket b = (Basket)session.getAttribute("basket");
    	prods = AvailableProducts.getAvailableProducts(b, prods);
    	int i = 0;
    	for(Product p: prods){
    %>
   
   	<%if(i==0) {%> 
    <div class="row">
    <%} %>
      <div class="col-lg-4">
        <svg class="bd-placeholder-img rounded-circle" width="140" height="140" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 140x140" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#777"/><text x="50%" y="50%" fill="#777" dy=".3em">140x140</text></svg>
        <h2><%=p.getType() %></h2>
        <p>Height: <%=p.getHeight() %>cm</p>
        <p>Material: <%=p.getMaterial() %></p>
        <p>Supplier: <%=p.getSupplierName()%></p>
        <p>Price: £<%=p.getPrice() %>
        <p>Description: <%=p.getTreeDescription() %>
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