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

@WebServlet("/updateAddress")
public class updateAddress extends HttpServlet {

    public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		        HttpSession session = request.getSession(true);
			int zip = 0;
			try{
			zip = Integer.parseInt(request.getParameter("zip"));
			}catch(Exception e){
			  response.sendRedirect("/errorpages/blankRequired3.jsp");
 			 return;
			}
			String city = request.getParameter("city");
			int id = (Integer) session.getAttribute("customerid");
			String street = request.getParameter("street");
			String state = request.getParameter("state");

			Connection con;

	if ( (city.equals("")) || (street.equals("")) || (state.equals("")) ) {
		response.sendRedirect("/errorpages/blankRequired3.jsp");
		return;
	}

		try{
				System.out.println(id);
				//dont forget to update user object
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
				Statement stmt=null;
				stmt = con.createStatement();
				String query = "select * from shipping_address where shippingid='" + id + "';";
				ResultSet rs =stmt.executeQuery(query);
				System.out.println(query);
				if(rs.next()) {
				query = "update shipping_address set city='" + city + "', street='" + street + "', zipcode='" + zip + "', state='" + state + "' where shippingid='" + id + "';";
                		System.out.println(query);
                                int result = stmt.executeUpdate(query);
				}else{
				query = "insert into shipping_address (street, state, zipcode, shippingid, city) values ('" + street + "','" + state + "','" + zip + "','" + id + "','" + city + "');";
				int result = stmt.executeUpdate(query);
				System.out.println("into second");
				}
				response.sendRedirect("/errorpages/profilesuccess.jsp");
	}catch(Exception e){
	System.out.println(e.getMessage());
	}
		session.setAttribute("zip", zip);
		session.setAttribute("street", street);
		session.setAttribute("city", city);
		session.setAttribute("state", state);
}
}
