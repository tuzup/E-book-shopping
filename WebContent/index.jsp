<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype HTML>
<html>
   <head>
      <title>E-book Shop</title>
      <link rel="stylesheet" type="text/css" href="style/style.css">
   </head>
   <body class="login-background">
      <script src="js\script.js"></script>
      <header>
         <img src="images/logo.png" width=50px height=50px class="logo">
         <span class="logo-text">E-book</span>
      </header>
      <section>
         <div class="row">
            <div class="column">
               <span class="intro-text">
                  <span class="headings">Welcome
                  to E-book Shopping</span>     
               </span>
            </div>
            <div class="column">
               <form method="post" action="LoginAuthentication" class="login-container" name="loginForm" onsubmit="return validateLogin()">
                  <h1>Login</h1>
                  <c:if test="${loginFail}">
                     <p class="validation-message">*Email Id/Password is Invalid!!</p>
                  </c:if>
                  <c:if test="${registerSuccess}">
                     <p class="notification-message">Your Details are Submitted Successfully!!</p>
                  </c:if>
                  <c:if test="${loginStatus}">
                  	<p class="validation-message">*Please Login First!!!!</p>
                  </c:if>
                  <p id="validationMessage" class="validation-message"></p>
                  <label for="email"><b>Email</b></label> <input type="text"
                     placeholder="Enter Email" name="email" id="email"> <label
                     for="password"><b>Password</b></label> <input type="password"
                     placeholder="Enter Password" name="password" id="password">
                  <button type="submit" class="login-button">Login</button>
                  <br> <a href="user-registration.jsp" class="register-link">New User ? Register</a>
               </form>
            </div>
         </div>
      </section>
      <footer>
         <p>Copyright &copy; 2019</p>
      </footer>
   </body>
</html>