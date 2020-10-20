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
        //we need to find a way to check if the acctID and pwd match w the database
	//requires database connection for any verification here.
	
	//i refuse to add an ajax call in here.....
	//on failed login........rose needs to make jsp error pages w these specficiations
	if((acctID=="") || (password=="")){
	//response.sendRedirect("/loginError.jsp?errFlag=empty"); if a field is missing
	}

	//response.sendRedirect("/loginError.jsp?errFlag=notreg"); if a user is not registered
	//First, convert to hashed pwd using hashing mechanism here, then check against hashed password
	//response.sendRedirect("/loginError.jsp?errFlag=wrongpass"); if a user had the wrong pass


	//ON SUCESSFUL LOGIN:REDIRECT HERE 
	HttpSession oldSession = request.getSession(false);
	if (oldSession != null) {
		oldSession.invalidate();
	}
	HttpSession session = request.getSession(true);
	session.setMaxInactiveInterval(15*60);
	session.setAttribute("acctID", acctID);
	response.sendRedirect("/index.jsp");

}
}
