<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!doctype HTML>
<html>
   <head>
      <title>E-book Shop</title>
      <link rel="stylesheet" type="text/css" href="style/style.css">
      <script type="text/javascript" src="js/script.js"></script>
   </head>
   <body>
      <header>
		<img src="images/logo.png" class="logo">
		<span class="logo-text">E-book</span>
	</header>
      <div class="topnav">
         <a class="home" href="ShowCustomerHomePage">Home</a> <a
            href="ShowCart">Cart</a> <a href="ShowOrders">Your Orders</a> <a
            href="ShowBookListCustomer">Search</a>
         <div class="logout">
            <a href="Logout">Log Out</a>
         </div>
      </div>
      <h1 class="payment-status">Payment Status</h1>
      <p class="transaction-message">Your Order Placed Successfully.</p>
      <table class="order-details">
         <c:forEach var="order" items="${orderInfo}">
            <tr>
               <td>ORD_${order.ordId}</td>
               <td>${order.bookName}</td>
               <td>${order.quantity}</td>
               <td>${order.bookPrice}</td>
            </tr>
         </c:forEach>
      </table>
      <table class="order-details">
         <tr>
            <td><b>Delivery Address:</b></td>
            <td>
               <c:forEach begin="0" end="0" var="order"
                  items="${orderInfo}">
                  ${order.name} ,
                  ${order.address} ,
                  ${order.state} ,
                  ${order.zip}
               </c:forEach>
            </td>
         </tr>
         <tr>
            <td><b>Total:</b></td>
            <td>
               <%
                  int price = (Integer) session.getAttribute("cartTotal");
                  out.print(price);
                  %>
            </td>
         </tr>
      </table>
      <footer>
         <p>Copyright &copy; 2019</p>
      </footer>
   </body>
</html>
