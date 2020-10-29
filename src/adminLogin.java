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
 

 
@WebServlet("/adminLogin")
public class adminLogin extends HttpServlet {
 
    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {


        String acctID = request.getParameter("acctID");
        String password = request.getParameter("pwd");
	boolean spaceFlag = false;
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


	Admin admin  = new backendUser().loginAdmin(acctID, password);	
	if(admin == null){
		System.out.println("login has failed....");
		response.sendRedirect("admin/login.jsp");
		//login fails
		return;
	}
	
	//ON SUCESSFUL LOGIN:REDIRECT HERE 
	HttpSession oldSession = request.getSession(false);
	if (oldSession != null) {
		oldSession.invalidate();
	}
	HttpSession session = request.getSession(true);
	session.setMaxInactiveInterval(15*60);
	
	System.out.println(admin.getFirstName());
	
	session.setAttribute("firstName", admin.getFirstName());
	session.setAttribute("lastName", admin.getLastName());
	session.setAttribute("email", admin.getEmail());
	session.setAttribute("adminid", admin.getID());
	session.setAttribute("admin", "YES");
	response.sendRedirect("/admin/index.jsp");

}
}
