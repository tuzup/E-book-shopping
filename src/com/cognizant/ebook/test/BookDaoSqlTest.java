package com.cognizant.ebook.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cognizant.ebook.dao.BookDao;
import com.cognizant.ebook.dao.BookDaoSqlImpl;
import com.cognizant.ebook.dao.BookNotFoundException;
import com.cognizant.ebook.dao.OfferEmptyException;
import com.cognizant.ebook.model.Book;

public class BookDaoSqlTest {
	Book firstBook = null;
	Book secondBook = null;
	List<Book> testList = new ArrayList<Book>();
	List<Book> bookList = new ArrayList<Book>();
	Book book = null;
	float delta = 0;

	@Test
	public void testAddNewBookAdmin() {
		BookDao bookDao = new BookDaoSqlImpl();
		Date date = new Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		bookDao.AddNewBookAdmin("The Da Vinci Code", 500, "Mystery",
				"description", 0, 30, "Hardcover", "English", "Dan Brown",
				"abc books", sqlDate);

		bookDao.AddNewBookAdmin("And The Mountains Echoed", 600, "Fiction",
				"description", 0, 30, "Hardcover", "English",
				"Khaled Hosseini", "abc books", sqlDate);

	}

	@Test
	public void testGetNewBook() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		BookDao bookDao = new BookDaoSqlImpl();
		int j = 0;

