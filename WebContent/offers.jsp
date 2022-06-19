<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Offers</title>
        <link rel="stylesheet" type="text/css" href="style/style.css">
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
            <span class="headings">
            Offers
            </span>
            <table class="offer-table">
                <tr>
                    <th>Name</th>
                    <th></th>
                    <th>Author</th>
                    <th>Price</th>
                    <th>Offer</th>
                    <th>Offer Price</th>
                </tr>
                <c:forEach items="${offer}" var="book">
                    <tr>
                        <td colspan="2">
                            <a href="DisplayBookDetails?bookId=${book.bookId}">
                                <c:out value="${book.title}" />
                            </a>
                        </td>
                        <td>
                            <c:out value="${book.author}" />
                        </td>
                        <td>
                            Rs
                            <fmt:formatNumber type="number" minFractionDigits="3"
                                value="${book.price}" />
                        </td>
                        <td>${book.offer}%</td>
                        <td>Rs.${book.price - book.price * book.offer/100}</td>
                    </tr>
                </c:forEach>
            </table>
        </section>
        <footer>
            <p>Copyright &copy; 2019</p>
        </footer>
    </body>
</html>