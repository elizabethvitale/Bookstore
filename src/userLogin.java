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
 

 
@WebServlet("/userLogin")
public class userLogin extends HttpServlet {
 
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        String acctID = request.getParameter("acctID");
        String password = request.getParameter("pwd");
	boolean spaceFlag = false;
	//i refuse to add an ajax call in here.....
	//on failed login........rose needs to make jsp error pages w these specficiations
	if(acctID.equals("") || password.equals("")){
	response.sendRedirect("/errorpages/blankRequired3.jsp");
	}

	User user  = new backendUser().login(acctID, password);	
	if(user == null){
		System.out.println("login has failed....");
		response.sendRedirect("user/login.jsp");
		//login fails
		return;
	}else{
	if(user.getStatus().equals("Inactive")){
	
	response.sendRedirect("/user/confirm.jsp");
	return;
	}
	
	//ON SUCESSFUL LOGIN:REDIRECT HERE 
	HttpSession oldSession = request.getSession(false);
	if (oldSession != null) {
		oldSession.invalidate();
	}
	HttpSession session = request.getSession(true);
	session.setMaxInactiveInterval(15*60);
	
	session.setAttribute("firstName", user.getFirstName());
	session.setAttribute("lastName", user.getLastName());
	session.setAttribute("email", user.getEmail());
	session.setAttribute("customerid", user.getID());
	session.setAttribute("phone", user.getPhone());
	session.setAttribute("acctID", acctID);
	session.setAttribute("street", user.getStreet());
	session.setAttribute("city", user.getCity());
	session.setAttribute("state", user.getState());
	session.setAttribute("zip", user.getZip());
	session.setAttribute("cardType", user.getCardType());
	session.setAttribute("expirationDate", user.getExpirationDate());
	session.setAttribute("cardType2", user.getCardType2());
        session.setAttribute("expirationDate2", user.getExpirationDate2());
        session.setAttribute("cardType3", user.getCardType3());
        session.setAttribute("expirationDate3", user.getExpirationDate3());
	session.setAttribute("cardNumber", user.getCardNumber());
	session.setAttribute("cardNumber2", user.getCardNumber2());
	session.setAttribute("cardNumber3", user.getCardNumber3());

	request.getSession(true).setAttribute("user", user);
	response.sendRedirect("/index.jsp");

}
}}