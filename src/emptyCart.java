package com.ugabookstore;

import java.io.IOException;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
@WebServlet("/emptyCart")
public class emptyCart
       	extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		String cartid ="";
		HttpSession session = request.getSession(false);
		try{
	    	cartid = (String)session.getAttribute("cartid");
		}catch(Exception e){
			response.sendRedirect("/errorpages/loginfirst.jsp");
			return;
		}
		try{
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
		Statement stmt = con.createStatement();
		String query = "delete from cart_item where cartid='" + cartid + "';";
	       	System.out.println(query);
		stmt.executeUpdate(query);	
		}catch(Exception e){
			System.out.println(e);
		}
		
		BookDAO dao = new BookDAO();
         
        try {

		List<Integer>bookIds = new ArrayList<Integer>(dao.getCart(cartid));
		List<String> blobs = new ArrayList<String>(dao.getBlobs(bookIds, bookIds.size()));
            	request.setAttribute("cart_ids", bookIds);
		List<Book> books = new ArrayList<Book>();
		for(int i =0; i < bookIds.size(); i++){
		Book book = dao.get(bookIds.get(i));
		books.add(book);
		}
		request.setAttribute("booksCart", books);
		request.setAttribute("imagesCart", blobs);
            	String page = "/checkout/shoppingcart.jsp";
            	RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            	requestDispatcher.forward(request, response);
        } catch (Exception ex) {
            	ex.printStackTrace(System.out);
		response.sendRedirect("/errorpages/editbook_error.jsp");
        }

	
         
    }
}
