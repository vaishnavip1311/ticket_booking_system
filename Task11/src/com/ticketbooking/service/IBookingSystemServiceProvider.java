package com.ticketbooking.service;

import java.util.List;

import com.ticketbooking.bean.Booking;
import com.ticketbooking.bean.Customer;
import com.ticketbooking.bean.Event;
import com.ticketbooking.exception.EventNotFoundException;
import com.ticketbooking.exception.InsufficientSeatsException;
import com.ticketbooking.exception.InvalidBookingIDException;

public interface IBookingSystemServiceProvider {
	
	double calculateBookingCost(int numTickets, double ticketPrice) throws InsufficientSeatsException;

	Booking bookTickets(Event event, int numTickets, List<Customer> customers)
			throws InsufficientSeatsException, EventNotFoundException;

    void cancelBooking(int bookingId) throws InvalidBookingIDException;

    Booking getBookingDetails(int bookingId) throws InvalidBookingIDException;
  
}
