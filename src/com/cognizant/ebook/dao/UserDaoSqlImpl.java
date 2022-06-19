package com.cognizant.ebook.dao;

import java.sql.*;

import com.cognizant.ebook.model.User;

public class UserDaoSqlImpl implements UserDao {
	private final static String LOGIN_AUTHENTICATION = "SELECT us_id,us_password from e_book.user WHERE us_email_id = ? ;";
	private static final String ADD_USER = "INSERT INTO e_book.user(us_first_name,us_last_name,us_age,us_gender,us_contact_number,us_email_id,us_password) VALUES(?,?,?,?,?,?,?);";
	private static final String IS_EXIST_USER = "SELECT COUNT(us_id) FROM e_book.user WHERE us_email_id=?;";
	private Connection connection = null;
	private ResultSet resultSet = null;
	private PreparedStatement preparedStatement = null;

	@Override
	public long loginAuthentication(String email, String password) {
		connection = ConnectionHandler.getConnection();
		try {
			preparedStatement = connection
					.prepareStatement(LOGIN_AUTHENTICATION);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				if (resultSet.getString("us_password").equals(password)) {
					return resultSet.getLong("us_id");
				}
			}
			return 0;
		} catch (Exception e) {
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

			try {
				if (null != resultSet) {
					resultSet.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
		return 0;
	}

	@Override
	public void addUser(User user) {
		connection = ConnectionHandler.getConnection();
		try {
			preparedStatement = connection.prepareStatement(ADD_USER);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setInt(3, user.getAge());
			preparedStatement.setString(4, user.isGender() ? "Male" : "Female");
			preparedStatement.setLong(5, user.getContactNumber());
			preparedStatement.setString(6, user.getEmailId());
			preparedStatement.setString(7, user.getPassword());
			preparedStatement.executeUpdate();
		} catch (Exception e) {
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
	public boolean isExistUser(String emailId) {
		try {
			connection = ConnectionHandler.getConnection();
			preparedStatement = connection.prepareStatement(IS_EXIST_USER);
			preparedStatement.setString(1, emailId);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				if (resultSet.getInt(1) == 0) {
					return true;
				} else {
					return false;
				}
			}
		} catch (Exception e) {
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

				try {
					if (null != resultSet) {
						resultSet.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
			}
		return false;
	}
}
