package com.cognizant.ebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognizant.ebook.model.Order;
import com.cognizant.ebook.model.OrderInfo;

public class OrderDaoSqlImpl implements OrderDao {

	private final static String CLEAR_CART = "delete from e_book.cart where cart_us_id=?;";
	private final static String ADD_ORD = "INSERT INTO e_book.payment(ord_us_id, ord_full_name, ord_email, ord_address, ord_city, ord_state, ord_zip, ord_card_name, ord_card_number, ord_exp_month, ord_exp_year, ord_cvv, ord_mode_of_payment, ord_total_cost) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final static String GET_ORD_ID = "SELECT MAX(ord_id) FROM e_book.payment WHERE ord_us_id = ?";
	private final static String GET_ORDERS = "SELECT o.order_id,b.book_title,o.order_book_qty,b.book_price,p.ord_full_name,p.ord_address,p.ord_state,p.ord_zip,p.ord_total_cost FROM e_book.order_table o INNER JOIN e_book.payment p ON o.order_id = p.ord_id INNER JOIN e_book.book b ON o.order_book_id = b.book_id WHERE o.order_us_id = ? ORDER BY o.order_id DESC";
	private final static String STORE_ORD = "INSERT INTO e_book.order_table (order_id,order_us_id,order_book_id,order_book_qty) SELECT ?,cart_us_id,cart_book_id,cart_book_qty FROM e_book.cart WHERE cart_us_id = ?;";
	private final static String GET_ORDER = "SELECT b.book_title,o.order_book_qty, b.book_price - (b.book_price * b.book_offer/100) ,p.ord_full_name,p.ord_address,p.ord_state,p.ord_zip,p.ord_total_cost FROM e_book.order_table o INNER JOIN e_book.payment p ON o.order_id = p.ord_id INNER JOIN e_book.book b ON o.order_book_id = b.book_id WHERE o.order_id = ? ";
	private final static String CANCEL_ORDER = "DELETE FROM order_table WHERE order_us_id = ? AND order_id = ?;";
	private final static String GET_BOOK_ID = "SELECT cart_book_id from e_book.cart WHERE cart_us_id = ?;";
	private final static String REDUCE_STOCK = "UPDATE e_book.book SET book_stock = book_stock - (SELECT cart_book_qty FROM e_book.cart WHERE cart_us_id = ? AND cart_book_id = ?) WHERE book_id = ? AND book_stock > 0;";
	private final static String GET_BOOK_ID_ORDER = "SELECT order_book_id FROM e_book.order_table WHERE order_id = ?;";
	private final static String ADD_STOCK = "UPDATE e_book.book SET book_stock = book_stock + (SELECT order_book_qty FROM e_book.order_table WHERE order_id = ? AND order_book_id = ?) WHERE book_id = ?;";
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	@Override
	public void addToOrder(long userId, Order newOrder) {
		// TODO Auto-generated method stub

		connection = ConnectionHandler.getConnection();
		try {
			preparedStatement = connection.prepareStatement(ADD_ORD);
			preparedStatement.setLong(1, userId);
			preparedStatement.setString(2, newOrder.getOrdFullName());
			preparedStatement.setString(3, newOrder.getEmail());
			preparedStatement.setString(4, newOrder.getAddress());
			preparedStatement.setString(5, newOrder.getCity());
			preparedStatement.setString(6, newOrder.getState());
			preparedStatement.setInt(7, newOrder.getZip());
			preparedStatement.setString(8, newOrder.getCardName());
			preparedStatement.setLong(9, newOrder.getCardNumber());
			preparedStatement.setString(10, newOrder.getExpMonth());
			preparedStatement.setInt(11, newOrder.getExpYear());
			preparedStatement.setInt(12, newOrder.getCvv());
			preparedStatement.setString(13, newOrder.getModeOfPayment());
			preparedStatement.setInt(14, newOrder.getTotalPrice());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public int getOrderId(long userId) {
		int id = 0;
		connection = ConnectionHandler.getConnection();
		try {
			preparedStatement = connection.prepareStatement(GET_ORD_ID);
			preparedStatement.setLong(1, userId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				id = resultSet.getInt("max");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	public List<OrderInfo> getAllOrders(long userId) throws OrderEmptyException {

		List<OrderInfo> orderList = new ArrayList<OrderInfo>();
		connection = ConnectionHandler.getConnection();
		OrderInfo info = null;
		try {
			preparedStatement = connection.prepareStatement(GET_ORDERS);
			preparedStatement.setLong(1, userId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				do {
					long orderId = resultSet.getLong("order_id");
					String bookName = resultSet.getString("book_title");
					int quantity = resultSet.getInt("order_book_qty");
					int bookPrice = resultSet.getInt("book_price");
					String name = resultSet.getString("ord_full_name");
					String orderAddress = resultSet.getString("ord_address");
					String orderState = resultSet.getString("ord_state");
					long orderZip = resultSet.getLong("ord_zip");
					int total = resultSet.getInt("ord_total_cost");
					info = new OrderInfo(orderId, bookName, quantity,
							bookPrice, name, orderAddress, orderState,
							orderZip, total);
					orderList.add(info);
				} while (resultSet.next());
			} else {
				throw new OrderEmptyException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return orderList;
	}

	@Override
	public void storeOrder(long orderId, long userId) {
		connection = ConnectionHandler.getConnection();
		long bookId;
		try {
			preparedStatement = connection.prepareStatement(STORE_ORD);
			preparedStatement.setLong(1, orderId);
			preparedStatement.setLong(2, userId);
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(GET_BOOK_ID);
			preparedStatement.setLong(1, userId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				bookId = resultSet.getLong(1);
				preparedStatement = connection.prepareStatement(REDUCE_STOCK);
				preparedStatement.setLong(1, userId);
				preparedStatement.setLong(2, bookId);
				preparedStatement.setLong(3, bookId);
				preparedStatement.executeUpdate();
			}
			preparedStatement = connection.prepareStatement(CLEAR_CART);
			preparedStatement.setLong(1, userId);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public List<OrderInfo> getCurrentOrder(long orderId) throws OrderEmptyException {
		connection = ConnectionHandler.getConnection();
		OrderInfo info = null;
		List<OrderInfo> orderList = new ArrayList<OrderInfo>();
		try {
			preparedStatement = connection.prepareStatement(GET_ORDER);
			preparedStatement.setLong(1, orderId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				do {
					String bookName = resultSet.getString("book_title");
					int quantity = resultSet.getInt("order_book_qty");
					int bookPrice = resultSet.getInt(3);
					String name = resultSet.getString("ord_full_name");
					String orderAddress = resultSet.getString("ord_address");
					String orderState = resultSet.getString("ord_state");
					long orderZip = resultSet.getLong("ord_zip");
					int total = resultSet.getInt("ord_total_cost");
					info = new OrderInfo(orderId, bookName, quantity,
							bookPrice, name, orderAddress, orderState,
							orderZip, total);
					orderList.add(info);
				} while (resultSet.next());
			} else {
				throw new OrderEmptyException();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return orderList;
	}

	@Override
	public void orderCancellation(long userId, long orderId) {
		// TODO Auto-generated method stub
		connection = ConnectionHandler.getConnection();
		long bookId;
		try {
			preparedStatement = connection.prepareStatement(GET_BOOK_ID_ORDER);
			preparedStatement.setLong(1, orderId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				bookId = resultSet.getLong(1);
				preparedStatement = connection.prepareStatement(ADD_STOCK);
				preparedStatement.setLong(1, orderId);
				preparedStatement.setLong(2, bookId);
				preparedStatement.setLong(3, bookId);
				preparedStatement.executeUpdate();
			}
			preparedStatement = connection.prepareStatement(CANCEL_ORDER);
			preparedStatement.setLong(1, userId);
			preparedStatement.setLong(2, orderId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
