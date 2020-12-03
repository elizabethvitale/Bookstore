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


@WebServlet("/checkCart")
public class checkCart extends HttpServlet {
 
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
		Connection con;

	try {
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			Statement stmt=null;
			String query = "select count(bookid) from cart_item where cartid='" + cartid + "'";
			stmt = con.createStatement();
			int db_conf_code=0;
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			int booksInCart = rs.getInt("count(bookid)");
			if (booksInCart == 0) {
				response.sendRedirect("/errorpages/noBookInCart.jsp");
			} else {
				response.sendRedirect("/checkStock");
			}
			
	} catch(Exception e){
		System.out.println(e.getMessage());
	}
	
	}


}
