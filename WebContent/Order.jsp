<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <title>E-book Shop</title>
        <link rel="stylesheet" type="text/css" href="style/style.css">
        <script type="text/javascript"  src="js/script.js"></script>
    </head>
    <body>
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
        	<c:set var="previousOrderId"  value="0"/> 
            <p class = "payment-status">Your Orders</p>
            <table class="order-details">
                <c:forEach var="order" items="${orderList}">
                	<c:if test="${order.ordId==previousOrderId}">
                    	<tr>
                        	<td>${order.bookName}</td>
                        	<td>${order.quantity}</td>
                        </tr>
                        <tr>
                        </tr>
                    </c:if>
                	<c:if test="${order.ordId!=previousOrderId}">
                    	<c:set var="previousOrderId"  value="${order.ordId}"/> 
                    	<tr>
                        	<th><h3>ORD_${order.ordId}</h3> </th>
                        	<td><a href="OrderCancellation?ordId=${order.ordId}">Cancel Order</a></td>
                        </tr>
                        <tr>
                        	<th>Book Title</th>
                        	<th>Quantity</th>
                        	<th> Total : Rs.<fmt:formatNumber type="number" minFractionDigits="2"
                                value="${order.total}" /></th>
                        	
                        </tr>
                        <tr>
                        	<td>${order.bookName}</td>
                        	<td>${order.quantity}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </section>
        <footer>
            <p>Copyright &copy; 2019</p>
        </footer>
    </body>
</html>