package com.utd.libmgmnt.model;

/*
 * Description : Author is used to get and set the parameters of the book table in the application.
 * @author : nikita
 */

public class Book {
	
	private Author author;
	private String isbn10;
	private String isbn13;
	private String title;
	private String cover;
	private String publisher;
	private int pages;
	private int noOfCopies;
	
	
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	/* Getters and Setters for book table */
	public String getIsbn10() {
		return isbn10;
	}
	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}
	public String getIsbn13() {
		return isbn13;
	}
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public int getNoOfCopies() {
		return noOfCopies;
	}
	public void setNoOfCopies(int noOfCopies) {
		this.noOfCopies = noOfCopies;
	}
	@Override
	public String toString() {	
		return "Book{" + "bookId=" + isbn13 + ", title=" + title + ", cover=" + cover + ", "
				+ "publisher=" + publisher + ", noOfCopies=" + noOfCopies + ", pages=" + pages + '}';
		
	}

}