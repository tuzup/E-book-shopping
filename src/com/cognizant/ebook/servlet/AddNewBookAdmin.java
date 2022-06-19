package com.cognizant.ebook.servlet;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognizant.ebook.dao.BookDao;
import com.cognizant.ebook.dao.BookDaoSqlImpl;

/**
 * Servlet implementation class AddNewBookAdmin
 */
@WebServlet("/AddNewBookAdmin")
public class AddNewBookAdmin extends HttpServlet {
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
				String title = request.getParameter("title");
				float price = Float.parseFloat(request.getParameter("price"));
				java.util.Date dateOfLaunchUtil;
				dateOfLaunchUtil = new SimpleDateFormat("yyyy-MM-dd")
						.parse(request.getParameter("dateOfLaunch"));
				Date dateOfLaunch = new Date(dateOfLaunchUtil.getTime());
				String author = request.getParameter("author");
				String description = request.getParameter("description");
				String category = request.getParameter("category");
				int offer = Integer.parseInt(request.getParameter("offers"));
				int stock = Integer.parseInt(request.getParameter("stock"));
				String language = request.getParameter("language");
				String publisher = request.getParameter("publisher");
				String binding = request.getParameter("binding");

				BookDao book = new BookDaoSqlImpl();

				book.AddNewBookAdmin(title, price, category, description,
						offer, stock, binding, language, author, publisher,
						dateOfLaunch);
				RequestDispatcher requestDispatcher = request
						.getRequestDispatcher("/add-book-admin.jsp");
				requestDispatcher.forward(request, response);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("loginStatus", true);
			rd.forward(request, response);

		}

	}
}
