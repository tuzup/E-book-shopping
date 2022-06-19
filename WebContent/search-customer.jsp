<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="style/style.css">
    </head>
    <body>
        <script src="js\script.js"></script>
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
        <article>
            <form method="post" action="AddToCart">
                <div class="search-container">
                    <input type="text" id="search-bar" onkeyup="filterSearch()" placeholder="Search for books.."/>
                    <input class="search-button" type="submit" value="Add to cart">
                </div>
                <c:if test="${addToCartStatus}">
                    <p class="notification-message">Books added to Cart Successfully</p>
                </c:if>
                <c:if test="${bookSelectionStatus}">
                    <p class="validation-message">*No selections made to add to cart</p>
                </c:if>
                <table id="book-list">
                    <tr class="header">
                        <th class="title-heading">Title</th>
                        <th class="heading">Price</th>
                        <th class="heading">Offer</th>
                        <th class="heading">Offer Price</th>
                        <th class="heading">Quantity</th>
                        <th class="heading">Select</th>
                    </tr>
                    <c:forEach items = "${bookList}" var="book" >
                        <tr>
                            <td> <a href="DisplayBookDetails?bookId=${book.bookId}">${book.title}</a></td>
                            <td>
                            	Rs.
          						<fmt:formatNumber type="number" minFractionDigits="2"
                           		value="${book.price}" />
                           	</td>
                            <td>${book.offer} %</td>
                            <td>Rs.
          						<fmt:formatNumber type="number" minFractionDigits="2"
                           		value="${book.price - book.price * book.offer/100}" /> 
                           </td>
                            <td><input type="number" name="numberOfBooks" min="0" max="${book.stock}" value=0 /></td>
                            <td>
                                <input type="hidden" name="selection" value="0"><input type="checkbox" onclick="this.previousSibling.value=1-this.previousSibling.value">
                                <input type="hidden" name="bookId" value="${book.bookId}"/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </form>
        </article>
        <footer>
            <p>Copyright &copy; 2019</p>
        </footer>
    </body>
</html>