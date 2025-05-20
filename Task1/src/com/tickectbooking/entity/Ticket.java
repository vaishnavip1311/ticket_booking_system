package com.tickectbooking.entity;

public class Ticket {
	 private int availableTickets;

	public Ticket(int availableTickets) {
		super();
		this.availableTickets = availableTickets;
	}

	public int getAvailableTickets() {
		return availableTickets;
	}
	
	public String bookTickets(int noOfBookingTickets) {
        if (noOfBookingTickets <= availableTickets) {
            availableTickets -= noOfBookingTickets;
            return noOfBookingTickets + " tickets booked successfully. Remaining tickets: " + availableTickets;
        } else {
            return "Tickets unavailable. Only " + availableTickets + " tickets left.";
        }
    }
	 

}
