package com.ugabookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import com.ugabookstore.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class backendUser{
	Connection con;
	int result = 1;

	public int register(User user) {
		String fname = user.getFirstName();
		String lname = user.getLastName();;
		String email = user.getEmail();
		String phone = user.getPhone();
		String password = user.getPassword();
		String status = user.getStatus();
		int customerid = user.getID();
		
		try{
			


			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			Statement stmt=null;
			stmt = con.createStatement();
			String query = "select max(customerid) from customer;";

			password = getSha1(password);

			
			stmt = con.createStatement();
			query = "insert into customer(customerid, firstname, lastname, email, password, phone, status, enroll_for_promotes) values(" + customerid + ",'" + fname + "','" +lname+"','"+email+"','"+password+"','"+phone+"','"+status+"',true)";
			result=stmt.executeUpdate(query);
		} catch(SQLException err){
			System.out.println("ERROR: " + err.getMessage());
			return 1;
		} catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
			return 1;
		}
		return 0;
	}


	public User login(String acctID_or_email, String pwd){
                User user = new User();
                try{
                        
                        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
                        Statement stmt = null;
                        stmt = con.createStatement();
                        String query = "select * from customer where customerid='" + acctID_or_email + "' or email='" + acctID_or_email+"';";
                       	pwd = getSha1(pwd); 
			ResultSet rs=stmt.executeQuery(query);
                                if(rs.next()) {
                                        
					user.setID(rs.getInt("customerid"));
                                        user.setFirstName(rs.getString("firstname"));
                                        user.setLastName(rs.getString("lastname"));
                                        user.setEmail(rs.getString("email"));
                                        user.setPassword(rs.getString("password"));
                                        user.setPhoneNumber(rs.getString("phone"));
                                        user.setStatus(rs.getString("status"));
                                        user.setEnrolled(rs.getBoolean("enroll_for_promotes"));

                                }else{
					System.out.println("catch1");
                                        user = null;
                                        return user;
                                }
                                if(user.getPassword().equals(pwd)){ 
				return user;
				}
				System.out.println(user.getStatus());
                                user=null;
                                return user;

                        }catch(SQLException err){
                                user = null;
                                System.out.println(err.getMessage());
                                return user;
                        }catch(Exception e){
				user = null;
				System.out.println(e.getMessage());
				return user;
			}


        }


	public static String getSha1(String input) {
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
