package com.cognizant.ebook.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.ebook.dao.UserDao;
import com.cognizant.ebook.dao.UserDaoSqlImpl;
import com.cognizant.ebook.model.User;

@WebServlet("/UserRegistration")
public class UserRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// long id = Long.parseLong(request.getParameter("id"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		int age = Integer.parseInt(request.getParameter("age"));
		boolean gender;
		if (request.getParameter("gender").equalsIgnoreCase("no")) {
			gender = false;
		} else {
			gender = true;
		}
		long contactNumber = Long.parseLong(request
				.getParameter("contactNumber"));
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = new User(firstName, lastName, age, gender, contactNumber,
				email, password);
		UserDao userDao = new UserDaoSqlImpl();
		if (userDao.isExistUser(email)) {
			userDao.addUser(user);
			request.setAttribute("registerSuccess", true);
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		} else {
			request.setAttribute("isExist", true);
			RequestDispatcher rd = request
					.getRequestDispatcher("/user-registration.jsp");
			rd.forward(request, response);
		}

	}
}
