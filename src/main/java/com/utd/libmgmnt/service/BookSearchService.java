package com.utd.libmgmnt.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utd.libmgmnt.dao.BookDAO;
import com.utd.libmgmnt.model.Book;
import java.sql.Connection;
import java.util.List;


/**
 * Servlet implementation class BookSearchService
 * 
 *  @author : nikita
 */

@WebServlet(name = "BookSearchService", urlPatterns = {"/BookSearchService"})


public class BookSearchService extends HttpServlet {
	
	private static final long serialVersionUID = 1L;	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookSearchService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = (Connection)getServletContext().getAttribute("DbConnection");
		List<Book> books = null;
    	String search = request.getParameter("search");
		books = BookDAO.getBook(con, search);
		if (books.isEmpty()) {
			request.setAttribute("errorsearch", "No Match can be found!");
		} else {
			request.setAttribute("books", books);
		}
	   	RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
	   	rd.forward(request, response);
	}
}
