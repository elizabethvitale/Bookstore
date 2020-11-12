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


@WebServlet("/confirm")
public class confirm extends HttpServlet {
 
	public void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String customerID = request.getParameter("confirmID");



		Connection con;

	try{
			//CONFIRMS IT MATCHES THE DATABASE
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			Statement stmt=null;
			String query = "select * from customer where email='"+email+"';";
			stmt = con.createStatement();
			int db_conf_code=0;
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				db_conf_code = rs.getInt("customerid");
			}
			String id = db_conf_code + "";
			System.out.println(id);
			System.out.println(customerID);
			if(!(id.equals(customerID))){
			response.sendRedirect("/errorpages/codeIncorrect.jsp");
			return;
			}
			
			//GENERATES NEW UNQIUE ID
			int newid=0;
			stmt = con.createStatement();
			query = "select max(customerid) from customer;";
			ResultSet ra=stmt.executeQuery(query);
			while(ra.next()){
				newid = ra.getInt("max(customerid)");
			}
			newid = newid + 1;
			if(newid < 10000){
				newid = 10000;
			}
			System.out.println(newid);
			//INSERTS NEW UNIQUE ID AND STATUS INTO DATABASE
			stmt = con.createStatement();
			query = "update customer set customerid='" + newid + "', status='Active' where email='" + email +"';";	
			System.out.println(query);
			int result = stmt.executeUpdate(query);
			//CREATE SHOPPING CART FOR USER
			stmt = con.createStatement();
			query = "select max(cartid) from cart;";
			ra = stmt.executeQuery(query);
			int cartid=0;
			while(ra.next()){
				cartid = ra.getInt("max(cartid)");
			}
			cartid = cartid + 1;
			if(cartid < 3000){
				cartid = 3000;
			}
			stmt = con.createStatement();
			query = "insert into cart (cartid, customercartid) values('" + cartid + "','" + newid + "');";
			int result2 = stmt.executeUpdate(query);	
			response.sendRedirect("/errorpages/sucess.jsp");	
	}catch(Exception e){
	System.out.println(e.getMessage());
	}
	}



}
