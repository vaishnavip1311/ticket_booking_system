package com.ticketbooking.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Concert extends Event{
	
	    private String artist;
	    private String type;

	    public Concert() {}

	    public Concert(String eventName, LocalDate date, LocalTime time, Venue venue, int totalSeats, double ticketPrice, String artist, String type) {
	        super(eventName, date, time, venue, totalSeats, ticketPrice, "Concert");
	        this.artist = artist;
	        this.type = type;
	    }

	    @Override
	    public void displayEventDetails() {
	        System.out.println("Concert Event: " + eventName + " | Artist: " + artist + " | Type: " + type);
	        System.out.println("Date: " + eventDate + " Time: " + eventTime);
	        venue.displayVenueDetails();
	        System.out.println("Tickets Available: " + availableSeats + "/" + totalSeats + " | Price: " + ticketPrice);
	    }
	}

