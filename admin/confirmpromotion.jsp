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
				<script>if(performance.navigation.type == 2){
	location.reload(true);
		 }</script>
<%@ page session="false" %>
<%
String name="";
String pass="";
HttpSession session = request.getSession(false);

String id = (String)session.getAttribute("id");
String percent = (String)session.getAttribute("percent");
String start = (String)session.getAttribute("start");
String end = (String)session.getAttribute("end");
if(session == null){
response.sendRedirect("/errorpages/404.jsp");
System.out.println("entered");
}
else{
name = (String)session.getAttribute("firstName");
pass = String.valueOf(session.getAttribute("admin"));
if(pass.equals(null) | !pass.equals("YES")){
	response.sendRedirect("/errorpages/404.jsp");
}
String holder = (String)session.getAttribute("submitted");
if(holder.equals("after")){
	response.sendRedirect("/admin/managepromotions.jsp");
}
}
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
                                <a href="../user/login.html">Login</a>
                            </li>
                            <li>
                                <a href="../user/register.html">Register</a>
                            </li>
                            <li>
                                <a href="../user/editprofile.html">Edit Profile</a>
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
	<br>
    <h1>Confirmation Promotion Dates</h1>
	<p style="color:red">The following message will be sent to EVERY registered user signed up for promotions upon clicking onto "Confirm Promotion"</p>
	<p style="color:red">Please ensure the message below is correct and the promotion is correct.</p>
	<br>
	<p>We don't want you to miss out!
	<br><br>From <%=start%> to <%=end%>, we will be running a discount of <%=percent%>% on every book at UGA Bookshop! Feel free to log in and browse books at this discount. Enter the promo code "<%=id%>" at checkout to make sure you recieve your discount. <br><br>Thanks,<br>UGA Bookshop </p>
	<br>	
<a href="editpromotions.jsp" class=button>Edit Promotion</a>
<br><br>
<form action="/emailPromo" method="post">
<input type='hidden' name='id' value="<%=id%>">
<input type='hidden' name='percent' value="<%=percent%>">
<input type='hidden' name='start' value="<%=start%>">
<input type='hidden' name='end' value="<%=end%>">
<button class="button" style="color:red" type=submit>Confirm Promotion</button>

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
