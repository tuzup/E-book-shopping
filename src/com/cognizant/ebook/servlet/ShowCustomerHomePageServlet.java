package com.cognizant.ebook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognizant.ebook.dao.BookDao;
import com.cognizant.ebook.dao.BookDaoSqlImpl;
import com.cognizant.ebook.model.Book;

/**
 * Servlet implementation class ShowCustomerHomePageServlet
 */
@WebServlet("/ShowCustomerHomePage")
public class ShowCustomerHomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (null == session.getAttribute("sessionAlive")) {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("loginStatus", true);
			rd.forward(request, response);
		} else {
			BookDao bookDao = new BookDaoSqlImpl();
			List<Book> newArrivals = bookDao.getNewBooks();
			List<Book> mostPurchased = bookDao.mostPurchased();
			List<Book> mostViewedBooks = bookDao.getMostViewedBooks();
			request.setAttribute("mostViewedBooks", mostViewedBooks);
 			request.setAttribute("newArrivals", newArrivals);
 			request.setAttribute("mostPurchased", mostPurchased);
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("/book-list-customer.jsp");
			requestDispatcher.forward(request, response);
		}
	}

}
