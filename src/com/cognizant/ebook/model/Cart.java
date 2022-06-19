package com.cognizant.ebook.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3344629729812410088L;
	private List<Book> bookList = new ArrayList<Book>();
	private double totalPrice;
	private Map<Long, Integer> numberOfBooks = new HashMap<>();

	public Cart(List<Book> bookList, double totalPrice,
			Map<Long, Integer> numberOfBooks) {
		this.bookList = bookList;
		this.totalPrice = totalPrice;
		this.numberOfBooks = numberOfBooks;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Map<Long, Integer> getNumberOfBooks() {
		return numberOfBooks;
	}

	public void setNumberOfBooks(Map<Long, Integer> numberOfBooks) {
		this.numberOfBooks = numberOfBooks;
	}

}