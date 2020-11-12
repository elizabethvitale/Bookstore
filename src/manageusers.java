package com.ugabookstore;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/manageusers")
public class manageusers extends HttpServlet
{
     public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
      {
         PrintWriter out = res.getWriter();
         res.setContentType("text/html");
         out.println("<html><body>");
         try
         {
             Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
             Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from customers;");
             out.println("<table border=1 width=50% height=50%>");
             out.println("<tr><th>EmpId</th><th>EmpName</th><th>Salary</th><tr>");
             while (rs.next())
             {
                 String id = rs.getString("customerid");
                 String fn = rs.getString("fn");
                 String ln = rs.getString("lastname");
                 out.println("<tr><td>" + id + "</td><td>" + fn + "</td><td>" + ln + "</td></tr>");
             }
             out.println("</table>");
             out.println("</html></body>");
             con.close();
            }
             catch (Exception e)
            {
             out.println("error");
         }
     }
 }
