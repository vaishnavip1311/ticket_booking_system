package com.ticketbooking.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.ticketbooking.bean.Event;
import com.ticketbooking.bean.Sport;
import com.ticketbooking.bean.Venue;
import com.ticketbooking.bean.Movie;
import com.ticketbooking.bean.Concert;

public class EventServiceProviderImpl implements IEventServiceProvider{
	
	protected static List<Event> events = new ArrayList<>();

    @Override
    public Event createEvent(String eventName, LocalDate date, LocalTime time, int totalSeats, double ticketPrice, String eventType, Venue venue) {
        Event event = null;

        switch (eventType.toLowerCase()) {
            case "movie":
                event = new Movie(eventName, date, time, venue, totalSeats, ticketPrice, "Movie", "Action", "Vicky", "Rashmika");
                break;
            case "concert":
                event = new Concert(eventName, date, time, venue, totalSeats, ticketPrice, "Concert", "Arijit Singh", "Rock");
                break;
            case "sports":
                event = new Sport(eventName, date, time, venue, totalSeats, ticketPrice, "Sports", "Cricket", "MI vs CSK");
                break;
            default:
                System.out.println("Invalid event type.");
        }

        if (event != null) {
            events.add(event);
        }

        return event;
    }

    @Override
    public Event[] getEventDetails() {
        return events.toArray(new Event[0]);
    }

    @Override
    public int getAvailableNoOfTickets() {
        int totalAvailable = 0;
        for (Event e : events) {
            totalAvailable += e.getAvailableSeats();
        }
        return totalAvailable;
    }
	
}
