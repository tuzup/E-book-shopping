package com.cognizant.ebook.servlet;

import java.io.IOException;

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

@WebServlet("/ShowEditBookDetails")
public class ShowEditBookDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (null != session.getAttribute("sessionAlive")) {
			try{
				long bookId = Long.parseLong(request.getParameter("bookId"));
				BookDao bookDao = new BookDaoSqlImpl();
				Book book = bookDao.getBook(bookId);
				request.setAttribute("book", book);
				RequestDispatcher rd = request
						.getRequestDispatcher("/edit-book-details.jsp");
				rd.forward(request, response);
			}catch(Exception e)
			{
				e.printStackTrace();
				RequestDispatcher rd = request.getRequestDispatcher("BookAdmin");
				request.setAttribute("loginStatus", true);
				rd.forward(request, response);
			}

		} else {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("loginStatus", true);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
