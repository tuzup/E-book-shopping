package com.cognizant.ebook.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cognizant.ebook.model.Book;
import com.cognizant.ebook.model.Cart;

public class CartDaoSqlImpl implements CartDao {
	private final static String GET_CART = "SELECT b.book_id, b.book_title, b.book_price - (b.book_price * b.book_offer/100), b.book_category, b.book_description, b.book_offer, b.book_stock, b.book_binding, b.book_language, b.book_author, b.book_publisher, b.book_date_of_launch, c.cart_book_qty FROM e_book.book b INNER JOIN e_book.cart c ON c.cart_book_id = b.book_id WHERE c.cart_us_id = ? AND c.cart_book_qty>0;";
	private final static String GET_TOTAL = "SELECT SUM((b.book_price - (b.book_price * b.book_offer/100)) * c.cart_book_qty) FROM e_book.book b INNER JOIN e_book.cart c ON c.cart_book_id = b.book_id WHERE c.cart_us_id = ?;";
	private final static String DELETE_CART = "DELETE FROM e_book.cart WHERE cart_us_id = ? AND cart_book_id = ?";
	private final static String UPDATE_CART = "UPDATE e_book.cart SET cart_book_qty = ? WHERE cart_us_id=? AND cart_book_id=?;";
	private static final String ADD_CART = "INSERT INTO e_book.cart(cart_us_id,cart_book_id,cart_book_qty) VALUES(?,?,?)";
	private static final String CHECK_PREVIOUS_CART_ENTRY = "SELECT cart_id,cart_book_qty FROM e_book.cart WHERE cart_book_id = ? AND cart_us_id=?";
	private static final String UPDATE_EXISTING_ENTRY = "INSERT INTO cart(cart_id,cart_us_id,cart_book_id,cart_book_qty) VALUES(?,?,?,?) ON DUPLICATE KEY UPDATE cart_book_qty=?";
	private Connection connection = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;

	@Override
	public void addToCart(long userId, long bookId, int numberOfBooks) throws  NoSelectionException  {
		connection = ConnectionHandler.getConnection();
		try {
			preparedStatement = connection
					.prepareStatement(CHECK_PREVIOUS_CART_ENTRY);
			preparedStatement.setLong(1, bookId);
			preparedStatement.setLong(2, userId);
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				if (numberOfBooks != 0) {
					preparedStatement = connection.prepareStatement(ADD_CART);
					preparedStatement.setInt(1, (int) (userId));
					preparedStatement.setLong(2, bookId);
					preparedStatement.setInt(3, numberOfBooks);
					preparedStatement.executeUpdate();
				} else {
					throw new NoSelectionException();
				}
			} else {
				long cartId = resultSet.getLong(1);
				int bookQuantity = resultSet.getInt(2);
				preparedStatement = connection
						.prepareStatement(UPDATE_EXISTING_ENTRY);
				preparedStatement.setLong(1, cartId);
				preparedStatement.setLong(2, userId);
				preparedStatement.setLong(3, bookId);
				preparedStatement.setInt(4, bookQuantity);
				preparedStatement.setInt(5, numberOfBooks);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != preparedStatement) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (null != connection) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public Cart getCart(long userId) throws CartEmptyException {
		Book obj = null;
		float totalPrice = 0;
		Map<Long, Integer> numberOfBooks = new HashMap<>();
		List<Book> bookList = new ArrayList<Book>();
		Cart cart = new Cart(bookList, totalPrice, numberOfBooks);
		connection = ConnectionHandler.getConnection();
		try {
			preparedStatement = connection.prepareStatement(GET_CART);
			preparedStatement.setLong(1, userId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				do {
					long bookId = resultSet.getLong(1);
					String title = resultSet.getString(2);
					float price = resultSet.getFloat(3);
					String category = resultSet.getString(4);
					String description = resultSet.getString(5);
					int offer = resultSet.getInt(6);
					int stock = resultSet.getInt(7);
					String binding = resultSet.getString(8);
					String language = resultSet.getString(9);
					String author = resultSet.getString(10);
					String publisher = resultSet.getString(11);
					Date dateOfLaunch = resultSet.getDate(12);
					obj = new Book(bookId, title, author, category, price,
							dateOfLaunch, language, publisher, description,
							binding, offer, stock);
					int bookCount = resultSet.getInt(13);
					numberOfBooks.put(bookId, bookCount);
					bookList.add(obj);
				} while (resultSet.next());
			} else {
				throw new CartEmptyException();
			}
			cart.setBookList(bookList);
			cart.setNumberOfBooks(numberOfBooks);
			preparedStatement = connection.prepareStatement(GET_TOTAL);
			preparedStatement.setLong(1, userId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				totalPrice = resultSet.getFloat(1);
				cart.setTotalPrice(totalPrice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != resultSet) {
					resultSet.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
			try {
				if (null != preparedStatement) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (null != connection) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return cart;
	}

	@Override
	public void removeFromCart(long userId, long bookId) {
		// TODO Auto-generated method stub
		connection = ConnectionHandler.getConnection();
		try {
			preparedStatement = connection.prepareStatement(DELETE_CART);
			preparedStatement.setLong(1, userId);
			preparedStatement.setLong(2, bookId);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != preparedStatement) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (null != connection) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void updateCart(long userId, String[] bookId, String[] numberOfBooks) {
		// TODO Auto-generated method stub
		connection = ConnectionHandler.getConnection();
		try {
			preparedStatement = connection.prepareStatement(UPDATE_CART);
			for (int i = 0; i < bookId.length; i++) {
				long bookIdLong = Long.parseLong(bookId[i]);
				int numberOfBooksInt = Integer.parseInt(numberOfBooks[i]);
				preparedStatement.setInt(1, numberOfBooksInt);
				preparedStatement.setLong(2, userId);
				preparedStatement.setLong(3, bookIdLong);
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != preparedStatement) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (null != connection) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
