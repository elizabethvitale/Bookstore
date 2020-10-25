<%@ page import="com.ugabookstore.User"%>
<%@ page import="com.ugabookstore.backendUser"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@300;500;600&family=Peddana&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/auth.css">
  </head>
  <body>
<%@ page session="false" %> 
<%
backendUser retriever = new backendUser();
HttpSession httpSession = request.getSession();
String firstName = "";
String lastName = "";
String phone = "";
String email="";
int cardNum = -1;
String cardType = "";
String expDate= "";
String street = "";
String city = "";
String state = "";
int zipcode = -1;
int userId = -1;
try {
	userId = Integer.parseInt(String.valueOf(httpSession.getAttribute("acctID")));
} catch (NumberFormatException e) {
	userId = retriever.retrieveIdFromEmail(String.valueOf(httpSession.getAttribute("acctID")));
}
firstName = String.valueOf(httpSession.getAttribute("firstName"));
lastName = String.valueOf(httpSession.getAttribute("lastName"));
phone = String.valueOf(httpSession.getAttribute("phone"));
email = String.valueOf(httpSession.getAttribute("email"));



//cardNum = retriever.retrieveCardNumber(userId);
//cardType = retriever.retrieveCardType(userId);
//expDate = retriever.retrieveExpDate(userId);
//street = retriever.retrieveStreet(userId);
//city  = retriever.retrieveCity(userId);
//state = retriever.retrieveState(userId);
//zipcode = retriever.retrieveZipcode(userId);

%>

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
              <div>
		<label>Email Address</label>
		<br>
		<p><%=email%></p>
		<br
		</div>
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
              <br>
              <button type="submit">Update</button>
            </div>
          </form>
          <form action="/loggedUpdatePwd" method="post">
            <div id="ChangePassword" class="tabcontent">
              <h3>Change Password</h3>
              <div>
               <label>Current Password</label>
                <br>
                <input type="text" name="currentPwd">
              </div>
              <br>
              <div>
                <label>New Password</label>
                <br>
                <input type="text" name="newPwd1">
              </div>
              <br>
              <div>
                <label>Confirm New Password</label>
                <br>
                <input type="text" name="newPwd2">
              </div>
              <br>
              <button type="submit">Update</button>
            </div>
          </form>
          <form action="" method="post">
            <div id="PaymentInfo" class="tabcontent">
              <h3>Payment Info</h3>
              <div>
                <label>Card type*</label>
                <br>
                <input type="text" value="<%=cardType%>">
              </div>
              <br>
              <div>
                <label>Number*</label>
                <br>
                <input type="text" value="<%=cardNum%>">
              </div>
              <br>
              <div>
                <label>Exp date*</label>
                <br>
                <input type="text" value="<%=expDate%>">
              </div>
              <br>
              <button type="submit">Update</button>
            </div>
          </form>
          <form action="" method="post">
            <div id="Address" class="tabcontent">
              <h3>Address</h3>
              <div>
                <label>Street*</label>
                <br>
                <input type="text" value="<%=street%>">
              </div>
              <br>
              <div>
                <label>City*</label>
                <br>
                <input type="text" value="<%=city%>">
              </div>
              <br>
              <div>
                <label>State*</label>
                <br>
                <input type="text" value="<%=state%>">
              </div>
              <br>
              <div>
                <label>Zip code*</label>
                <br>
                <input type="text" value="<%=zipcode%>">
              </div>
              <br>
              <button type="submit">Update</button>
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
            <button type="submit">Update</button>
          </div>
        </form>
        <form action="" method="post">
          <div id="OrderHistory" class="tabcontent">
            <div>
              <img src="../image/books/theEveningandtheMorning.jpg">
              <h2>Order confirmation number: 98362043</h2>
              <h2>Total Price: 31.08</h2>
              <h2>Date Purchased: 9/31/2020</h2>
              <button type="button">Reorder</button>
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
