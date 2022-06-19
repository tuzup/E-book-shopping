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
 * Servlet implementation class RemoveCartServlet
 */
@WebServlet("/RemoveCart")
public class RemoveCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (null != session.getAttribute("sessionAlive")) {
			try {
				long bookId = Long.parseLong(request.getParameter("bookId"));
				long userId = (long) session.getAttribute("userId");
				CartDao cartDao = new CartDaoSqlImpl();
				cartDao.removeFromCart(userId, bookId);
				try {
					Cart cartItems = cartDao.getCart(userId);
					request.setAttribute("cart", cartItems);
					request.setAttribute("userId", userId);
					Integer price = new Integer((int) cartItems.getTotalPrice());
					session.setAttribute("cartTotal", price);
					RequestDispatcher dispacher = request
							.getRequestDispatcher("/cart.jsp");
					dispacher.forward(request, response);
				} catch (CartEmptyException e) {
					// TODO Auto-generated catch block

					RequestDispatcher dispacher = request
							.getRequestDispatcher("/cart-empty.jsp");
					dispacher.forward(request, response);

				}
			} catch (Exception e) {
				RequestDispatcher rd = request
						.getRequestDispatcher("/page-not-found.jsp");
				rd.forward(request, response);
			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("loginStatus", true);
			rd.forward(request, response);
		}

	}

}
