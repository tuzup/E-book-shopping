<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Book E Shopping</title>
        <link rel="stylesheet" type="text/css" href="style/style.css">
    </head>
    <body>
        <script src="js/script.js"></script>
        <header>
         	<img src="images/logo.png" class="logo">
         	<span class="logo-text">E-book</span>
      	</header>
        <section class="register-container">
            <a href = "index.jsp"><img src="images/close.png" class="register-close"></a>
            <h2>User Registration</h2>
            <c:if test="${isExist}">
                <p class="validation-message">*EmailId already Exists</p>
            </c:if>
            <c:if test="${correct}">
                <p class="alert1">Your Details are Submitted Successfully</p>
            </c:if>
            <p id="validationMessage" class="validation-message"></p>
            <p id="validationFirstName" class="validation-message"></p>
            <p id="validationLastName" class="validation-message"></p>
            <p id="validationAge" class="validation-message"></p>
            <p id="validationContactNumber" class="validation-message"></p>
            <p id="validationEmail" class="validation-message"></p>
            <p id="errorMessage" class="validation-message"></p>
            <form autocomplete="off" name="userForm" method="post" onsubmit="return validateRegister()" action="UserRegistration" class="registration">
                <label for="firstname">First Name :</label><br> 
                <input type="text" name="firstName" id="firstname" placeholder="Enter First Name"> <br>
                <label for="lastname">Last Name :</label><br>
                <input type="text" name="lastName" id="lastname" placeholder="Enter Last Name"> <br>
                <label for="age">Age :</label> 
                <input type="number" name="age" id="age" min=0 width="10px"><br><br>
                Gender : 
                <input type="radio" name="gender" id="male" value="yes" checked>
                Male
                <input type="radio" name="gender" id="female" value="no">
                Female<br><br>
                <label for="contactNumber">Contact Number :</label> 
                <input type="text" name="contactNumber" id="contactNumber" placeholder="Enter Contact Number"><br> 
                <label for="emailId">E-mail Id :</label><br>
                <input type="text" name="email" id="emailId" placeholder="Enter Email Id"> <br>
                <label for="password">Password :</label> <br>
                <input type="password" name="password" id="password" placeholder="Enter Password"> <br>
                <input type="submit" value="Register" class="register-button">
            </form>
        </section>
        <footer>
            <p>Copyright &copy; 2019</p>
        </footer>
    </body>
</html>