package com.ugabookstore;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.HttpSession;
 

 

 
@WebServlet("/loggedUpdatePwd")
public class loggedUpdatePwd extends HttpServlet {
 
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		int id = (Integer) session.getAttribute("customerid");
		String pwd1 = request.getParameter("newPwd1");
		String pwd2 = request.getParameter("newPwd2");
		String userpwd = request.getParameter("currentPwd");
		
		if(!pwd1.equals(pwd2)){
			response.sendRedirect("/error.jsp"); //doesnt exist...
			return;
		}

		Connection con;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			Statement stmt = null;
			String query = "select * from customer where customerid='"+id+"';";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			String temp_code = "";
			if(rs.next()){
				temp_code = rs.getString("password");		
			}

			if(!temp_code.equals(getSha1(userpwd))){
                        response.sendRedirect("/error.jsp"); //doesnt exist...
                        return;		
			}
			String password = getSha1(pwd1);
			stmt = con.createStatement(); 
			query = "update customer set password='" + password + "' where customerid='" + id + "';";
			int rt = stmt.executeUpdate(query);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		response.sendRedirect("index.jsp");

	}


        public static String getSha1(String input) {
                try {
                        MessageDigest m = MessageDigest.getInstance("SHA-1");
                        byte[] messageDigest = m.digest(input.getBytes());

                        BigInteger no = new BigInteger(1, messageDigest);
                        String hashtext = no.toString(16);

                        while (hashtext.length() < 32) {
                                hashtext = "0" + hashtext;
                        }
                        return hashtext;
                } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                }
        }

}
