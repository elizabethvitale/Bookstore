<%@ page import="com.ugabookstore.User"%>
<%@ page import="com.ugabookstore.backendUser"%>
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
//HttpSession session = request.getSession(false);
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
            <input type="text" placeholder="Browse by author, by title..">
            <a href="../search.html"><button type="button"><img src="../image/search.svg"></button></a>
          </div>
        </section>
        <section>
          <nav>
            <ul>
              <li>
                BROWSE
              </li>
              <li>
                <a href="../checkout/cart.html"><img src="../image/shoppingcartblack.svg"></a>
              </li>
              <li style='position: relative;'>
                <img id="auth-dropdown-toggle" src="../image/accountblack.svg">
                <ul class='auth-dropdown'>
                  <li>
                    <a href="login.jsp">Login</a>
                  </li>
                  <li>
                    <a href="register.jsp">Register</a>
                  </li>
                  <li>
                    <a href="editprofile.jsp">Edit Profile</a>
                  </li>
                  <li>
                    <a href="logout.jsp">Logout</a>
                  </li>
                </ul>
              </li>
            </ul>
          </nav>
        </section>
      </div>
    </header>
    <main>
      <section id='authBox'>
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
		<p>Email Address</p>
		<p><%=email%></p>

		<div>
                <label>First Name</label>
                <br>
                <input type="text" name="firstName" value="<%=firstName%>">
              </div>
              <br>

              <div>
                <label>Last Name</label>
                <br>
                <input type="text" name="lastName" value="<%=lastName%>">
              </div>
              <br>

              <div>
                <label>Phone number</label>
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
               <label>Current Password</label>
                <br>
                <input type="password" name="currentPwd">
              </div>
              <br>
              <div>
                <label>New Password</label>
                <br>
                <input type="password" name="newPwd1">
              </div>
              <br>
              <div>
                <label>Confirm New Password</label>
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
                <label>Card type*</label>
                <br>
                <input type="text" name="cardType" value="<%=cardType%>">
              </div>
              <br>
              <div>
                <label>Number*</label>
                <br>
                <input type="text" name="cardNum" value="<%=cardNum%>">
              </div>
              <br>
              <div>
                <label>Exp date*</label>
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
                <label>Street*</label>
                <br>
                <input type="text" name="street" value="<%=street%>">
              </div>
              <br>
              <div>
                <label>City*</label>
                <br>
                <input type="text" name="city" value="<%=city%>">
              </div>
              <br>
              <div>
                <label>State*</label>
                <br>
                <input type="text" name="state" value="<%=state%>">
              </div>
              <br>
              <div>
                <label>Zip code*</label>
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
             <label>Name</label>
              <br>
              <input type="text">
            </div>
            <br>
            <div>
              <label>Phone number</label>
              <br>
              <input type="text">
            </div>
            <br>
            <div>
              <label>Email address</label>
              <br>
              <input type="text">
            </div>
            <br>
            <button type="submit" class="button">Update</button>
          </div>
        </form>
        <form action="" method="post">
          <div id="OrderHistory" class="tabcontent">
            <div>
              <img src="../image/books/theEveningandtheMorning.jpg">
              <h2>Order confirmation number: 98362043</h2>
              <h2>Total Price: 31.08</h2>
              <h2>Date Purchased: 9/31/2020</h2>
              <button type="button" class="button">Reorder</button>
            </div>
            </div>
          </div>
        </form>
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
