<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>


<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="style/style.css">
		<title>E-book</title>
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
		
		<section>
			<a href = "BookAdmin"><img src="images/close.png" class="register-close"></a>
			<h1 class="headings">Enter Details of New Book</h1>
			<form method="post" name="addBook" action="AddNewBookAdmin" onsubmit="return validateAddBook()">
			
				<table class="cart-table">
					<tr>
						<td>Name of book :</td>
						<td><input id="title" type="text" name="title"></td>
					</tr>
					<tr>
						<td>Price :</td>
						<td><input id="price" type="text" name="price"></td>
					</tr>
					<tr>
						<td>Date of Launch : </td>
						<td><input id="dateOfLaunch" type="text" name="dateOfLaunch"></td>
					</tr>
					<tr>
						<td>Author :</td>
						<td><input id="author" type="text" name="author"></td>
					</tr>
					<tr>
						<td>Description:</td>
						<td><textarea name="description" rows="10"
							cols="60" id="description" ></textarea></td>
					</tr>
					<tr>
						<td>Category:</td>
						<td><input type="text" name="category" id="category" ></td>
					</tr>
					<tr>
						<td>Offer:</td>
						<td><input id="offers" type="text" name="offers"></td>
					</tr>
					<tr>
						<td>Stock:</td>
						<td><input type="text" id="stock" name="stock"></td>
					</tr>
					<tr>
						<td>Language:</td>
						<td><input type="text" id="language" name="Language"></td>
					</tr>
					<tr>
						<td>Publisher:</td>
						<td><input type="text" name="publisher" id="publisher" ></td>
					</tr>
					<tr>
						<td>Binding:</td>
						<td><input type="text" name="binding" id="binding"></td>
					</tr>
				</table>
				<input class="button" type="submit" name="submit" value="Submit">
			</form>
		</section>
		<footer>
         <p>Copyright &copy; 2019</p>
      </footer>
	</body>
</html>