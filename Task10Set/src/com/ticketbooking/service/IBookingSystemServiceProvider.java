package com.ticketbooking.service;

import java.util.Set;

import com.ticketbooking.bean.Booking;
import com.ticketbooking.bean.Customer;
import com.ticketbooking.exception.EventNotFoundException;
import com.ticketbooking.exception.InvalidBookingIDException;

public interface IBookingSystemServiceProvider {
	
	    double calculateBookingCost(int numTickets, double ticketPrice);
	    
	    Booking bookTickets(String eventName, int numTickets, Set<Customer> customers) throws EventNotFoundException;

	    boolean cancelBooking(int bookingId) throws InvalidBookingIDException;

        Booking getBookingDetails(int bookingId) throws InvalidBookingIDException;

}
