package com.ugabookstore;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import javax.servlet.annotation.WebServlet;

@WebServlet("/promote")
public class promote extends HttpServlet
{
     public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
       String id = req.getParameter("id");
   		 Connection con;

       	try{
       			//CONFIRMS IT MATCHES THE DATABASE
       			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
       			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
       			Statement stmt= con.createStatement();
       			String query = "select `employee` from customer where customerid='"+id+"';";
       			ResultSet rs = stmt.executeQuery(query);

            int setEmployee = 0;
       			if(rs.next()){
       				setEmployee = rs.getInt("employee");
       			}

            stmt = con.createStatement();
      			query = "update customer set employee='" + (setEmployee == 0 ? 1 : 0) + "' where customerid='" + id +"';";
      			int result = stmt.executeUpdate(query);
            res.sendRedirect("/admin/manageusers.jsp");
       	}catch(Exception e){
       	  System.out.println(e.getMessage());
       	}
     }
 }
