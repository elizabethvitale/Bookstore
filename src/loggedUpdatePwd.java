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
 
 

 

 
@WebServlet("/loggedUpdatePwd")
public class loggedUpdatePwd extends HttpServlet {
 
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		int id = (Integer) session.getAttribute("customerid");
		String pwd1 = request.getParameter("newPwd1");
		String pwd2 = request.getParameter("newPwd2");
		String userpwd = request.getParameter("currentPwd");
		String email = (String)session.getAttribute("email");
		

		//checking to make sure the length of password is 8+
		if (pwd1.length() < 8) {
		response.sendRedirect("/errorpages/shortpassword.jsp");
		return;
		}


		if(!pwd1.equals(pwd2)){
			response.sendRedirect("/errorpages/passwordsdontmatch.jsp");
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
			//WRONG CURRENT PWD
                        response.sendRedirect("/errorpages/currentIncorrect.jsp"); //doesnt exist...
                        return;		
			}
			String password = getSha1(pwd1);
			stmt = con.createStatement(); 
			query = "update customer set password='" + password + "' where customerid='" + id + "';";
			int rt = stmt.executeUpdate(query);
			if(rt == 1){
				sendEmail(email, "You have successfully reset your password. If you did not request to change your password, please change it immediately.");
			}


		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		//SEND TO SUCCESS PAGE
		response.sendRedirect("/errorpages/profilesuccess.jsp");

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


	public static void sendEmail(String emailAdd,String body){
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
	      	msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAdd, false));
	      	System.out.println("Message is ready");
    	  	Transport.send(msg);  

	      	System.out.println("Email Sent Successfully!!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
