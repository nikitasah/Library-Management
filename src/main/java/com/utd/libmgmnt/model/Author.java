package com.utd.libmgmnt.model;

/*
 * Description : Author is used to get and set the parameters of the author table in the application.
 * @author : nikita
 */

public class Author {
	private String isbn13;
	private String authorName;
	
	/* Getters and Setters for author table */
	
	public String getIsbn13() {
		return isbn13;
	}
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	@Override
	public String toString() {	
		return "Author{" + "bookId=" + isbn13 + ", authorName=" + authorName +'}';
	}
	

}
