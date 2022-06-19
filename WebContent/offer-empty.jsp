<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype HTML>
<html>
    <head>
        <title>Cart</title>
        <link rel="stylesheet" type="text/css" href="style/style.css">
    </head>
    <body>
        <script src="js/script.js"></script>
         <header>
         	<img src="images/logo.png" class="logo">
         	<span class="logo-text">E-book</span>
      	</header>
        <div class="topnav">
            <a class="home" href="ShowCustomerHomePage">Home</a>
            <a href="ShowCart">Cart</a>
            <a href="ShowOrders">Your Orders</a>
            <a href="ShowBookListCustomer">Search</a>
            <div class="logout">
                <a href="Logout">Log Out</a>
            </div>
        </div>
        <section>
            <div class="headings">
                Offer
            </div>
            <br>
            <h3>No active offers!!!</h3>
        </section>
        <footer>
            <p>Copyright &copy; 2019</p>
        </footer>
    </body>
</html>