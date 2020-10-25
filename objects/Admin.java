package com.ugabookstore;


public class Admin{

	private int actID;
	private String password;
	private String firstName;
	private String email;
	private String lastName;

	public Admin(){

	}
	public Admin(int actID, String password, String firstName, String lastName, String email){
		this.actID = actID;
		this.password = password;//hash this
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
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
         public void setFirstName(String name){
                this.firstName = name;
        }
         public void setLastName(String lastName){
                this.lastName = lastName;
        }
         public void setEmail(String email){
                this.email = email;
        }
         public void setPassword(String password){
                this.password = password;
        }
	public void setID(int ID){
		this.actID = ID;}
}
