package com.utd.libmgmnt.service;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utd.libmgmnt.dao.LoanDAO;
import com.utd.libmgmnt.model.Loan;

/**
 * Servlet implementation class BookCheckInService
 * 
 * @author : nikita
 */
@WebServlet(name = "BookCheckInService", urlPatterns = {"/BookCheckInService"})
public class BookCheckInService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookCheckInService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = (Connection)getServletContext().getAttribute("DbConnection");
		String search = request.getParameter("search");
		String cardId = request.getParameter("card-id");
		String isbn =  request.getParameter("selected-isbn13");
		List<Loan> loan = null;
		if(search != null) {
			loan = LoanDAO.getLoan(con, search);
			if (loan.isEmpty()) {
				request.setAttribute("errorcheckin", "No Match can be found!");
			} else {
				request.setAttribute("loan", loan);
			}
		} else {
			LoanDAO.updateDateIn(con, cardId, isbn);
			request.setAttribute("success", "Book successfully checked in with card-id "+cardId);
		}		
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/checkInBook.jsp");
	   	rd.forward(request, response);
	}
}
