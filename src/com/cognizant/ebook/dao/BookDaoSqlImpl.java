package com.cognizant.ebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.cognizant.ebook.model.Book;

public class BookDaoSqlImpl implements BookDao {
	private Book book = null;
	private List<Book> bookListCustomer;
	private PreparedStatement preparedStatement;
	private Statement statement;
	private Connection connection;
	private ResultSet resultSet;
	private static final String bookListCustomerSql = "SELECT  book_id, book_title, book_price, book_category, book_description, book_offer, book_stock, book_binding, book_language, book_author, book_publisher, book_date_of_launch FROM e_book.book WHERE book_stock > 0;";
	private static final String getBookSql = "SELECT book_id, book_title, book_price, book_category, book_description, book_offer, book_stock, book_binding, book_language, book_author, book_publisher, book_date_of_launch FROM e_book.book WHERE book_id=?";
	private static final String insertNewBookSql = "INSERT INTO e_book.book(book_title, book_price, book_category, book_description, book_offer, book_stock, book_binding, book_language, book_author, book_publisher, book_date_of_launch) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String getOffer = "SELECT book_id, book_title, book_price, book_category, book_description, book_offer, book_stock, book_binding, book_language, book_author, book_publisher, book_date_of_launch FROM e_book.book WHERE book_offer >0 ORDER BY book_offer DESC;";
	private static final String getNewBooksSql = "SELECT book_id, book_title, book_price, book_category, book_description, book_offer, book_stock, book_binding, book_language, book_author, book_publisher, book_date_of_launch FROM e_book.book WHERE DATE(book_date_of_launch) >= now() - INTERVAL '30 DAY' ORDER BY book_date_of_launch DESC LIMIT 10 ";
	private static final String GET_BOOK_LIST_ADMIN = "SELECT book_id, book_title, book_price, book_category, book_description, book_offer, book_stock, book_binding, book_language, book_author, book_publisher, book_date_of_launch FROM e_book.book;";
	private static final String EDIT_BOOK_DETAILS = "UPDATE e_book.book SET book_id=?,book_title =?,book_author = ?,book_category = ?,book_price = ?,book_date_of_launch = ?,book_language = ?,book_publisher =?,book_description = ?,book_binding = ?,book_stock = ?,book_offer = ? WHERE book_id = ?;";
	private final static String DISTINCT_BOOK_ID = "SELECT distinct(order_book_id) from e_book.order_table;";
	private final static String INSERT_PURCHASED = "INSERT INTO e_book.purchased (purchased_book_id,purchased_book_qty) SELECT book_id,sum(order_book_qty) FROM e_book.book INNER JOIN e_book.order_table on book_id = order_book_id AND order_book_id = ? GROUP BY book_id;";
	private static final String PURCHASED_BOOKS = "SELECT book_id, book_title, book_price, book_category, book_description, book_offer, book_stock, book_binding, book_language, book_author, book_publisher, book_date_of_launch FROM e_book.book INNER JOIN e_book.purchased ON book_id = purchased_book_id   ORDER BY purchased_book_qty DESC LIMIT 5;";
	private static final String TRUNCATE_PURCHASED = "TRUNCATE TABLE e_book.purchased;";
	private static final String increment_view = "UPDATE e_book.book SET views = views+1 WHERE book_id = ?;";
	private static final String select_max_view = "SELECT book_id, book_title, book_price, book_category, book_description, book_offer, book_stock, book_binding, book_language, book_author, book_publisher, book_date_of_launch FROM e_book.book WHERE book_category = (SELECT book_category FROM e_book.book ORDER BY views DESC LIMIT 1 ) ORDER BY views DESC LIMIT 5;";

