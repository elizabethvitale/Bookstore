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



@WebServlet("/setShipping")
public class setShipping extends HttpServlet {

    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		session.setAttribute("shipFName", request.getParameter("firstName"));
		session.setAttribute("shipLName", request.getParameter("lastName"));
		session.setAttribute("shipStreet", request.getParameter("street"));
		session.setAttribute("shipCity", request.getParameter("city"));
		session.setAttribute("shipState", request.getParameter("state"));
		session.setAttribute("shipZip", request.getParameter("zipcode"));
	
		response.sendRedirect("/checkout/checkout-review.jsp");
	}
}