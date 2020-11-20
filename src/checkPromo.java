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

@WebServlet("/checkPromo")
public class checkPromo extends HttpServlet {
 
	public void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code");
		HttpSession session = request.getSession(false);


		Connection con;

	try{
			//CONFIRMS IT MATCHES THE DATABASE
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			Statement stmt=null;
			String query = "select discount from promotion where promoid='"+code+"';";
			stmt = con.createStatement();
			int percentage=0;
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
				percentage = rs.getInt("discount");
			}else{
				response.sendRedirect("/errorpages/InvalidCode.jsp");
				return;
			}
			System.out.println(percentage);
			response.sendRedirect("/viewCart?");
			session.setAttribute("discount", percentage);
			session.setAttribute("promocode", code);


	}catch(Exception e){
	System.out.println(e.getMessage());
	}
	}



}