		List<Book> newBooks = bookDao.getNewBooks();
		for (Book i : newBooks) {
			while (j < 1) {

				assertEquals(62, i.getBookId());
				assertEquals("And The Mountains Echoed", i.getTitle());
				assertEquals("Khaled Hosseini", i.getAuthor());
				assertEquals("Fiction", i.getCategory());
				assertEquals("26/09/2019",
						formatter.format(i.getDateOfLaunch()));
				assertEquals(600F, i.getPrice(), delta);
				assertEquals("English", i.getLanguage());
				assertEquals("abc books", i.getPublisher());
				assertEquals("description", i.getDescription());
				assertEquals("Hardcover", i.getBinding());
				assertEquals(10, i.getOffer());
				assertEquals(30, i.getStock());
				j++;
			}
		}
		assertEquals(1, newBooks.size());

	}

	@Test
	public void testGetBookListCustomer() {
		int j = 0;

		BookDao bookDao = new BookDaoSqlImpl();

		bookList = bookDao.getBookListCustomer();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		for (Book i : bookList) {

			while (j < 1) {

				assertEquals(60, i.getBookId());
				assertEquals("The Da Vinci Code", i.getTitle());
				assertEquals("Dan Brown", i.getAuthor());
				assertEquals("Mystery", i.getCategory());
				assertEquals("26/05/2016",
						formatter.format(i.getDateOfLaunch()));
				assertEquals(500F, i.getPrice(), delta);
				assertEquals("English", i.getLanguage());
				assertEquals("abc books", i.getPublisher());
				assertEquals("description", i.getDescription());
				assertEquals("Hardcover", i.getBinding());
				assertEquals(0, i.getOffer());
				assertEquals(30, i.getStock());
				j++;
			}

		}

		assertEquals(3, bookList.size());
	}

	@Test
	public void testGetBookListCustomerFailCase() {

		BookDao bookDao = new BookDaoSqlImpl();

		bookList = bookDao.getBookListCustomer();

		for (Book i : bookList) {
			assertNotEquals(63, i.getBookId());

		}

	}

	@Test
	public void testGetBookListAdmin() {
		int j = 0;

		BookDao bookDao = new BookDaoSqlImpl();

		bookList = bookDao.getBookListAdmin();

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		for (Book i : bookList) {

			while (j < 1) {

				assertEquals(60, i.getBookId());
				assertEquals("The Da Vinci Code", i.getTitle());
				assertEquals("Dan Brown", i.getAuthor());
				assertEquals("Mystery", i.getCategory());
				assertEquals("26/05/2016",
						formatter.format(i.getDateOfLaunch()));
				assertEquals(500F, i.getPrice(), delta);
				assertEquals("English", i.getLanguage());
				assertEquals("abc books", i.getPublisher());
				assertEquals("description", i.getDescription());
				assertEquals("Hardcover", i.getBinding());
				assertEquals(0, i.getOffer());
				assertEquals(30, i.getStock());
				j++;
			}

		}

		assertEquals(3, bookList.size());

	}

	@Test
	public void testGetBook() throws BookNotFoundException {

		BookDao bookDao = new BookDaoSqlImpl();

		book = bookDao.getBook(60);

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		assertEquals(60, book.getBookId());
		assertEquals("The Da Vinci Code", book.getTitle());
		assertEquals("Dan Brown", book.getAuthor());
		assertEquals("Mystery", book.getCategory());
		assertEquals("26/05/2016", formatter.format(book.getDateOfLaunch()));
		assertEquals(500F, book.getPrice(), delta);
		assertEquals("English", book.getLanguage());
		assertEquals("abc books", book.getPublisher());
		assertEquals("description", book.getDescription());
		assertEquals("Hardcover", book.getBinding());
		assertEquals(0, book.getOffer());
		assertEquals(30, book.getStock());

	}

	@Test
	public void testGetBookFailCase() throws BookNotFoundException {

		BookDao bookDao = new BookDaoSqlImpl();

		book = bookDao.getBook(60);

		assertNotEquals(62, book.getBookId());
		assertNotEquals(63, book.getBookId());

	}

	@Test
	public void testGetOffer() throws OfferEmptyException {

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		BookDao bookDao = new BookDaoSqlImpl();
		int j = 0;
		try {
			List<Book> offerBooks = bookDao.getOffer();
			for (Book i : offerBooks) {
				while (j < 1) {

					assertEquals(62, i.getBookId());
					assertEquals("And The Mountains Echoed", i.getTitle());
					assertEquals("Khaled Hosseini", i.getAuthor());
					assertEquals("Fiction", i.getCategory());
					assertEquals("26/09/2019",
							formatter.format(i.getDateOfLaunch()));
					assertEquals(600F, i.getPrice(), delta);
					assertEquals("English", i.getLanguage());
					assertEquals("abc books", i.getPublisher());
					assertEquals("description", i.getDescription());
					assertEquals("Hardcover", i.getBinding());
					assertEquals(10, i.getOffer());
					assertEquals(30, i.getStock());
					j++;
				}
			}
			assertEquals(2, offerBooks.size());
		} catch (OfferEmptyException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testModifyBook() throws BookNotFoundException {
		BookDao bookDao = new BookDaoSqlImpl();
		book = bookDao.getBook(60);
		book.setTitle("TestModify");
		bookDao.modifyBook(book);

	}

	@Test
	public void testGetMostViewedBook() {
		BookDao bookDao = new BookDaoSqlImpl();
		bookList = bookDao.getMostViewedBooks();
		int j = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		for (Book i : bookList) {

			while (j < 1) {
				System.out.println(i.getTitle());
				assertEquals(60, i.getBookId());
				assertEquals("The Da Vinci Code", i.getTitle());
				assertEquals("Dan Brown", i.getAuthor());
				assertEquals("Mystery", i.getCategory());
				assertEquals("26/05/2016",
						formatter.format(i.getDateOfLaunch()));
				assertEquals(500F, i.getPrice(), delta);
				assertEquals("English", i.getLanguage());
				assertEquals("abc books", i.getPublisher());
				assertEquals("description", i.getDescription());
				assertEquals("Hardcover", i.getBinding());
				assertEquals(0, i.getOffer());
				assertEquals(30, i.getStock());
				j++;
			}

		}

		assertEquals(1, bookList.size());
	}

	@AfterClass
	public static void testModifiedBook() throws BookNotFoundException {
		float delta = 0;
		BookDao bookDao = new BookDaoSqlImpl();
		Book book = null;
		book = bookDao.getBook(60);

		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		assertEquals(60, book.getBookId());
		assertNotEquals("The Da Vinci Code", book.getTitle());
		assertEquals("Dan Brown", book.getAuthor());
		assertEquals("Mystery", book.getCategory());
		assertEquals("26/05/2016", formatter.format(book.getDateOfLaunch()));
		assertEquals(500F, book.getPrice(), delta);
		assertEquals("English", book.getLanguage());
		assertEquals("abc books", book.getPublisher());
		assertEquals("description", book.getDescription());
		assertEquals("Hardcover", book.getBinding());
		assertEquals(0, book.getOffer());
		assertEquals(30, book.getStock());

	}

	@BeforeClass
	public static void changeModifiedBook() throws BookNotFoundException {
		BookDao bookDao = new BookDaoSqlImpl();
		Book book = null;
		book = bookDao.getBook(60);
		book.setTitle("The Da Vinci Code");
		bookDao.modifyBook(book);
	}

}