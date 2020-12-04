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
<header>
    <div>
        <h2><div><a href="../index.jsp">UGA Bookshop</a></div></h2>
        <section class="searchbox-container">
            <div class="searchbox">
                <input type="text" placeholder="Browse by author, by title..">
                <a href="../search.html"><button type="button"><img src="../image/search.svg"></button></a>
            </div>
        </section>
        <section>
            <nav>
                <ul>
                    <li>
                        BROWSE
                    </li>
                    <li>
                        <a href="cart.html"><img src="../image/shoppingcart.svg"></a>
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
            <li>2. Shipping</li>
            <li>3. Review Order</li>
            <li class="active">4. Confirmation</li>
        </ul>
    </section>
    <section>
        <h1>Order Complete (#1123443)</h1>
        <p>Thank you for placing your order for "The Evening and the Morning" for $29.99 %name%.</p>
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
