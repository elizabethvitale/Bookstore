package com.ugabookstore;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
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

import java.lang.Object;
import java.lang.String;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.*;


@WebServlet("/emailPromo")
public class emailPromo extends HttpServlet {
 
	public void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String percent = request.getParameter("percent");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String body = "We don't want you to miss out!\n\nFrom " + start + " to " + end;
                body = body + ", we will be running a discount of " + percent + "% on every book at UGA Bookshop! Feel free to log in and browse books at this discount. Enter the promo code \"";
                body = body + id + "\" at checkout to make sure you recieve your discount.\n\nThanks,\nUGA Bookshop";


		Connection con;
		try{
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			Statement stmt=null;
			String query = "select * from customer where enroll_for_promotes='1' and status='Active';";
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				if(rs.getInt("enroll_for_promotes") == 1){
				String email = rs.getString("email");		
				sendEmail(email,body);
				}
			}
			query = "insert into promotion(promoid, start, end, discount) values('" + Integer.parseInt(id) + "', STR_TO_DATE('" + start + "','%m/%d/%Y'), STR_TO_DATE('" + end + "','%m/%d/%Y'),'" + Double.parseDouble(percent) + "');";
			int result = stmt.executeUpdate(query);	
		}catch(Exception e){
			System.out.println(e);
			//redirect did not enter into DB
			return;
		}
		HttpSession session = request.getSession(true);
		session.setAttribute(percent, null);
		session.setAttribute(start, null);
		session.setAttribute(end, null);
		session.setAttribute(id,null);
		response.sendRedirect("/errorpages/promotionSucess.jsp");
	}
        public static void sendEmail(String emailAdd,String body){
                String host = "smtp.gmail.com";
                System.out.println(emailAdd);
                System.out.println(body);
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
                msg.setSubject("Discount Code", "UTF-8");
                msg.setText(body, "UTF-8");
                msg.setSentDate(new Date());
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAdd, false));
                System.out.println("Message is ready");
                Transport.send(msg);

                System.out.println("Email Sent Successfully!!");
                }catch(Exception e){
                        e.printStackTrace();
                }
        }


}
