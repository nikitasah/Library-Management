package com.utd.libmgmnt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.utd.libmgmnt.model.Borrower;
import com.utd.libmgmnt.model.Loan;

/**
 * 
 * Description : BorrowerDAO is used to retreive, insert and update the loan table
 * 				 
 * 
 * @author nikita
 *
 */

public class LoanDAO {
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	private static List<Loan> loan = null;
	
	public static boolean checkNoOfBooksIssued(Connection con, String cardId) {
		// TODO Auto-generated method stub
		try {
			ps = con.prepareStatement("select count(*) from loan where card_id = ? and date_in is null");
	        ps.setString(1, cardId);
	        rs = ps.executeQuery(); 
	        if(rs.next()) {
	        	if(rs.getInt(1) >= 3) {
	        		return false;
	        	}
	        }
		} catch (SQLException e) {
			e.printStackTrace();
            System.out.println("Database connection problem");
	    }
		return true;
	}
	
	public static boolean insertLoanRecord(Connection con, String cardId, String isbn) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Calendar c1 = Calendar.getInstance();
			c1.add(Calendar.DATE, 0); //Today's date
			c.add(Calendar.DATE, 14); // Adding 14 days
			String dueDate = sdf.format(c.getTime());
			String dateOut = sdf.format(c1.getTime());
			ps = con.prepareStatement("insert into loan(isbn13,card_id,date_out,due_date) values(?,?,?,?)");
			ps.setString(1, isbn.substring(1));
			ps.setString(2, cardId.toUpperCase());
			ps.setString(3, dateOut);
			ps.setString(4, dueDate);
			ps.execute();	
			ps = con.prepareStatement("update book set no_of_copies_available=no_of_copies_available-? where isbn13=? and no_of_copies_available=?"); 
			ps.setString(1, "1");
			ps.setString(2, isbn.substring(1));
			ps.setString(3, "1");
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database connection problem");
		}
		return true;
	}

	public static List<Loan> getLoan(Connection con, String s) {
		loan = new ArrayList<>();		
        try {
        	ps = con.prepareStatement("select l.card_id, b.fname, b.lname, l.isbn13, l.date_out, l.due_date from loan l LEFT JOIN borrowers b on b.card_id = l.card_id "
        			+ "where  date_in is null and (l.isbn13 = ? or l.card_id = ? or fname like ? or lname like ?)");
        	ps.setString(1, s);
        	ps.setString(2, s);
        	ps.setString(3, "%"+s+"%");
        	ps.setString(4, "%"+s+"%");
        	rs = ps.executeQuery(); 
        	while(rs.next()) {
        		Loan l = new Loan();
        		Borrower b = new Borrower();
        		l.setCardId(rs.getString(1));
        		b.setFname(rs.getString(2));
        		b.setLname(rs.getString(3));
        		b.setCardId(rs.getString(1));
        		l.setIsbn13(rs.getString(4));
        		l.setDueDate(rs.getString(6));
        		l.setDateOut(rs.getString(5));        		
        		l.setBorrower(b);
        		loan.add(l);    	
        	}
        }catch (SQLException e) {
        	e.printStackTrace();
        	System.out.println("Database connection problem");
        }
        return loan;
	}

	public static void updateDateIn(Connection con, String cardId, String isbn) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, 0); //Today's date
			String dateIn = sdf.format(c.getTime());
			ps = con.prepareStatement("update loan set date_in=? where isbn13=? and card_id=?");
			ps.setString(1, dateIn);
			ps.setString(2, isbn.substring(1));
			ps.setString(3, cardId);
			ps.execute(); 
			ps = con.prepareStatement("update book set no_of_copies_available=no_of_copies_available+? where isbn13=? and no_of_copies_available=?");
			ps.setString(1, "1");
			ps.setString(2, isbn.substring(1));
			ps.setString(3, "0");
			ps.execute();
			ps = con.prepareStatement("update fine f left join loan l on f.loan_id=l.loan_id set f.paid=?" + 
					"where isbn13=? and card_id=?;");
			ps.setString(1, "1");
			ps.setString(2, isbn.substring(1));
			ps.setString(3, cardId);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Database connection problem");
		}
	}
}
