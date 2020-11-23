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
@WebServlet("/displayCart")
public class displayCart
       	extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        	
	    	HttpSession session = request.getSession(false);
		session.setAttribute("shipFName", request.getParameter("firstName"));
		session.setAttribute("shipLName", request.getParameter("lastName"));
		session.setAttribute("shipStreet", request.getParameter("street"));
		session.setAttribute("shipCity", request.getParameter("city"));
		session.setAttribute("shipState", request.getParameter("state"));
		session.setAttribute("shipZip", request.getParameter("zipcode"));
		System.out.println("SHIPPING INFO SET HERE IN DISPLAY CART");
		
		String cartid ="";
		try{
	    	cartid = (String)session.getAttribute("cartid");
		}catch(Exception e){
			response.sendRedirect("/errorpages/loginfirst.jsp");
			return;
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
		session.setAttribute("booksCart", books);

		session.setAttribute("shipFName", request.getParameter("firstName"));
		session.setAttribute("shipLName", request.getParameter("lastName"));
		session.setAttribute("shipStreet", request.getParameter("street"));
		session.setAttribute("shipCity", request.getParameter("city"));
		session.setAttribute("shipState", request.getParameter("state"));
		session.setAttribute("shipZip", request.getParameter("zipcode"));

            	String page = "/checkout/checkout-review.jsp";
            	RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            	requestDispatcher.forward(request, response);
        } catch (Exception ex) {
            	ex.printStackTrace(System.out);
		response.sendRedirect("/errorpages/checkout_error.jsp");
        }

	
         
    }
}
