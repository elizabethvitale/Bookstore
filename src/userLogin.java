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
	System.out.print("entered");         
        String acctID = request.getParameter("acctID");
        String password = request.getParameter("pwd");
        //we need to find a way to check if the acctID and pwd match w the database
	//requires database connection for any verification here.
	System.out.println(acctID + password);
	//i refuse to add an ajax call in here.....
	//on failed login........rose needs to make jsp error pages w these specficiations
	if((acctID=="") || (password=="")){
	//response.sendRedirect("/loginError.jsp?errFlag=empty"); if a field is missing
	}


	User user  = new backendUser().login(acctID, password);	
	if(user == null){
		System.out.println("login has failed....");
		response.sendRedirect("/login.jsp");
		//login fails
	}else{
		//login works...!
	if(user.getStatus().equals("Inactive"){
	response.sendRedirect("/user/confirm.jsp");
	}
	
	//ON SUCESSFUL LOGIN:REDIRECT HERE 
	HttpSession oldSession = request.getSession(false);
	if (oldSession != null) {
		oldSession.invalidate();
	}
	HttpSession session = request.getSession(true);
	session.setMaxInactiveInterval(15*60);
	session.setAttribute("acctID", acctID);
	response.sendRedirect("/index.jsp");

}}
}
