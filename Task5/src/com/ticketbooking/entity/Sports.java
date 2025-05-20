package com.ticketbooking.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Sports extends Event{
	
	private String sportName;
    private String teamsName;

    public Sports(String eventName, LocalDate eventDate, LocalTime eventTime, String venueName,
                  int totalSeats, double ticketPrice, String sportName, String teamsName) {
        super(eventName, eventDate, eventTime, venueName, totalSeats, ticketPrice, "Sports");
        this.sportName = sportName;
        this.teamsName = teamsName;
    }

    @Override
    public void displayEventDetails() {
        super.displayEventDetails();
        System.out.println("Sport: " + sportName);
        System.out.println("Team: " + teamsName);
        System.out.println("----------------------------");
    }
}
