package com.cognizant.ebook.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import com.cognizant.ebook.dao.BookDao;
import com.cognizant.ebook.dao.BookDaoSqlImpl;
import com.cognizant.ebook.dao.CartDao;
import com.cognizant.ebook.dao.CartDaoSqlImpl;
import com.cognizant.ebook.dao.NoSelectionException;
import com.cognizant.ebook.dao.OrderDao;
import com.cognizant.ebook.dao.OrderDaoSqlImpl;
import com.cognizant.ebook.dao.OrderEmptyException;
import com.cognizant.ebook.model.Book;
import com.cognizant.ebook.model.Order;
import com.cognizant.ebook.model.OrderInfo;

public class OrderSqlImpJUnitTest {

	@Test
	public void testAddAndStoreOrderTest() throws NoSelectionException {

		CartDao cartDao = new CartDaoSqlImpl();

		cartDao.addToCart(3, 60, 3);

		OrderDao orderDao = new OrderDaoSqlImpl();
		Order order = new Order(1, "Lokesh", "lokesh@ebook.com", "KCT",
				"Coimbatore", "Tamil Nadu", 123456, "LOKESH", 111122223, "03",
				2020, 243, "COD", 500);
		orderDao.addToOrder(3, order);
		orderDao.storeOrder(18, 3); 
	}

	@Test
	public void getCurrentOrderTest() throws OrderEmptyException {
		OrderDao orderDao = new OrderDaoSqlImpl();
		try {
			for (OrderInfo i : orderDao.getCurrentOrder(18)) {
				System.out.println(i.getBookName());
				
				assertEquals("TestModify", i.getBookName());
				assertEquals(3, i.getQuantity());
				assertEquals(500, i.getBookPrice());
				assertEquals("Lokesh", i.getName());
				assertEquals("KCT", i.getAddress());
				assertEquals("Tamil Nadu", i.getState());
				assertEquals(123456, i.getZip());
				assertEquals(500, i.getTotal());
			}
		} catch (OrderEmptyException e) {
			e.printStackTrace();

		}

	}

	@Test
	public void getAllOrderTest() throws OrderEmptyException {
		int j = 0;
		OrderDao orderDao = new OrderDaoSqlImpl();
		List<OrderInfo> orderList = orderDao.getAllOrders(3);
		for (OrderInfo i : orderList) {
			while (j < 1) {
				
				assertEquals("TestModify", i.getBookName());
				assertEquals(3, i.getQuantity());
				assertEquals(500, i.getBookPrice());
				assertEquals("Lokesh", i.getName());
				assertEquals("KCT", i.getAddress());
				assertEquals("Tamil Nadu", i.getState());
				assertEquals(123456, i.getZip());
				assertEquals(500, i.getTotal());
				j++;
			}
		}
		assertEquals(1, orderList.size());

	}

	@AfterClass
	public static void orderCancellationTest() {
		OrderDao orderDao = new OrderDaoSqlImpl();
		orderDao.orderCancellation(3, 18);
		try {
			for (OrderInfo i : orderDao.getAllOrders(1)) {
				
				assertEquals("TestModify", i.getBookName());
				assertEquals(3, i.getQuantity());
				assertEquals(500, i.getBookPrice());
				assertEquals("Lokesh", i.getName());
				assertEquals("KCT", i.getAddress());
				assertEquals("Tamil Nadu", i.getState());
				assertEquals(123456, i.getZip());
				assertEquals(500, i.getTotal());
			}
		} catch (OrderEmptyException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testGetMostPurchasedBooks() {
		int j = 0;
		float delta = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		BookDao bookDao = new BookDaoSqlImpl();
		List<Book> mostPurchasedBooks = bookDao.mostPurchased();

		for (Book i : mostPurchasedBooks) {
			System.out.println(i.getTitle());
			while (j < 1) {

				assertEquals(60, i.getBookId());
				assertEquals("TestModify", i.getTitle());
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
				assertEquals(27, i.getStock());

				j++;
			}
		}
		assertEquals(1, mostPurchasedBooks.size());
	}
	
	@Test
	public void testCorrectValueGetOrderId() {
		OrderDao orderDao = new OrderDaoSqlImpl();
		assertEquals(19, orderDao.getOrderId(3)); //OrderId is auto Increment in database
	}
	
	@Test
	public void testWrongValueGetOrderId() {
		OrderDao orderDao = new OrderDaoSqlImpl();
		assertNotEquals(1234, orderDao.getOrderId(3));	
	}
	
	@Test
	public void testTwoDigitValueGetOrderId() {
		OrderDao orderDao = new OrderDaoSqlImpl();
		assertNotEquals(12, orderDao.getOrderId(3));
	}
	
	@Test
	public void testAlphabetGetOrderId() {
		OrderDao orderDao = new OrderDaoSqlImpl();
		assertNotEquals("a", orderDao.getOrderId(1));	
	}


}
