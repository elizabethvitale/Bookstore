package com.ugabookstore;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.HttpSession;
 
@WebServlet("/updateUserInfo")
public class updateUserInfo extends HttpServlet {
 
    public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		        HttpSession session = request.getSession(true);
			int id = (Integer) session.getAttribute("customerid");
			String firstName = request.getParameter("firstName");
			System.out.println(firstName);
			String lastName = request.getParameter("lastName");
			String phone = request.getParameter("phone");
			Connection con;
			try{
				//dont forget to update user object
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
				Statement stmt=null;
				stmt = con.createStatement();
				String query = "update customer set firstname='" + firstName + "', lastname='" + lastName + "', phone='" + phone + "' where customerid='" + id + "';";
		System.out.println(query);
				int result = stmt.executeUpdate(query);
				response.sendRedirect("index.jsp");
	}catch(Exception e){
	System.out.println(e.getMessage());
	}
		session.setAttribute("firstName", firstName);	
		session.setAttribute("lastName", lastName); 
		session.setAttribute("phone", phone); 
}
}
