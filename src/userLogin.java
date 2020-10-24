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
	session.setAttribute("acctID", acctID);
	request.getSession().setAttribute("user", user);
	response.sendRedirect("/index.jsp");

}
}}
