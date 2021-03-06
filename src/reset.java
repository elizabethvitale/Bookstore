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
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;



@WebServlet("/reset")
public class reset extends HttpServlet{

    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {


		String email = request.getParameter("email");
		String temp_code = "";

	//email is blank
	if (email.equals("")) {
			response.sendRedirect("/errorpages/blankRequired1.jsp");
			return;
	}
	//email is incorrect
	String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	if (email.matches(regex) == false) {
		response.sendRedirect("/errorpages/emailFormat1.jsp");
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
			if(rs.next()){
				temp_code = rs.getString("password");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		try{
		temp_code = temp_code.substring(0,6);
		}catch(Exception e){
			response.sendRedirect("/errorpages/incorrectEmail.jsp");
			return;
		}
		

		String body="We are sorry you forgot your password!\nBelow is a temporary code you can use to reset your password on our website. If you did not request this email, please ignore it.\n\nTemporary code: " + temp_code + "\n\nPlease enter this at our website forgot password portal, linked below.\n\nhttp://localhost:8080/user/forgotpwd2.jsp";

		String host = "smtp.gmail.com";
		final String username="ugabookstore2020@gmail.com";
		final String password="Ugabookstore1!";
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		Session session = Session.getInstance(properties, new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
			return new PasswordAuthentication(username,password);
		}});
		try{
		MimeMessage msg = new MimeMessage(session);
	      	msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
		msg.setFrom(new InternetAddress("ugabookstore2020@gmail.com"));
		msg.setSubject("Registration Email Confirmation", "UTF-8");
		msg.setText(body, "UTF-8");
		msg.setSentDate(new Date());
	      	msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));
	      	System.out.println("Message is ready");
    	  	Transport.send(msg);

	      	System.out.println("Email Sent Successfully!!");
		}catch(Exception e){
			e.printStackTrace();
		}
	response.sendRedirect("/user/forgotpwd2.jsp");
	}


}
