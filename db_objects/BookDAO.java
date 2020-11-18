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

	public List<Integer> getCart(String cartid){
	List<Integer> idArray = new ArrayList<>();
	try{
		System.out.println(cartid);
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
		PreparedStatement stmt = null;
		String query = "select bookid from cart_item where cartid='" + cartid + "' order by bookid asc;";
		System.out.println(query);
		stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()){
		idArray.add(rs.getInt("bookid"));
		}
		rs.close();
		stmt.close();
		con.close();
	}catch(Exception e){
		System.out.println(e);

	}
	return idArray;
	}
	public List<Integer> getBookIds(String term, String keyword) {
		List<Integer> idArray = new ArrayList<>();
		try {
			System.out.println("TERM: "  + term);
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			PreparedStatement stmt = null;
			String query = null;
			if (term.equals("bookid")){ 
				query = "SELECT bookid FROM book WHERE bookid = ? AND WHERE active = 1";
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				int keywordInt = Integer.parseInt(keyword);
				stmt.setInt(1, keywordInt);
			} else if (term.equals("title")) {
				query = "SELECT bookid FROM book WHERE title = ? AND WHERE active = 1";
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				stmt.setString(1, keyword);
			} else if (term.equals("author")) {
				query = "SELECT bookid FROM book WHERE author = ? AND WHERE active = 1";
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				stmt.setString(1, keyword);
			} else if (term.equals("isbn")) {
				query = "SELECT bookid FROM book WHERE isbn = ? AND WHERE active = 1";
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				stmt.setString(1, keyword);
			} else if (term.equals("category")) {
				query = "SELECT bookid FROM book WHERE category = ? AND WHERE active = 1";
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				stmt.setString(1, keyword);
			} else if (term.equals("publisher")) {
				query = "SELECT bookid FROM book WHERE publisher = ? AND WHERE active = 1";
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				stmt.setString(1, keyword);
			} else if(term.equals("")){
					
				query = "select bookid from book where active='1' and (author like '%" + keyword + "%' or title like '%" + keyword + "%' or description like '%" + keyword + "%' or category like '%" + keyword + "%' or year like '%" + keyword + "%' or publisher like '%" + keyword + "%' or isbn like '%" + keyword + "%') order by title asc;";
				System.out.println(query);
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			} else if(term.equals("Author")){
				query = "select bookid from book where active='1' and (author like '%" + keyword + "%' or title like '%" + keyword + "%' or description like '%" + keyword + "%' or category like '%" + keyword + "%' or year like '%" + keyword + "%' or publisher like '%" + keyword + "%' or isbn like '%" + keyword + "%') order by author asc;";	
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
			}else if(term.equals("Title")){
				query = "select bookid from book where active='1' and (author like '%" + keyword + "%' or title like '%" + keyword + "%' or description like '%" + keyword + "%' or category like '%" + keyword + "%' or year like '%" + keyword + "%' or publisher like '%" + keyword + "%' or isbn like '%" + keyword + "%') order by title asc;";
				stmt= con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

			}else if(term.equals("Subject")){
				query = "select bookid from book where active='1' and (author like '%" + keyword + "%' or title like '%" + keyword + "%' or description like '%" + keyword + "%' or category like '%" + keyword + "%' or year like '%" + keyword + "%' or publisher like '%" + keyword + "%' or isbn like '%" + keyword + "%') order by category asc;";
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			}else if(term.equals("ISBN")){
				query = "select bookid from book where active='1' and (author like '%" + keyword + "%' or title like '%" + keyword + "%' or description like '%" + keyword + "%' or category like '%" + keyword + "%' or year like '%" + keyword + "%' or publisher like '%" + keyword + "%' or isbn like '%" + keyword + "%') order by isbn asc;";
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

			}else {
				query = "SELECT bookid FROM book WHERE year = ?";
				stmt = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
				int keywordInt = Integer.parseInt(keyword);
				stmt.setInt(1, keywordInt);
			}

			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				idArray.add(rs.getInt("bookid"));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace(System.out);
			
		}
		return idArray;
	}

	public List<String> getBlobs(List<Integer> ids, int length){
	List<String> blobArray = new ArrayList<>();
		try { 
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			for (int i = 0; i < length; i++) {
				String query = "SELECT * FROM book WHERE bookid = ?";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setInt(1, ids.get(i));
				ResultSet rs = stmt.executeQuery();
				rs.next();
				Blob blob = rs.getBlob("picture");
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
			PreparedStatement stmt = null;
			ResultSet rs = null;
			for (int i = 0; i < length; i++) {
				String query = "SELECT title FROM book WHERE bookid = ?";
				stmt = con.prepareStatement(query);
				stmt.setInt(1, ids.get(i));
				rs = stmt.executeQuery();
				rs.next();
				titleArray.add(rs.getString("title"));
				rs.close();
				stmt.close();
			}
			stmt.close();
			con.close();
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
				book.setBookid(bookId);
				book.setTitle(result.getString("title"));
				book.setAuthor(result.getString("author"));
				book.setIsbn(result.getString("isbn"));
				book.setCategory(result.getString("category"));
				book.setEdition(result.getString("edition"));
				book.setPublisher(result.getString("publisher"));
				book.setDescription(result.getString("description"));
				book.setYear(result.getInt("year"));
				book.setQuantity(result.getInt("quantity"));
				book.setMinThreshold(result.getInt("minthreshold"));
				book.setWprice(result.getDouble("w_price"));
				book.setRprice(result.getDouble("r_price"));
				
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
			result.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace(System.out);
			
		}

		return book;

	}



}
