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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
 
@WebServlet("/updateCard")
public class updateCard extends HttpServlet {
 
    public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		        HttpSession session = request.getSession(true);
			String cardType = request.getParameter("cardType");
			int id = (Integer) session.getAttribute("customerid");
			String cardNumDB = (String)session.getAttribute("cardNumber");
			String expirationDate = request.getParameter("expirationDate");
			String cardNumber = request.getParameter("cardNum");
			System.out.println(cardNumber);
			System.out.println(expirationDate);
			System.out.println(cardType);
			if(!cardNumber.equals("**** **** **** ****")){
				cardNumber = getSha1(cardNumber);
			}else{
				cardNumber = "";
			}
//card number requirements, c&p here

			Connection con;
			try{
				System.out.println(id);
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
				Statement stmt=null;
				stmt = con.createStatement();
				String query = "select * from payment_card where userid='" + id + "';";
				ResultSet rs =stmt.executeQuery(query);
				System.out.println(query);
				if(rs.next()) {
				if(!cardNumber.equals("")){
				query = "update payment_card set cardnumber='" + cardNumber + "', type='" + cardType + "', expdate='" + expirationDate + "' where userid='" + id + "' and cardnumber='" + cardNumDB + "';";      
                		session.setAttribute("cardNumber", cardNumber);
				}else{
				query = "update payment_card set type='" + cardType + "', expdate='" + expirationDate + "' where userid='" + id + "' and cardnumber='" + cardNumDB + "';";
				}
				System.out.println(query); 
                                int result = stmt.executeUpdate(query);	
				}else{
				query = "insert into payment_card (cardnumber, type, expdate, userid) values ('" + cardNumber + "','" + cardType + "','" + expirationDate + "','" + id + "');";
				session.setAttribute("cardNumber", cardNumber);
				int result = stmt.executeUpdate(query);
				System.out.println("into second");
				}
				response.sendRedirect("index.jsp");
	}catch(Exception e){
	System.out.println(e.getMessage());
	}
		session.setAttribute("expirationDate", expirationDate); 
		session.setAttribute("cardType", cardType); 
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
