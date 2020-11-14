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
import java.sql.Blob;
import java.io.InputStream;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import java.util.Base64;
import java.io.ByteArrayOutputStream;

public class BookDAO {

	public List<Integer> getBookIds(String term, String keyword) {
		List<Integer> idArray = new ArrayList<>();
		try {

			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			PreparedStatement stmt = null;
			String query = null;
			if (term.equals("bookid")){ 
				query = "SELECT bookid FROM book WHERE bookid = ?";
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				int keywordInt = Integer.parseInt(keyword);
				stmt.setInt(1, keywordInt);
			} else if (term.equals("title")) {
				query = "SELECT bookid FROM book WHERE title = ?";
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				stmt.setString(1, keyword);
			} else if (term.equals("author")) {
				query = "SELECT bookid FROM book WHERE author = ?";
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				stmt.setString(1, keyword);
			} else if (term.equals("isbn")) {
				query = "SELECT bookid FROM book WHERE isbn = ?";
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				stmt.setString(1, keyword);
			} else if (term.equals("category")) {
				query = "SELECT bookid FROM book WHERE category = ?";
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				stmt.setString(1, keyword);
			} else if (term.equals("publisher")) {
				query = "SELECT bookid FROM book WHERE publisher = ?";
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				stmt.setString(1, keyword);
			} else if(term.equals("")){
				query = "SELECT bookid from book;";
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			} else {
				query = "SELECT bookid FROM book WHERE year = ?";
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				int keywordInt = Integer.parseInt(keyword);
				stmt.setInt(1, keywordInt);
			}

			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				idArray.add(rs.getInt("bookid"));
			}

		} catch (Exception e) {
			e.printStackTrace(System.out);
			
		}
		return idArray;
		
	}
	public List<String> getBlobs(List<Integer> ids, int length){
	List<String> blobArray = new ArrayList<>();
		try { 
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			for (int i = 0; i < length; i++) {
				String query = "SELECT title FROM book WHERE bookid = ?";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setInt(1, ids.get(i));
				ResultSet rs = stmt.executeQuery();
				rs.next();
				Blob blob = rs.getBlob("picture"));
				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				
                		while ((bytesRead = inputStream.read(buffer)) != -1) {
                    			outputStream.write(buffer, 0, bytesRead);                  
                		}
                 
                		byte[] imageBytes = outputStream.toByteArray();
                		String base64Image = Base64.getEncoder().encodeToString(imageBytes);

				inputStream.close();
				outputStream.close();
				blobArray.add(base64Image);

			}
			

		} catch (Exception e) {
			e.printStackTrace(System.out);
			
		}
		return blobArray;

	}
	public List<String> getBookTitles(List<Integer> ids, int length) {
		List<String> titleArray = new ArrayList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			for (int i = 0; i < length; i++) {
				String query = "SELECT title FROM book WHERE bookid = ?";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setInt(1, ids.get(i));
				ResultSet rs = stmt.executeQuery();
				rs.next();
				titleArray.add(rs.getString("title"));
			}
			

		} catch (Exception e) {
			e.printStackTrace(System.out);
			
		}
		return titleArray;
	}
		
	public Book get(int bookId) {
		Book book = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");

			String query = "SELECT * FROM book WHERE bookid = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, bookId);
			ResultSet result = stmt.executeQuery();
			Blob blob;

			if (result.next()) {
				book = new Book();
				book.setBookId(bookId);
				book.setTitle(result.getString("title"));
				book.setAuthor(result.getString("author"));
				book.setISBN(result.getString("isbn"));
				book.setCategory(result.getString("category"));
				book.setEdition(result.getString("edition"));
				book.setPublisher(result.getString("publisher"));
				book.setDescription(result.getString("description"));
				book.setYear(result.getInt("year"));
				book.setQuantity(result.getInt("quantity"));
				book.setMinThreshold(result.getInt("minthreshold"));
				book.setWPrice(result.getDouble("w_price"));
				book.setRPrice(result.getDouble("r_price"));
				
				blob = result.getBlob("picture");
				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;
				
                		while ((bytesRead = inputStream.read(buffer)) != -1) {
                    			outputStream.write(buffer, 0, bytesRead);                  
                		}
                 
                		byte[] imageBytes = outputStream.toByteArray();
                		String base64Image = Base64.getEncoder().encodeToString(imageBytes);

				inputStream.close();
				outputStream.close();

				book.setBase64Image(base64Image);
				
			}

		} catch (Exception e) {
			e.printStackTrace(System.out);
			
		}

		return book;

	}



}
