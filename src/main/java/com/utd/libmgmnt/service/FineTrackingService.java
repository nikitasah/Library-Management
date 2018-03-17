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

import com.utd.libmgmnt.dao.FineDAO;
import com.utd.libmgmnt.model.Fine;

/**
 * Servlet implementation class FineTrackingService
 * 
 *  * @author : nikita
 */
@WebServlet(name = "FineTrackingService", urlPatterns = {"/FineTrackingService"})
public class FineTrackingService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FineTrackingService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = (Connection)getServletContext().getAttribute("DbConnection");
		//To display fine table
		if(request.getParameter("display") != null) {
			List<Fine> fine = null;
			fine = FineDAO.displayFine(con);
			if (fine.isEmpty()) {
				request.setAttribute("errordisplayfine", "No record exists in fine table which is unpaid!");
			} else {
				request.setAttribute("fine", fine);
			}
		}
		// To update fine table
		else if(request.getParameter("update") != null) {
			FineDAO.updateFine(con);
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/fineTracking.jsp");
	   	rd.forward(request, response);
	}

}
