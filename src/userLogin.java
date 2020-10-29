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
	//i refuse to add an ajax call in here.....
	//on failed login........rose needs to make jsp error pages w these specficiations
	if((acctID=="") || (password=="")){
	//response.sendRedirect("/loginError.jsp?errFlag=empty"); if a field is missing
	}

/*
	//checking to make sure the length of password is 8+
	if (password.length() < 8) {
	//redirect to an error page or show error message!
	}

	//checking to make sure there are no white spaces in the pw
	for (int i = 0; i < password.length(); i++) {
		if (password.charAt(i) == ' ') {
			spaceFlag = true;
			break;
		}
	}
	if (spaceFlag == true) {
	//redirect to an error page or show message
	}
*/

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
	System.out.println(user.getCardType());
	System.out.println(user.getExpirationDate());
	session.setAttribute("cardType", user.getCardType());
	session.setAttribute("expirationDate", user.getExpirationDate());
	request.getSession(true).setAttribute("user", user);
	response.sendRedirect("/index.jsp");

}
}}
