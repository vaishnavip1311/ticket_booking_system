package com.ticketbooking.service;

import java.util.List;

import com.ticketbooking.bean.Booking;
import com.ticketbooking.bean.Customer;
import com.ticketbooking.bean.Event;
import com.ticketbooking.exception.EventNotFoundException;
import com.ticketbooking.exception.InsufficientSeatsException;
import com.ticketbooking.exception.InvalidBookingIDException;

public class BookingSystemServiceProviderImpl implements IBookingSystemServiceProvider{
	
	private IBookingSystemRepository repository;

    public BookingSystemServiceProviderImpl(IBookingSystemRepository repository) {
        this.repository = repository;
    }

    public BookingSystemServiceProviderImpl() {
	}

	@Override
    public double calculateBookingCost(int numTickets, double ticketPrice) throws InsufficientSeatsException {
        return repository.calculateBookingCost(numTickets, ticketPrice);
    }

	@Override 
	public Booking bookTickets(Event event, int numTickets, List<Customer> customers)
	        throws InsufficientSeatsException, EventNotFoundException {
	    
	    if (event == null) {
	        throw new EventNotFoundException("Event object is null.");
	    }

	    if (event.getAvailableSeats() < numTickets) {
	        throw new InsufficientSeatsException("Only " + event.getAvailableSeats() + " seats are available.");
	    }

	    Booking booking = repository.bookTickets(event, numTickets, customers);

	    return booking;
	}


    @Override
    public void cancelBooking(int bookingId) throws InvalidBookingIDException {
        repository.cancelBooking(bookingId);
    }

    @Override
    public Booking getBookingDetails(int bookingId) throws InvalidBookingIDException {
        return repository.getBookingDetails(bookingId);
    }

}
