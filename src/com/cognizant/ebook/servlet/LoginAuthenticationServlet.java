package com.cognizant.ebook.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognizant.ebook.dao.UserDao;
import com.cognizant.ebook.dao.UserDaoSqlImpl;

/**
 * Servlet implementation class LoginAuthenticationServlet
 */
@WebServlet("/LoginAuthentication")
public class LoginAuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserDao userDao = new UserDaoSqlImpl();
		long userId = userDao.loginAuthentication(email, password);
		if(userId == -1)
		{
			HttpSession session = request.getSession(true);
			session.setAttribute("userId", userId);
			session.setAttribute("sessionAlive",true);
			response.sendRedirect("BookAdmin");
		}else if (userId != 0) {
			HttpSession session = request.getSession(true);
			session.setAttribute("userId", userId);
			session.setAttribute("sessionAlive",true);
			response.sendRedirect("ShowCustomerHomePage");
		} else {
			RequestDispatcher dispacher = request
					.getRequestDispatcher("/index.jsp");
			request.setAttribute("loginFail", true);
			dispacher.forward(request, response);
		}
	}
}
