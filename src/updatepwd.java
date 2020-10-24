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

 

 
@WebServlet("/updatepwd")
public class updatepwd extends HttpServlet {
 
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
		String pwd1 = request.getParameter("cpwd1");
		String pwd2 = request.getParameter("cpwd2");
		String email = request.getParameter("email");
		String code = request.getParameter("confirmID");
		if(!pwd1.equals(pwd2)){
			response.sendRedirect("/error.jsp"); //doesnt exist...
			return;
		}

		Connection con;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			Statement stmt = null;
			String query = "select * from customer where email='"+email+"';";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			String temp_code = "";
			if(rs.next()){
				temp_code = rs.getString("password");		
			}
			System.out.println(code);

			temp_code = temp_code.substring(0,6);
			if(!temp_code.equals(code)){
                        response.sendRedirect("/error.jsp"); //doesnt exist...
                        return;		
			}
			String password = getSha1(pwd1);
			stmt = con.createStatement(); 
			query = "update customer set password='" + password + "' where email='" + email + "';";
			int rt = stmt.executeUpdate(query);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

		response.sendRedirect("user/login.jsp");

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
