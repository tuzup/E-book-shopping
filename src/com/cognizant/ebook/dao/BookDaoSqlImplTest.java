package com.cognizant.ebook.dao;

import java.util.List;

import com.cognizant.ebook.model.Book;

public class BookDaoSqlImplTest {

	public static void main(String[] args) {
		testGetBookListCustomer();
	}

	public static void testGetBookListCustomer() {
		BookDao bookDao = new BookDaoSqlImpl();
		List<Book> bookListDisplay = bookDao.getBookListCustomer();

		for (Book i : bookListDisplay) {
			System.out.println(i.toString());
		}
	}
}
