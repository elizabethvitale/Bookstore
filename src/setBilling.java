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

		if (!card.equalsIgnoreCase("")) {
			if (card.equalsIgnoreCase(String.valueOf(session.getAttribute("cardNumber")))) {
				cardinfo = String.valueOf(session.getAttribute("expirationDate")) + " " +
					String.valueOf(session.getAttribute("cardType"));
			} else if (card.equalsIgnoreCase(String.valueOf(session.getAttribute("cardNumber2")))) {
				cardinfo = String.valueOf(session.getAttribute("expirationDate2")) + " " +
					String.valueOf(session.getAttribute("cardType2"));
			} else if (card.equalsIgnoreCase(String.valueOf(session.getAttribute("cardNumber3")))) {
				cardinfo = String.valueOf(session.getAttribute("expirationDate3")) + " " +
					String.valueOf(session.getAttribute("cardType3"));
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

		} else {
			if ((!request.getParameter("ccname").equalsIgnoreCase("")) && (!request.getParameter("ccnumber").equalsIgnoreCase(""))
				&& (!request.getParameter("ccexpdate").equalsIgnoreCase("")) && (!request.getParameter("cctype").equalsIgnoreCase(""))) {
				
				cardinfo = request.getParameter("ccexpdate") + " " +
					request.getParameter("cctype");

				String ccnumber = getSha1(request.getParameter("ccnumber"));

				session.setAttribute("ordercard", ccnumber);
				session.setAttribute("cardinfo", cardinfo);
				session.setAttribute("billFName", request.getParameter("firstName"));
				session.setAttribute("billLName", request.getParameter("lastName"));
				session.setAttribute("billStreet", request.getParameter("street"));
				session.setAttribute("billCity", request.getParameter("city"));
				session.setAttribute("billState", request.getParameter("state"));
				session.setAttribute("billZip", request.getParameter("zipcode"));
	
				response.sendRedirect("/checkout/checkout-shipping.jsp");
		
			} else {
				response.sendRedirect("/errorpages/checkoutError.jsp");
			}
		}


	}

        private static String getSha1(String input) {
                try {
                        MessageDigest m = MessageDigest.getInstance("SHA-1");
                        byte[] messageDigest = m.digest(input.getBytes());

                        BigInteger no = new BigInteger(1, messageDigest);
                        String hashtext = no.toString(16);

                        while (hashtext.length() < 32) {
                                hashtext = "0" + hashtext;
                        }
                        return hashtext;
                } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                }
        }
}