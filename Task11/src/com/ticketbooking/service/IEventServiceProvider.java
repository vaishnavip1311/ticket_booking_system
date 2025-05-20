package com.ticketbooking.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.ticketbooking.bean.Event;
import com.ticketbooking.bean.Venue;
import com.ticketbooking.exception.EventNotFoundException;

public interface IEventServiceProvider {
	
	Event createEvent(String eventName, LocalDate date, LocalTime time, int totalSeats,
            double ticketPrice, String eventType, Venue venue);

    List<Event> getEventDetails();

    int getAvailableNoOfTickets(String eventName) throws EventNotFoundException;
    
    Event getEventByName(String eventName) throws EventNotFoundException;
}
