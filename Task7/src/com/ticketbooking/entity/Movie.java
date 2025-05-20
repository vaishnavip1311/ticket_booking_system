package com.ticketbooking.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Movie extends Event{

	    private String genre;
	    private String actorName;
	    private String actressName;

	    public Movie() {}

	    public Movie(String eventName, LocalDate date, LocalTime time, Venue venue, int totalSeats, double ticketPrice, String genre, String actor, String actress) {
	        super(eventName, date, time, venue, totalSeats, ticketPrice, "Movie");
	        this.genre = genre;
	        this.actorName = actor;
	        this.actressName = actress;
	    }

	    @Override
	    public void displayEventDetails() {
	        System.out.println("Movie Event: " + eventName + " | Genre: " + genre);
	        System.out.println("Lead Actor: " + actorName + ", Actress: " + actressName);
	        System.out.println("Date: " + eventDate + " Time: " + eventTime);
	        venue.displayVenueDetails();
	        System.out.println("Tickets Available: " + availableSeats + "/" + totalSeats + " | Price: " + ticketPrice);
	    }
	}


