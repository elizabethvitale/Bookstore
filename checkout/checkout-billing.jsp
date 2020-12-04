<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@300;500;600&family=Peddana&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/checkout.css">
</head>
<body>
<%@ page session="false" %>
<%
HttpSession httpSession = request.getSession();
	String cardNumber = String.valueOf(httpSession.getAttribute("cardNumber"));
	String cardNumber2 = String.valueOf(httpSession.getAttribute("cardNumber2"));
	String cardNumber3 = String.valueOf(httpSession.getAttribute("cardNumber3"));

String firstName = "";
String lastName = "";
String cardNum = "";
String cardType = "";
String expDate= "";
String street = "";
String city = "";
String state = "";
String zipcode = "";
String cardType2="";
String expDate2="";
String cardType3="";
String expDate3="";

firstName = String.valueOf(httpSession.getAttribute("firstName"));
lastName = String.valueOf(httpSession.getAttribute("lastName"));
city = String.valueOf(httpSession.getAttribute("city"));
state = String.valueOf(httpSession.getAttribute("state"));
zipcode = String.valueOf(httpSession.getAttribute("zip"));
street = String.valueOf(httpSession.getAttribute("street"));
expDate = String.valueOf(httpSession.getAttribute("expirationDate"));
cardType = String.valueOf(httpSession.getAttribute("cardType"));
expDate2 = String.valueOf(httpSession.getAttribute("expirationDate2"));
cardType2 = String.valueOf(httpSession.getAttribute("cardType2"));
expDate3 = String.valueOf(httpSession.getAttribute("expirationDate3"));
cardType3 = String.valueOf(httpSession.getAttribute("cardType3"));

%>
<header>
    <div>
        <h2><div><a href="../index.jsp">UGA Bookshop</a></div></h2>
        <section class="searchbox-container">
            <div class="searchbox">
                <input type="text" placeholder="Browse by author, by title..">
                <a href="../search.jsp"><button type="button"><img src="../image/search.svg"></button></a>
            </div>
        </section>
        <section>
            <nav>
                <ul>
                    <li>
                        BROWSE
                    </li>
                    <li>
                        <a href="cart.jsp"><img src="../image/shoppingcart.svg"></a>
                    </li>
                    <li style='position: relative;'>
                        <img id="auth-dropdown-toggle" src="../image/account.svg">
                          <ul class='auth-dropdown'>
                            <%
                            HttpSession session=request.getSession(false);
                            if(session!=null){ %>
                              <li>
                                <a href="/user/editprofile.jsp">Edit Profile</a>
                              </li>
                              <li>
                                <a href="/user/logout.jsp">Logout</a>
                              </li>
                            <%} else { %>
                              <li>
                                <a href="/user/login.jsp">Login</a>
                              </li>
                              <li>
                                <a href="/user/register.jsp">Register</a>
                              </li>
                            <%}%>
                          </ul>
                    </li>
                </ul>
            </nav>
        </section>
    </div>
</header>
<main>
    <section class="tracker">
        <ul>
            <li class="active">1. Payment</li>
            <li>2. Shipping</li>
            <li>3. Review Order</li>
            <li>4. Confirmation</li>
        </ul>
    </section>
    <section class="storedMethods">
        <h4>Stored Payment Methods</h4>
	<form action="/setBilling" class="needs-validation" novalidate="" method="POST">
        <div class="method">
            <span style="flex-grow: 1;"></span>
		<select class="custom-select d-block w-100" name="ordercard" required>
		<option value="">Please select pay method or enter one:</option>
