package com.ugabookstore;

import java.io.IOException;
import java.sql.SQLException;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import javax.activation.*;
import com.ugabookstore.*;
import java.util.Random;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet("/addToCart")
public class addToCart extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bookid = request.getParameter("bookid");
	int quantity = Integer.parseInt(request.getParameter("quantity"));
	HttpSession session = request.getSession(false);
	String cartid = "";	
	try{	
		cartid = (String)session.getAttribute("cartid");
	}catch(Exception e){
		response.sendRedirect("/errorpages/loginfirst.jsp");
		return;

	}
	if(quantity <= 0){
		response.sendRedirect("/errorpages/addBookError.jsp");
		return;
	}
	Connection con;
        try {
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	   	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "rootroot");
	    	Statement stmt = null;
		stmt = con.createStatement();
		String query = "insert into cart_item (cartid, bookid) values('" + cartid + "','" + bookid + "');";
		stmt.executeUpdate(query);
		response.sendRedirect("/errorpages/booktocart.jsp");
		return;

        } catch (Exception ex) {
            ex.printStackTrace(System.out);
		response.sendRedirect("/errorpages/addBookError.jsp");
		return;
        }

	
         
    }
}
