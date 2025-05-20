package com.ticketbooking.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class Movie extends Event{
	
	    private String genre;
	    private String actorName;
	    private String actressName;

	    public Movie() {}

	    public Movie(String eventName, LocalDate eventDate, LocalTime eventTime, Venue venue, int totalSeats, double ticketPrice, String eventType, String genre, String actorName, String actressName) {
	        super(eventName, eventDate, eventTime, venue, totalSeats, ticketPrice, eventType);
	        this.genre = genre;
	        this.actorName = actorName;
	        this.actressName = actressName;
	    }
	    
	    public String getGenre() {
			return genre;
		}

		public void setGenre(String genre) {
			this.genre = genre;
		}

		public String getActorName() {
			return actorName;
		}

		public void setActorName(String actorName) {
			this.actorName = actorName;
		}

		public String getActressName() {
			return actressName;
		}

		public void setActressName(String actressName) {
			this.actressName = actressName;
		}

		@Override
	    public void displayEventDetails() {
	        System.out.println("Movie: " + getEventName() + " | Genre: " + genre);
	        System.out.println("Actor: " + actorName + ", Actress: " + actressName);
	        getVenue().displayVenueDetails();
	        System.out.println("Tickets: " + getAvailableSeats() + "/" + getTotalSeats());
	        System.out.println("-----------");
	    }


}
