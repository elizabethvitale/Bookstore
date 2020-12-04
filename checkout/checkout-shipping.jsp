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

HttpSession session = request.getSession(true);

String firstName = String.valueOf(session.getAttribute("firstName"));
String lastName = String.valueOf(session.getAttribute("lastName"));
String street = String.valueOf(session.getAttribute("street"));
String city = String.valueOf(session.getAttribute("city"));
String zipcode = String.valueOf(session.getAttribute("zip"));
String state = String.valueOf(session.getAttribute("state"));

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
            <li>1. Payment</li>
            <li class="active">2. Shipping</li>
            <li>3. Review Order</li>
            <li>4. Confirmation</li>
        </ul>
    </section>
    <section>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h4 class="mb-3">Shipping</h4>
                    <form action="/displayCart" method="get" class="needs-validation" novalidate="">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="firstName">First name</label>
                                <input type="text" class="form-control" value="<%=firstName%>" id="firstName" name="firstName" required>
                                <div class="invalid-feedback">
                                    Valid first name is required.
                                </div>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="lastName">Last name</label>
                                <input type="text" class="form-control" value="<%=lastName%>" id="lastName" name="lastName" required>
                                <div class="invalid-feedback">
                                    Valid last name is required.
                                </div>
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="address">Address</label>
                            <input type="text" value="<%=street%>" name="street" class="form-control" id="address" required>
                            <div class="invalid-feedback">
                                Please enter your shipping address.
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="address2">City</label>
                            <input type="text" value="<%=city%>" name="city" class="form-control" id="address2" required>
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
                                <input type="text" value="<%=zipcode%>" class="form-control" id="zip" name="zipcode">
                                <div class="invalid-feedback">
                                    Zip code required.
                                </div>
                            </div>
                        </div>
                        <hr class="mb-4">
                        <button class="btn btn-dark btn-lg btn-block" type="submit">Continue</button>
                    </form>
                </div>
            </div>
        </div>
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
