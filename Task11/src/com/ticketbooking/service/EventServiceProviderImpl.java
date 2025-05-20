package com.ticketbooking.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.ticketbooking.bean.Event;
import com.ticketbooking.bean.Venue;
import com.ticketbooking.exception.EventNotFoundException;

public class EventServiceProviderImpl implements IEventServiceProvider{

	private IBookingSystemRepository repository;

    public EventServiceProviderImpl(IBookingSystemRepository repository) {
        this.repository = repository;
    }

    @Override
    public Event createEvent(String eventName, LocalDate date, LocalTime time, int totalSeats,
                             double ticketPrice, String eventType, Venue venue) {
        return repository.createEvent(eventName, date, time, totalSeats, ticketPrice, eventType, venue);
    }

    @Override
    public List<Event> getEventDetails() {
        return repository.getEventDetails();
    }

    @Override
    public int getAvailableNoOfTickets(String eventName) throws EventNotFoundException {
        return repository.getAvailableNoOfTickets(eventName);
    }

	@Override
	public Event getEventByName(String eventName) throws EventNotFoundException {
		return repository.getEventByName(eventName);
	}
}
