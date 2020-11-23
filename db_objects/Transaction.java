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
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.servlet.http.HttpSession;
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
public class Transaction {

	public void createTransaction(int userId, int orderId, int cartId, String firstName, String lastName, String date, double price, String email) {
		try {	
			String body = "ORDER#" + orderId + "\n\nThank you for placing an order at UGA Bookshop, " + firstName + " " + lastName;	
			body = body + "\n\nYour order was ordered on " + date + ". The current status of your order is: \"Being shipped\". \nFor more information, check out your shipping progress on your profile.";
			body = body + "\n\nThe books in your order are as follows:";
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			
			String query = "select bookid, count(bookid) from cart_item where cartid='" + cartId + "' group by bookid";
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String tQuery = "INSERT INTO transaction (orderid, bookid, quantity) VALUES (?, ?, ?)";
				PreparedStatement stmt2 = con.prepareStatement(tQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				int bookid = rs.getInt("bookid");
				int count = rs.getInt("count(bookid)");
				stmt2.setInt(1, orderId);
				stmt2.setInt(2, rs.getInt("bookid"));
				stmt2.setInt(3, rs.getInt("count(bookid)"));
				int row = stmt2.executeUpdate();
				stmt2.close();
				String query2 = "select title from book where bookid='" + bookid + "';";
				Statement stmt3 = con.createStatement();
				ResultSet ra = stmt3.executeQuery(query2);
				if(ra.next()){
					String title = ra.getString("title");
					if(count==1){
					body = body + "\n \t- " + count + " copy of \"" + title + "\"";
					}else{
					body = body + "\n \t- " + count + " copies of \"" + title + "\""; 
					}
				}
				stmt3.close();
			}
			body = body + "\n\nFor a total of: $" + price;
			body = body + "\n\n\nYour confirmation number is " +orderId + userId + "\nIf there are issues with this order, please email our site adminstrator ttgiang223@gmail.com with your confirmation number attached.";
			System.out.println(body);
			sendEmail(email, body);
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
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
