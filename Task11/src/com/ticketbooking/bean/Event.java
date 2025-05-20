package com.ticketbooking.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class Event {
	
	protected int eventId;
	protected String eventName;
    protected LocalDate eventDate;
    protected LocalTime eventTime;
    protected Venue venue;
    protected int totalSeats;
    protected int availableSeats;
    protected double ticketPrice;
    protected String eventType;

    public Event() {}

    public Event(int eventId, String eventName, LocalDate eventDate, LocalTime eventTime, Venue venue,
                 int totalSeats, int availableSeats, double ticketPrice, String eventType) {
        this.eventId = eventId;
    	this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.venue = venue;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.ticketPrice = ticketPrice;
        this.eventType = eventType;
    }

    public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalTime eventTime) {
        this.eventTime = eventTime;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public double calculateTotalRevenue() {
        return (totalSeats - availableSeats) * ticketPrice;
    }

    public int getBookedNoOfTickets() {
        return totalSeats - availableSeats;
    }

    public void bookTickets(int numTickets) {
        if (numTickets <= availableSeats) {
            availableSeats -= numTickets;
            System.out.println(numTickets + " ticket(s) booked successfully.");
        } else {
            System.out.println("Insufficient seats available.");
        }
    }

    public void cancelBooking(int numTickets) {
        if (availableSeats + numTickets <= totalSeats) {
            availableSeats += numTickets;
            System.out.println(numTickets + " ticket(s) cancelled successfully.");
        } else {
            System.out.println("Cancellation failed. Invalid number of tickets.");
        }
    }

    public void displayEventDetails() {
    	System.out.println("Event Id:" + eventId);
        System.out.println("Event Name: " + eventName);
        System.out.println("Date: " + eventDate);
        System.out.println("Time: " + eventTime);
        System.out.println("Venue: " + venue.getVenueName());
        System.out.println("Total Seats: " + totalSeats);
        System.out.println("Available Seats: " + availableSeats);
        System.out.println("Ticket Price: ₹" + ticketPrice);
        System.out.println("Event Type: " + eventType);
        System.out.println("--------------------");
    }

}
