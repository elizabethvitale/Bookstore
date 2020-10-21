package com.ugabookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import com.ugabookstore.*;

public class backendUser{
	Connection con;
	int result = 1;

	public int register(User user) {
		String fname = "Shit";
		String lname = "Scott";
		String email = "ses95990@uga.edu";
		String phone = "4782391911";
		String password = "root";
		String status = "ACTIVE";
		
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			Statement stmt=null;
            stmt = con.createStatement();
			String query = "insert into customer(customerid, firstname, lastname, email, password, phone, status, enroll_for_promotes) values(00003,'" + fname + "','" +lname+"','"+email+"','"+password+"','"+phone+"','"+status+"',true)";
			//System.out.println(query);
			result=stmt.executeUpdate(query);
		} catch(SQLException err){
			System.out.println("ERROR: " + err.getMessage());
		} catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
		}
		return 0;
	}


	/*public User login(String acctID){
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
	}*/


} 
