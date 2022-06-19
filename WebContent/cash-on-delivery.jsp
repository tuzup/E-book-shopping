<!DOCTYPE html>
<html>
    <head>
        <title>E-book Shop</title>
        <link rel="stylesheet" type="text/css" href="style/style.css">
        <script type="text/javascript" src="js/script.js"></script>
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
        	<a href = "payment-process.jsp"><img src="images/close.png" class="register-close"></a>
            <form autocomplete="off" class="container" name="cashOnDelivery" method="Post" onsubmit="return addressValidate()" action = "CashOnDeliveryDetails">
                <span class="price"> Total  <b> <%
                              int price = (Integer) session.getAttribute("cartTotal");
                              out.print(price);
                              %>
				</b> </span>   
                <h3>Billing Address</h3>
                <label for="fname"> Full Name</label> 
                <input class="box" type="text" id="fname" name="firstname" placeholder="John M. Doe"> 
                <label for="email"> Email</label>
                <input class="box" type="text" id="email" name="email" placeholder="john@example.com">
                <label for="adr"> Address</label> 
                <input class="box" type="text" id="adr" name="address" placeholder="542 W. 15th Street">
                <label for="city"> City</label> 
                <input class="box" type="text" id="city" name="city" placeholder="New York">
                <label for="state">State</label> 
                <input class="box" type="text" id="state" name="state" placeholder="NY">
                <label for="zip">Zip</label> 
                <input class="box" type="text" id="zip" name="zip" placeholder="10001">
                <p Id="validationMessage" class="validateMessage"></p>
				<p Id="validationName" class="validateMessage"></p>
				<p Id="validationEmail" class="validateMessage"></p>
				<p Id="validationCity" class="validateMessage"></p>
				<p Id="validationState" class="validateMessage"></p>
				<p Id="validationZip" class="validateMessage"></p>

                <input type="submit" value="submit" class="btn">
            </form>
        </article>
        <footer>
            <p>Copyright &copy; 2019</p>
        </footer>
    </body>
</html>