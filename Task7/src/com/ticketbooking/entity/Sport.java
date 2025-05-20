package com.ticketbooking.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Sport extends Event{
	
	    private String sportName;
	    private String teams;

	    public Sport() {}

	    public Sport(String eventName, LocalDate date, LocalTime time, Venue venue, int totalSeats, double ticketPrice, String sportName, String teams) {
	        super(eventName, date, time, venue, totalSeats, ticketPrice, "Sport");
	        this.sportName = sportName;
	        this.teams = teams;
	    }

	    @Override
	    public void displayEventDetails() {
	        System.out.println("Sport Event: " + eventName + " | Game: " + sportName + " | Teams: " + teams);
	        System.out.println("Date: " + eventDate + " Time: " + eventTime);
	        venue.displayVenueDetails();
	        System.out.println("Tickets Available: " + availableSeats + "/" + totalSeats + " | Price: " + ticketPrice);
	    }

}
