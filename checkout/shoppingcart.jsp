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

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/checkout.css">
</head>
<body>
	<%@ page session="false" %>
			<script>if(performance.navigation.type == 2){
	location.reload(true);
		 }</script>
			
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
<main><br>
    <h1>Shopping Cart</h1>
	<form action="/emptyCart" type="get">
    <button class="button" type="submit"style="float:right;">Empty Cart</button>
	</form>
    <div style="clear:right;"></div>
    <br><br>
    <table>
	    <tr>
		    <th colspan="2">Item</th>
		    <th>Price.</th>
		    <th></th>
	    </tr>

	<%
	List<String>images = new ArrayList<>();
	double total = 0;
	images = (List<String>) request.getAttribute("imagesCart");
	List<Book> books = (List<Book>)request.getAttribute("booksCart");
	try{
	for(int i=0; i < images.size(); i++){%>
		<tr>
			<td><input type="image" alt="submit" height='200' src="data:image/jpg;base64, <%=images.get(i)%>"/></td>
				<%Book book = books.get(i);
				String title = book.getTitle();
				String author = book.getAuthor();
				int id = book.getBookid();
				System.out.println(id);
				double price = book.getRprice();
				String formatting = String.format("%.02f", price);
				total = total + price;%>
				
				<td><h2><%=title%><h2><h4><%=author%></h4></td> <td>$<%=formatting%></td>
						<td>	<form action="/removeCartItem" method="get">
								<input type="hidden" name="holder" value=<%=id%>>
							<button type="submit" class=button>Remove</button>
							</form>
						</td>
			</tr>
			<% } } catch (Exception e) {
				e.printStackTrace(System.out);
			}
			%>

	</table>

	<%
	HttpSession session = request.getSession(false);
	String promo = "Promo Code";
	if(session!=null){
	try{
	int discount = (Integer)session.getAttribute("discount");
	String promocode = (String)session.getAttribute("promocode");
	total = (100-discount)*.01*total;
	promo = promocode;
	}catch(Exception e){
	System.out.println(e);
	}}
	String formattedDouble = String.format("%.02f", total);
	session.setAttribute("TOTAL", formattedDouble);
	%>
    <form action="/checkPromo" method="get">
	    <input type="text" name="code" placeholder=<%=promo%> /><button class="button" type="submit">Apply</button>
    </form>
    <form action="/checkStock" method="get">
	    <button class="button" type="submit" style="float: right">Checkout (Total: $<%=formattedDouble%>)</button>
    </form>
    <br><br><br>
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
