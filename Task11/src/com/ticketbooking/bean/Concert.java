package com.ticketbooking.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class Concert extends Event{
	
	private String artist;
    private String type; 

    public Concert() {}

    public Concert(int eventId, String eventName, LocalDate eventDate, LocalTime eventTime, Venue venue,
                   int totalSeats, int availableSeats, double ticketPrice, String eventType,
                   String artist, String type) {
        super(eventId, eventName, eventDate, eventTime, venue, totalSeats, availableSeats, ticketPrice, eventType);
        this.artist = artist;
        this.type = type;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void displayConcertDetails() {
        displayEventDetails();
        System.out.println("Artist: " + artist);
        System.out.println("Concert Type: " + type);
    }

    @Override
    public void displayEventDetails() {
        super.displayEventDetails();
        System.out.println("Artist: " + artist);
        System.out.println("Concert Type: " + type);
    }

}
