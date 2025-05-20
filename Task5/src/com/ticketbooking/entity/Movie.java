package com.ticketbooking.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Movie extends Event{
	
	private String genre;
    private String actorName;
    private String actressName;

    public Movie(String eventName, LocalDate eventDate, LocalTime eventTime, String venueName,
                 int totalSeats, double ticketPrice, String genre, String actorName, String actressName) {
        super(eventName, eventDate, eventTime, venueName, totalSeats, ticketPrice, "Movie");
        this.genre = genre;
        this.actorName = actorName;
        this.actressName = actressName;
    }

	
	@Override
    public void displayEventDetails(){
        super.displayEventDetails();
        System.out.println("Genre: " + genre );
        System.out.println("Actor: " + actorName);
        System.out.println("Actress: " + actressName);
        System.out.println("----------------------------");
    }
	
}
