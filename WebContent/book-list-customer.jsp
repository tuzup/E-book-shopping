<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype HTML>
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
         	<div class="new-arrivals">
       			<p >New Arrivals</p>
	          
                  <marquee>
	             	 <c:forEach items="${newArrivals}" var="book">
						 <a href="DisplayBookDetails?bookId=${book.bookId}">${book.title}
							&emsp;&emsp;&emsp;
						 </a>
	             	</c:forEach>
             	</marquee>
             	
            </div>
            
            <div class="offers">
       			<a href="ShowOffer"><img id="offer" src="images/offer.jpg"></a>
            </div>     
          	<span class="headings">Most Purchased Books</span><br><br>
            <table id="book-list-homepage">
                  <tr>
					<th class="heading">Title</th>
					
					<th class="heading">Author</th>
					
					<th class="heading">Select</th>
				  </tr>
                  <c:forEach items="${mostPurchased}" var="book">
                  	<tr class="header">
                    	<td>
                             ${book.title}
                        </td>
                        <td>
                            ${book.author}
                        </td>
                        <td>
                              <a href="DisplayBookDetails?bookId=${book.bookId}">Book Details</a>
                        </td>
                    </tr>
                   </c:forEach>             
            </table>
            <br><br>
			<span class="headings">Books From Most Viewed Category : ${mostViewedBooks[1].category} </span>
            <br><br>
            <table id="book-list-homepage">
                  <tr >
					<th class="heading">Title</th>
					
					<th class="heading">Author</th>
					
					<th class="heading">Select</th>
				  </tr>
                  	<c:forEach items="${mostViewedBooks}" var="book">
                         <tr class="header">
                         <td>
                             ${book.title}
                        </td>
                        <td>
                            ${book.author}
                        </td>
                        <td>
                              <a href="DisplayBookDetails?bookId=${book.bookId}">Book Details </a>
                        </td>
                         </tr>
                    </c:forEach>    
            </table>
			
            </section>
            <footer>
                  <p>Copyright &copy; 2019</p>
            </footer>
      </body>
</html>