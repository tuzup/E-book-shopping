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

import com.cognizant.ebook.dao.OrderDao;
import com.cognizant.ebook.dao.OrderDaoSqlImpl;
import com.cognizant.ebook.model.OrderInfo;

/**
 * Servlet implementation class ShowOrdersServlet
 */
@WebServlet("/ShowOrders")
public class ShowOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (null != session.getAttribute("sessionAlive")) {
			long userId = (long) session.getAttribute("userId");
			List<OrderInfo> orderList = null;
			OrderDao orderDao = new OrderDaoSqlImpl();

			try {
				orderList = orderDao.getAllOrders(userId);
				request.setAttribute("orderList", orderList);
				RequestDispatcher rd = request
						.getRequestDispatcher("Order.jsp");
				rd.forward(request, response);
			} catch (Exception e) {
				RequestDispatcher rd = request
						.getRequestDispatcher("order-empty.jsp");
				rd.forward(request, response);
			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("loginStatus", true);
			rd.forward(request, response);
		}
	}
}
