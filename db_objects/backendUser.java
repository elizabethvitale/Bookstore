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
		String street = user.getStreet();
		String city = user.getCity();
		int zip = user.getZip();
		String state = user.getState();
		String cardType = user.getCardType();
		String expirationDate = user.getExpirationDate();
		String cardNumber = user.getCardNumber();
		try{
			
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			Statement stmt=null;
            		//int customerid= 0;
			stmt = con.createStatement();
			String query = "select max(customerid) from customer;";
			//ResultSet rs=stmt.executeQuery(query);
			//while(rs.next()) {
			//	customerid = rs.getInt("max(customerid)");
				
			//}

			password = getSha1(password);

			
			//customerid = customerid + 1;
			stmt = con.createStatement();
			query = "insert into customer(customerid, firstname, lastname, email, password, phone, status, enroll_for_promotes) values(" + customerid + ",'" + fname + "','" +lname+"','"+email+"','"+password+"','"+phone+"','"+status+"',true)";
			result=stmt.executeUpdate(query);
		
			if(!street.equals(null) && !street.equals("")){
			stmt = con.createStatement();
			query = "insert into shipping_address(street, city, state, zipcode, shippingid) values('" + street + "','"+city+"','" + state + "','" + zip + "','" + customerid + "');";
			System.out.println(query);
			result = stmt.executeUpdate(query);			
			}
			if(!cardType.equals(null) && !cardType.equals("")){
			stmt = con.createStatement();
			String card = getSha1(cardNumber);
			query = "insert into payment_card(cardnumber, userid, type, expdate) values('" + card + "','" + customerid + "','" + cardType + "','" + expirationDate + "');";
			
			System.out.println(query);
			result = stmt.executeUpdate(query);
			}
			

		} catch(SQLException err){
			System.out.println("ERROR: " + err.getMessage());
			return 1;
		} catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
			return 1;
		}
		return 0;
	}

	public Admin loginAdmin(String acctID_or_email, String pwd){
		Admin admin = new Admin();
		try{
						
                        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
                        Statement stmt = null;
                        stmt = con.createStatement();
                        String query = "select * from admin where adminid='" + acctID_or_email + "' or email='" + acctID_or_email+"';";
                        pwd = getSha1(pwd);
                        ResultSet rs=stmt.executeQuery(query);
                                if(rs.next()) {

                                        admin.setID(rs.getInt("adminid"));
                                        admin.setFirstName(rs.getString("firstname"));
                                        admin.setLastName(rs.getString("lastname"));
                                        admin.setEmail(rs.getString("email"));
                                        admin.setPassword(rs.getString("password"));
				}
                                if(admin.getPassword().equals(pwd)){
                                return admin;
                                }
                                admin=null;
                                return admin;

		}catch(Exception e){
			System.out.println(e.getMessage());
			admin = null;
			return admin;
		}



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
					stmt = con.createStatement();
					query = "select * from shipping_address where shippingid='" + rs.getInt("customerid") + "';";
					ResultSet ra =stmt.executeQuery(query);
					if(ra.next()) {
						user.setCity(ra.getString("city"));	
                                                user.setStreet(ra.getString("street"));  
                                                user.setZip(Integer.parseInt(ra.getString("zipcode")));  
                                                user.setState(ra.getString("state"));  			
					}
					query = "select * from payment_card where userid='" + rs.getInt("customerid") + "';";
					ResultSet rb = stmt.executeQuery(query);
					System.out.println(query);
					if(rb.next()){
						user.setExpirationDate(rb.getString("expdate"));
						user.setCardType(rb.getString("type"));
					}if(rb.next()){
						user.setExpirationDate2(rb.getString("expdate"));
						user.setCardType2(rb.getString("type"));
					}if(rb.next()){
						user.setExpirationDate3(rb.getString("expdate"));
						user.setCardType3(rb.getString("type"));
					}

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

	public int retrieveIdFromEmail(String email) {
		int userId = -1;
		try {

			ResultSet rset = null;
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			Statement stmt = null;
			stmt = con.createStatement();
			String query = "select * from customer where email='" + email + "';";
			rset = stmt.executeQuery(query);
			if (rset.next()) {
				userId = rset.getInt("customerid");
			} 
			return userId;
		} catch(SQLException err){
			System.out.println("ERROR: " + err.getMessage());
			return userId;
		} catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
			return userId;
		}
	}

	public ResultSet retrievePersonalInfo(int customerId) {	
		ResultSet rset = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			Statement stmt = null;
			stmt = con.createStatement();
			String query = "select * from customer where customerid='" + customerId + "';";
			rset = stmt.executeQuery(query);
			return rset;
		} catch(SQLException err){
			System.out.println("ERROR: " + err.getMessage());
			return rset;
		} catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
			return rset;
		}

	} //retrieveInfo

	public String retrieveName(int customerId) {
		String resultString = null;
		try {
			ResultSet rs = retrievePersonalInfo(customerId);
			if (rs == null) {
				System.out.println("ERROR: Retrieval from DB failed!");
			} else {
				if (rs.next()) {
					resultString = "" + rs.getString("firstname") + " " + rs.getString("lastname");
				}
			}
		} catch(SQLException err){
			System.out.println("ERROR: " + err.getMessage());
			return null;
		} catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
			return null;
		}
		return resultString;
	}

	public String retrievePhone(int customerId) {
		String resultString = null;
		try {
			ResultSet rs = retrievePersonalInfo(customerId);
			if (rs == null) {
				System.out.println("ERROR: Retrieval from DB failed!");
			} else {
				if (rs.next()) {
					resultString = rs.getString("phone");
				}
			}
		} catch(SQLException err){
			System.out.println("ERROR: " + err.getMessage());
			return null;
		} catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
			return null;
		}
		return resultString;
	}

	
	public ResultSet retrievePaymentInfo(int customerId) {	
		ResultSet rset = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			Statement stmt = null;
			stmt = con.createStatement();
			String query = "select * from payment_card where userid='" + customerId + "';";
			rset = stmt.executeQuery(query);
			return rset;
		} catch(SQLException err){
			System.out.println("ERROR: " + err.getMessage());
			return rset;
		} catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
			return rset;
		}

	} //retrievePayment

	public int retrieveCardNumber(int customerId) {
		int resultInt = -1;
		try {
			ResultSet rs = retrievePaymentInfo(customerId);
			if (rs == null) {
				System.out.println("ERROR: Retrieval from DB failed!");
			} else {
				if (rs.next()) {
					resultInt = rs.getInt("cardnumber");
				}
			}
		} catch(SQLException err){
			System.out.println("ERROR: " + err.getMessage());
			return resultInt;
		} catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
			return resultInt;
		}
		return resultInt;
	}

	public String retrieveCardType(int customerId) {
		String resultString = null;
		try {
			ResultSet rs = retrievePaymentInfo(customerId);
			if (rs == null) {
				System.out.println("ERROR: Retrieval from DB failed!");
			} else {
				if (rs.next()) {
					resultString = rs.getString("type");
				}
			}
		} catch(SQLException err){
			System.out.println("ERROR: " + err.getMessage());
			return null;
		} catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
			return null;
		}
		return resultString;
	}

	public String retrieveExpDate(int customerId) {
		String resultString = null;
		try {
			ResultSet rs = retrievePaymentInfo(customerId);
			if (rs == null) {
				System.out.println("ERROR: Retrieval from DB failed!");
			} else {
				if (rs.next()) {
					resultString = rs.getString("expdate");
				}
			}
		} catch(SQLException err){
			System.out.println("ERROR: " + err.getMessage());
			return null;
		} catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
			return null;
		}
		return resultString;
	}

	public ResultSet retrieveShippingInfo(int customerId) {	
		ResultSet rset = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore","root","rootroot");
			Statement stmt = null;
			stmt = con.createStatement();
			String query = "select * from shipping_address where shippingid='" + customerId + "';";
			rset = stmt.executeQuery(query);
			return rset;
		} catch(SQLException err){
			System.out.println("ERROR: " + err.getMessage());
			return rset;
		} catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
			return rset;
		}

	} //retrievePayment

	public String retrieveStreet(int customerId) {
		String resultString = null;
		try {
			ResultSet rs = retrieveShippingInfo(customerId);
			if (rs == null) {
				System.out.println("ERROR: Retrieval from DB failed!");
			} else {
				if (rs.next()) {
					resultString = rs.getString("street");
				}
			}
		} catch(SQLException err){
			System.out.println("ERROR: " + err.getMessage());
			return null;
		} catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
			return null;
		}
		return resultString;
	}

	public String retrieveCity(int customerId) {
		String resultString = null;
		try {
			ResultSet rs = retrieveShippingInfo(customerId);
			if (rs == null) {
				System.out.println("ERROR: Retrieval from DB failed!");
			} else {
				if (rs.next()) {
					resultString = rs.getString("city");
				}
			}
		} catch(SQLException err){
			System.out.println("ERROR: " + err.getMessage());
			return null;
		} catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
			return null;
		}
		return resultString;
	}

	public String retrieveState(int customerId) {
		String resultString = null;
		try {
			ResultSet rs = retrieveShippingInfo(customerId);
			if (rs == null) {
				System.out.println("ERROR: Retrieval from DB failed!");
			} else {
				if (rs.next()) {
					resultString = rs.getString("state");
				}
			}
		} catch(SQLException err){
			System.out.println("ERROR: " + err.getMessage());
			return null;
		} catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
			return null;
		}
		return resultString;
	}

	public int retrieveZipcode(int customerId) {
		int resultInt = -1;
		try {
			ResultSet rs = retrieveShippingInfo(customerId);
			if (rs == null) {
				System.out.println("ERROR: Retrieval from DB failed!");
			} else {
				if (rs.next()) {
					resultInt = rs.getInt("zipcode");
				}
			}
		} catch(SQLException err){
			System.out.println("ERROR: " + err.getMessage());
			return resultInt;
		} catch(Exception e){
			System.out.println("ERROR: " + e.getMessage());
			return resultInt;
		}
		return resultInt;
	}
		

} 
