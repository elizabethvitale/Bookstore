<%@ page import="com.ugabookstore.User"%>
<%@ page import="com.ugabookstore.backendUser"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
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
<%
Connection con;
String table="";

try{
  table = "<table class='table'> <thead class='thead-dark'> <tr> <th scope='col'>User ID</th> <th scope='col'>Email</th> <th scope='col'>First Name</th> <th scope='col'>Type</th> <th scope='col'>Suspended</th> <th scope='col'>Action</th> </tr> </thead> <tbody> <tr>";
  Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
  Statement adminStmt= con.createStatement();
  String query1 = "select * from admin";
  ResultSet rs1 = adminStmt.executeQuery(query1);
  while(rs1.next()){
    String id = rs1.getString("adminid");
    String email = rs1.getString("email");
    String fn = rs1.getString("firstname");
    table = table + "<tr> <th scope='row'>"+id+"</th> <td>"+email+"</td> <td>"+fn+"</td> <td>Admin</td> <td>Not Suspendable</td> <td><form action='/promote' method='post'><input type='hidden' name='id' value='"+id+"'><input type='submit' class='button' value='Depromote'></form></td> </tr>";
  }

  Statement stmt=null;
  String query = "select * from customer WHERE employee != -1";
  stmt = con.createStatement();
  ResultSet rs = stmt.executeQuery(query);
  while(rs.next()){
    String id = rs.getString("customerid");
    String email = rs.getString("email");
    String fn = rs.getString("firstname");
    String employeeType = rs.getString("employee").equals("0") ? "Customer" : "Employee";
    String suspended = rs.getString("suspended").equals("0") ? "No" : "Yes";
    String promoteForm = "<input type='hidden' name='id' value='"+id+"'>";
    String suspendForm = "<input type='hidden' name='id' value='"+id+"'>";

    if (employeeType.equals("Customer")) {
      promoteForm += "<input class='button' type='submit' value='Promote'>";
    } else {
      promoteForm += "<input class='button' type='submit' value='Depromote'>";
    }

    if (suspended.equals("No")) {
      suspendForm += "<input class='button' type='submit' value='Suspend'>";
    } else {
      suspendForm += "<input class='button' type='submit' value='Unsuspend>";
    }
    table = table + "<tr> <th scope='row'>"+id+"</th> <td>"+email+"</td> <td>"+fn+"</td> <td>"+employeeType+"</td> <td>"+suspended+"</td> <td><form action='/promote' method='post'>"+promoteForm+"</form><form action='/suspend' method='post'>"+suspendForm+"</form></td> </tr>";
  }
  table = table + "  </tbody></table>";
} catch(Exception e) {
  System.out.println(e.getMessage());
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
    <h1>Manage Users</h1>
    <%=table%>
    <a href="editusers.jsp"><button class="button"> Edit Users</button></a>
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
