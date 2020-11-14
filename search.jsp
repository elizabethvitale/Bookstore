<%@ page import="com.ugabookstore.User"%>
<%@ page import="com.ugabookstore.backendUser"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>





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
            <a href="search.jsp"><button type="button"><img src="image/search.svg"></button></a>
          </div>
        </section>
        <section>
          <nav>
            <ul>
              <li>
                BROWSE
              </li>
              <li>
                <a href="checkout/cart.jsp"><img src="image/shoppingcart.svg"></a>
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
    <main>
      <div>
	      <h1>Search for ""</h1>
        <form action="/action_page.php">
          <label for="books">Sort by:</label>
          <select name="books" id="books">
              <option value="title">Title</option>
              <option value="author">Author</option>
              <option value="priceLowHigh">Price: Low to High</option>
              <option value="priceHighLow">Price: High to Low</option>
          </select>
          <br><br>
        </form>


	<section class="book">
	<table>
		<tbody>
			<%

			List<String> titles = new ArrayList<>();
			titles = (List<String>) request.getAttribute("titles");
			List<Integer> bookIds = new ArrayList<>();
			bookIds = (List<Integer>) request.getAttribute("book_ids");
			try {
				for(int i = 0; i < bookIds.size(); i++) { %>
			<tr>
			<form action="/viewBook" method="get">
				<input type="image" alt="submit" height='200' src="data:image/jpg;base64, <%=titles.get(i)%>"/>
				<input type="hidden" value="<%=bookIds.get(i)%>" name="bookid">
			</form>
			</tr>
			<% } } catch (Exception e) {
				e.printStackTrace(System.out);
			}
			%>
		</tbody>
	</table>
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
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <script type="text/javascript" src="http://livejs.com/live.js"></script>
  </body>
</html>