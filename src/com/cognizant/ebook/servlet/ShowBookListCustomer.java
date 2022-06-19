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
 * Servlet implementation class SearchBook
 */
@WebServlet("/ShowBookListCustomer")
public class ShowBookListCustomer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (null != session.getAttribute("sessionAlive")) {
			try {
				BookDao bookDao = new BookDaoSqlImpl();
				List<Book> bookList = bookDao.getBookListCustomer();
				request.setAttribute("bookList", bookList);
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("/search-customer.jsp");
				requestDispatcher.forward(request, response);
			} catch (Exception e) {

			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("loginStatus", true);
			rd.forward(request, response);
		}
	}
}
