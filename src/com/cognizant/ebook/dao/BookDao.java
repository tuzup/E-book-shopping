package com.cognizant.ebook.dao;

import java.sql.Date;
import java.util.List;

import com.cognizant.ebook.model.Book;

public interface BookDao {
	public List<Book> getBookListAdmin();

	public List<Book> getBookListCustomer();

	public void modifyBook(Book book);

	public Book getBook(long bookId) throws BookNotFoundException;
	
	void AddNewBookAdmin(String title, float price, String category,
			String description, int offer, int stock, String binding,
			String language, String author, String publisher, Date dateOfLaunch);

	public List<Book> getOffer() throws OfferEmptyException;

	public List<Book> getNewBooks();

	public List<Book> mostPurchased();

	public List<Book> getMostViewedBooks();
}
