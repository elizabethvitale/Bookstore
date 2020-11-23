<%@ page import="com.ugabookstore.User"%>
<%@ page import="com.ugabookstore.backendUser"%>
<%@ page import="com.ugabookstore.Book"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@300;500;600&family=Peddana&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/checkout.css">
</head>
<body>
<%@ page session="false" %>
<%
HttpSession session = request.getSession();

String billName = String.valueOf(session.getAttribute("billFName")) + " " + String.valueOf(session.getAttribute("billLName"));
String billAddress = String.valueOf(session.getAttribute("billStreet")) + ", " + String.valueOf(session.getAttribute("billCity")) + ", " + String.valueOf(session.getAttribute("billState")) + ", " + String.valueOf(session.getAttribute("billZip"));
String cardinfo = String.valueOf(session.getAttribute("cardinfo"));

String shipName = String.valueOf(session.getAttribute("shipFName")) + " " + String.valueOf(session.getAttribute("shipLName"));
String shipAddress = String.valueOf(session.getAttribute("shipStreet")) + ", " + String.valueOf(session.getAttribute("shipCity")) + ", " + String.valueOf(session.getAttribute("shipState")) + ", " + String.valueOf(session.getAttribute("shipZip"));

%>
<header>
    <div>
        <h2><div><a href="../index.jsp">UGA Bookshop</a></div></h2>
        <section class="searchbox-container">
            <div class="searchbox">
                <input type="text" placeholder="Browse by author, by title..">
                <a href="../search.jsp"><button type="button"><img src="../image/search.svg"></button></a>
            </div>
        </section>
        <section>
            <nav>
                <ul>
                    <li>
                        BROWSE
                    </li>
                    <li>
                        <a href="cart.jsp"><img src="../image/shoppingcart.svg"></a>
                    </li>
                    <li style='position: relative;'>
                        <img id="auth-dropdown-toggle" src="../image/account.svg">
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
    <section class="tracker">
        <ul>
            <li>1. Payment</li>
            <li>2. Shipping</li>
            <li class="active">3. Review Order</li>
            <li>4. Confirmation</li>
        </ul>
    </section>
    <section>
        <div class="container">
            <div class="row">
                <div class="col-md-12 mb-4">
                    <h4 class="d-flex justify-content-between align-items-center mb-3">
                        <span class="text-muted">Your cart</span>
                    </h4>
                    <ul class="list-group mb-3">
	<%
	System.out.println(session.getAttribute("TOTAL"));
	
	List<String>images = new ArrayList<>();
	List<Book> books = (List<Book>)request.getAttribute("booksCart");
	System.out.println(books);
	double total = 0;
	try{
	for(int i=0; i < books.size(); i++){%>
                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                            <div>
				    <%Book book = books.get(i);%>
				    <h6 class="my-0"><%=book.getTitle()%></h6>
				    <% total = total + book.getRprice(); %>
					<small class="text-muted"><%=book.getDescription()%></small>
			    </div>
			    <span class="text-muted">$<%=String.format("%.02f", book.getRprice())%></span>
			</li>
			<%}}catch(Exception e){
			System.out.println(e);
			}%>
			    <% String discount="";
			    try{
			    	int dis = (Integer)session.getAttribute("discount");
				total = total*dis*.01;
				discount = String.format("%.02f", total);
				%>
			<li class="list-group-item d-flex justify-content-between bg-light">
                            <div class="text-success">
                                <h6 class="my-0">Promo code</h6>
				<small>"<%=session.getAttribute("promocode")%>"</small>
                            </div>
			    			    <span class="text-success">-$<%=discount%></span></li>
				                        <li class="list-group-item d-flex justify-content-between">
				<%
				}catch(Exception e){
				
				}
			    %>
                            <span>Total (USD)</span>

			    <strong>$<%=session.getAttribute("TOTAL")%></strong>
                        </li>
		    </ul>
		</div>
	    </div>
        </div>
	<div class="container">
            	<div class="row">
                	<div class="col-md-12 mb-4">
                    	<h4 class="d-flex justify-content-between align-items-center mb-3">
                        <span class="text-muted">Billing Info</span>
                    	</h4>
                    	<ul class="list-group mb-3">
				<li class="list-group-item d-flex justify-content-between lh-condensed">
                            		<div>
				    	<h6 class="my-0">Name: <% out.print(billName); %></h6>
			    		</div>
                            		<div>
				    	<h6 class="my-0">Card: <% out.print(cardinfo); %></h6>
			    		</div>
                            		<div>
				    	<h6 class="my-0">Address: <% out.print(billAddress); %></h6>
			    		</div>
			    <span class="text-muted"></span>
				</li>
			</ul>
			</div>
		</div>
	</div>
	<div class="container">
            	<div class="row">
                	<div class="col-md-12 mb-4">
                    	<h4 class="d-flex justify-content-between align-items-center mb-3">
                        <span class="text-muted">Shipping Info</span>
                    	</h4>
                    	<ul class="list-group mb-3">
				<li class="list-group-item d-flex justify-content-between lh-condensed">
                            		<div>
				    	<h6 class="my-0">Name: <% out.print(shipName); %></h6>
			    		</div>
                            		<div>
				    	<h6 class="my-0">Address: <% out.print(shipAddress); %></h6>
			    		</div>
			    <span class="text-muted"></span>
				</li>
			</ul>
			</div>
		</div>
	</div>

            <form action="/placeOrder" method="post">
                <button class="btn btn-success btn-lg btn-block" type="submit">Complete Order</button>
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
                        <input type="text" placeholder="Browse store..">
                        <button type="button" style="background-color: #b3011c;"><img src="../image/search.svg"></button>
                    </div>
                </div>
        </section>
    </div>
</footer>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/main.js"></script>
<script type="text/javascript" src="http://livejs.com/live.js"></script>
</body>
</html>
