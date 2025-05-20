package com.ticketbooking.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Event {
	
	protected String eventName;
    protected LocalDate eventDate;
    protected LocalTime eventTime;
    protected String venueName;
    protected int totalSeats;
    protected int availableSeats;
    protected double ticketPrice;
    protected String eventType;

    public Event() {}

    public Event(String eventName, LocalDate eventDate, LocalTime eventTime, String venueName, int totalSeats, double ticketPrice, String eventType) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.venueName = venueName;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.ticketPrice = ticketPrice;
        this.eventType = eventType;
    }

    public abstract void displayEventDetails();

    public void bookTickets(int num) {
        if (availableSeats >= num) {
            availableSeats -= num;
        } else {
            System.out.println("Not enough seats available.");
        }
    }

    public void cancelTickets(int num) {
        availableSeats += num;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double calculateTotalRevenue() {
        return (totalSeats - availableSeats) * ticketPrice;
    }

}
