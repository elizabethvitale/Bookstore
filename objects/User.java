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
	private String type2;
	private String type3;
	private String expirationDate2;
	private String expirationDate3;
	private String cardNumber2;
	private String cardNumber3;
	private String cartid;
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

	public String getCart(){
		return cartid;
	}
	public void setCart(String cartid){
		this.cartid = cartid;
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



        public String getCardNumber2(){
                return cardNumber2;
        }
        public void setCardNumber2(String cardNumber2){
                this.cardNumber2 = cardNumber2;
        }
        public String getCardNumber3(){
                return cardNumber3;
        }
        public void setCardNumber3(String cardNumber3){
                this.cardNumber3 = cardNumber3;
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




        
        public void setCardType2(String type2){
                this.type2 = type2;

        }
        public void setExpirationDate2(String expirationDate2){
                this.expirationDate2 = expirationDate2;
        }
        public String getCardType2(){
                return type2;
        }
        public String getExpirationDate2(){
                return expirationDate2;
        }

        
        public void setCardType3(String type3){
                this.type3 = type3;

        }
        public void setExpirationDate3(String expirationDate3){
                this.expirationDate3 = expirationDate3;
        }
        public String getCardType3(){
                return type3;
        }
        public String getExpirationDate3(){
                return expirationDate3;
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
