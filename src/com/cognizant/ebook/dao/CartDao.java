package com.cognizant.ebook.dao;

import com.cognizant.ebook.model.Cart;

public interface CartDao {
	public Cart getCart(long userId) throws CartEmptyException;

	public void removeFromCart(long userId, long bookId);

	public void updateCart(long userId, String bookId[], String numberOfBooks[]);

	public void addToCart(long userId, long bookId, int numberOfBooks)
			throws NoSelectionException;
}
