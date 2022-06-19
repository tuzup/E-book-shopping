package com.cognizant.ebook.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cognizant.ebook.dao.UserDao;
import com.cognizant.ebook.dao.UserDaoSqlImpl;
import com.cognizant.ebook.model.User;

public class LoginAuthenticationTest {

	@Test
	public void testAddUser() {
		UserDao userDao = new UserDaoSqlImpl();
		User user = new User("jack", "ma", 55, true, 1234567888, "jack@ma.com",
				"alibaba");

		userDao.addUser(user);
	}

	@Test
	public void testIsExistUser() {
		UserDao userDao = new UserDaoSqlImpl();

		assertEquals(false, userDao.isExistUser("jack@ma.com"));

	}

	@Test
	public void testNotIsExistUser() {
		UserDao userDao = new UserDaoSqlImpl();

		assertEquals(true, userDao.isExistUser("steve@jobs.com"));

	}

	@Test
	public void testAcceptCustomerLogin() {
		UserDao userDao = new UserDaoSqlImpl();
		assertEquals(11, userDao.loginAuthentication("jack@ma.com", "alibaba"));

	}

	@Test
	public void testInvalidCustomerLoginUsername() {
		UserDao userDao = new UserDaoSqlImpl();
		assertEquals(0, userDao.loginAuthentication("steve@musk.com", "1234"));

	}

	@Test
	public void testInvalidCustomerLoginPassword() {
		UserDao userDao = new UserDaoSqlImpl();
		assertEquals(0, userDao.loginAuthentication("jack@ma.com", "12"));

	}

	@Test
	public void testNotRegisteredCustomerLoginPassword() {
		UserDao userDao = new UserDaoSqlImpl();
		assertEquals(0, userDao.loginAuthentication("steve@steve.com", "123"));

	}

	@Test
	public void testCustomerLoginBlankPasswordField() {
		UserDao userDao = new UserDaoSqlImpl();
		assertEquals(0, userDao.loginAuthentication("jack@ma.com", ""));

	}

	@Test
	public void testCustomerLoginBlankUsernameField() {
		UserDao userDao = new UserDaoSqlImpl();
		assertEquals(0, userDao.loginAuthentication("", "alibaba"));

	}

}
