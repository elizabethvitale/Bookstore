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
		//String fname = "Shit";
		String fname = user.getFirstName();
		String lname = user.getLastName();;
		String email = user.getEmail();
		String phone = user.getPhone();
		String password = user.getPassword();
		String status = user.getStatus();
		
		
		try{
			


			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			Statement stmt=null;
            		int customerid= 0;
			stmt = con.createStatement();
			String query = "select max(customerid) from customer;";
			ResultSet rs=stmt.executeQuery(query);
			while(rs.next()) {
				customerid = rs.getInt("max(customerid)");
				
			}
			customerid = customerid + 1;
			stmt = con.createStatement();
			query = "insert into customer(customerid, firstname, lastname, email, password, phone, status, enroll_for_promotes) values(" + customerid + ",'" + fname + "','" +lname+"','"+email+"','"+password+"','"+phone+"','"+status+"',true)";
			result=stmt.executeUpdate(query);
		} catch(SQLException err){
			System.out.println("ERROR: " + err.getMessage());
		} catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
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
                        
			System.out.println(query);
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




	

} 
