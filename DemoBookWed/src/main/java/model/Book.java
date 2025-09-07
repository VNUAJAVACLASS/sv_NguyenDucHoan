package model;

public class Book {
	private int bookId;
	private String title;// tieu de
	private String author;// tac gia
	private Double price;// giâ
	private String imagePath;

	 public Book(int bookId, String title, String author, Double price, String imagaePath) {
		this.bookId = bookId;
		this.author = author;
		this.title = title;
		this.price = price;
		this.imagePath = imagaePath;
	}

	 public Book(String title, String author, Double price, String imagaePath) {
		this.author = author;
		this.title = title;
		this.price = price;
		this.imagePath = imagaePath;
	}

	public String getAuthor() {
		return author;
	}

	public int getBookId() {
		return bookId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public Double getPrice() {
		return price;
	}

	public String getTitle() {
		return title;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
