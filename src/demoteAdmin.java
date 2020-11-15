package com.ugabookstore;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import javax.servlet.annotation.WebServlet;

@WebServlet("/demoteAdmin")
public class demoteAdmin extends HttpServlet
{
     public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
       String id = req.getParameter("id");
   		 Connection con;

       	try{
       			//CONFIRMS IT MATCHES THE DATABASE
       			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
       			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
       			Statement stmt= con.createStatement();
            Statement stmt2= con.createStatement();
       			String query1 = "DELETE from admin WHERE adminid = " + id;
            String query2 = "update customer set employee = 1 where customerid = " + id;
            int rs1 = stmt.executeUpdate(query1);
            int rs2 = stmt2.executeUpdate(query2);

            res.sendRedirect("/admin/manageusers.jsp");
       	}catch(Exception e){
       	  System.out.println(e.getMessage());
       	}
     }
 }
