<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.time.format.DateTimeFormatter" %> 
<%@ page import=" java.time.LocalDateTime" %> 
<%@ page import="models.*" %> 
<!DOCTYPE html> 
<html lang="en">

    <head>
        <title>Invoice page</title>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js /bootstrap.bundle.min.js" integrity="sha384 -gtEjrD/ SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
        <link href ="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity ="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin ="anonymous">
        <link rel="stylesheet" href="css/style.css">
        <link rel="shortcut icon" type="image/jpg" href="favicon3.png"/>
		<script>
			
		</script>
        <style>
            .invoice-box {
                max-width: 800px;
                margin: auto;
                padding: 30px;
                border: 1px solid #eee;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);
                font-size: 16px;
                line-height: 24px;
                font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;
                color: #555;
            }

            .invoice-box table {
                width: 100%;
                line-height: inherit;
                text-align: left;
            }

            .invoice-box table td {
                padding: 5px;
                vertical-align: top;
            }

            .invoice-box table tr td:nth-child(2) {
                text-align: right;
            }

            .invoice-box table tr.top table td {
                padding-bottom: 20px;
            }

            .invoice-box table tr.top table td.title {
                font-size: 45px;
                line-height: 45px;
                color: #333;
            }

            .invoice-box table tr.information table td {
                padding-bottom: 40px;
            }

            .invoice-box table tr.heading td {
                background: #eee;
                border-bottom: 1px solid #ddd;
                font-weight: bold;
            }

            .invoice-box table tr.details td {
                padding-bottom: 20px;
            }

            .invoice-box table tr.item td {
                border-bottom: 1px solid #eee;
            }

            .invoice-box table tr.item.last td {
                border-bottom: none;
            }

            .invoice-box table tr.total td:nth-child(2) {
                border-top: 2px solid #eee;
                font-weight: bold;
            }

            @media only screen and (max-width: 600px) {
                .invoice-box table tr.top table td {
                    width: 100%;
                    display: block;
                    text-align: center;
                }

                .invoice-box table tr.information table td {
                    width: 100%;
                    display: block;
                    text-align: center;
                }
            }

            /** RTL **/
            .invoice-box.rtl {
                direction: rtl;
                font-family: Tahoma, 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;
            }

            .invoice-box.rtl table {
                text-align: right;
            }

            .invoice-box.rtl table tr td:nth-child(2) {
                text-align: left;
            }
        </style>

    </head>

    <body>
    <%@include file="navbar.jsp" %>
    
        <div class="invoice-box">
        <button class="" onclick="window.print()">Print Page</button>
            <table cellpadding="0" cellspacing="0">
                <tr class="top">
                    <td colspan="2">
                        <table>
                            <tr>
                                <td class="title">
                                    <img src="static/RentATree-logos.jpeg" style="width:70%; max-width: 300px" />
                                </td>

                                <td>
                                   	<br />
                                   	<% DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                                    	LocalDateTime now = LocalDateTime.now();   %>
                                    Created: <%=dtf.format(now) %><br />
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>

                <tr class="information">
                    <td colspan="2">
                        <table>
                            <tr>

                                <td>
                                    <%=(String)session.getAttribute("username") %>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>


                <tr class="heading">
                    <td>Item Type</td>
					<td>Item Material</td>
                    <td>Price</td>
                </tr>
				<%ArrayList<Product> b = (ArrayList<Product>)session.getAttribute("invoiceBasket"); 
				for(Product p: b){%>
                <tr class="item">
                    <td><%=p.getType() %></td>
					<td><%=p.getMaterial() %></td>
                    <td>£<%=p.getPrice() %></td>
                </tr>

                <%} %>
            </table>
        </div>
        
      
    </body>

</html>