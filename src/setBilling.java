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

		HttpSession session = request.getSession();

		String card = request.getParameter("ordercard");
		String cardinfo = null;

		if (card.equalsIgnoreCase(String.valueOf(session.getAttribute("cardNumber")))) {
			cardinfo = String.valueOf(session.getAttribute("expirationDate")) + " " +
					String.valueOf(session.getAttribute("cardType"));
		} else if (card.equalsIgnoreCase(String.valueOf(session.getAttribute("cardNumber2")))) {
			cardinfo = String.valueOf(session.getAttribute("expirationDate2")) + " " +
					String.valueOf(session.getAttribute("cardType2"));
		} else if (card.equalsIgnoreCase(String.valueOf(session.getAttribute("cardNumber3")))) {
			cardinfo = String.valueOf(session.getAttribute("expirationDate3")) + " " +
					String.valueOf(session.getAttribute("cardType3"));
		} else if (card.equalsIgnoreCase("")) {
			response.sendRedirect("/checkout/checkout-billing.jsp");
		}

		session.setAttribute("ordercard", request.getParameter("ordercard"));
		session.setAttribute("cardinfo", cardinfo);
		session.setAttribute("billFName", request.getParameter("firstName"));
		session.setAttribute("billLName", request.getParameter("lastName"));
		session.setAttribute("billStreet", request.getParameter("street"));
		session.setAttribute("billCity", request.getParameter("city"));
		session.setAttribute("billState", request.getParameter("state"));
		session.setAttribute("billZip", request.getParameter("zipcode"));
	
		response.sendRedirect("/checkout/checkout-shipping.jsp");
	}
}