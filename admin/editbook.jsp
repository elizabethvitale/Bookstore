<%@ page import="com.ugabookstore.User"%>
<%@ page import="com.ugabookstore.backendUser"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@300;500;600&family=Peddana&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/admin.css">
</head>
<body>
<%@ page session="false" %>
<%
HttpSession session = request.getSession();
String pass = String.valueOf(session.getAttribute("admin"));
if(pass.equals(null) | !pass.equals("YES")){
        response.sendRedirect("/errorpages/404.jsp");
}
%>

<header>
    <div>
        <h2><div><a href="../admin/index.jsp">UGA Bookshop</a></div></h2>
        <section>
            <nav>
                <ul>
                    <li>
                        <a href="../admin/managebooks.jsp">Manage Books</a>
                    </li>
                    <li>
                        <a href="../admin/manageusers.jsp">Manage Users</a>
                    </li>
                    <li>
                        <a href="../admin/managepromotions.jsp">Manage Promotions</a>
                    </li>
                    <li style='position: relative;'>
                        <img id="auth-dropdown-toggle" src="../image/accountblack.svg">
                          <ul class='auth-dropdown'>
                            <%
                            if(session!=null){ %>
                              <li>
                                <a href="../user/logout.jsp">Logout</a>
                              </li>
                            <%} else { %>
                              <li>
                                <a href="user/login.jsp">Login</a>
                              </li>
                              <li>
                                <a href="user/register.jsp">Register</a>
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
    <h1>Edit Book</h1>
    <section class="book">
	<div class="details">
            <form enctype="multipart/form-data" action="/editBook" method="post">
		<div>
			<input type="hidden" name="bookid" value="${book.bookid}">
		</div>
		<div>
			<label class="input_label">Image: </label>
			<img src="data:image/jpg;base64,${book.base64Image}" height="400" width="250"/>
		</div>
		<div>
			<label class="input_label">New image: </label>
                    	<input class="book_input" type="file" id="image" name="image" accept="image/jpeg">
		</div>
		<div>
                    <label class="input_label">Title: </label>
                    <input class="book_input" type="text" name="title" value="${book.title}" required>
                </div>
                <div>
                    <label class="input_label">Author: </label>
                    <input class="book_input" type="text" name="author" value="${book.author}" required>
                </div>
                <div>
                    <label class="input_label">ISBN: </label>
                    <input class="book_input" type="text" name="isbn"  value="${book.isbn}" required>
                </div>
		<div>
			<label class="input_label">Category: </label>
			<input class="book_input" type="text" name="category" value="${book.category}" required>
		</div>
		<div>
			<label class="input_label">Edition: </label>
			<input class="book_input" type="text" name="edition" value="${book.edition}" required>
		</div>
		<div>
			<label class="input_label">Publisher: </label>
			<input class="book_input" type="text" name="publisher" value="${book.publisher}" required>
		</div>
		<div>
			<label class="input_label">Publication year: </label>
			<input class="book_input" type="number" name="year" value="${book.year}" required>
		</div>
		<div>
			<label class="input_label">Initial quantity: </label>
			<input class="book_input" type="number" name="quantity" value="${book.quantity}" required>
		</div>
		<div>
			<label class="input_label">Minimum threshold: </label>
			<input class="book_input" type="number" name="m_threshold" value="${book.minThreshold}" required>
		</div>
                <div>
                    <label class="input_label">Price (Retail): </label>
                    <input class="book_input" type="number" min="0" max="100" name="r_price" step=".01" value="${book.rprice}" required>
                </div>
		<div>
			<label class="input_label">Price (Wholesale): </label>
			<input class="book_input" type="number" min="0" max="100" name="w_price" step=".01" value="${book.wprice}" required>
		</div>
                <div>
                    <label class="input_label">Description:</label>
                    <textarea class="desc" name="description">${book.description}</textarea>
                </div>
                <input type="submit" value="Edit Book" class="button">
            </form>
		<form action="/deleteBook" method="post">
		<input type="hidden" name="bookid" value="${book.bookid}">
		<input type="submit" class="button" value="Delete Book">
		</form>
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
</body>
</html>
