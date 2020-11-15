package com.ugabookstore;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import javax.servlet.annotation.WebServlet;

@WebServlet("/suspend")
public class suspend extends HttpServlet
{
     public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
       String id = req.getParameter("id");
   		 Connection con;

        try{
       			//CONFIRMS IT MATCHES THE DATABASE
       			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
       			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
       			Statement stmt2= con.createStatement();
       			String query2 = "select `suspended` from customer where customerid='"+id+"';";
       			ResultSet rs2 = stmt2.executeQuery(query2);

            int setSuspended = 0;
       			if(rs2.next()){
       				setSuspended = rs2.getInt("suspended");
       			}

            stmt2 = con.createStatement();
      			query2 = "update customer set suspended='" + (setSuspended == 0 ? 1 : 0) + "' where customerid='" + id +"';";
      			int result2 = stmt2.executeUpdate(query2);
            res.sendRedirect("/admin/manageusers.jsp");
       	}catch(Exception e){
       	  System.out.println(e.getMessage());
       	}

     }
 }
