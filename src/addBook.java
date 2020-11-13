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
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.io.InputStream;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

@MultipartConfig
@WebServlet("/addBook")
public class addBook extends HttpServlet {
	
	public void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		String category = request.getParameter("category");
		String edition = request.getParameter("edition");
		String publisher = request.getParameter("publisher");
		String description = request.getParameter("description");
		int year = 1900;
		try { 
			year = Integer.parseInt(request.getParameter("year"));
		} catch (Exception e) {
			response.sendRedirect("/admin/addbook.jsp");
		}
		double rPrice = 0.0;
		try { 
			rPrice = Double.parseDouble(request.getParameter("r_price"));
		} catch (Exception e) {
			response.sendRedirect("/admin/addbook.jsp");
		}
		double wPrice = 0.0;
		try { 
			wPrice = Double.parseDouble(request.getParameter("w_price"));
		} catch (Exception e) {
			response.sendRedirect("/admin/addbook.jsp");
		}
		int quantity = 0;
		try { 
			quantity = Integer.parseInt(request.getParameter("quantity"));
		} catch (Exception e) {
			response.sendRedirect("/admin/addbook.jsp");
		}
		int minThreshold = 0;
		try { 
			minThreshold = Integer.parseInt(request.getParameter("m_threshold"));
		} catch (Exception e) {
			response.sendRedirect("/admin/addbook.jsp");
		}
		InputStream inputStream = null;
		Part filePart = request.getPart("image");
		if (filePart != null) {
			inputStream = filePart.getInputStream();
		}

		try {
		Connection con;
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		con = DriverManager.getConnection
			("jdbc:mysql://localhost:3306/bookstore","root","rootroot");

		String query = "INSERT INTO book (title, author, isbn, category, edition, publisher, description, year, quantity, minthreshold, w_price, r_price, picture, active) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setString(1, title);
		stmt.setString(2, author);
		stmt.setString(3, isbn);
		stmt.setString(4, category);
		stmt.setString(5, edition);
		stmt.setString(6, publisher);
		stmt.setString(7, description);
		stmt.setInt(8, year);
		stmt.setInt(9, quantity);
		stmt.setInt(10, minThreshold);
		stmt.setDouble(11, wPrice);
		stmt.setDouble(12, rPrice);
		if (inputStream != null) {
			stmt.setBlob(13, inputStream);
		}
		boolean active = true;
		stmt.setBoolean(14, active);

		int row = stmt.executeUpdate();

		if (row > 0) {
			response.sendRedirect("/admin/addbook_confirmation.jsp");
		}
		} catch (Exception e) {
			e.printStackTrace(System.out);
			response.sendRedirect("/errorpages/addbook_error.jsp");
		}


	}



}