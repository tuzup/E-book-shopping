package com.cognizant.ebook.model;

import java.io.Serializable;


public class OrderInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8564264546936371516L;
	private long ordId;
	private String bookName;
	private int quantity;
	private int bookPrice;
	private String name;
	private String address;
	private String state;
	private long zip;
	private int total;
	
	public OrderInfo(long ordId, String bookName, int quantity, int bookPrice, String name, String address, String state, long zip, int total) {
	this.ordId = ordId;
	this.bookName = bookName;
	this.quantity = quantity;
	this.bookPrice = bookPrice;
	this.name = name;
	this.address = address;
	this.state = state;
	this.zip = zip;
	this.total = total;
	}
	
	public long getOrdId() {
		return ordId;
	}

	public String getBookName() {
		return bookName;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public String getAddress() {
		return address;
	}

	public String getState() {
		return state;
	}

	public long getZip() {
		return zip;
	}

	public int getTotal() {
		return total;
	}

	public void setOrdId(long ordId) {
		this.ordId = ordId;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZip(long zip) {
		this.zip = zip;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}		
}
