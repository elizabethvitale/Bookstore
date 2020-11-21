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
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.servlet.http.HttpSession;

public class Transaction {

	public void createTransaction(int userId, int orderId, int cartId) {
		try {		
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			
			String query = "select bookid, count(bookid) from cart_item where cartid='" + cartId + "' group by bookid";
			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String tQuery = "INSERT INTO transaction (orderid, bookid, quantity) VALUES (?, ?, ?)";
				PreparedStatement stmt2 = con.prepareStatement(tQuery, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				stmt2.setInt(1, orderId);
				stmt2.setInt(2, rs.getInt("bookid"));
				stmt2.setInt(3, rs.getInt("count(bookid)"));
				int row = stmt2.executeUpdate();
				stmt2.close();
			}
			
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

}
