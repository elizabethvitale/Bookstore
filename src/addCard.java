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

@WebServlet("/addCard")
public class addCard extends HttpServlet {

    public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
		        HttpSession session = request.getSession(true);
			String cardType = request.getParameter("cardType");
			int id = (Integer) session.getAttribute("customerid");
			String expirationDate = request.getParameter("expirationDate");
			String cardNumber = request.getParameter("cardNum");

			cardNumber = getSha1(cardNumber);

	if (cardType.equals("") || expirationDate.equals("") || cardNumber.equals("")) {
	response.sendRedirect("/errorpages/blankRequired2.jsp");
	return;
	}

	//cardNum isn't 16 digits
  else if (cardNumber.equals("") == false && cardNumber.length() != 16) {
	response.sendRedirect("/errorpages/cardInvalid2.jsp");
		return;
	}

	//cardType isn't a string
  else if ( (cardType.matches("[a-zA-Z]+") == false) && (cardType.equals("") == false) ){
	response.sendRedirect("/errorpages/cardtypeInvalid1.jsp");
		return;
	}

	//card exp date format is incorrect
  else if ( (expirationDate.equals("") == false) && (expirationDate.charAt(2) != '/') || (expirationDate.equals("") == false)&&(expirationDate.length() != 5) ) {
	response.sendRedirect("/errorpages/cardExpirationInvalid1.jsp");
		return;
	}
	else if ((expirationDate.equals("") == false) && ((Character.isDigit(expirationDate.charAt(0)) == false) || (Character.isDigit(expirationDate.charAt(1)) == false) || (Character.isDigit(expirationDate.charAt(3)) == false) ||(Character.isDigit(expirationDate.charAt(4)) == false) )) {
	response.sendRedirect("/errorpages/cardExpirationInvalid1.jsp");
	return;
	}

			Connection con;
			try{
				System.out.println(id);
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
				Statement stmt=null;
				stmt = con.createStatement();
				String query = "select count(userid) as num from payment_card where userid='" + id + "';";
				System.out.println(query);
				ResultSet rs = stmt.executeQuery(query);
				int counter=0;
				if(rs.next()){
					System.out.println(rs.getString("num"));
					counter = rs.getInt("num");
				}
				query = "insert into payment_card (cardnumber, type, expdate, userid) values ('" + cardNumber + "','" + cardType + "','" + expirationDate + "','" + id + "');";
				System.out.println(query);
				int result = stmt.executeUpdate(query);
				if(counter == 1){
				session.setAttribute("cardNumber2", cardNumber);
				session.setAttribute("cardType2",cardType);
				session.setAttribute("expirationDate2",expirationDate);
				}
				if(counter == 2){
				session.setAttribute("cardNumber3", cardNumber);
				session.setAttribute("cardType3",cardType);
                                session.setAttribute("expirationDate3",expirationDate);
				}
				response.sendRedirect("index.jsp");



	}catch(Exception e){
	System.out.println(e.getMessage());
	}
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
