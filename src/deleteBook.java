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
import javax.servlet.http.HttpSession;

@WebServlet("/deleteBook")
public class deleteBook extends HttpServlet {
	
	public void doPost(HttpServletRequest request,
	HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);

		int bookid = 0;
		try {
			bookid = Integer.parseInt(request.getParameter("bookid"));
		} catch (Exception e) {
			response.sendRedirect("/admin/editbook.jsp");
		}
		
		try {
			Connection con;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection
				("jdbc:mysql://localhost:3306/bookstore","root","rootroot");

			String query = "UPDATE book SET active = 0 WHERE bookid = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, bookid);
			int row = stmt.executeUpdate();
			stmt.close();

			con.close();
			if (row > 0) {
				response.sendRedirect("/admin/deletebook_confirmation.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace(System.out);
			response.sendRedirect("/errorpages/deletebook_error.jsp");
		}


	}



}