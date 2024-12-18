<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import = "db.*" %>
<%@page import = "models.*" %>
<%@page import = "java.util.*" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<title>Basket</title>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js /bootstrap.bundle.min.js" integrity="sha384 -gtEjrD/ SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
	<link href ="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity ="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin ="anonymous">
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/bootstrap.css">
	<script src="js/bootstrap.js"></script>
	<script src="js/jquery-3.6.0.min.js"></script>
	<script>
	function validateForm(){
		let address = document.forms["billing"]["address"].value;
		let street = document.forms["billing"]["address2"].value;
		let city = document.forms["billing"]["city"].value;
		let postcode = document.forms["billing"]["postcode"].value;
		if(address==""){
			alert("Address line 1 cannot be empty");
			return false;
		}
		if(street==""){
			alert("Address line 2 cannot be empty");
			return false;
		}
		if(city==""){
			alert("City cannot be empty");
			return false;
		}
		if(postcode==""){
			alert("Postcode cannot be empty");
			return false;
		}
	}
	</script>
</head>
<%@include file="navbar.jsp" %>
<body class="bg-light">

<div class="container">
  <main>
	<div class="py-5 text-center">
      <img class="d-block mx-auto mb-4" src="static/RentATree-logos.jpeg" alt="" width="90" height="82">
      <h2>Your RentATree Basket</h2>
      <p class="lead">Please complete the information to make a purchase.</p>
	  <p class="lead" id= "Discount"><b>Discount: Rent one, Get one half-price!</b></p>
		
	
	
	</div>

    <div class="row g-5">
      <div class="col-md-5 col-lg-4 order-md-last">
        <h4 class="d-flex justify-content-between align-items-center mb-3">
          <span class="text-primary">Your cart</span>
          <% 
          	Basket b = (Basket)session.getAttribute("basket"); 
          	String username = (String)session.getAttribute("username");
          	Transactions t;
          	if(request.getParameter("promo")!=null){
          		t = new Transactions(b,username, (String)session.getAttribute("start"), (String)session.getAttribute("end"), "", "", request.getParameter("promo"));
          		session.setAttribute("discount", t.getDiscount());
          	}else{
          		t = new Transactions(b,username, (String)session.getAttribute("start"), (String)session.getAttribute("end"), "", "", "");
  
          	}
	        
        %>
          <span class="badge bg-primary rounded-pill"><%=b.getBasket().size() %></span>
        </h4>
        <ul class="list-group mb-3">
        <%
        
        for(Product p:b.getBasket()){
        %>
          <li class="list-group-item d-flex justify-content-between lh-sm">
            <div>
              <h6 class="my-0"><%=p.getType()%></h6>
              <small class="text-muted"><%=p.getTreeDescription()%></small>
            </div>
            <span class="text-muted">�<%=p.getPrice() %></span>
          </li>
          <%} %>
          <li class="list-group-item d-flex justify-content-between bg-light">
            <div class="text-success">
              <h6 class="my-0">Discount</h6>
              <small>Rent 1, Get one half price</small>
            </div>
           	<%if(session.getAttribute("discount")!=null) {%>
            <span class="text-success">-�<%=session.getAttribute("discount")%></span>
            <%}else{ %>
            <span class="text-success">-�0%></span>
            <%} %>
          </li>
		  <li class="list-group-item d-flex justify-content-between bg-light">
            <div class="text-success">
              <h6 class="my-0">Delivery Fee</h6>
              <small>Extra �3.99 fee for selected am/pm slot</small>
            </div>
            <span class="text-success">�3.99</span>
          </li>
          <li class="list-group-item d-flex justify-content-between">
            <span>Total (GBP)</span>
            <strong>�<%=t.getTotalCost()%></strong>
          </li>
          <li class="list-group-item d-flex justify-content-between">
          <form action="${pageContext.request.contextPath}/ClearCart" method="post">
          <button type="submit" class="btn btn-primary btn-lg px-4 gap-3">Clear cart</button>
          </form>
          </li>
        </ul>
		
       
        <form class="card p-2" action = "${pageContext.request.contextPath}/PromoSubmit" method="post">
          <div class="input-group">
          	<% if(session.getAttribute("promo")!=null){%>
            <input type="text" class="form-control" value="<%=(String)session.getAttribute("promo")%>" placeholder="Promo code" name="promo">
            <%}else{ %>
            <input type="text" class="form-control"  placeholder="Promo code" name="promo">
            <%} %>
            <button type="submit" class="btn btn-secondary">Redeem</button>
          </div>
        </form>
      </div>

      <div class="col-md-7 col-lg-8">
        <h4 class="mb-3">Billing address</h4>
        
        <form name="billing" class="needs-validation" novalidate action = "${pageContext.request.contextPath}/ExecOrder" onsubmit="return validateForm()" method="post">
          <div class="row g-3">

            <div class="col-12">
              <label for="address" class="form-label">Address (HouseNumber)</label>
              <input type="text" class="form-control" name="address" placeholder="1234 Main St" required>
              <div class="invalid-feedback">
                Please enter your shipping address.
              </div>
            </div>

            <div class="col-12">
              <label for="address2" class="form-label">Address 2 <span class="text-muted"></span></label>
              <input type="text" class="form-control" name="address2" placeholder="Apartment or suite">
            </div>

            <div class="col-md-4">
              <label for="city" class="form-label">City</label>
			  <input type="text" class="form-control" name="city" placeholder="" required>
				<!-- <input> type="text" class="form-select" id="city" placeholder="" required</input> -->
              <div class="invalid-feedback">
                Please provide a valid city.
              </div>
            </div>

            <div class="col-md-3">
              <label for="postcode" class="form-label">Post code</label>
              <input type="text" class="form-control" name="postcode" placeholder="" required>
              <div class="invalid-feedback">
                Post code required.
              </div>
            </div>
          </div>

          <hr class="my-4">

        <!-- drop down menu-->

			<div class="drop-down menu">
			<label for="deliveryslot">Choose a delivery slot (extra �3.99 fee):</label>
				<select name="deliveryslot" >
					<option value="am" id="am">AM</option>
					<option value="pm" id="pm">PM</option>
				</select>
			</div>

      <div class="drop-down menu">
        <label for="returnslot"><br>Choose a return delivery slot: </br></label>
          <select name="returnslot" >
            <option value="am" id="am">AM</option>
            <option value="pm" id="pm">PM</option>
          </select>
        </div>
      

          <!-- <div class="form-check">
            <input type="checkbox" class="form-check-input" id="deliveryslot">
            <label class="form-check-label" for="deliveryslot">Please </label>
          </div> -->

          <!--return policy-->

          <!-- <div class="form-check">
            <input type="checkbox" class="form-check-input" id="save-info">
            <label class="form-check-label" for="save-info">Save this information for next time</label>
          </div> -->

          <hr class="my-4">

          <h4 class="mb-3">Payment</h4>

          <div class="my-3">
            <div class="form-check">
              <input id="credit" name="paymentMethod" type="radio" class="form-check-input" checked required>
              <label class="form-check-label" for="credit">Credit card</label>
            </div>
            <div class="form-check">
              <input id="debit" name="paymentMethod" type="radio" class="form-check-input" required>
              <label class="form-check-label" for="debit">Debit card</label>
            </div>
            <!-- <div class="form-check">
              <input id="paypal" name="paymentMethod" type="radio" class="form-check-input" required>
              <label class="form-check-label" for="paypal">PayPal</label>
            </div> -->
          </div>

          <div class="row gy-3">
            <div class="col-md-6">
              <label for="cc-name" class="form-label">Name on card</label>
              <input type="text" class="form-control" id="cc-name" placeholder="" required>
              <small class="text-muted">Full name as displayed on card</small>
              <div class="invalid-feedback">
                Name on card is required
              </div>
            </div>

            <div class="col-md-6">
              <label for="cc-number" class="form-label">Credit card number</label>
              <input type="text" class="form-control" id="cc-number" placeholder="" required>
              <div class="invalid-feedback">
                Credit card number is required
              </div>
            </div>

            <div class="col-md-3">
              <label for="cc-expiration" class="form-label">Expiration</label>
              <input type="text" class="form-control" id="cc-expiration" placeholder="" required>
              <div class="invalid-feedback">
                Expiration date required
              </div>
            </div>

            <div class="col-md-3">
              <label for="cc-cvv" class="form-label">CVV</label>
              <input type="text" class="form-control" id="cc-cvv" placeholder="" required>
              <div class="invalid-feedback">
                Security code required
              </div>
            </div>
          </div>

          <hr class="my-4">
			
          <button class="w-100 btn btn-primary btn-lg" type="submit">Confirm Order</button>
        </form>
      </div>
    </div>
  </main>

  <footer class="my-5 pt-5 text-muted text-center text-small">
    <p class="mb-1">&copy; 2021 RentATree</p>
    <!-- <ul class="list-inline">
      <li class="list-inline-item"><a href="#">Privacy</a></li>
      <li class="list-inline-item"><a href="#">Terms</a></li>
      <li class="list-inline-item"><a href="#">Support</a></li>
    </ul> -->
  </footer>
</div>


    <script src="/docs/5.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

      <script src="form-validation.js"></script>
  </body>

</html>
       
