package com.ticketbooking.service;

import java.time.LocalDate;
import java.time.LocalTime;

import com.ticketbooking.bean.Event;
import com.ticketbooking.bean.Venue;

public interface IEventServiceProvider {
	
	Event createEvent(String eventName, LocalDate date, LocalTime time, int totalSeats, double ticketPrice, String eventType, Venue venue);
    Event[] getEventDetails();
    int getAvailableNoOfTickets();

}
