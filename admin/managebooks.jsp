<%@ page import="com.ugabookstore.User"%>
<%@ page import="com.ugabookstore.backendUser"%>
<%@ page import="com.ugabookstore.BookDAO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

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
HttpSession session = request.getSession(false);

if(session == null){
 response.sendRedirect("/errorpages/404.jsp");
}else{
String pass = String.valueOf(session.getAttribute("admin"));
if(pass.equals(null) | !pass.equals("YES")){
        response.sendRedirect("/errorpages/404.jsp");
}}
%>
<header>
    <div>
        <h2><div><a href="../admin/index.jsp">UGA Bookshop</a></div></h2>
        <section>
            <nav>
                <ul>
                    <li>
                        <a href="managebooks.jsp">Manage Books</a>
                    </li>
                    <li>
                        <a href="manageusers.jsp">Manage Users</a>
                    </li>
                    <li>
                        <a href="managepromotions.jsp">Manage Promotions</a>
                    </li>
                    <li style='position: relative;'>
                        <img id="auth-dropdown-toggle" src="../image/accountblack.svg">
                        <ul class='auth-dropdown'>
                            <li>
                                <a href="../user/login.jsp">Login</a>
                            </li>
                            <li>
                                <a href="../user/register.jsp">Register</a>
                            </li>
                            <li>
                                <a href="../user/editprofile.jsp">Edit Profile</a>
                            </li>
                            <li>
                                <a href="../user/logout.jsp">Logout</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </nav>
        </section>
    </div>
</header>
<main>
    <h1>Manage Books</h1>
<section class="book">
<%
try {
	BookDAO dao = new BookDAO();
        List<Integer> bookIds = new ArrayList<Integer>(dao.getAllBookIds());
	List<String> titles = new ArrayList<String>(dao.getAllTitles(bookIds, bookIds.size()));
	List<String> authors = new ArrayList<String>(dao.getAllAuthors(bookIds, bookIds.size()));
	if (bookIds.size() > 0) {
%>
	<table class="table">
		<thead class="thead-dark">
		<tr>
			<th scope="col">Title</th>
			<th scope="col">Author</th>
			<th scope="col">Edit</th>
		</tr>
		</thead>
		<tbody>
			<%
			for(int i = 0; i < bookIds.size(); i++) { 
			%>
			<tr>
			<form action="/getBook" method="get">
				<th scope="row"><% out.print(titles.get(i)); %>
				<input type="hidden" value="<%=bookIds.get(i)%>" name="bookid"></th>
				<td><% out.print(authors.get(i)); %></td>
				<td><input type="submit" value="Edit"></td>
			</form>
			</tr>
			<% } %>
		</tbody>
	</table>
<%
	}
} catch (Exception e) {
	e.printStackTrace(System.out);
	response.sendRedirect("/errorpages/connection_error.jsp");
}
%>
</section>
	<a href="addbook.jsp"><button class="button">Add Book</button></a>
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
