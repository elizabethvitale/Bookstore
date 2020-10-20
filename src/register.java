package com.ugabookstore;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.activation.*;

import java.io.IOException;
import java.io.PrintWriter;
 

 
@WebServlet("/register")
public class register extends HttpServlet {
 
    public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
         

	//add optional signup options.
        String name = request.getParameter("name");
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


	if(name=="" || email=="" || number =="" || pwd==""){
	//account for other formatting issues too.
	//throw error
	}else{
	

	//hash password
	//create object in database
	response.sendRedirect("/index.jsp");
	}
}
}
