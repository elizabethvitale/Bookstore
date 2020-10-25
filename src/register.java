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
 
@WebServlet("/register")
public class register extends HttpServlet {
 
    public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
         

	//add optional signup options.
        String fname = request.getParameter("fname");
	String lname = request.getParameter("lname");
        String email = request.getParameter("email");
	String number = request.getParameter("number");
	String pwd = request.getParameter("password");
	String street = request.getParameter("street");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        int zip =-1;
	if(!street.equals("")){
	zip = Integer.parseInt(request.getParameter("zip"));
	}
	String ctype = request.getParameter("ctype");
	int cnum = -1;
	if(!ctype.equals("")){
	cnum = Integer.parseInt(request.getParameter("cnum"));
	}
        String exd = request.getParameter("exd");
	int min = 1000;
	int max = 9999;
	Random rand = new Random();
	int randomNum = rand.nextInt((max - min) + 1) + min;
	//would need to make sql call to ensure email is unique.
	//would need to make sql call to get unique r id value by max(all user ids) + 1
	//I HAVE DONE THE ABOVE..THIS NUMBER IS USED FOR EMAIL CONFIRMATION NOW 
	User new_user=null;
	if(street.equals("") && ctype.equals("")){
		System.out.println("first");
		new_user = new User(randomNum, pwd, fname, lname, email, number, "Inactive", false);
	}else if(!street.equals("") && ctype.equals("")){
		System.out.println("second");
		new_user = new User(randomNum, pwd, fname, lname, email, number, "Inactive", false, street, city, state, zip);
	}else if(street.equals("") && !ctype.equals("")){
		new_user = new User(randomNum, pwd, fname, lname, email, number, "Inactive", false, cnum, ctype, exd);
	}else if(!street.equals("") && !ctype.equals("")){
		new_user = new User(randomNum, pwd, fname, lname, email, number, "Inactive", false, street, city, state, zip, cnum, ctype, exd);
	}	

	int register_user = new backendUser().register(new_user);	
	String body = "Thank you for signing up for UGABOOKSTORE.COM!!!\nYour confirmation number is: " + randomNum + "\nPlease enter this on our website!!!";

	if(register_user == 0){
		sendEmail(email,body);
	}



	if(fname=="" || email=="" || number =="" || pwd==""){
	//account for other formatting issues too.
	//throw error
	}else{
	

	//hash password
	response.sendRedirect("/user/registrationconfirm.html");
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
