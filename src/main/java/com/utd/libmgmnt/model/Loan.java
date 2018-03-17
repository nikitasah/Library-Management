package com.utd.libmgmnt.model;

/*
 * Description : Author is used to get and set the parameters of the loan table in the application.
 * @author : nikita
 */

public class Loan {
	private int loanId;
	private String isbn13;
	private String cardId;
	private String dueDate;
	private String dateOut;
	private String dueIn;
	private Borrower borrower;
	
	public Borrower getBorrower() {
		return borrower;
	}
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
	
	
	/*Getters and Setters for Loan table*/
	
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	public String getIsbn13() {
		return isbn13;
	}
	public void setIsbn13(String isbn13) {
		this.isbn13 = isbn13;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getDateOut() {
		return dateOut;
	}
	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}
	public String getDueIn() {
		return dueIn;
	}
	public void setDueIn(String dueIn) {
		this.dueIn = dueIn;
	}
	
	@Override
	public String toString() {	
		return "Loan{" + ", loanId=" + loanId + "bookId=" + isbn13 +  ", cardId=" + cardId + ", "
				+ "dueDate=" + dueDate + ", dateOut=" + dateOut + ", dueIn=" + dueIn + '}';
		
	}

}