	public List<Book> getBookListCustomer() {
		connection = ConnectionHandler.getConnection();
		bookListCustomer = new ArrayList<Book>();

		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(bookListCustomerSql);
			while (resultSet.next()) {
				long bookId = resultSet.getLong("book_id");
				String bookTitle = resultSet.getString("book_title");
				float bookPrice = resultSet.getFloat("book_price");
				String bookCategory = resultSet.getString("book_category");
				String bookDescription = resultSet
						.getString("book_description");
				int bookOffer = resultSet.getInt("book_offer");
				int bookStock = resultSet.getInt("book_stock");
				String bookBinding = resultSet.getString("book_binding");
				String bookLanguage = resultSet.getString("book_language");
				String bookAuthor = resultSet.getString("book_author");
				String bookPublisher = resultSet.getString("book_publisher");
				Date dateOfLaunch = resultSet.getDate("book_date_of_launch");
				book = new Book(bookId, bookTitle, bookAuthor, bookCategory,
						bookPrice, dateOfLaunch, bookLanguage, bookPublisher,
						bookDescription, bookBinding, bookOffer, bookStock);
				bookListCustomer.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
			try {
				if (resultSet != null) {
					resultSet.close();
				}

			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		return bookListCustomer;
	}

	@Override
	public List<Book> getBookListAdmin() {
		List<Book> bookListAdmin = new ArrayList<Book>();
		Book book;
		try {
			connection = ConnectionHandler.getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(GET_BOOK_LIST_ADMIN);
			while (resultSet.next()) {
				long bookId = resultSet.getLong("book_id");
				String bookTitle = resultSet.getString("book_title");
				float bookPrice = resultSet.getFloat("book_price");
				String bookCategory = resultSet.getString("book_category");
				String bookDescription = resultSet
						.getString("book_description");
				int bookOffer = resultSet.getInt("book_offer");
				int bookStock = resultSet.getInt("book_stock");
				String bookBinding = resultSet.getString("book_binding");
				String bookLanguage = resultSet.getString("book_language");
				String bookAuthor = resultSet.getString("book_author");
				String bookPublisher = resultSet.getString("book_publisher");
				Date dateOfLaunch = resultSet.getDate("book_date_of_launch");
				book = new Book(bookId, bookTitle, bookAuthor, bookCategory,
						bookPrice, dateOfLaunch, bookLanguage, bookPublisher,
						bookDescription, bookBinding, bookOffer, bookStock);
				bookListAdmin.add(book);
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
			try {
				if (resultSet != null) {
					resultSet.close();
				}

			} catch (Exception e) {
				e.printStackTrace();

			}
		}

		return bookListAdmin;
	}

	@Override
	public void modifyBook(Book book) {
		Connection connection = null;
		try {
			connection = ConnectionHandler.getConnection();
			preparedStatement = connection.prepareStatement(EDIT_BOOK_DETAILS);
			preparedStatement.setLong(1, book.getBookId());
			preparedStatement.setString(2, book.getTitle());
			preparedStatement.setString(3, book.getAuthor());
			preparedStatement.setString(4, book.getCategory());
			preparedStatement.setFloat(5, book.getPrice());
			preparedStatement.setDate(6, book.getDateOfLaunch());
			preparedStatement.setString(7, book.getLanguage());
			preparedStatement.setString(8, book.getPublisher());
			preparedStatement.setString(9, book.getDescription());
			preparedStatement.setString(10, book.getBinding());
			preparedStatement.setInt(11, book.getStock());
			preparedStatement.setInt(12, book.getOffer());
			preparedStatement.setLong(13, book.getBookId());
			preparedStatement.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		}

	}

	@Override
	public Book getBook(long bookId)  throws BookNotFoundException {
		connection = ConnectionHandler.getConnection();
		preparedStatement = null;

		try {
			preparedStatement = connection.prepareStatement(increment_view);
			preparedStatement.setLong(1, bookId);
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(getBookSql);
			preparedStatement.setLong(1, bookId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String bookTitle = resultSet.getString("book_title");
				float bookPrice = resultSet.getFloat("book_price");
				String bookCategory = resultSet.getString("book_category");
				String bookDescription = resultSet
						.getString("book_description");
				int bookOffer = resultSet.getInt("book_offer");
				int bookStock = resultSet.getInt("book_stock");
				String bookBinding = resultSet.getString("book_binding");
				String bookLanguage = resultSet.getString("book_language");
				String bookAuthor = resultSet.getString("book_author");
				String bookPublisher = resultSet.getString("book_publisher");
				Date dateOfLaunch = resultSet.getDate("book_date_of_launch");
				book = new Book(bookId, bookTitle, bookAuthor, bookCategory,
						bookPrice, dateOfLaunch, bookLanguage, bookPublisher,
						bookDescription, bookBinding, bookOffer, bookStock);
			} else{
				throw new BookNotFoundException();
			}
		} catch (SQLException e) {

		

			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
			try {
				if (resultSet != null) {
					resultSet.close();
				}

			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		return book;

	}

	public void AddNewBookAdmin(String title, float price, String category,
			String description, int offer, int stock, String binding,
			String language, String author, String publisher, Date dateOfLaunch) {
		connection = ConnectionHandler.getConnection();
		try {
			preparedStatement = connection.prepareStatement(insertNewBookSql);
			preparedStatement.setString(1, title);
			preparedStatement.setFloat(2, price);
			preparedStatement.setString(3, category);
			preparedStatement.setString(4, description);
			preparedStatement.setInt(5, offer);
			preparedStatement.setInt(6, stock);
			preparedStatement.setString(7, binding);
			preparedStatement.setString(8, language);
			preparedStatement.setString(9, author);
			preparedStatement.setString(10, publisher);
			preparedStatement.setDate(11,
					new java.sql.Date(dateOfLaunch.getTime()));
			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

	@Override
	public List<Book> getOffer() throws OfferEmptyException {
		connection = ConnectionHandler.getConnection();
		Book obj;
		List<Book> offerList = new ArrayList<Book>();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(getOffer);
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
					offerList.add(obj);
				} while (resultSet.next());
			} else {
				throw new OfferEmptyException();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
			try {
				if (resultSet != null) {
					resultSet.close();
				}

			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		return offerList;
	}

	@Override
	public List<Book> getNewBooks() {
		connection = ConnectionHandler.getConnection();
		preparedStatement = null;
		List<Book> newBooksList = new ArrayList<Book>();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(getNewBooksSql);
			while (resultSet.next()) {
				long bookId = resultSet.getLong("book_id");
				String bookTitle = resultSet.getString("book_title");
				float bookPrice = resultSet.getFloat("book_price");
				String bookCategory = resultSet.getString("book_category");
				String bookDescription = resultSet
						.getString("book_description");
				int bookOffer = resultSet.getInt("book_offer");
				int bookStock = resultSet.getInt("book_stock");
				String bookBinding = resultSet.getString("book_binding");
				String bookLanguage = resultSet.getString("book_language");
				String bookAuthor = resultSet.getString("book_author");
				String bookPublisher = resultSet.getString("book_publisher");
				Date dateOfLaunch = resultSet.getDate("book_date_of_launch");
				book = new Book(bookId, bookTitle, bookAuthor, bookCategory,
						bookPrice, dateOfLaunch, bookLanguage, bookPublisher,
						bookDescription, bookBinding, bookOffer, bookStock);
				newBooksList.add(book);
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
			try {
				if (resultSet != null) {
					resultSet.close();
				}

			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		return newBooksList;

	}

	@Override
	public List<Book> mostPurchased() {
		connection = ConnectionHandler.getConnection();
		int orderBookId = 0;
		Book book = null;
		List<Book> newBooksList = new ArrayList<Book>();
		try {
			statement = connection.createStatement();
			statement.executeUpdate(TRUNCATE_PURCHASED);
			resultSet = statement.executeQuery(DISTINCT_BOOK_ID);
			while (resultSet.next()) {
				orderBookId = resultSet.getInt(1);
				preparedStatement = connection
						.prepareStatement(INSERT_PURCHASED);
				preparedStatement.setInt(1, orderBookId);
				preparedStatement.executeUpdate();
			}
			resultSet = statement.executeQuery(PURCHASED_BOOKS);
			while (resultSet.next()) {
				long bookId = resultSet.getLong("book_id");
				String bookTitle = resultSet.getString("book_title");
				float bookPrice = resultSet.getFloat("book_price");
				String bookCategory = resultSet.getString("book_category");
				String bookDescription = resultSet
						.getString("book_description");
				int bookOffer = resultSet.getInt("book_offer");
				int bookStock = resultSet.getInt("book_stock");
				String bookBinding = resultSet.getString("book_binding");
				String bookLanguage = resultSet.getString("book_language");
				String bookAuthor = resultSet.getString("book_author");
				String bookPublisher = resultSet.getString("book_publisher");
				Date dateOfLaunch = resultSet.getDate("book_date_of_launch");
				book = new Book(bookId, bookTitle, bookAuthor, bookCategory,
						bookPrice, dateOfLaunch, bookLanguage, bookPublisher,
						bookDescription, bookBinding, bookOffer, bookStock);
				newBooksList.add(book);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return newBooksList;
	}

	public List<Book> getMostViewedBooks() {

		connection = ConnectionHandler.getConnection();
		List<Book> mostViewedBooksList = new ArrayList<Book>();
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(select_max_view);
			while (resultSet.next()) {
				long bookId = resultSet.getLong("book_id");
				String bookTitle = resultSet.getString("book_title");
				float bookPrice = resultSet.getFloat("book_price");
				String bookCategory = resultSet.getString("book_category");
				String bookDescription = resultSet
						.getString("book_description");
				int bookOffer = resultSet.getInt("book_offer");
				int bookStock = resultSet.getInt("book_stock");
				String bookBinding = resultSet.getString("book_binding");
				String bookLanguage = resultSet.getString("book_language");
				String bookAuthor = resultSet.getString("book_author");
				String bookPublisher = resultSet.getString("book_publisher");
				Date dateOfLaunch = resultSet.getDate("book_date_of_launch");
				book = new Book(bookId, bookTitle, bookAuthor, bookCategory,
						bookPrice, dateOfLaunch, bookLanguage, bookPublisher,
						bookDescription, bookBinding, bookOffer, bookStock);
				mostViewedBooksList.add(book);
			}

		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {

					resultSet.close();
				}
				if (statement != null) {

					statement.close();
				}
				if (connection != null) {

					connection.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return mostViewedBooksList;

	}

}
