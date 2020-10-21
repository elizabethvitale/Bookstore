package com.ugabookstore;


public class User{

	private int actID;
	private String password;
	private String firstName;
	private String email;
	private String lastName;
	private String phoneNumber;
	private String status; 
	private boolean enrolled;

	public User(){

	}
	public User(int actID, String password, String firstName, String lastName, String email, String phoneNumber, String status, boolean enrolled){
		this.actID = actID;
		this.password = password;//hash this
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.enrolled = enrolled;
		System.out.println(firstName);
	}
	public int getID(){
		return actID;
	}
	public String getFirstName(){
		return firstName;
	}
	public String getLastName(){
		return lastName;
	}
	public String getEmail(){
		return email;
	}
	public String getPassword(){
		return password;
	}
	public String getPhone(){
		return phoneNumber;
	}
	public String getStatus(){
		return status;
	}
	public boolean getEnroll(){
		return enrolled;
	}
	



}