<% if (!(cardNumber.equalsIgnoreCase("null"))) { %>
		<option value=<%=cardNumber%>><%out.print(expDate);%> <%out.print(cardType);%></option>
<%
} %>
<% if (!(cardNumber2.equalsIgnoreCase("null"))) { %>
		<option value=<%=cardNumber2%>><%out.print(expDate2);%> <%out.print(cardType2);%></option>
<%
} %>
<% if (!(cardNumber3.equalsIgnoreCase("null"))) { %>
		<option value=<%=cardNumber3%>><%out.print(expDate3);%> <%out.print(cardType3);%></option>
<%
} %>
		</select>
        </div>
    </section>
    <section>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <h4 class="mb-3">Billing</h4>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="firstName">First name</label>
                                <input type="text" class="form-control" name="firstName" id="firstName" placeholder="" value="<% out.print(firstName);%>" required>
                                <div class="invalid-feedback">
                                    Valid first name is required.
                                </div>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="lastName">Last name</label>
                                <input type="text" class="form-control" name="lastName" id="lastName" placeholder="" value="<% out.print(lastName);%>" required>
                                <div class="invalid-feedback">
                                    Valid last name is required.
                                </div>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="address">Address</label>
                            <input type="text" class="form-control" value="<% out.print(street);%>" name="street" id="address" placeholder="1234 Main St" required>
                            <div class="invalid-feedback">
                                Please enter your shipping address.
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="address2">City</label>
                            <input type="text" class="form-control" name="city" value="<% out.print(city);%>" id="address2" required>
                        </div>

                        <div class="row">
                            <div class="col-md-4 mb-3">
                                <label for="state">State</label>
				<input type="text" class="form-control" id="state" value="<% out.print(state);%>" name="state" required>
                                <div class="invalid-feedback">
                                    Please provide a valid state.
                                </div>
                            </div>
                            <div class="col-md-3 mb-3">
                                <label for="zip">Zip</label>
                                <input type="text" class="form-control" id="zip" value="<% out.print(zipcode);%>" name="zipcode" required>
                                <div class="invalid-feedback">
                                    Zip code required.
                                </div>
                            </div>
                        </div>
                        <hr class="mb-4">
                        <button class="btn btn-dark btn-lg btn-block" type="submit">Continue</button>

                </div>
		<div class="col-md-6">
                    <h4 class="mb-3">Payment</h4>
                    <div class="d-block my-3">
                        <div class="custom-control custom-radio">
                            <input id="credit" name="paymentMethod" type="radio" class="custom-control-input" checked="" required="">
                            <label class="custom-control-label" for="credit">Credit card</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input id="debit" name="paymentMethod" type="radio" class="custom-control-input" required="">
                            <label class="custom-control-label" for="debit">Debit card</label>
                        </div>
                        <div class="custom-control custom-radio">
                            <input id="paypal" name="paymentMethod" type="radio" class="custom-control-input" required="">
                            <label class="custom-control-label" for="paypal">Paypal</label>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="cc-name">Name on card</label>
                            <input type="text" class="form-control" id="cc-name" name="ccname" placeholder="" required>
                            <small class="text-muted">Full name as displayed on card</small>
                            <div class="invalid-feedback">
                                Name on card is required
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="cc-number">Credit card number</label>
                            <input type="number" name="ccnumber" minlength="16" maxlength="16" class="form-control" id="cc-number" placeholder="" required>
                            <div class="invalid-feedback">
                                Credit card number is required
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="cc-expirationmonth">Expiration Month</label>
                            	<select class="form-control" id="cc-expmonth" name="ccexpmonth">
					<option value="" disabled>Please select exp. month: </option>
					<option value="01">01</option>
					<option value="02">02</option>
					<option value="03">03</option>
					<option value="04">04</option>
					<option value="05">05</option>
					<option value="06">06</option>
					<option value="07">07</option>
					<option value="08">08</option>
					<option value="09">09</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
				</select>
                            <div class="invalid-feedback">
                                Expiration date required
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="cc-expirationyear">Expiration Year</label>
                            	<select class="form-control" id="cc-expirationyear" name="ccexpyear" >
					<option value="" disabled>Please select exp. year: </option>
					<option value="2021">2021</option>
					<option value="2022">2022</option>
					<option value="2023">2023</option>
					<option value="2024">2024</option>
					<option value="2025">2025</option>
				</select>
                            <div class="invalid-feedback">
                                Expiration date required
                            </div>
                        </div>

                    </div>
		    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="cc-type">Card Type</label>
                            	<select class="form-control" id="cc-type" name="cctype">
					<option value="" disabled>Please select card type: </option>
					<option value="Visa">Visa</option>
					<option value="Mastercard">Mastercard</option>
					<option value="American Express">American Express</option>
					<option value="Discovery">Discovery</option>
				</select>
                            <div class="invalid-feedback">
                                Card type required
                            </div>
                        </div>
		    </div>
                </div>

            </div>
        </div>
                    </form>
    </section>
</main>
<footer>
    <div>
        <section style="flex-grow: 2;align-items: baseline;flex-direction: column;">
            <ul>
                <li>
                    ABOUT
                </li>
                <li>
                    CONTACT
                </li>
                <li>
                    TERMS OF USE
                </li>
                <li>
                    PRIVACY NOTICE
                </li>
                <li>
                    BOOKSHOP FOR AUTHORS
                </li>
            </ul>
            <div>
                <button class="button" style="margin-right: 10px;">LOGIN</button>
                <button class="button">REGISTER</button>
            </div>
        </section>
        <section style="flex-grow: 1;align-items: baseline;">
            <div style="display: flex;flex-direction: column;height: 100%;">
                <div style="flex-grow: 2;justify-content: center;">
                    <h2><div>UGA Bookshop</div></h2>
                    <h4>SUPPORTING LOCAL BOOKSTORE</h4>
                </div>
                <div class="searchbox-container">
                    <div class="searchbox">
                        <input type="text" placeholder="Browse store..">
                        <button type="button" style="background-color: #b3011c;"><img src="../image/search.svg"></button>
                    </div>
                </div>
        </section>
    </div>
</footer>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/main.js"></script>
<script type="text/javascript" src="http://livejs.com/live.js"></script>
</body>
</html>
