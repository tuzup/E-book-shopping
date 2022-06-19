package com.cognizant.ebook.model;

import java.io.Serializable;

public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -390655278955317425L;
	private int ordId;
	private String ordFullName;
	private String email;
	private String address;
	private String city;
	private String state;
	private int zip;
	private String cardName;
	private long cardNumber;
	private String expMonth;
	private int expYear;
	private int cvv;
	private String modeOfPayment;
	private int totalPrice;
	
	public Order(int ordId, String ordFullName, String email, String address, String city, String state, int zip, String cardName, long cardNumber, String expMonth, int expYear, int cvv, String modeOfPayment, int totalPrice){
		this.ordId = ordId;
		this.ordFullName = ordFullName;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.cardName = cardName;
		this.cardNumber = cardNumber;
		this.expMonth = expMonth;
		this.expYear = expYear;
		this.cvv = cvv;
		this.modeOfPayment = modeOfPayment;
		this.totalPrice = totalPrice;
	}
	
	public int getOrdId() {
		return ordId;
	}

	public void setOrdId(int ordId) {
		this.ordId = ordId;
	}

	public String getOrdFullName() {
		return ordFullName;
	}

	public void setOrdFullName(String ordFullName) {
		this.ordFullName = ordFullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}

	public int getExpYear() {
		return expYear;
	}

	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "Order [ordId=" + ordId + ", ordFullName=" + ordFullName
				+ ", email=" + email + ", address=" + address + ", city="
				+ city + ", state=" + state + ", zip=" + zip + ", cardName="
				+ cardName + ", cardNumber=" + cardNumber + ", expMonth="
				+ expMonth + ", expYear=" + expYear + ", cvv=" + cvv
				+ ", modeOfPayment=" + modeOfPayment + ", totalPrice="
				+ totalPrice + "]";
	}
	
	

}
