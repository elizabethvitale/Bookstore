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
 
@WebServlet("/search")
public class search extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        	String term = "";
		String keyword = request.getParameter("keyword");
        	BookDAO dao = new BookDAO();
         
        try {
        	List<Integer> bookIds = new ArrayList<Integer>(dao.getBookIds(term, keyword));
		List<String> titles = new ArrayList<String>(dao.getBookTitles(bookIds, bookIds.size()));
            	request.setAttribute("book_ids", bookIds);
		request.setAttribute("titles", titles);
            	System.out.println(bookIds);
	       	System.out.println(titles);	
            	String page = "search.jsp";
            	RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            	requestDispatcher.forward(request, response);
        } catch (Exception ex) {
            	ex.printStackTrace(System.out);
		response.sendRedirect("/errorpages/editbook_error.jsp");
        }

	
         
    }
}
