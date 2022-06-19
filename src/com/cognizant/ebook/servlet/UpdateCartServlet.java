package com.cognizant.ebook.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognizant.ebook.dao.CartDao;
import com.cognizant.ebook.dao.CartDaoSqlImpl;
import com.cognizant.ebook.dao.CartEmptyException;
import com.cognizant.ebook.model.Cart;

/**
 * Servlet implementation class UpdateCartServlet
 */
@WebServlet("/UpdateCart")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (null != session.getAttribute("sessionAlive")) {
			String[] bookId;
			String[] numberOfBooks;
			numberOfBooks = request.getParameterValues("numberOfBooks");
			bookId = request.getParameterValues("bookId");
			long userId = (long) session.getAttribute("userId");
			CartDao cartDao = new CartDaoSqlImpl();
			cartDao.updateCart(userId, bookId, numberOfBooks);
			try {
				Cart cartItems = cartDao.getCart(userId);
				request.setAttribute("cart", cartItems);
				request.setAttribute("cartUpdate", true);
				RequestDispatcher dispacher = request
						.getRequestDispatcher("/cart.jsp");
				dispacher.forward(request, response);
			} catch (CartEmptyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				RequestDispatcher dispacher = request
						.getRequestDispatcher("/cart-empty.jsp");
				dispacher.forward(request, response);
				e.printStackTrace();
			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("loginStatus", true);
			rd.forward(request, response);
		}
	}

}
