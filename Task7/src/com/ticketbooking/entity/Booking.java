package com.ticketbooking.entity;

import java.time.LocalDateTime;

public class Booking {
	
	    private static int idCounter = 1001;
	    private int bookingId;
	    private Customer[] customers;
	    private Event event;
	    private int numTickets;
	    private double totalCost;
	    private LocalDateTime bookingDate;

	    public Booking(Customer[] customers, Event event, int numTickets) {
	        this.bookingId = idCounter++;
	        this.customers = customers;
	        this.event = event;
	        this.numTickets = numTickets;
	        this.totalCost = event.ticketPrice * numTickets;
	        this.bookingDate = LocalDateTime.now();
	    }
	    
	    public Event getEvent() { return event; }
	    public int getBookingId() { return bookingId; }
	    public int getNumTickets() { return numTickets; }

	    public void displayBookingDetails() {
	        System.out.println("Booking ID: " + bookingId + ", Event: " + event.getEventName() + ", Tickets: " + numTickets + ", Total: " + totalCost);
	        System.out.println("Booking Date: " + bookingDate);
	        for (Customer c : customers) {
	            c.displayCustomerDetails();
	        }
	    }

}
