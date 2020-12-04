<!DOCTYPE html>
<html lang="en">
  <head>
<%@ page session="false" %>
    <meta charset="utf-8">
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@300;500;600&family=Peddana&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/errorpages.css">
    <style>
      main {
        font-family: 'IBM Plex Sans', sans-serif;
        display: flex;
        align-items: center;
        text-align: center;
      }
      main > div {
        margin: 0 auto;
        color: var(--primary-color);
      }
      main h1 {
        margin-top: 0;
      }
    </style>
  </head>
  <body>
<%@ page session="false" %>
    <header>
      <div>
        <h2><div><a href="../index.jsp">UGA Bookshop</a></div></h2>
        <section class="searchbox-container">
          <div class="searchbox">
                    		  <form action="../search" method="get">
		    <input type="text" name="keyword" placeholder="Browse by author, by title..">
           	<button type="submit"><img src="../image/search.svg"></button>
 </form>
	  </div>
        </section>
        <section>
          <nav>
            <ul>
              <li>
                BROWSE
              </li>
              <li>
                            	<form action="/viewCart" method="get">
		<button class="button" type="submit"><img src="../image/shoppingcart.svg"></button>
		</form>
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
      <div>
        <h1>Error page</h1>
        <p>User has not filled in all the requirements for the shipping address.
          Check to make sure all fields have been entered.</p>
        <a href="../user/register.jsp"><button class="button" style="margin-right: 10px;">RETURN TO REGISTER PAGE</button></a>
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
