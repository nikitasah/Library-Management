package com.utd.libmgmnt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.utd.libmgmnt.model.Borrower;
import com.utd.libmgmnt.model.Fine;
import com.utd.libmgmnt.model.Loan;

/**
 * 
 * Description : FineDAO is used to retreive and update the fine table
 * 				 
 * 
 * @author nikita
 *
 */

public class FineDAO {
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	private static List<Fine> fine = null;
	
	public static List<Fine> displayFine(Connection con) {
		fine = new ArrayList<>();
		try {
        	ps = con.prepareStatement("select fname, lname, l.card_id, sum(fine_amt) from fine f LEFT JOIN loan l on f.loan_id = l.loan_id "
        			+ "LEFT JOIN borrowers b on l.card_id = b.card_id where paid = ? group by l.card_id");
        	ps.setString(1, "0");
        	rs = ps.executeQuery(); 
        	while(rs.next()) {
        		Borrower b = new Borrower();
        		Fine f = new Fine();
        		Loan l = new Loan();
        		b.setFname(rs.getString(1));
        		b.setLname(rs.getString(2));
        		l.setCardId(rs.getString(3));
        		f.setFineAmt(rs.getFloat(4));
        		f.setBorrower(b);
        		f.setLoan(l);
        		fine.add(f);    	
        	}
        }catch (SQLException e) {
        	e.printStackTrace();
        	System.out.println("Database connection problem");
        }
        return fine;
	}

	public static void updateFine(Connection con) {
		try {
			ps = con.prepareStatement("insert into fine (loan_id, fine_amt) select A.loan_id, A.fine*? from (select loan_id," + 
					"if(date_in IS NULL,datediff(curdate(),due_date),if(date_in>due_date," + 
					"datediff(date_in,due_date),?)) as fine from loan having fine>?) as A where A.loan_id " + 
					"NOT IN(select f.loan_id from fine as f);");
			ps.setString(1, "0.25");
			ps.setString(2, "0");
			ps.setString(3, "0");
			ps.execute();	
			ps = con.prepareStatement("update fine LEFT JOIN (select loan_id," + 
					"if(date_in IS NULL,datediff(curdate(),due_date),if(date_in>due_date," + 
					"datediff(date_in,due_date),?)) as fine from loan having fine>?) as A "
					+"on fine.loan_id = A.loan_id set fine.fine_amt = A.fine*?;");
			ps.setString(1, "0");
			ps.setString(2, "0");
			ps.setString(3, "0.25");
			ps.execute();
		}catch (SQLException e) {
        	e.printStackTrace();
        	System.out.println("Database connection problem");
        }
		
	}

}
