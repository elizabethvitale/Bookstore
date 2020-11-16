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
                            <li>
                                <a href="../user/login.html">Login</a>
                            </li>
                            <li>
                                <a href="../user/register.html">Register</a>
                            </li>
                            <li>
                                <a href="../user/editprofile.html">Edit Profile</a>
                            </li>
                            <li>
                                <a href="../user/logout.html">Logout</a>
                            </li>
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
			<label>Image: </label>
			<img src="data:image/jpg;base64,${book.base64Image}"/>
		</div>
		<div>
			<label>New image: </label>
                    	<input type="file" id="image" name="image" accept="image/jpeg">
		</div>
		<div>
                    <label>Title: </label>
                    <input type="text" name="title" value="${book.title}">
                </div>
                <div>
                    <label>Author: </label>
                    <input type="text" name="author" value="${book.author}">
                </div>
                <div>
                    <label>ISBN: </label>
                    <input type="text" name="isbn"  value="${book.isbn}">
                </div>
		<div>
			<label>Category: </label>
			<input type="text" name="category" value="${book.category}">
		</div>
		<div>
			<label>Edition: </label>
			<input type="text" name="edition" value="${book.edition}">
		</div>
		<div>
			<label>Publisher: </label>
			<input type="text" name="publisher" value="${book.publisher}">
		</div>
		<div>
			<label>Publication year: </label>
			<input type="number" name="year" value="${book.year}">
		</div>
		<div>
			<label>Initial quantity: </label>
			<input type="number" name="quantity" value="${book.quantity}">
		</div>
		<div>
			<label>Minimum threshold: </label>
			<input type="number" name="m_threshold" value="${book.minThreshold}">
		</div>
                <div>
                    <label>Price (Retail): </label>
                    <input type="number" min="0" max="1000" name="r_price" value="${book.rprice}">
                </div>
		<div>
			<label>Price (Wholesale): </label>
			<input type="number" min="0" max="1000" name="w_price" value="${book.wprice}">
		</div>
                <div>
                    <label>Description:</label>
                    <textarea name="description">${book.description}</textarea>
                </div>
                <input type="submit" value="Edit Book">
            </form>
		<form action="/deleteBook" method="post">
		<input type="hidden" name="bookid" value="${book.bookid}">
		<input type="submit" value="Delete Book">
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
