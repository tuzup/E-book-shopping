package com.cognizant.ebook.servlet;

import java.io.IOException;
import java.sql.Date;

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

@WebServlet("/EditBookDetails")
public class EditBookDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (null != session.getAttribute("sessionAlive")) {
			Long bookId = Long.parseLong(request.getParameter("bookId"));
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			String category = request.getParameter("category");
			String language = request.getParameter("Language");
			String publisher = request.getParameter("publisher");
			String binding = request.getParameter("binding");
			String description = request.getParameter("description");
			Date dateOfLaunch = Date.valueOf(request
					.getParameter("dateOfLaunch"));
			int offer = Integer.parseInt(request.getParameter("offers"));
			int stock = Integer.parseInt(request.getParameter("stock"));
			float price = Float.parseFloat(request.getParameter("price"));

			Book book = new Book(bookId, title, author, category, price,
					dateOfLaunch, language, publisher, description, binding,
					offer, stock);
			BookDao bookDao = new BookDaoSqlImpl();
			bookDao.modifyBook(book);
			RequestDispatcher requestdispatcher = request
					.getRequestDispatcher("/book-edit-status.jsp");
			requestdispatcher.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("loginStatus", true);
			rd.forward(request, response);

		}
	}

}
