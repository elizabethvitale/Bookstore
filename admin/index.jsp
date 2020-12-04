<%@ page import="com.ugabookstore.User"%>
<%@ page import="com.ugabookstore.backendUser"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ page session="false" %>
    <meta charset="utf-8">
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@300;500;600&family=Peddana&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/admin.css">
</head>
<body>
<%@ page session="false" %>
<%
String name="";
String pass="";
HttpSession session = request.getSession(false);
System.out.println("here");
if(session == null){
response.sendRedirect("/errorpages/404.jsp");
System.out.println("entered");
}
else{
name = (String)session.getAttribute("firstName");
pass = String.valueOf(session.getAttribute("admin"));
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
                            <%
                            if(session!=null){ %>
                              <li>
                                <a href="../user/logout.jsp">Logout</a>
                              </li>
                            <%} else { %>
                              <li>
                                <a href="../user/login.jsp">Login</a>
                              </li>
                              <li>
                                <a href="../user/register.jsp">Register</a>
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


	<br>
	<h1>Welcome to the Admin Panel, <%=name%></h1><br>
	<h3>Use the tabs at the top to manage users, books, and promotions</h3><br>
	<p>If anything goes wrong, please contact the senior site administrator at ttgiang223@ugabookshop.com</p>
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
