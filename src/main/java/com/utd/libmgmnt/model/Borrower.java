package com.utd.libmgmnt.model;

/*
 * Description : Author is used to get and set the parameters of the borrower table in the application.
 * @author : nikita
 */

public class Borrower {
	private String cardId;
	private String ssn;
	private String fname;
	private String lname;
	private String email;
	private String address;
	private String city;
	private String state;
	private String phone;
	
	
	/* Getters and Setters for Borrowers table */
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {	
		return "Borrower{" + "cardId=" + cardId + ", ssn=" + ssn + ", fname=" + fname + ", "
				+ "lname=" + lname + ", email=" + email + ", address=" + address + 
				", city=" + city + ", state=" + state + ", phone=" + phone + '}';
		
	}

}
