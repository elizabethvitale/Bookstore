package com.ugabookstore;



public class Book {

	private int bookId;

	private String title;
	private String author;
	private String isbn;
	private String category;
	private String edition;
	private String publisher;
	private String description;
	private int year;
	private int quantity;
	private int minThreshold;
	private double wPrice;
	private double rPrice;
	
	private byte[] image;
	private String base64Image;

	public Book(int bookId, String title, String author, String isbn, String category, String edition, String publisher, String description, int year, int quantity, int minThreshold, double wPrice, double rPrice) {
		
		this.bookId = bookId;
		this.author = author;
		this.isbn = isbn;
		this.category = category;
		this.edition = edition;
		this.publisher = publisher;
		this.description = description;
		this.year = year;
		this.quantity = quantity;
		this.minThreshold = minThreshold;
		this.wPrice = wPrice;
		this.rPrice = rPrice;
		
	}

	public Book() {
		this.title = "nullo";
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getISBN() {
		return isbn;
	}

	public void setISBN(String isbn) {
		this.isbn = isbn;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEdition(){
		return edition;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public String getPublisher(){
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getYear(){
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getQuantity(){
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getMinThreshold(){
		return minThreshold;
	}

	public void setMinThreshold(int minThreshold) {
		this.minThreshold = minThreshold;
	}

	public double getWPrice(){
		return wPrice;
	}

	public void setWPrice(double wPrice) {
		this.wPrice = wPrice;
	}

	public double getRPrice(){
		return rPrice;
	}

	public void setRPrice(double rPrice) {
		this.rPrice = rPrice;
	}

	public void setBase64Image (String base64Image) {
		this.base64Image = base64Image;
	}

	public String getBase64Image() {
		return base64Image;
	}

}