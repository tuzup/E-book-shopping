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
import com.cognizant.ebook.dao.CartDao;
import com.cognizant.ebook.dao.CartDaoSqlImpl;
import com.cognizant.ebook.dao.NoSelectionException;
import com.cognizant.ebook.model.Book;

/**
 * Servlet implementation class AddTocart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (null != session.getAttribute("sessionAlive")) {
			try {
				long userId = (long) session.getAttribute("userId");
				String[] bookId = null;
				String[] numberOfBooks = null;
				String[] selection = null;
				int numberOfBooksInt;
				long bookIdLong;
				boolean selectionFlag = false;
				CartDao cartDao = new CartDaoSqlImpl();
				numberOfBooks = request.getParameterValues("numberOfBooks");
				bookId = request.getParameterValues("bookId");
				selection = request.getParameterValues("selection");
				for (int i = 0; i < selection.length; i++) {
					if (Integer.parseInt((selection[i])) == 1) {
						bookIdLong = Long.parseLong(bookId[i]);
						if (numberOfBooks[i] != "" && Integer.parseInt(numberOfBooks[i]) > 0) {
							numberOfBooksInt = Integer
									.parseInt(numberOfBooks[i]);
							cartDao.addToCart(userId, bookIdLong,
									numberOfBooksInt);
							selectionFlag = true;
						}
					}
				}
				if (!selectionFlag) {
					request.setAttribute("bookSelectionStatus", true);
					throw new NoSelectionException();
				}
				BookDao bookDao = new BookDaoSqlImpl();
				List<Book> bookList = bookDao.getBookListCustomer();
				request.setAttribute("bookList", bookList);
				request.setAttribute("addToCartStatus", true);
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("/search-customer.jsp");
				requestDispatcher.forward(request, response);

			} catch (NoSelectionException e) {
				BookDao bookDao = new BookDaoSqlImpl();
				List<Book> bookList = bookDao.getBookListCustomer();
				request.setAttribute("bookList", bookList);
				request.setAttribute("addToCartStatus", false);
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("/search-customer.jsp");
				requestDispatcher.forward(request, response);
			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("loginStatus", true);
			rd.forward(request, response);
		}
	}

}
