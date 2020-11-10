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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import javax.servlet.http.HttpSession;
@WebServlet("/submitPromo")
public class submitPromo extends HttpServlet {
 
	public void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("promocode");
		String percent = request.getParameter("percent");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		try{
			int percent2 = Integer.parseInt(percent);
			if(percent2 >= 100 || percent2 <=0){
			//incorrect percentage redirect
			return;
			}
		}catch(Exception e){
			System.out.println(e);
			//incorrect percentage redirect
			return;
		}
		
		try{
		        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");	
			Date begin = format.parse(start);
			Date ending = format.parse(end);
			if(begin.compareTo(ending)>0){
				//date 2 occurs first
				return;
			}else if(begin.compareTo(ending) ==0){
				//these are the same dates
				return;
			}
		}catch(Exception e){
			System.out.println(e);
			//redirect to incorrect date error page
			return;
		}
		Connection con;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			Statement stmt=null;
			String query = "select * from promotion where promoid='" + id + "';";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()){
			//promoid code already registered
			return;
			}

		}catch(Exception e){
			System.out.println(e);
			//redirect string id is incorrect
			return;
		}
		HttpSession session = request.getSession(true);
		session.setAttribute("id", id);
		System.out.println(id);
		session.setAttribute("percent", percent);
		session.setAttribute("start", start);
		session.setAttribute("end", end);
		response.sendRedirect("/admin/confirmpromotion.jsp");
	}



}
