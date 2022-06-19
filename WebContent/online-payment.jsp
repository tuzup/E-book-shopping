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
        <article>
        	<a href = "payment-process.jsp"><img src="images/close.png" class="register-close"></a>
            <form autocomplete="off" name="payment" class="container" method = "Post" onsubmit="return paymentValidate()" action="OnlinePaymentDetails">
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
                <div class="row">
                    <label for="state">State</label>
                    <input class="box" type="text" id="state" name="state" placeholder="NY">
                    <label for="zip">Zip</label>
                    <input class="box" type="text" id="zip" name="zip" placeholder="10001">
                </div>
                <h3>Payment</h3>
                <label for="fname">Accepted Cards</label>
                <div class="icon-container">
                    <img class="paymentcard" src="images/paymentcards.jpg">
                </div>
                <label for="cname">Name on Card</label>
                <input class="box" type="text" id="cname" name="cardName" placeholder="John More Doe">
                <label for="ccnum">Credit card number</label>
                <input class="box" type="text" id="ccnum" name="cardNumber" placeholder="1111222233334444">
                <label for="expmonth">Exp Month</label>
                <input class="box" type="text" id="expmonth" name="expMonth" placeholder="06">
                <div class="row">
                    <label for="expyear">Exp Year</label>
                    <input class="box" type="text" id="expyear" name="expYear" placeholder="2018">
                    <label for="cvv">CVV</label>
                    <input class="box" type="text" id="cvv" name="cvv" placeholder="352">
                </div>
                <p Id = "validationMessage" class = "validateMessage"></p>
				<p Id = "validationFirstName" class = "validateMessage"></p>
				<p Id = "validationEmail" class = "validateMessage"></p>
				<p Id = "validationCity" class = "validateMessage"></p>
				<p Id = "validationState" class = "validateMessage"></p>
				<p Id = "validationZip" class = "validateMessage"></p>
				<p Id = "validationCardName" class = "validateMessage"></p>
				<p Id = "validationCardNumber" class = "validateMessage"></p>
				<p Id = "validationMonth" class = "validateMessage"></p>
				<p Id = "validationYear" class = "validateMessage"></p>
				<p Id = "validationMonthYear" class = "validateMessage"></p>
				<p Id = "validationCvv" class = "validateMessage"></p>
                <input type="submit" value="submit" class="btn">
            </form>
        </article>
	    <footer>
	    	<p>Copyright &copy; 2019</p>
	    </footer>
    </body>
</html>