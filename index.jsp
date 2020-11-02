<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@300;500;600&family=Peddana&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/main.css">
  </head>
  <body>
<%@ page session="false" %>  
  <header>
      <div>
        <h2><div><a href="index.jsp">UGA Bookshop</a></div></h2>
        <section class="searchbox-container">
          <div class="searchbox">
            <input type="text" placeholder="Browse by author, by title..">
            <a href="search.html"><button type="button"><img src="image/search.svg"></button></a>
          </div>
        </section>
        <section>
          <nav>
            <ul>
              <li>
                BROWSE
              </li>
              <li>
                <a href="checkout/cart.html"><img src="image/shoppingcart.svg"></a>
              </li>
              <li style='position: relative;'>
                <img id="auth-dropdown-toggle" src="image/account.svg">
                <ul class='auth-dropdown'>
                  <li>
                    <a href="user/login.jsp">Login</a>
                  </li>
                  <li>
                    <a href="user/register.jsp">Register</a>
                  </li>
                  <li>
                    <a href="user/editprofile.jsp">Edit Profile</a>
                  </li>
                  <li>
                    <a href="user/logout.jsp">Logout</a>
                  </li>
                </ul>
              </li>
            </ul>
          </nav>
        </section>
      </div>
    </header>

<%
	String message = "";
       HttpSession session=request.getSession(false);  
        if(session!=null){ 
        message="Welcome, " + (String)session.getAttribute("acctID");  
}%>

    <main>
      <div>
	<h4><%=message%></h4>
        <h1>Topseller Books</h1>
        <a href="bookDetail.html"><img class="books" src="image/books/theEveningandtheMorning.jpg"></a>
        <a href="bookDetail.html"><img class="books" src="image/books/toSleepinAseaOfstars.jpg"></a>
        <a href="bookDetail.html"><img class="books" src="image/books/totalpower.jpg"></a>
        <a href="bookDetail.html"><img class="books" src="image/books/troubledBlood.jpg"></a>
      </div>
      <div>
        <h1>Featured Books</h1>
        <a href="bookDetail.html"><img class="books" src="image/books/theMidwifeMurders.jpeg"></a>
        <a href="bookDetail.html"><img class="books" src="image/books/thenShewasGone.jpeg"></a>
        <a href="bookDetail.html"><img class="books" src="image/books/thisTenderland.jpeg"></a>
        <a href="bookDetail.html"><img class="books" src="image/books/whatHappensinParadise.jpeg"></a>
      </div>
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
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <script type="text/javascript" src="http://livejs.com/live.js"></script>
  </body>
</html>
