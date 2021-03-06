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

import java.lang.Object;
import java.lang.String;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.*;

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
        String checkbox = request.getParameter("subscribed");
	boolean enroll = true;
	try{
		boolean x =checkbox.equals("on");
		enroll = true;
	}catch(Exception e){
		enroll = false;
	}
	int zip =-1;
	try{
	if(!street.equals("")){
	zip = Integer.parseInt(request.getParameter("zip"));
	}}catch(Exception e){
		response.sendRedirect("/errorpages/incorrectFormat.jsp");
		return;
	}
	String ctype = request.getParameter("ctype");
	String cnum = request.getParameter("cnum");
        String exd = request.getParameter("exd");
	boolean spaceFlag = false;
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
		new_user = new User(randomNum, pwd, fname, lname, email, number, "Inactive", enroll);
	}else if(!street.equals("") && ctype.equals("")){
		System.out.println("second");
		new_user = new User(randomNum, pwd, fname, lname, email, number, "Inactive", enroll, street, city, state, zip);
	}else if(street.equals("") && !ctype.equals("")){
		new_user = new User(randomNum, pwd, fname, lname, email, number, "Inactive", enroll, cnum, ctype, exd);
	}else if(!street.equals("") && !ctype.equals("")){
		new_user = new User(randomNum, pwd, fname, lname, email, number, "Inactive", enroll, street, city, state, zip, cnum, ctype, exd);
	}

	if (fname.equals("") || lname.equals("") || email.equals("") || number.equals("") || pwd.equals("")) {
	response.sendRedirect("/errorpages/blankRequired1.jsp");
	return;
	}

	//checking to make sure the length of password is 8+
	if (pwd.length() < 8) {
	response.sendRedirect("/errorpages/shortPw1.jsp");
	return;
	}

	//checking to make sure there are no white spaces in the pw
	for (int i = 0; i < pwd.length(); i++) {
		if (pwd.charAt(i) == ' ') {
			spaceFlag = true;
			break;
		}
	}
	if (spaceFlag == true) {
	response.sendRedirect("/errorpages/whitespacePw1.jsp");
	return;
	}

	//email is incorrect
	String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	if (email.matches(regex) == false) {
	response.sendRedirect("/errorpages/emailFormat1.jsp");
	return;
	}
	//missing card info response.sendRedirect("/user/registrationconfirm.html");
	else if ( (ctype.equals("") == false) && (cnum.equals("") == true) && (exd.equals("") == true) ) {
		response.sendRedirect("/errorpages/blankRequired1.jsp");
		return;
	}
	else if ( (ctype.equals("") == true) && (cnum.equals("") == false) && (exd.equals("") == true) ) {
		response.sendRedirect("/errorpages/blankRequired1.jsp");
		return;
	}
	else if ( (ctype.equals("") == true) && (cnum.equals("") == true) && (exd.equals("") == false) ) {
		response.sendRedirect("/errorpages/blankRequired1.jsp");
		return;
	}
	else if ( (ctype.equals("") == false) && (cnum.equals("") == false) && (exd.equals("") == true) ) {
		response.sendRedirect("/errorpages/blankRequired1.jsp");
		return;
	}
	else if ( (ctype.equals("") == false) && (cnum.equals("") == true) && (exd.equals("") == false) ) {
		response.sendRedirect("/errorpages/blankRequired1.jsp");
		return;
	}
	else if ( (ctype.equals("") == true) && (cnum.equals("") == false) && (exd.equals("") == false) ) {
		response.sendRedirect("/errorpages/blankRequired1.jsp");
		return;
	}

	//cardNum isn't 16 digits
	else if (cnum.equals("") == false && cnum.length() != 16) {
	response.sendRedirect("/errorpages/cardInvalid1.jsp");
		return;
	}

	//cardType isn't a string
	else if ( (ctype.matches("[a-zA-Z]+") == false) && (ctype.equals("") == false) ){
	response.sendRedirect("/errorpages/cardtypeInvalid1.jsp");
		return;
	}

	//phone number has a letter
	else if (number.matches("[a-zA-Z]+") == true) {
	response.sendRedirect("/errorpages/phoneIncorrect1.jsp");
		return;
	}

	//card exp date format is incorrect
	else if ( (exd.equals("") == false) && (exd.charAt(2) != '/') || (exd.equals("") == false)&&(exd.length() != 5) ) {
	response.sendRedirect("/errorpages/cardExpirationInvalid1.jsp");
		return;
	}
	else if ((exd.equals("") == false) && ((Character.isDigit(exd.charAt(0)) == false) || (Character.isDigit(exd.charAt(1)) == false) || (Character.isDigit(exd.charAt(3)) == false) ||(Character.isDigit(exd.charAt(4)) == false) )) {
	response.sendRedirect("/errorpages/cardExpirationInvalid1.jsp");
	return;
	}

	//missing address info
	else if ( (street.equals("") == false) && (city.equals("") == true) && (state.equals("") == true)) {
		response.sendRedirect("/errorpages/missingAddress1.jsp");
		return;
	}
	else if ( (street.equals("") == false) && (city.equals("") == false) && (state.equals("") == true)) {
		response.sendRedirect("/errorpages/missingAddress1.jsp");
		return;
	}
	else if ( (street.equals("") == true) && (city.equals("") == false) && (state.equals("") == false)) {
		response.sendRedirect("/errorpages/missingAddress1.jsp");
		return;
	}
	else if ( (street.equals("") == true) && (city.equals("") == true) && (state.equals("") == false)) {
		response.sendRedirect("/errorpages/missingAddress1.jsp");
		return;
	}
	else if ( (street.equals("") == false) && (city.equals("") == true) && (state.equals("") == false)) {
		response.sendRedirect("/errorpages/missingAddress1.jsp");
		return;
	}
	else if ( (street.equals("") == true) && (city.equals("") == false) && (state.equals("") == true)) {
		response.sendRedirect("/errorpages/missingAddress1.jsp");
		return;
	}
	else if ( (street.equals("") == true) && (city.equals("") == false) && (state.equals("") == false)) {
		response.sendRedirect("/errorpages/missingAddress1.jsp");
		return;
	}
	else if ( (street.equals("") == false) && (city.equals("") == true) && (state.equals("") == true)) {
		response.sendRedirect("/errorpages/missingAddress1.jsp");
		return;
	}
	else if ( (street.equals("") == false) && (city.equals("") == true) && (state.equals("") == false)) {
		response.sendRedirect("/errorpages/missingAddress1.jsp");
		return;
	}

	int register_user = new backendUser().register(new_user);
	String body = "Thank you for signing up for UGABOOKSTORE.COM!!!\nYour confirmation number is: " + randomNum + "\nPlease enter this on our website!!!";
	System.out.println(register_user);
	if(register_user == 0){
		sendEmail(email,body);
	}else if(register_user == 800){
		response.sendRedirect("/errorpages/alreadyRegistered.jsp");
		return;
	}else if(register_user == 90){
		response.sendRedirect("/errorpages/alreadyRegistered.jsp");
                return;
	}

	//hash password
	response.sendRedirect("/user/registrationconfirm.jsp");
	return;
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
