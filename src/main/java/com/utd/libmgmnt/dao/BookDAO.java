package com.utd.libmgmnt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.utd.libmgmnt.model.Author;
import com.utd.libmgmnt.model.Book;

/**
 * 
 * Description : BookDAO is used to retrieve the data from the book table
 * 				 
 * 
 * @author nikita
 *
 */

public class BookDAO {
	
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	private static List<Book> books = null;
	
	public static List<Book>getBook(Connection con, String s) {
		books = new ArrayList<>();		
        try {
        	ps = con.prepareStatement("select distinct b.isbn13, a.author_name,b.isbn10,b.title,b.no_of_copies_available from author a LEFT JOIN book b on a.isbn13 = b.isbn13 "
        			+ "where isbn10 like ? or b.isbn13 like ? or title like ? or author_name like ?");
        	ps.setString(1, "%"+s+"%");
        	ps.setString(2, "%"+s+"%");
        	ps.setString(3, "%"+s+"%");
        	ps.setString(4, "%"+s+"%");
        	rs = ps.executeQuery(); 
        	while(rs.next()) {
        		Book b = new Book();
        		Author a = new Author();
        		a.setAuthorName(rs.getString(2));
        		b.setIsbn10(rs.getString(3));
        		a.setIsbn13(rs.getString(1));
        		b.setIsbn13(rs.getString(1));
        		b.setTitle(rs.getString(4));
        		b.setNoOfCopies(rs.getInt(5));
        		b.setAuthor(a);
        		books.add(b);    	
        	}
        }catch (SQLException e) {
        	e.printStackTrace();
        	System.out.println("Database connection problem");
        }
        return books;
	} 
}
