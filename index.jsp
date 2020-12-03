<%@ page import="com.ugabookstore.User"%>
<%@ page import="com.ugabookstore.backendUser"%>
<%@ page import="com.ugabookstore.Book"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ugabookstore.BookDAO"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans:wght@300;500;600&family=Peddana&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="css/main.css">
  </head>
  <body>
<%@ page session="false" %>
  <header>
      <div>
        <h2><div><a href="index.jsp">UGA Bookshop</a></div></h2>
        <section class="searchbox-container">
          <div class="searchbox">
		  <form action="/search" method="get">
		    <input type="text" name="keyword" placeholder="Browse by author, by title..">
           	<button type="submit"><img src="image/search.svg"></button>
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
		<button class="button" type="submit"><img src="image/shoppingcart.svg"></button>
		</form>
	      </li>
              <li style='position: relative;'>
                <img id="auth-dropdown-toggle" src="image/account.svg">
                <ul class='auth-dropdown'>
                  <li>
                    <a href="user/login.jsp">Login</a>
                  </li>
                  <li>
                    <a href="user/register.jsp">Register</a>
                  </li>
                  <li>
                    <a href="user/editprofile.jsp">Edit Profile</a>
                  </li>
                  <li>
                    <a href="user/logout.jsp">Logout</a>
                  </li>
                </ul>
              </li>
            </ul>
          </nav>
        </section>
      </div>
    </header>

<%
	String message = "";
       HttpSession session=request.getSession(false);
        if(session!=null){
        message="Welcome, " + (String)session.getAttribute("firstName")
         + " " + (String)session.getAttribute("lastName");
}%>

    <div id="homeCarousel" class="carousel slide" data-ride="carousel">
      <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="https://images.squarespace-cdn.com/content/v1/5d39be915804eb00017ab307/1597669537598-4B3ZSMGYX8B8MK5HLXBV/ke17ZwdGBToddI8pDm48kDCS9NWuBBzb3g35P34aRwRZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZamWLI2zvYWH8K3-s_4yszcp2ryTI0HqTOaaUohrI8PIYvxqG26orBnNg9dHOqYbKQsNDnAkGmvIcYWp2psWtn0KMshLAGzx4R3EDFOm1kBS/Evening+and+Morning_Amazon+banners_Two+%282%29.jpg" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
          <img src="https://www.panmacmillan.com.au/wp-content/uploads/2020/09/to-sleep-in-a-sea-of-stars_Web-banner.png" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
          <img src="https://www.wattpad.com/banner?id=243484431&amp;v=378000" class="d-block w-100" alt="...">
        </div>
      </div>
    </div>

    <main>
	<h4 class="welcome-name"><%=message%></h4>
	<br>
      <div>
	<h1 class="titles">Highest Rated Books</h1>
        <div class="slider responsive">
<%
	try{
		BookDAO dao = new BookDAO();
		List<Integer> bookIds = new ArrayList<Integer>(dao.getBookIds("index1", ""));
		List<String> blobs = new ArrayList<String>(dao.getBlobs(bookIds, bookIds.size()));
		List<Book> books = new ArrayList<Book>();
		for(int i =0; i < bookIds.size(); i++){
		Book book = dao.get(bookIds.get(i));
		books.add(book);
		%>
		<form action="/viewBook" method="get">
		<input type="image" alt="submit" height='200' src="data:image/jpg;base64, <%=blobs.get(i)%>"/>
		<input type="hidden" value="<%=bookIds.get(i)%>" name="bookid">
		</form>
		<%
		}

	}catch(Exception e){
	System.out.println(e);
	}
%>
        </div>
      </div><br>

	<h1 class="titles">Featured Books</h1>
        <div class="slider responsive">
<%
	try{
		BookDAO dao = new BookDAO();
		List<Integer> bookIds2 = new ArrayList<Integer>(dao.getBookIds("index2", ""));
		List<String> blobs2 = new ArrayList<String>(dao.getBlobs(bookIds2, bookIds2.size()));
		List<Book> books2 = new ArrayList<Book>();
		for(int i =0; i < bookIds2.size(); i++){
		Book book = dao.get(bookIds2.get(i));
		books2.add(book);
		%>
		<form action="/viewBook" method="get">
		<input type="image" alt="submit" height='200' src="data:image/jpg;base64, <%=blobs2.get(i)%>"/>
		<input type="hidden" value="<%=bookIds2.get(i)%>" name="bookid">
		</form>
		<%
		}

	}catch(Exception e){
	System.out.println(e);
	}
%>
        </div>
      </div><br>



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
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="https://kenwheeler.github.io/slick/slick/slick.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    <script type="text/javascript">
    $(document).ready(function() {

  $('.responsive').slick({
      // dots: true,
      infinite: true,
      speed: 300,
      slidesToShow: 8,
      slidesToScroll: 1,
      responsive: [{
          breakpoint: 1024,
          settings: {
              slidesToShow: 6,
              slidesToScroll: 1,
              // centerMode: true,

          }

        }, {
            breakpoint: 800,
            settings: {
                slidesToShow: 3,
                slidesToScroll: 2,
                dots: true,
                infinite: true,

            }


        }, {
            breakpoint: 600,
            settings: {
                slidesToShow: 2,
                slidesToScroll: 2,
                dots: true,
                infinite: true,

            }
        }, {
            breakpoint: 480,
            settings: {
                slidesToShow: 1,
                slidesToScroll: 1,
                dots: true,
                infinite: true,
                autoplay: true,
                autoplaySpeed: 2000,
            }
        }]
    });


  });
    </script>
    <script type="text/javascript" src="http://livejs.com/live.js"></script>
  </body>
</html>
