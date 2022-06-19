package com.cognizant.ebook.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PaymentProcessingServlet
 */
@WebServlet("/PaymentProcessing")
public class PaymentProcessingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (null != session.getAttribute("sessionAlive")) {
			RequestDispatcher rd = request
					.getRequestDispatcher("payment-process.html");
			rd.forward(request, response);
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

}
