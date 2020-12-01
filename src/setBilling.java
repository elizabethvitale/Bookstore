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
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



@WebServlet("/setBilling")
public class setBilling extends HttpServlet {

    public void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		String card = request.getParameter("ordercard");
		String cardinfo = "";
		String sameShip = request.getParameter("sameshipping");

		if (card.equalsIgnoreCase(String.valueOf(session.getAttribute("cardNumber")))) {
			cardinfo = String.valueOf(session.getAttribute("expirationDate")) + " " +
					String.valueOf(session.getAttribute("cardType"));

			session.setAttribute("ordercard", request.getParameter("ordercard"));
			session.setAttribute("cardinfo", cardinfo);
			session.setAttribute("billFName", request.getParameter("firstName"));
			session.setAttribute("billLName", request.getParameter("lastName"));
			session.setAttribute("billStreet", request.getParameter("street"));
			session.setAttribute("billCity", request.getParameter("city"));
			session.setAttribute("billState", request.getParameter("state"));
			session.setAttribute("billZip", request.getParameter("zipcode"));
	
			if (sameShip != null) {
				session.setAttribute("shipFName", request.getParameter("firstName"));
				session.setAttribute("shipLName", request.getParameter("lastName"));
				session.setAttribute("shipStreet", request.getParameter("street"));
				session.setAttribute("shipCity", request.getParameter("city"));
				session.setAttribute("shipState", request.getParameter("state"));
				session.setAttribute("shipZip", request.getParameter("zipcode"));
				response.sendRedirect("/checkout/checkout-review.jsp");
			} else {
				response.sendRedirect("/checkout/checkout-shipping.jsp");
			}
		} else if (card.equalsIgnoreCase(String.valueOf(session.getAttribute("cardNumber2")))) {
			cardinfo = String.valueOf(session.getAttribute("expirationDate2")) + " " +
					String.valueOf(session.getAttribute("cardType2"));

			session.setAttribute("ordercard", request.getParameter("ordercard"));
			session.setAttribute("cardinfo", cardinfo);
			session.setAttribute("billFName", request.getParameter("firstName"));
			session.setAttribute("billLName", request.getParameter("lastName"));
			session.setAttribute("billStreet", request.getParameter("street"));
			session.setAttribute("billCity", request.getParameter("city"));
			session.setAttribute("billState", request.getParameter("state"));
			session.setAttribute("billZip", request.getParameter("zipcode"));
	
			if (sameShip != null) {
				session.setAttribute("shipFName", request.getParameter("firstName"));
				session.setAttribute("shipLName", request.getParameter("lastName"));
				session.setAttribute("shipStreet", request.getParameter("street"));
				session.setAttribute("shipCity", request.getParameter("city"));
				session.setAttribute("shipState", request.getParameter("state"));
				session.setAttribute("shipZip", request.getParameter("zipcode"));
				response.sendRedirect("/checkout/checkout-review.jsp");
			} else {
				response.sendRedirect("/checkout/checkout-shipping.jsp");
			}
		} else if (card.equalsIgnoreCase(String.valueOf(session.getAttribute("cardNumber3")))) {
			cardinfo = String.valueOf(session.getAttribute("expirationDate3")) + " " +
					String.valueOf(session.getAttribute("cardType3"));

			session.setAttribute("ordercard", request.getParameter("ordercard"));
			session.setAttribute("cardinfo", cardinfo);
			session.setAttribute("billFName", request.getParameter("firstName"));
			session.setAttribute("billLName", request.getParameter("lastName"));
			session.setAttribute("billStreet", request.getParameter("street"));
			session.setAttribute("billCity", request.getParameter("city"));
			session.setAttribute("billState", request.getParameter("state"));
			session.setAttribute("billZip", request.getParameter("zipcode"));
			
			if (sameShip != null) {
				session.setAttribute("shipFName", request.getParameter("firstName"));
				session.setAttribute("shipLName", request.getParameter("lastName"));
				session.setAttribute("shipStreet", request.getParameter("street"));
				session.setAttribute("shipCity", request.getParameter("city"));
				session.setAttribute("shipState", request.getParameter("state"));
				session.setAttribute("shipZip", request.getParameter("zipcode"));
				response.sendRedirect("/checkout/checkout-review.jsp");
			} else {
				response.sendRedirect("/checkout/checkout-shipping.jsp");
			}

		} else if (card.equalsIgnoreCase("")) {
			response.sendRedirect("/checkout/checkout-billing.jsp");
		}


	}
}