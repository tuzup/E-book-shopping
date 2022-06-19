package com.cognizant.ebook.dao;

public class OrderEmptyException extends Exception {

	private static final long serialVersionUID = 1L;

	public OrderEmptyException() {
		super("Order is Empty");
	}

}
