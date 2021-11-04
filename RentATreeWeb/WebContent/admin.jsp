<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html> 
<html lang="en">

<!--Admin-->

<head>
	<title>Admin</title>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js /bootstrap.bundle.min.js" integrity="sha384 -gtEjrD/ SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
	<link href ="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity ="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin ="anonymous">
	<link rel="stylesheet" href="style.css">
  <link rel="shortcut icon" type="image/jpg" href="favicon3.png"/>
</head>

<body>
    
    <section class="py-5 text-center container">
        <div class="row py-lg-5">
          <div class="col-lg-6 col-md-8 mx-auto">
            <img class="mb-4" src="static/RentATree-logos.jpeg" alt="" width="75" height="62">
            <h1 class="fw-light">Hello Admin</h1>
            <p class="lead text-muted"><b>Administrator changes</b></p>
            <label for="customerid" class="form-label">Please enter the customer username</label>
            <p>
            <form action="${pageContext.request.contextPath}/Hit" method="post" >
            <input type="text" class="form-control" name="customerid" placeholder="CustomerID" required>
              <button type="submit" class="btn btn-primary btn-lg px-4 gap-3">Hit</button>
             </form>
             <form action="${pageContext.request.contextPath}/Miss" method="post">
             <input type="text" class="form-control" name="customerid" placeholder="CustomerID" required>
              <button type="submit" class="btn btn-primary btn-lg px-4 gap-3">Miss</button>
              </form>
            </p>
			
            <p class="lead text-muted"><b>Add a new tree</b></p>
				<form action="${pageContext.request.contextPath}/InsertTreeType" method="post">
                <label for="itemtype" class="form-label">Tree type:</label>
                    <input type="text" class="form-control" name="type" placeholder="Tree type" required>

                <label for="itemmaterial" class="form-label">Tree material:</label>
                    <input type="text" class="form-control" name="material" placeholder="Tree material" required>

                <label for="itemsupplier" class="form-label">Supplier Name:</label>
                    <input type="text" class="form-control" name="sname" placeholder= "Supplier Name" required>

                <label for="itemheight" class="form-label">Height:</label>
                    <input type="text" class="form-control" name="height" placeholder="Height" required>

                <label for="itemprice" class="form-label">Price:</label>
                    <input type="text" class="form-control" name="price" placeholder="Price i.e 34.90" required>


              <p>
               <button type="submit" class="btn btn-primary btn-lg px-4 gap-3">Add</button>
            </p>
            </form>
            <p class="lead text-muted"><b>Delete a tree</b></p>
                <label for="treeid" class="form-label">Tree ID:</label>
                    <input type="text" class="form-control" id="treeid" placeholder="Tree ID" required>

              <p>
                <a href="#" class="btn btn-primary my-2">Remove</a>
            </p>


            <!--Add a new tree type-->
            <p class="lead text-muted"><b>Add a new tree type:</b></p>

                <label for="itemtype" class="form-label">Tree type:</label>
                    <input type="text" class="form-control" id="itemtype" placeholder="Tree type" required>

                <label for="itemmaterial" class="form-label">Tree material:</label>
                    <input type="text" class="form-control" id="itemmaterial" placeholder="Tree material" required>

                <label for="itemdescription" class="form-label">Description:</label>
                    <input type="text" class="form-control" id="itemdescription" placeholder="Description" required>

              <p>
                <a href="#" class="btn btn-primary my-2">Add</a>
            </p>

            <p class="lead text-muted"><b>Delete a tree type</b></p>
                <label for="treetype" class="form-label">Tree type:</label>
                    <input type="text" class="form-control" id="treetype" placeholder="Tree type" required>

              <p>
                <a href="#" class="btn btn-primary my-2">Remove</a>
            </p>

            <!-- Current stock
            <p class="lead text-muted"><b>Update the current stock</b></p>
            <li class="list-group-item d-flex justify-content-between lh-sm">

                <div>
    
                  <h6 class="my-0">Product name</h6>
                  <small class="text-muted">Brief description</small>
                  <a href="url"><h8>Delete</h8></a>
                </div>
    
                <span class="text-muted">$12</span>
              </li>
              
              <li class="list-group-item d-flex justify-content-between lh-sm">
                <div>
                  <h6 class="my-0">Second product</h6>
                  <small class="text-muted">Brief description</small>
                  <a href="url"><h8>Delete</h8></a>
                </div>
                <span class="text-muted">$8</span>
              </li> -->


          </div>
        </div>

      </section>

</body>

</html>