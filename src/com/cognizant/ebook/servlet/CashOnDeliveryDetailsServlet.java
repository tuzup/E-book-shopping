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
import com.cognizant.ebook.model.Order;
import com.cognizant.ebook.model.OrderInfo;

/**
 * Servlet implementation class CashOnDeliveryDetailsServlet
 */
@WebServlet("/CashOnDeliveryDetails")
public class CashOnDeliveryDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false);
		if (null != session.getAttribute("sessionAlive")) {
			long userId = (long) session.getAttribute("userId");
			int ordId = 1;
			List<OrderInfo> orderList = null;
			String ordFullName = request.getParameter("firstname");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			int zip = Integer.parseInt(request.getParameter("zip"));
			int total = (int) session.getAttribute("cartTotal");
			Order ord = new Order(ordId, ordFullName, email, address, city,
					state, zip, null, 0, null, 0, 0, "COD", total);
			OrderDao orderDao = new OrderDaoSqlImpl();
			orderDao.addToOrder(userId, ord);
			orderDao.addToOrder(userId, ord);
			ordId = orderDao.getOrderId(userId);
			orderDao.storeOrder(ordId, userId);

			try {
				orderList = orderDao.getCurrentOrder(ordId);
			} catch (OrderEmptyException e) {
				e.printStackTrace();
			}
			request.setAttribute("orderInfo", orderList);
			RequestDispatcher rd = request
					.getRequestDispatcher("payment-successful.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("loginStatus", true);
			rd.forward(request, response);
		}
	}

}
