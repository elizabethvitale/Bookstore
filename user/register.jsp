<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@300;500;600&family=Peddana&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/auth.css">
  </head>
  <body>
<%@ page session="false" %>
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
                <a href="../checkout/cart.html"><img src="../image/shoppingcartblack.svg"></a>
              </li>
              <li style='position: relative;'>
                <img id="auth-dropdown-toggle" src="../image/accountblack.svg">
                <ul class='auth-dropdown'>
                  <li>
                    <a href="login.jsp">Login</a>
                  </li>
                  <li>
                    <a href="register.jsp">Register</a>
                  </li>
                  <li>
                    <a href="editprofile.jsp">Edit Profile</a>
                  </li>
                  <li>
                    <a href="logout.jsp">Logout</a>
                  </li>
                </ul>
              </li>
            </ul>
          </nav>
        </section>
      </div>
    </header>
    <main>
      <section id='authBox'>
        <h1>New Customer</h1>
        <form action="/register" method="post">
          <div>
            <label>First Name*</label>
            <br>
            <input type="text" id="fname" required>
          </div>
          <br>
	  <div>
	    <label>Last Name*</label>
	<br>
	<input type="text" id="lname" required>
	</div>
	<br>
          <div>
            <label>Phone number*</label>
            <br>
            <input type="text" id="number" required>
          </div>
          <br>
          <div>
            <label>Email Address*</label>
            <br>
            <input type="text" id="email" required>
          </div>
          <br>
          <div>
            <label>Password*</label>
            <br>
            <input type="text" id="password" required>
          </div>
          <br>
	  <h3>Shipping Information</h3>
          <div>
            <label>Street</label>
            <br>
            <input type="text" id="street">
          </div>
          <br>
          <div>
            <label>City</label>
            <br>
            <input type="text" id="city">
          </div>
          <br>
          <div>
            <label>State</label>
            <br>
            <input type="text" id="state">
          </div>
          <br>
          <div>
            <label>Zip Code</label>
            <br>
		<input type="text" id="zip">
          </div>
          <br>
	<h3>Payment Info</h3>
          <div>
            <label>Card Type</label>
            <br>
            <input type="text" id="ctype">
          </div>
          <br>
          <div>
            <label>Number</label>
            <br>
            <input type="text" id="cnum">
          </div>
          <br>
          <div>
            <label>Expiration Date</label>
            <br>
            <input type="text" id="exd">
          </div>
          <br>



          <button type="submit">Register</button>
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
                  <button type="button" style="background-color: #b3011c;"><img src="image/search.svg"></button>
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
