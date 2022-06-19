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
import com.cognizant.ebook.dao.OrderEmptyException;
import com.cognizant.ebook.model.OrderInfo;

/**
 * Servlet implementation class OrderCancellationServlet
 */
@WebServlet("/OrderCancellation")
public class OrderCancellationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderCancellationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (null != session.getAttribute("sessionAlive")) {
			try {
				long userId = (long) session.getAttribute("userId");
				long orderId = Long.parseLong(request.getParameter("ordId"));
				OrderDao orderDao = new OrderDaoSqlImpl();
				List<OrderInfo> orderList;
				try {
					orderDao.orderCancellation(userId, orderId);
					orderList = orderDao.getAllOrders(userId);
					request.setAttribute("orderList", orderList);
					RequestDispatcher rd = request
							.getRequestDispatcher("Order.jsp");
					rd.forward(request, response);

				} catch (OrderEmptyException e) {
					RequestDispatcher rd = request
							.getRequestDispatcher("order-empty.jsp");
					rd.forward(request, response);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
