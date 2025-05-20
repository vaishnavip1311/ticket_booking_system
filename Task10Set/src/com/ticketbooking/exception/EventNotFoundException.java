package com.ticketbooking.exception;

public class EventNotFoundException extends Exception{

	public EventNotFoundException() {
		super();
	}
	
	public EventNotFoundException(String message) {
        super(message);
    }

	@Override
	public String toString() {
		return "Event Not Found";
	}
	
	

}
