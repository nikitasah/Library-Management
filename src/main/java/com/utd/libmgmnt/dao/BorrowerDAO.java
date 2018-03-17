package com.utd.libmgmnt.dao;

import java.sql.Connection;

/**
 * 
 * Description : BorrowerDAO is used to set the borrower in the borrower table
 * 				 
 * 
 * @author nikita
 *
 */
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BorrowerDAO {
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;

	public static boolean setBorrower(String ssn, String fname, String lname, String email, String address, String city,
			String state, String phone, Connection con) {
			String id = "";
			String cardId = "";
			try {
				ps = con.prepareStatement("select ssn from borrowers where ssn = ?");
				ps.setString(1, ssn);
				rs = ps.executeQuery();
				if(rs.next()) {
					return false;
				} else {
					ps = con.prepareStatement("select card_id from borrowers order by card_id desc limit 1");
					rs = ps.executeQuery();
					if(rs.next()) {
						id = rs.getString(1);
					}
					int number = Integer.parseInt(id.substring(2));
					number = number + 1;
					cardId = "ID00" + String.valueOf(number);
					ps = con.prepareStatement("insert into borrowers(card_id, ssn, fname, lname, email,"
							+ " address, city, state, phone) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
					ps.setString(1, cardId);
					ps.setString(2, ssn);
					ps.setString(3, fname);
					ps.setString(4, lname);
					ps.setString(5, email);
					ps.setString(6,  address);
					ps.setString(7,  city);
					ps.setString(8, state);
					ps.setString(9, phone);
					ps.execute();           	
				}
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Database connection problem");
			}
			return true;
	}
}
