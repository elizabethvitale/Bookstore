<!DOCTYPE html>
<%@ page session="false" %>
<html lang="en">

  <head>
    <meta charset="utf-8">
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@300;500;600&family=Peddana&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/auth.css">
  </head>
  <body>

<%


	String message = "";
	HttpSession session=request.getSession(false);
        if(session!=null){
        message="User is already logged in. <a href='../user/logout.jsp' style='color:blue'>Log out?</a>";
}%>


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
                  <%-- <ul class='auth-dropdown'>
                    <%
                    if(session!=null){ %>
                      <li>
                        <a href="user/editprofile.jsp">Edit Profile</a>
                      </li>
                      <li>
                        <a href="user/logout.jsp">Logout</a>
                      </li>
                    <%} else { %>
                      <li>
                        <a href="user/login.jsp">Login</a>
                      </li>
                      <li>
                        <a href="user/register.jsp">Register</a>
                      </li>
                    <%}%>
                  </ul> --%>
              </li>
            </ul>
          </nav>
        </section>
      </div>
    </header>
    <main>
      <section id='authBox'>
        <h1>Login as Admin</h1>
        <form action="/adminLogin" method="post">
          <div>
            <label for="acctID">Admin ID/Email Address</label>
            <br>
            <input type="text" name="acctID" id="acctID">
          </div>
          <br>
          <div>
            <label for="pwd">Password</label>
            <br>
            <input type="password" id="pwd" name="pwd">
          </div>
	<p><%=message%></p>


          <button id="formsub" type="submit" class="button">Login</button>
        </form>
	<br>
	<br><br><br>

     </section>
	<br><br><br>
	<a href="/user/login.jsp">Navigate to user login?</a>
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

    <script language="javascript">
        var s = "<%=message%>";
        if(s!==""){
                document.getElementById("formsub").disabled = true;
        }
    </script>

    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" src="../js/main.js"></script>
    <script type="text/javascript" src="http://livejs.com/live.js"></script>
  </body>
</html>
