package com.capgemini.entities;

import java.math.BigInteger;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Booking {
	@Id
	private BigInteger bookingId;
	private String bookingDate;
	private int noOfPassengers;

	public Booking(BigInteger bookingId, String bookingDate, int noOfPassengers) {
		// TODO Auto-generated constructor stub
		this.bookingId=bookingId;
		this.bookingDate=bookingDate;
		this.noOfPassengers=noOfPassengers;
	}
	
	public Booking() 
	{
		
	}

	public BigInteger getBookingId() {
		return bookingId;
	}

	public void setBookingId(BigInteger bookingId) {
		this.bookingId = bookingId;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}

	public int getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}
}
