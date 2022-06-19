package com.cognizant.ebook.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import com.cognizant.ebook.dao.OfferEmptyException;
import com.cognizant.ebook.model.Book;

/**
 * Servlet implementation class ShowOfferServlet
 */
@WebServlet("/ShowOffer")
public class ShowOfferServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (null != session.getAttribute("sessionAlive")) {
			BookDao bookDao = new BookDaoSqlImpl();
			List<Book> offerList = new ArrayList<Book>();
			try {
				offerList = bookDao.getOffer();
				request.setAttribute("offer", offerList);
				RequestDispatcher dispacher = request
						.getRequestDispatcher("/offers.jsp");
				dispacher.forward(request, response);
			} catch (OfferEmptyException e) {
				RequestDispatcher dispacher = request
						.getRequestDispatcher("/offer-empty.jsp");
				dispacher.forward(request, response);
			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("loginStatus", true);
			rd.forward(request, response);
		}
	}

}
