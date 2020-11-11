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
			String checkbox = request.getParameter("subscribed");
			//checks if names or phone is blank
			if ( firstName.equals("") || lastName.equals("") || phone.equals("") ){
			response.sendRedirect("/errorpages/return2editprof.jsp");
			return;
			}

			//if phone number is a digit
			if (phone.matches("-?\\d+(\\d+)?") == false) {
			response.sendRedirect("/errorpages/phoneIncorrect2.jsp");
			return;
			}

			//if phone is not 10 digits
			if (phone.length() != 10) {
			response.sendRedirect("/errorpages/phoneIncorrect2.jsp");
			return;
			}
			try{
				boolean x =checkbox.equals("on");
			}catch(Exception e){
				checkbox = "off";
			}
			try{
				//dont forget to update user object
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
				Statement stmt=null;
				stmt = con.createStatement();
				String query = "";
				if(checkbox.equals("on")){
				query = "update customer set firstname='" + firstName + "', lastname='" + lastName + "', phone='" + phone + "', enroll_for_promotes='1'" + " where customerid='" + id + "';";
				session.setAttribute("enroll",true);
				}else{
				query = "update customer set firstname='" + firstName + "', lastname='" + lastName + "', phone='" + phone + "', enroll_for_promotes='0'" + " where customerid='" + id + "';";
				session.setAttribute("enroll",false);
				}
				System.out.println(query);
				
				int result = stmt.executeUpdate(query);
				response.sendRedirect("/errorpages/profilesuccess.jsp");
	}catch(Exception e){
	System.out.println("error");
	System.out.println(e.getMessage());
	}
	
		session.setAttribute("firstName", firstName);
		session.setAttribute("lastName", lastName);
		session.setAttribute("phone", phone);
}
}
