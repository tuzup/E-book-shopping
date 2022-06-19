package com.cognizant.ebook.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import com.cognizant.ebook.dao.CartDao;
import com.cognizant.ebook.dao.CartDaoSqlImpl;
import com.cognizant.ebook.dao.CartEmptyException;
import com.cognizant.ebook.dao.NoSelectionException;
import com.cognizant.ebook.model.Book;
import com.cognizant.ebook.model.Cart;

public class CartTestCase {

	@Test
	public void testAddToCart() throws NoSelectionException {

		CartDao cartDao = new CartDaoSqlImpl();

		cartDao.addToCart(3, 60, 3);
	}

	@Test
	public void testGetCart() throws NoSelectionException, CartEmptyException {
		CartDao cartDao = new CartDaoSqlImpl();
		Cart cart = cartDao.getCart(3);
		List<Book> bookList = cart.getBookList();

		for (Book i : bookList) {
			assertEquals(60, i.getBookId());
		}

		assertEquals(1, bookList.size());
	}

	@AfterClass
	public static void testremoveFromCart() throws NoSelectionException {

		CartDao cartDao = new CartDaoSqlImpl();

		cartDao.removeFromCart(3, 60);
	}

	@Test
	public void testupdateCart() {

		CartDao cartDao = new CartDaoSqlImpl();
		String[] bookId = { "60" };
		String[] numberOfBooks = { "6" };

		cartDao.updateCart(3, bookId, numberOfBooks);
	}

	@Test
	public void testNoSelectionException() throws NoSelectionException {
		CartDao cartDao = new CartDaoSqlImpl();
		cartDao.addToCart(7, 3, 0);

	}
}
