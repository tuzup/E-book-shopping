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
import com.cognizant.ebook.model.Book;


@WebServlet("/BookAdmin")
public class BookAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(null != session.getAttribute("sessionAlive")){
		BookDao bookDao = new BookDaoSqlImpl();
		List<Book> bookListAdmin = new ArrayList<Book>();
		bookListAdmin = bookDao.getBookListAdmin();
		request.setAttribute("bookListAdmin",bookListAdmin);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/view-admin-page.jsp");
		requestDispatcher.forward(request, response);
		}
		else{
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("loginStatus", true);
			rd.forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
