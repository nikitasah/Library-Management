package com.utd.libmgmnt.service;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.utd.libmgmnt.dao.BorrowerDAO;

/**
 * Servlet implementation class BorrowerManageService
 *  @author : nikita
 */
@WebServlet(name = "BookManageService", urlPatterns = "/BorrowerManageService")
public class BorrowerManageService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BorrowerManageService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con = (Connection)getServletContext().getAttribute("DbConnection");
		String ssn = request.getParameter("ssn");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String phone = request.getParameter("phone");
		boolean result = BorrowerDAO.setBorrower(ssn, fname, lname, email, address, city, state, phone, con);
		if(!result) {
			request.setAttribute("error", "Ssn already exists!");
		} else {
			request.setAttribute("success", "Borrower successfully added!");
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/addBorrower.jsp");
	   	rd.forward(request, response);		
	}

}
