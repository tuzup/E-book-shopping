package com.cognizant.ebook.model;

import java.io.Serializable;
import java.sql.Date;

public class Book implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -716563222333755595L;
	private long bookId;
	private String title;
	private String author;
	private String category;
	private float price;
	private Date dateOfLaunch;
	private String language;
	private String publisher;
	private String description;
	private String binding;
	private int offer;
	private int stock;

	public Book(long bookId, String title, String author, String category,
			float price, Date dateOfLaunch, String language, String publisher,
			String description, String binding, int offer, int stock) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.category = category;
		this.price = price;
		this.dateOfLaunch = dateOfLaunch;
		this.language = language;
		this.publisher = publisher;
		this.description = description;
		this.binding = binding;
		this.offer = offer;
		this.stock = stock;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Date getDateOfLaunch() {
		return dateOfLaunch;
	}

	public void setDateOfLaunch(Date dateOfLaunch) {
		this.dateOfLaunch = dateOfLaunch;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getOffer() {
		return offer;
	}

	public void setOffer(int offer) {
		this.offer = offer;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getBinding() {
		return binding;
	}

	public void setBinding(String binding) {
		this.binding = binding;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", title=" + title + ", author="
				+ author + ", category=" + category + ", price=" + price
				+ ", dateOfLaunch=" + dateOfLaunch + ", language=" + language
				+ ", publisher=" + publisher + ", description=" + description
				+ ", binding=" + binding + ", offer=" + offer + ", stock="
				+ stock + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (bookId != other.bookId)
			return false;
		return true;
	}

}
