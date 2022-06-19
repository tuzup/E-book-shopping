package com.cognizant.ebook.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognizant.ebook.dao.BookDao;
import com.cognizant.ebook.dao.BookDaoSqlImpl;
import com.cognizant.ebook.model.Book;

/**
 * Servlet implementation class ShowOfferBooks
 */
@WebServlet("/ShowOfferBooks")
public class ShowOfferBooks extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			BookDao bookDao = new BookDaoSqlImpl();
			List<Book> bookList = bookDao.getOffer();
			request.setAttribute("bookList", bookList);
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("/display-offer-books.jsp");
			requestDispatcher.forward(request, response);
		} catch (Exception e) {

		}
	}

}
