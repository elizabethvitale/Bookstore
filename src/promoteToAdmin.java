package com.ugabookstore;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

import javax.servlet.annotation.WebServlet;

@WebServlet("/promoteToAdmin")
public class promoteToAdmin extends HttpServlet
{
     public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
       String id = req.getParameter("id");
   		 Connection con;

       	try{
       			//CONFIRMS IT MATCHES THE DATABASE
       			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
       			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
       			Statement stmt= con.createStatement();
            Statement stmt3= con.createStatement();
       			String query1 = "select customerid, firstname, lastname, password, email from customer where customerid = " + id;
            String query2 = "update customer set employee = -1 where customerid = " + id;
            String query3 = "INSERT INTO admin (adminid, firstname, lastname, password, email) VALUES (?, ?, ?, ?, ?)";
            ResultSet rs1 = stmt.executeQuery(query1);
            int rs2 = stmt3.executeUpdate(query2);
            rs1.next();
            // 1. Get the customerid, firstname, lastname, password, and email FROM customer table
            // 2. UPDATE customers SET employee=-1 WHERE customerid=...
            //    Example: query = "update customer set employee='" + (setEmployee == 0 ? 1 : 0) + "' where customerid='" + id +"';";

            // 3. INSERT the information from number 1 into the admin table which has the following fields
            //      - adminid, firstname, lastname, password, email
            //    Example: String query = "INSERT INTO admin (adminid, firstname, lastname, password, email) VALUES (?, ?, ?, ?)";
            String fn = rs1.getString("firstname");
            String ln = rs1.getString("lastname");
            String pw = rs1.getString("password");
            String email = rs1.getString("email");

           PreparedStatement stmt2 = con.prepareStatement(query3);
           stmt2.setString(1, id);
           stmt2.setString(2, fn);
           stmt2.setString(3, ln);
           stmt2.setString(4, pw);
           stmt2.setString(5, email);
           int row = stmt2.executeUpdate();

            res.sendRedirect("/admin/manageusers.jsp");
       	}catch(Exception e){
       	  System.out.println(e.getMessage());
       	}
     }
 }
