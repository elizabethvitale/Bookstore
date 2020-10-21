package com.ugabookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import com.ugabookstore.*;

public class backendUser{
	Connection con;
	public Connection connect(){
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			Class.forName("com.mysql.jdbc.Driver").newInstance();	
		}catch(SQLException err){
			System.out.println(err.getMessage());
		}catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}

	public int register(User user){
		con=connect();
		Statement stmt;
                if(con!=null){
		try{
                stmt = con.createStatement();
                }catch(SQLException err){
                        System.out.println(err.getMessage());
                }}
		String query = "insert into customer(customerid, firstname, lastname, email, password, phone, status, enroll_for_promotes) values(" + user.getID() + "," + user.getFirstName() + "," + user.getLastName() + "," + user.getEmail() + "," + user.getPassword() + "," +user.getPhone() + "," + user.getStatus() + "," + user.getEnroll() + ");";
		return 0;


	}


	public User login(String acctID){
                con=connect();
		Statement stmt = null;
		try{
		stmt = con.createStatement();
		}catch(SQLException err){
                        System.out.println(err.getMessage());
                }
		String query = "select customerid, firstname, lastname, email, password, phone, status, enroll_for_promotes from customerwhere customerid=" + acctID;
		User user = new User();
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery(query);
                }catch(SQLException err){
                        System.out.println(err.getMessage());
                }


		try{
		if(rs.next()){
			
			//set all this crap.

		}else{
		user = null;
		}}catch(SQLException err){
			user=null;
			System.out.println(err.getMessage());
		}
		return user;
	}


} 
