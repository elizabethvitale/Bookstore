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
    <h1>Editing "The Evening and the Morning"</h1>
    <section class="book">
        <img class="image" src="../image/books/theEveningandtheMorning.jpg">
        <div class="details">
            <form action="" method="post">
                <div>
                    <label>Title: </label>
                    <input type="text" value="The Evening and the Morning" />
                </div>
                <div>
                    <label>Author: </label>
                    <input type="text" value="Ken Follett" />
                </div>
                <div>
                    <label>Price (in USD): </label>
                    <input type="number" min="0" max="1000"  value="29.99" />
                </div>
                <div>
                    <label>Description:</label>
                    <textarea>
                        From the #1 New York Times bestselling author, a thrilling and addictive new novel—a prequel to The Pillars of the Earth—set in England at the dawn of a new era: the Middle Age. It is 997 CE, the end of the Dark Ages. England is facing attacks from the Welsh in the west and the Vikings in the east. Those in power bend justice according to their will, regardless of ordinary people and often in conflict with the king. Without a clear rule of law, chaos reigns. In these turbulent times, three characters find their lives intertwined. A young boatbuilder's life is turned upside down when the only home he's ever known is raided by Vikings, forcing him and his family to move and start their lives anew in a small hamlet where he does not fit in. . . . A Norman noblewoman marries for love, following her husband across the sea to a new land, but the customs of her husband's homeland are shockingly different, and as she begins to realize that everyone around her is engaged in a constant, brutal battle for power, it becomes clear that a single misstep could be catastrophic. . . . A monk dreams of transforming his humble abbey into a center of learning that will be admired throughout Europe. And each in turn comes into dangerous conflict with a clever and ruthless bishop who will do anything to increase his wealth and power. Thirty years ago, Ken Follett published his most popular novel, The Pillars of the Earth. Now, Follett's masterful new prequel The Evening and the Morning takes us on an epic journey into a historical past rich with ambition and rivalry, death and birth, love and hate, that will end where The Pillars of the Earth begins.
                    </textarea>
                </div>
                <button type="submit">Edit Book</button>
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
