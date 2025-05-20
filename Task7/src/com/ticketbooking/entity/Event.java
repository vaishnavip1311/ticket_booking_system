package com.ticketbooking.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Event {

	    protected String eventName;
	    protected LocalDate eventDate;
	    protected LocalTime eventTime;
	    protected Venue venue;
	    protected int totalSeats;
	    protected int availableSeats;
	    protected double ticketPrice;
	    protected String eventType;

	    public Event() {}

	    public Event(String eventName, LocalDate date, LocalTime time, Venue venue, int totalSeats, double ticketPrice, String eventType) {
	        this.eventName = eventName;
	        this.eventDate = date;
	        this.eventTime = time;
	        this.venue = venue;
	        this.totalSeats = totalSeats;
	        this.availableSeats = totalSeats;
	        this.ticketPrice = ticketPrice;
	        this.eventType = eventType;
	    }

	    public int getAvailableSeats() { return availableSeats; }
	    public String getEventName() { return eventName; }

	    public void bookTickets(int numTickets) {
	        if (availableSeats >= numTickets) {
	            availableSeats -= numTickets;
	        } else {
	            System.out.println("Not enough tickets available.");
	        }
	    }

	    public void cancelBooking(int numTickets) {
	        availableSeats += numTickets;
	    }

	    public int getBookedTickets() {
	        return totalSeats - availableSeats;
	    }

	    public double calculateTotalRevenue() {
	        return getBookedTickets() * ticketPrice;
	    }

	    public abstract void displayEventDetails();

}
