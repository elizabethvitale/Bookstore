<%@ page import="com.ugabookstore.User"%>
<%@ page import="com.ugabookstore.backendUser"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@300;500;600&family=Peddana&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/auth.css">
  </head>
  <body>
<%@ page session="false" %>
<%
String firstName = "";
String lastName = "";
String phone = "";
String email="";
String cardNum = "";
String cardType = "";
String expDate= "";
String street = "";
String city = "";
String state = "";
String zipcode = "";
int userId = -1;
String customerid= "";
String cardType2="";
String expDate2="";
String cardType3="";
String expDate3="";
String enroll="";
String holder="";
if(request.getSession(false) == null){
response.sendRedirect("/errorpages/405.jsp");
System.out.println("entered");
}
if(request.getSession(false) != null){
    HttpSession httpSession = request.getSession();
    customerid = String.valueOf(httpSession.getAttribute("customerid"));
firstName = String.valueOf(httpSession.getAttribute("firstName"));
lastName = String.valueOf(httpSession.getAttribute("lastName"));
enroll = String.valueOf(httpSession.getAttribute("enroll"));
phone = String.valueOf(httpSession.getAttribute("phone"));
email = String.valueOf(httpSession.getAttribute("email"));
city = String.valueOf(httpSession.getAttribute("city"));
state = String.valueOf(httpSession.getAttribute("state"));
zipcode = String.valueOf(httpSession.getAttribute("zip"));
street = String.valueOf(httpSession.getAttribute("street"));
expDate = String.valueOf(httpSession.getAttribute("expirationDate"));
cardType = String.valueOf(httpSession.getAttribute("cardType"));
expDate2 = String.valueOf(httpSession.getAttribute("expirationDate2"));
cardType2 = String.valueOf(httpSession.getAttribute("cardType2"));
expDate3 = String.valueOf(httpSession.getAttribute("expirationDate3"));
cardType3 = String.valueOf(httpSession.getAttribute("cardType3"));
if(enroll.equals("true")){
	holder="checked";
}
if(!cardType.equals("null")){
	cardNum= "**** **** **** ****";
}else{
	expDate="";
}if(cardType.equals("null")){
	cardType="";
}
if(street.equals("null")){
	street = "";
}if(city.equals("null")){
        city = "";
}if(state.equals("null")){
        state = "";
}if(zipcode.equals("null")){
        zipcode = "";
}
}
%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <header>
      <div>
        <h2><div><a href="../index.jsp">UGA Bookshop</a></div></h2>
        <section class="searchbox-container">
          <div class="searchbox">
          		  <form action="/search" method="get">
		    <input type="text" name="keyword" placeholder="Browse by author, by title..">
           	<button type="submit"><img src="../image/search.svg"></button>
		  </form>

	  </div>
        </section>
        <section>
          <nav>
            <ul>
              <li>
                BROWSE
              </li>
              <li>
                            		<form action="/viewCart" method="get">
		<button class="button" type="submit"><img src="../image/shoppingcart.svg"></button>
		</form>
	      </li>
              <li style='position: relative;'>
                <img id="auth-dropdown-toggle" src="../image/accountblack.svg">
                  <ul class='auth-dropdown'>
                    <%
                    HttpSession session=request.getSession(false);
                    if(session!=null){ %>
                      <li>
                        <a href="editprofile.jsp">Edit Profile</a>
                      </li>
                      <li>
                        <a href="logout.jsp">Logout</a>
                      </li>
                    <%} else { %>
                      <li>
                        <a href="login.jsp">Login</a>
                      </li>
                      <li>
                        <a href="register.jsp">Register</a>
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
      <section class='editauthBox'>
        <h1>Edit Profile</h1>
        <div class="tab">
          <button class="tablinks" onclick="editProfile(event, 'EditPersonalInfo')" id="defaultOpen">Edit Personal Info</button>
          <button class="tablinks" onclick="editProfile(event, 'ChangePassword')">Change Password</button>
          <button class="tablinks" onclick="editProfile(event, 'Address')">Address</button>
          <button class="tablinks" onclick="editProfile(event, 'PaymentInfo')">Payment Info</button>
          <button class="tablinks" onclick="editProfile(event, 'OrderHistory')">Order History</button>
        </div>
        <form action="/updateUserInfo" method="post">
          <div id="EditPersonalInfo" class="tabcontent">
            <h3>Personal Info</h3>
		<p class="editFontcolor">Email Address</p>
		<p><%=email%></p>

		<div>
                <label class="editFontcolor">First Name</label>
                <br>
                <input type="text" name="firstName" value="<%=firstName%>">
              </div>
              <br>

              <div>
                <label class="editFontcolor">Last Name</label>
                <br>
                <input type="text" name="lastName" value="<%=lastName%>">
              </div>
              <br>

              <div>
                <label class="editFontcolor">Phone number</label>
                <br>
                <input type="text" name="phone" value="<%=phone%>">
              </div>
		<div>
		<br><br>
		<input type="checkbox" name="subscribed" <%=holder%>>
		<label> Subscribe to email promotions</label>
		</div>
              <br>
              <button type="submit" class="button">Update</button>
            </div>
          </form>
          <form action="/loggedUpdatePwd" method="post">
            <div id="ChangePassword" class="tabcontent">
              <h3>Change Password</h3>
              <div>
               <label class="editFontcolor">Current Password</label>
                <br>
                <input type="password" name="currentPwd">
              </div>
              <br>
              <div>
                <label class="editFontcolor">New Password</label>
                <br>
                <input type="password" name="newPwd1">
              </div>
              <br>
              <div>
                <label class="editFontcolor">Confirm New Password</label>
                <br>
                <input type="password" name="newPwd2">
              </div>
              <br>
              <button type="submit" class="button" >Update</button>
            </div>
          </form>

	<div id="PaymentInfo" class="tabcontent">
          <form action="/updateCard" method="post">
              <h3>Payment Info</h3>
              <div>
                <label class="editFontcolor">Card type*</label>
                <br>
                <input type="text" name="cardType" value="<%=cardType%>">
              </div>
              <br>
              <div>
                <label class="editFontcolor">Number*</label>
                <br>
                <input type="text" name="cardNum" value="<%=cardNum%>">
              </div>
              <br>
              <div>
                <label class="editFontcolor">Exp date*</label>
                <br>
                <input type="text" name="expirationDate" value="<%=expDate%>">
              </div>
              <br>
              <button type="submit" class="button">Update</button>
	</form>
       <br><br>
		<div id="second" hidden="hidden">
		</div>
		<div id ="third" hidden="hidden">
		</div>
		<div>
                <button name="addCard" id="addCard" hidden="hidden">Add Additional Card</button>
	  	</div>
		 </div>
<script>
var div = document.getElementById('second');
div.innerHTML += '<form action="/updateCard2" method="post"><h3>Payment Info</h3><div><label>Card type*</label><br><input type="text" name="cardType2" value="<%=cardType2%>"></div><br><div><label>Number*</label><br><input type="text" name="cardNum2" value="<%=cardNum%>"></div><br><div><label>Exp date*</label><br><input type="text" name="expirationDate2" value="<%=expDate2%>"></div><br><button type="submit" class="button">Update</button></form><br><br>';
var div = document.getElementById('third');
div.innerHTML += '<form action="/updateCard3" method="post"><h3>Payment Info</h3><div><label>Card type*</label><br><input type="text" name="cardType3" value="<%=cardType3%>"></div><br><div><label>Number*</label><br><input type="text" name="cardNum3" value="<%=cardNum%>"></div><br><div><label>Exp date*</label><br><input type="text" name="expirationDate3" value="<%=expDate3%>"></div><br><button type="submit" class="button">Update</button></form><br><br>';
</script>

<script>
$(document).ready(function(){
	if("<%=cardType2%>" !== "null")
	{
		let x = document.getElementById('second');
		x.removeAttribute("hidden");
	}
        if("<%=cardType3%>" !== "null")
        {
                let x = document.getElementById('third');
                x.removeAttribute("hidden");
        }
});
</script>

<script>
$(document).ready(function(){
	if("<%=cardType3%>" === "null" && "<%=cardType%>" !== "")
	{
		console.log('enter');
		let x = document.getElementById('addCard');
		x.removeAttribute("hidden");
	}
	});
</script>
<script>
$(document).ready(function() {
    $("button[name='addCard']").click(function() {
       var domElement = $('<form action="/addCard" method="post"><h3>Payment Info</h3><div><label>Card type*</label><br><input type="text" name="cardType" value=""></div><br><div><label>Number*</label><br><input type="text" name="cardNum" value=""></div><br><div><label>Exp date*</label><br><input type="text" name="expirationDate" value=""></div><br><button type="submit" class="button">Update</button></form><br><br>');
        $(this).after(domElement);
    	var oldButton = document.getElementById("addCard");
	oldButton.remove();
	});
});
</script>

          <form action="/updateAddress" method="post">
            <div id="Address" class="tabcontent">
              <h3>Address</h3>
              <div>
                <label class="editFontcolor">Street*</label>
                <br>
                <input type="text" name="street" value="<%=street%>">
              </div>
              <br>
              <div>
                <label class="editFontcolor">City*</label>
                <br>
                <input type="text" name="city" value="<%=city%>">
              </div>
              <br>
              <div>
                <label class="editFontcolor">State*</label>
                <br>
                <input type="text" name="state" value="<%=state%>">
              </div>
              <br>
              <div>
                <label class="editFontcolor">Zip code*</label>
                <br>
                <input type="text" name="zip" value="<%=zipcode%>">
              </div>
              <br>
              <button type="submit" class="button">Update</button>


	</div>
        </form>
        <form action="" method="post">
          <div id="Personal Info" class="tabcontent">
            <h3>Personal Info</h3>
            <div>
             <label class="editFontcolor">Name</label>
              <br>
              <input type="text">
            </div>
            <br>
            <div>
              <label class="editFontcolor">Phone number</label>
              <br>
              <input type="text">
            </div>
            <br>
            <div>
              <label class="editFontcolor">Email address</label>
              <br>
              <input type="text">
            </div>
            <br>
            <button type="submit" class="button">Update</button>
          </div>
        </form>

<%
Connection con;
String table="";
String query = "select * from orders where userid = '" + customerid + "';";
table = "<table class='table'> <thead class='thead-dark'> <tr> <th scope='col'>Order Id</th> <th scope='col'>Books Ordered</th> <th scope='col'>Confirmation Number</th> <th scope='col'>Order Status</th><th scope='col'>Reorder</th></tr> </thead>";
Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
while(rs.next()){
	int order = rs.getInt("orderid");
	String status = rs.getString("status");
	table = table + "<form action='/reorderBooks' method='post'><tr><td><input type='hidden' name='orderid' value='" + order + "'>" + order + "</td>";
		//<td>BOOKS HERE</td><td>" + order + customerid + "</td><td>Being Shipped</td><td><button>Add to cart</button></tr>";
	String query2 = "select * from transaction where orderid='" + order+ "';";
	stmt = con.createStatement();
	ResultSet ra = stmt.executeQuery(query2);
	table = table + "<td>";
	while(ra.next()){
	int quantity = ra.getInt("quantity");
	int bookid = ra.getInt("bookid");
	String query3 = "select title from book where bookid='" + bookid + "';";
	stmt = con.createStatement();
	ResultSet rb = stmt.executeQuery(query3);
	String title = "";
	if(rb.next()){
		title = rb.getString("title");
	}
	if(quantity==1){
	//table = table + "<tr><td>" + quantity + " copy of " + title + "</td></tr>";
	table = table  + "<p>" + quantity + " copy of " + title + "</p>";
	}else{
	//table = table + "<tr><td>" + quantity + " copies of " + title + "</td></tr>";
	table = table + "<p>" + quantity + " copies of " + title + "</p>";
	}
	}
	table = table + "</td>";
table = table + "<td>" + order + customerid + "</td><td>" + status + "</td><td><button type='submit' class='button'>Add Books to Cart</button></td></form>";
	table = table + "</tr>";
}
table = table + "</table>";
stmt.close();
con.close();
%>
	<div id="OrderHistory" class="tabcontent">
		  <%=table%>
            </div>
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
                  <input type="text" placeholder="../Browse store..">
                  <button type="button" style="background-color: #b3011c;"><img src="../image/search.svg"></button>
                </div>
            </div>
        </section>
      </div>
    </footer>
    <script type="text/javascript" src="../js/jquery.js"></script>
    <script type="text/javascript" src="../js/main.js"></script>
    <script type="text/javascript">
      function editProfile(evt, authOptions) {
        var i, tabcontent, tablinks;
        tabcontent = document.getElementsByClassName("tabcontent");
        for (i = 0; i < tabcontent.length; i++) {
          tabcontent[i].style.display = "none";
        }
        // Get all elements with class="tablinks" and remove the class "active"
        tablinks = document.getElementsByClassName("tablinks");
        for (i = 0; i < tablinks.length; i++) {
          tablinks[i].className = tablinks[i].className.replace(" active", "");
        }
        // Show the current tab, and add an "active" class to the button that opened the tab
        document.getElementById(authOptions).style.display = "block";
        evt.currentTarget.className += " active";
      }
      document.getElementById("defaultOpen").click();
    </script>
    <script type="text/javascript" src="http://livejs.com/live.js"></script>
  </body>
</html>
