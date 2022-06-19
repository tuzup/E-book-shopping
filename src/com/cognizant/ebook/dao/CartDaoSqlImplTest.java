package com.cognizant.ebook.dao;

import java.util.ArrayList;
import java.util.List;

import com.cognizant.ebook.model.Book;

public class CartDaoSqlImplTest {

	public static void main(String[] args) {
		testAddToCart();

	}

	public static void testAddToCart() {
		CartDao cartDao = new CartDaoSqlImpl();
		try {
			cartDao.addToCart(1, 1, 1);
			cartDao.addToCart(1, 2, 1);
			List<Book> addedBooks = new ArrayList<Book>();
			addedBooks = cartDao.getCart(1).getBookList();

			for (Book i : addedBooks) {
				System.out.println(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
