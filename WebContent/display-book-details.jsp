<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="style/style.css">
        <script src="js/script.js"></script>
        <title>E-book</title>
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
        <article>
            <span class="headings">Book Details</span>
            <form name="quantity"method="post" action="AddToCart" onsubmit = "return validateQuantity()">
                <table class="book-table">
                    <c:set var="book" value="${book}" />
                    <tr>
                        <th>Name of book :</th>
                        <th colspan="3">
                            <c:out value="${book.title}"/>
                        </th>
                    </tr>
                    <tr>
                        <td>Author :</td>
                        <td>
                            <c:out value="${book.author}" />
                        </td>
                    </tr>
                    <tr>
                        <td>Price :</td>
                        <td>
                            <c:out value="${book.price}" />
                        </td>
                        <c:if test="${book.offer != 0}">
                        	<td>Offer</td>                        
                        	<td>
                            	<c:out value="${book.offer}" /> %
                        	</td>             
                        	<th>Offer Price</th>
                        	<th>Rs.${book.price - book.price * book.offer/100}</th>
                        </c:if>
                    </tr>
                    <tr>
                        <td>Stock</td>
                        <td>
                            <c:out value="${book.stock}" />
                        </td>
                        <td>Date of Launch : </td>
                        <td>
                            <c:out value="${book.dateOfLaunch}" />
                        </td>
                    </tr>
                    <tr>
                        <td>Category</td>
                        <td>
                            <c:out value="${book.category}" />
                        </td>
                        <td>Language</td>
                        <td>
                            <c:out value="${book.language}" />
                        </td>
                    </tr>
                    <tr>
                        <td>Publisher</td>
                        <td>
                            <c:out value="${book.publisher}" />
                        </td>
                        <td>Binding</td>
                        <td>
                            <c:out value="${book.binding}" />
                        </td>
                    </tr>
                    <tr>
                        <td>Description</td>
                        <td colspan ="4">
                            <c:out value="${book.description}"/>
                        </td>
                    </tr>
                    <tr>
                    <tr>
                    <tr>
                        <td colspan="2">Quantity :
                            <input type="number" id="numberOfBooks" name="numberOfBooks" min="0" max="${book.stock}" value="0"/>
                            <input type="hidden" name="bookId" value="${book.bookId}" />
                            <input type="hidden" name="selection" value="1" />
                            <input type="submit" class="button" value="Add to cart">
                        </td>
                    </tr>
                    <tr>
                    <td colspan="2"><p id="noQuantitySelected" class="validation-message"></p></td>
                    </tr>
                </table>
            </form>
        </article>
        <footer>
            <p>Copyright &copy; 2019</p>
        </footer>
    </body>
</html>