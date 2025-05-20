package com.ticketbooking.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class Movie extends Event{
	
	private String genre;
    private String actorName;
    private String actressName;

    public Movie() {}

    public Movie(int eventId, String eventName, LocalDate eventDate, LocalTime eventTime, Venue venue,
                 int totalSeats, int availableSeats, double ticketPrice, String eventType,
                 String genre, String actorName, String actressName) {
        super(eventId, eventName, eventDate, eventTime, venue, totalSeats, availableSeats, ticketPrice, eventType);
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
        super.displayEventDetails();
        System.out.println("Genre: " + genre);
        System.out.println("Actor: " + actorName);
        System.out.println("Actress: " + actressName);
    }

}
