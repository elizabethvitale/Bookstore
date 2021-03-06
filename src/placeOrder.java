package com.ugabookstore;
import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.activation.*;
import com.ugabookstore.*;
import java.util.Random;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import javax.servlet.http.HttpSession;
import java.sql.Connection;

@WebServlet("/placeOrder")
public class placeOrder extends HttpServlet {
	
	public void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int cartId = -1;
		try {
			String temp = (String) session.getAttribute("cartid");
			cartId = Integer.parseInt(temp);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			response.sendRedirect("/errorpages/placeorder_error.jsp");
			return;
		}

		int userId = (int) session.getAttribute("customerid");
		String firstName = (String)session.getAttribute("firstName");
		String lastName = (String)session.getAttribute("lastName");
		String email = (String)session.getAttribute("email");
		/*
		try {
			String temp = (String) session.getAttribute("customerid");
			userId = Integer.parseInt(temp);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			response.sendRedirect("/errorpages/placeorder_error.jsp");
		}
*/

		String cardnum = (String) session.getAttribute("ordercard");
		System.out.println("ordercard in placeOrder = " + cardnum);
		double grandTotal = -1.0;
		try {
			String temp = (String) session.getAttribute("TOTAL");
			grandTotal = Double.parseDouble(temp);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			response.sendRedirect("/errorpages/placeorder_error.jsp");
			return;
		}

		String date = null;
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			date = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DATE);
		} catch (Exception e) {
			e.printStackTrace(System.out);
			response.sendRedirect("/errorpages/placeorder_error.jsp");
			return;
		}
		
		int promoid = -1;
		try {
			String temp = (String) session.getAttribute("promocode");
			promoid = Integer.parseInt(temp);
		} catch (Exception e) {
			System.out.println("NO PROMO");
		}

		try {
			Connection con;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection
				("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			String query = null;
			PreparedStatement stmt = null;
			if (promoid == -1) {
				query = "INSERT INTO orders (userid, cardnumber, amount, orderdate) VALUES (?, ?, ?, ?)";
				stmt = con.prepareStatement(query);
				stmt.setInt(1, userId);
				stmt.setString(2, cardnum);
				stmt.setDouble(3, grandTotal);
				stmt.setString(4, date);
			} else {
				query = "INSERT INTO orders (userid, cardnumber, amount, orderdate, promoid) VALUES (?, ?, ?, ?, ?)";
				stmt = con.prepareStatement(query);
				stmt.setInt(1, userId);
				stmt.setString(2, cardnum);
				stmt.setDouble(3, grandTotal);
				stmt.setString(4, date);
				stmt.setInt(5, promoid);
			}
			int row = stmt.executeUpdate();
			stmt.close();
			if (row > 0) {
				query = "SELECT orderid from orders ORDER BY orderid DESC LIMIT 1";
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery();
				int orderId = -1;
				while (rs.next()) {
					orderId = rs.getInt("orderid");
				}
				Transaction trans = new Transaction();
				trans.createTransaction(userId, orderId, cartId, firstName, lastName,date, grandTotal, email);
				Statement stmt2 = con.createStatement();
				String query2 = "delete from cart_item where cartid='" + cartId + "';";
				stmt2.executeUpdate(query2);
				con.close();	
				session.removeAttribute("ordercard");
				session.removeAttribute("cardinfo");
				session.removeAttribute("billFName");
				session.removeAttribute("billLName");
				session.removeAttribute("billStreet");
				session.removeAttribute("billCity");
				session.removeAttribute("billState");
				session.removeAttribute("billZip");
				session.removeAttribute("shipFName");
				session.removeAttribute("shipLName");
				session.removeAttribute("shipStreet");
				session.removeAttribute("shipCity");
				session.removeAttribute("shipState");
				session.removeAttribute("shipZip");
				session.removeAttribute("discount");
				session.removeAttribute("promocode");
				response.sendRedirect("/errorpages/placeorder_confirmation.jsp");
				return;
			} else {
				session.removeAttribute("discount");
				session.removeAttribute("promocode");
				response.sendRedirect("/errorpages/placeorder_error.jsp");
				return;
			}

		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
		


	}

}
