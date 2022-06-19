<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
         <div class="headings">Cart</div>
         <br>
         <c:if test="${cartUpdate}">
            <p class="notification-message">Cart Updated</p>
         </c:if>
         <form action="UpdateCart" method="post">
            <table class="cart-table">
               <tr>
                  <th>Name</th>
                  <th>Quantity</th>
                  <th>Unit Price</th>
                  <th>Price</th>
                  <td><input type="submit" value="Update" class="button"></td>
               </tr>
               <c:forEach items="${cart.bookList}" var="book">
                  <tr>
                     <td>
                        <a href="DisplayBookDetails?bookId=${book.bookId}">
                           <c:out value="${book.title}" />
                        </a>
                        <input type="hidden" value="${book.bookId}" name="bookId">
                     </td>
                     <td>
                        <div class="quantity">
                        	
                           		<input type="number" oninput="cartUpdated();" name="numberOfBooks" min="0" max="${book.stock}"
                              		value="${cart.numberOfBooks[book.bookId]}"  />
                          	<c:if test = "${book.stock<cart.numberOfBooks[book.bookId]}">
                          	<script>cartUpdated();</script>
                          	<br> <span class="validation-message">* Only ${book.stock} Item left!! </span>
                            </c:if>
                        </div>
                     </td>
                     <td>
                        Rs.
                        <fmt:formatNumber type="number" minFractionDigits="2"
                           value="${book.price}" />
                     </td>
                     <td>
                     	 Rs.
                        <fmt:formatNumber type="number" minFractionDigits="2"
                           value="${book.price * cart.numberOfBooks[book.bookId]}" />
                     </td>
                     <td><a href="RemoveCart?bookId=${book.bookId}">Remove</a>
                  </tr>
               </c:forEach>
               <tr>
                  <td></td><td></td>
                  <td><b>Total</b></td>
                  <td>
                     <b>
                        Rs.
                        <fmt:formatNumber type="number"
                           minFractionDigits="2" value="${cart.totalPrice}" />
                     </b>
                  </td>
               </tr>
               <tr></tr>
               <tr>
                  <td></td>
                  <td></td>
                  <td></td>
                  <td><a href="payment-process.jsp" onclick="return proceedPayment()" class="button">Make Payment</a></td>
               </tr>
            </table>
         </form>
      </section>
      <footer>
         <p>Copyright &copy; 2019</p>
      </footer>
   </body>
</html>