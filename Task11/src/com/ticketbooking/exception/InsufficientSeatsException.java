package com.ticketbooking.exception;

public class InsufficientSeatsException extends Exception{

	public InsufficientSeatsException() {
		super();
	}
	
	public InsufficientSeatsException(String message) {
        super(message);
    }

	@Override
	public String toString() {
		return "Insufficient Seats";
	}
	
	

}
