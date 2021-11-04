<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import = "db.*" %>
 <%@page import = "models.*" %>
 <%@page import = "java.util.*" %>
<!DOCTYPE html>

<header>
  <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">RentATree</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarCollapse">
        <ul class="navbar-nav me-auto mb-2 mb-md-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="Shop.jsp">Home</a>
          </li>
          <li class="nav-item">
            <!-- <a class="nav-link" href="#">Link</a> -->
          </li>
          
        </ul>
        
        <form class="d-flex">

                <!-- <a class="nav-link" href="#">Link</a> -->
                <!-- <img class="mb-4" src="basket2.jpg" alt="" width="40" height="30" href="#">Link</a>>
                <a href="#"><i class="fa fa-fw fa-search"></i> Search</a>  -->

                <a href="Checkout.jsp"> <!--change link to basket-->
                    <img src="static/basket2.png"
                    width="46" height="36">
                 </a>
						
           		<%if(session.getAttribute("basket")==null){
           			ProductDBHandler h = new ProductDBHandler();
           			session.setAttribute("products", h.getProducts());
           			session.setAttribute("basket", new Basket(h.getProducts()));
           		}
           		Basket navb = (Basket)session.getAttribute("basket");%>
                <span style="color: rgb(200, 10, 10);"><font size=5>
               <%=navb.getBasket().size()%> </font>&nbsp; </span> <!--updqate item in basket-->
			<a href="Login.jsp"><button type="button" class="btn btn-outline-primary me-2">Login</button></a>
			<a href="NewUser.jsp"><button type="button" class="btn btn-primary">Sign-up</button></a>
		</form>
      </div>
    </div>
  </nav>
  
  
  
</header>
	