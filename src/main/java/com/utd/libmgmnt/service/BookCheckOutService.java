package com.utd.libmgmnt.service;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utd.libmgmnt.dao.LoanDAO;

/**
 * Servlet implementation class BookCheckOutService
 *  * @author : nikita
 */
@WebServlet(name = "BookCheckOutService", urlPatterns = {"/BookCheckOutService"})
public class BookCheckOutService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookCheckOutService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = (Connection)getServletContext().getAttribute("DbConnection");
		String cardId = request.getParameter("card-id");
		String isbn =  request.getParameter("selected-isbn13");
		boolean result = LoanDAO.checkNoOfBooksIssued(con, cardId);
		if(!result) {
			request.setAttribute("errorloan", "Maximum Book Loans reached for borrower with card-id : "+cardId);
		} else {
			result = LoanDAO.insertLoanRecord(con, cardId, isbn); // insert the record in the loan table
			if(result) {
				request.setAttribute("success", "Record successfully inserted.");
			}
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
	   	rd.forward(request, response);
	}

}

