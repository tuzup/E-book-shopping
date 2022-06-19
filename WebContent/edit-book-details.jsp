<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Book E Shopping</title>
<link rel="stylesheet" type="text/css" href="style/style.css">
<script src="js/script.js"></script>
</head>
<body>
	  <header>
         <img src="images/logo.png" class="logo">
         <span class="logo-text">E-book</span>
      </header>
	<div class="topnav">
		<a class="home" href="BookAdmin">Home</a> <a href="ShowAddBookAdmin">Add</a> 
		<a href="Logout" class="logout">LogOut</a>
	</div>
	<section>
		<a href = "BookAdmin"><img src="images/close.png" class="register-close"></a>
		<form name="editBook" method="post"
			onsubmit="return validateEditBook()" action="EditBookDetails">
			<h2>Edit Book Details</h2>
			<table class="cart-table">
				<tr>
					<td><label for="title">Name of book :</label></td>
					<td><input type="text" name="title" id="title"
						value="${book.title}"></td>
				</tr>
				<tr>
					<td><label for="price">Price :</label></td>
					<td><input type="text" name="price" id="price"
						value="${book.price}"></td>
				</tr>
				<tr>
					<td><label for="dateOfLaunch">Date of Launch :</label></td>
					<td><input type="text" name="dateOfLaunch" id="dateOfLaunch"
						value="${book.dateOfLaunch}"></td>
				</tr>
				<tr>
					<td><label for="author">Author :</label></td>
					<td><input type="text" name="author" id="author"
						value="${book.author}"></td>
				</tr>
				<tr>
					<td><label for="description">Description:</label></td>
					<td><textarea name="description" id="description" rows="10"
							cols="60">${book.description}</textarea></td>
				</tr>
				<tr>
					<td><label for="category">Category:</label></td>
					<td><input type="text" name="category" id="category"
						value="${book.category}"></td>
				</tr>
				<tr>
					<td><label for="offers">Offer:</label></td>
					<td><input type="text" name="offers" id="offers"
						value="${book.offer}"></td>
				</tr>
				<tr>
					<td><label for="stock">Stock:</label></td>
					<td><input type="text" name="stock" id="stock"
						value="${book.stock}"></td>
				</tr>
				<tr>
					<td><label for="language">Language:</label></td>
					<td><input type="text" name="Language" id="language"
						value="${book.language}"></td>
				</tr>
				<tr>
					<td><label for="publisher">Publisher:</label></td>
					<td><input type="text" name="publisher" id="publisher"
						value="${book.publisher}"></td>
				</tr>
				<tr>
					<td><label for="binding">Binding:</label></td>
					<td><input type="text" name="binding" id="binding"
						value="${book.binding}"></td>
				</tr>
			</table>
			<input type="hidden" name="bookId" value="${book.bookId}"> <input
				type="submit" name="submit" value="Edit" class="button">
		</form>
	</section>
</body>
<footer>
	<p>Copyright &copy; 2019</p>
</footer>
</html>