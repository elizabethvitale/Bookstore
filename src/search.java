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
		List<String> blobs = new ArrayList<String>(dao.getBlobs(bookIds, bookIds.size()));
            	request.setAttribute("book_ids", bookIds);
		List<Book> books = new ArrayList<Book>();
		for(int i =0; i < bookIds.size(); i++){
		Book book = dao.get(bookIds.get(i));
		books.add(book);
		}
		request.setAttribute("books", books);
		request.setAttribute("titles", blobs);
		request.setAttribute("keyword", keyword);
            	String page = "search.jsp";
            	RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            	requestDispatcher.forward(request, response);
        } catch (Exception ex) {
            	ex.printStackTrace(System.out);
		response.sendRedirect("/errorpages/editbook_error.jsp");
        }

	
         
    }
}
