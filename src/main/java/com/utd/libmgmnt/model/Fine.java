package com.utd.libmgmnt.model;

/*
 * Description : Author is used to get and set the parameters of the fine table in the application.
 * @author : nikita
 */

public class Fine {
	private int loanId;
	private float fineAmt;
	private boolean paid;
	private Loan loan;
	private Borrower borrower;
	
	public Borrower getBorrower() {
		return borrower;
	}
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
	
	public Loan getLoan() {
		return loan;
	}
	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	/* Getters and Setters for fine table */ 
	public int getLoanId() {
		return loanId;
	}
	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	public float getFineAmt() {
		return fineAmt;
	}
	public void setFineAmt(float fineAmt) {
		this.fineAmt = fineAmt;
	}
	public boolean isPaid() {
		return paid;
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	
	@Override
	public String toString() {	
		return "Fine{" + "loanId=" + loanId + ",paid =" + paid + ", fineAmt=" + fineAmt +'}';
	}
}
