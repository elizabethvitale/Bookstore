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
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart; 

 
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
        String zip = request.getParameter("zip");
        String ctype = request.getParameter("ctype");
        String cnum = request.getParameter("cnum");
        String exd = request.getParameter("exd");
	int min = 1000;
	int max = 9999;
	Random rand = new Random();
	int randomNum = rand.nextInt((max - min) + 1) + min;
	//would need to make sql call to ensure email is unique.
	//would need to make sql call to get unique r id value by max(all user ids) + 1

	User new_user = new User(randomNum, pwd, fname, lname, email, number, "Inactive", false);
	int register_user = new backendUser().register(new_user);	
	if(register_user == 0){
	//send registration email....
	}


	if(fname=="" || email=="" || number =="" || pwd==""){
	//account for other formatting issues too.
	//throw error
	}else{
	

	//hash password
	response.sendRedirect("/index.jsp");
	}
}
	public static void sendEmail(Session session,String emailAdd,String body){
		try{
		MimeMessage msg = new MimeMessage(session);
	      	msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      	msg.addHeader("format", "flowed");
	      	msg.addHeader("Content-Transfer-Encoding", "8bit");
		msg.setFrom(new InternetAddress("ugabookstore@books.com", "NoReply-JD"));
		msg.setReplyTo(InternetAddress.parse("ugabookstore@books.com",false));
		msg.setSubject("Registration Email Confirmation", "UTF-8");
		msg.setText(body, "UTF-8");
		msg.setSentDate(new Date());
	      	msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAdd, false));
	      	System.out.println("Message is ready");
    	  	Transport.send(msg);  

	      	System.out.println("EMail Sent Successfully!!");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
