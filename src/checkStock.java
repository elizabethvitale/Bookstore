package com.ugabookstore;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.activation.*;
import com.ugabookstore.*;
import java.util.Random;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import javax.servlet.http.HttpSession;


@WebServlet("/checkStock")
public class checkStock extends HttpServlet {
 
	public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	    	HttpSession session = request.getSession(false);
		String cartid ="";
		try{
	    	cartid = (String)session.getAttribute("cartid");
		}catch(Exception e){
			response.sendRedirect("/errorpages/loginfirst.jsp");
			return;
		}
	
		BookDAO dao = new BookDAO();
		Connection con;

	try{
			List<Integer>bookIds = new ArrayList<Integer>(dao.getCart(cartid));
			List<Book> books = new ArrayList<Book>();
			for(int i =0; i < bookIds.size(); i++){
			Book book = dao.get(bookIds.get(i));
			books.add(book);
			}
			

			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			Statement stmt=null;
			String query = "select bookid, count(bookid) from cart_item where cartid='" + cartid + "' group by bookid;";
			stmt = con.createStatement();
			int db_conf_code=0;
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				int id = rs.getInt("bookid");
				int count = rs.getInt("count(bookid)");
				Book book = books.get(id);
				int quantity = book.getQuantity();
				if(quantity < count){
					response.sendRedirect("/errorpages/outOfStock.jsp");
				return;
				}
			}
			
	}catch(Exception e){
	System.out.println(e.getMessage());
	}
	response.sendRedirect("/checkout/checkout-billing.jsp");
	}


}
