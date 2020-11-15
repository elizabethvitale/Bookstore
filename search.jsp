<%@ page import="com.ugabookstore.User"%>
<%@ page import="com.ugabookstore.backendUser"%>
<%@ page import="com.ugabookstore.Book"%>
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
          
          		  <form action="/search" method="get">
		    <input type="text" name="keyword" placeholder="Browse by author, by title..">
           	<button type="submit"><img src="/image/search.svg"></button>

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
	      <% String message = ""; 
	      if(request.getAttribute("keyword").equals("")){
	      message = "Browse Entire Collection";
	      }else{
	      message = "Searching for \"" + request.getAttribute("keyword") + "\"";
	      }%>
	      <%
	      String searching = "";
	      if(request.getAttribute("search")!=null){
	      searching = "Sorted by " + request.getAttribute("search");
	      }else{
	      searching = "Sorted by Title";
	      }%>
	      <h1><%=message%></h1>
	      <h2><%=searching%></h2>
        <form action="/reorder" method="get">
          <input type="hidden" name="keyword" value="<%=request.getAttribute("keyword")%>">
		<label for="books">Sort by:</label>
          <select name="books" id="books" onchange="this.form.submit()">
		  <option></option>
		<option value="Title">Title</option>
              <option value="Author">Author</option>
              <option value="ISBN">ISBN</option>
              <option value="Subject">Subject</option>
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
			List<Book> books = (List<Book>) request.getAttribute("books");
			try {
				for(int i = 0; i < bookIds.size(); i++) { %>
			<tr>
			<form action="/viewBook" method="get">
				<input type="image" alt="submit" height='200' src="data:image/jpg;base64, <%=titles.get(i)%>"/>
				<%Book book = books.get(i);
				String title = book.getTitle();
				String author = book.getAuthor();
				double price = book.getRprice();%>
				<p><%=title%>, by <%=author%> at $<%=price%>0</p>
				<p>Rating: Not in DB Yet</p>
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
