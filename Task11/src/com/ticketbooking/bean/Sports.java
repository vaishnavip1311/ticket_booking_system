package com.ticketbooking.bean;

import java.time.LocalDate;
import java.time.LocalTime;

public class Sports extends Event{
	
	private String sportName;
    private String teamsName;

    public Sports() {}

    public Sports(int eventId, String eventName, LocalDate eventDate, LocalTime eventTime, Venue venue,
                  int totalSeats, int availableSeats, double ticketPrice, String eventType,
                  String sportName, String teamsName) {
        super(eventId, eventName, eventDate, eventTime, venue, totalSeats, availableSeats, ticketPrice, eventType);
        this.sportName = sportName;
        this.teamsName = teamsName;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public String getTeamsName() {
        return teamsName;
    }

    public void setTeamsName(String teamsName) {
        this.teamsName = teamsName;
    }

    public void displaySportDetails() {
        displayEventDetails();
        System.out.println("Sport: " + sportName);
        System.out.println("Teams: " + teamsName);
    }

    @Override
    public void displayEventDetails() {
        super.displayEventDetails();
        System.out.println("Sport: " + sportName);
        System.out.println("Teams: " + teamsName);
    }

}
