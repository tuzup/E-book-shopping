<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
			<title>
			Book E Shopping
			</title>
			<link rel="stylesheet" type="text/css" href="style/style.css">
			<script src="js/script.js"></script>
	</head>
      <body>
        	<header>
         		<img src="images/logo.png" class="logo">
         		<span class="logo-text">E-book</span>
      		</header>		  
      	  <div class="topnav">
		  <a class="home" href="BookAdmin">Home</a>
		  <a href="ShowAddBookAdmin">Add</a>
		  <a href="Logout" class = "logout">Log Out</a>
		 </div>
		 <article>
		 <table id="book-list">
				  <tr class="header">
					<th class="title-heading">Title</th>
					
					<th class="heading">Price</th>
					<th class="heading">Binding</th>
					<th class="heading">Offer</th>
					<th class="heading">Stock</th>
					<th class="heading">Edit</th>
					
					
				  </tr>
				  <c:forEach items = "${bookListAdmin}" var="book" >
				  <tr>
					<td> ${book.title}</td>
					
					<td> ${book.price}</td>
					<td> ${book.binding}</td>
					<td> ${book.offer}</td>
					<td>${book.stock}</td>
					<td><a href="ShowEditBookDetails?bookId=${book.bookId}">Edit</a></td>
				  </tr>
				  </c:forEach>
				</table>
				</article>
        <footer>
            <p>Copyright &copy; 2019</p>
        </footer>
       </body>
   </html>
