package com.ticketbooking.exception;

public class InvalidBookingIDException extends Exception{

	public InvalidBookingIDException() {
		super();
	}
	
	public InvalidBookingIDException(String message) {
        super(message);
    }

	@Override
	public String toString() {
		return "Invalid Booking ID";
	}
	
	

}
