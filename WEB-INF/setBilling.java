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



@WebServlet("/setBilling")
public class setBilling extends HttpServlet {

    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);

		session.setAttribute("ordercard", request.getParameter("ordercard"));
		session.setAttribute("billFName", request.getParameter("firstName"));
		session.setAttribute("billLName", request.getParameter("lastName"));
		session.setAttribute("billStreet", request.getParameter("street"));
		session.setAttribute("billCity", request.getParameter("city"));
		session.setAttribute("billState", request.getParameter("state"));
		session.setAttribute("billZip", request.getParameter("zipcode"));
	
		response.sendRedirect("/checkout/checkout-shipping.jsp");
	}
}