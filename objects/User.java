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
	private String street;
	private String city;
	private String state;
	private int zip;
	private String cardNumber;
	private String type;
	private String expirationDate;

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
	        public User(int actID, String password, String firstName, String lastName, String email, String phoneNumber, String status, boolean enrolled, String street, String city, String state, int zip){
		this.actID = actID;
                this.password = password;//hash this
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.phoneNumber = phoneNumber;
                this.status = status;
                this.enrolled = enrolled;
		this.street = street;
		this.city = city;
		this.state=state;
		this.zip = zip;			
	}
	public User(int actID, String password, String firstName, String lastName, String email, String phoneNumber, String status, boolean enrolled, String street, String city, String state, int zip, String cardNumber, String type, String expirationDate){
		this.actID = actID;
                this.password = password;//hash this
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.phoneNumber = phoneNumber;
                this.status = status;
                this.enrolled = enrolled;
                this.street = street;
                this.city = city;
                this.state=state;
                this.zip = zip;
		this.cardNumber = cardNumber;
		this.type=type;
		this.expirationDate = expirationDate;	
	}
	public User(int actID, String password, String firstName, String lastName, String email, String phoneNumber, String status, boolean enrolled, String cardNumber, String type, String expirationDate){
                this.actID = actID;
                this.password = password;//hash this
                this.firstName = firstName;
                this.lastName = lastName;
                this.email = email;
                this.phoneNumber = phoneNumber;
                this.status = status;
                this.enrolled = enrolled;

		this.cardNumber = cardNumber;
                this.type=type;
                this.expirationDate = expirationDate;
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
        public String getCity(){
                return city;
        }
        public String getStreet(){
                return street;
        }
        public String getState(){
                return state;
        }
        public int getZip(){
                return zip;
        }
	public String getCardNumber(){
		return cardNumber;
	}
	public void setCardNumber(String cardNumber){
		this.cardNumber = cardNumber;
	}
	public void setCardType(String type){
		this.type = type;

	}
	public void setExpirationDate(String expirationDate){
		this.expirationDate = expirationDate;
	}
	public String getCardType(){
		return type;
	}
	public String getExpirationDate(){
		return expirationDate;
	}
	
	 public void setID(int actID){
                this.actID = actID;
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
         public void setPhoneNumber(String number){
                this.phoneNumber = number;
        }
         public void setStatus(String status){
                this.status = status;
        }
         public void setEnrolled(boolean enroll){
                this.enrolled = enroll;
        }
         public void setCity(String city){
                this.city = city;
        }

         public void setStreet(String street){
                this.street = street;
        }

         public void setZip(int zip){
                this.zip = zip;
        }

         public void setState(String state){
                this.state = state;
        }



}
