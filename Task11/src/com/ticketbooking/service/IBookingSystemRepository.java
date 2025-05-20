package com.ticketbooking.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.ticketbooking.bean.Booking;
import com.ticketbooking.bean.Customer;
import com.ticketbooking.bean.Event;
import com.ticketbooking.bean.Venue;
import com.ticketbooking.exception.EventNotFoundException;
import com.ticketbooking.exception.InsufficientSeatsException;
import com.ticketbooking.exception.InvalidBookingIDException;

public interface IBookingSystemRepository {
	
    Event createEvent(String eventName, LocalDate date, LocalTime time, int totalSeats,
                      double ticketPrice, String eventType, Venue venue);

    List<Event> getEventDetails();

    int getAvailableNoOfTickets(String eventName) throws EventNotFoundException;

    double calculateBookingCost(int numTickets, double ticketPrice) throws InsufficientSeatsException;

    Booking bookTickets(Event event, int numTickets, List<Customer> customers)
            throws EventNotFoundException, InsufficientSeatsException;

    void cancelBooking(int bookingId) throws InvalidBookingIDException;

    Booking getBookingDetails(int bookingId) throws InvalidBookingIDException;

	Event getEventByName(String eventName) throws EventNotFoundException;
	
	

}
