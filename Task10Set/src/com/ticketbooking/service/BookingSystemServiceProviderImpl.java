package com.ticketbooking.service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.ticketbooking.bean.Booking;
import com.ticketbooking.bean.Customer;
import com.ticketbooking.bean.Event;
import com.ticketbooking.exception.EventNotFoundException;
import com.ticketbooking.exception.InvalidBookingIDException;

public class BookingSystemServiceProviderImpl extends EventServiceProviderImpl implements IBookingSystemServiceProvider{
	
	private static Set<Booking> bookings = new HashSet<>();

    @Override
    public double calculateBookingCost(int numTickets, double ticketPrice) {
        return numTickets * ticketPrice;
    }

    @Override
    public Booking bookTickets(String eventName, int numTickets, Set<Customer> customers) throws EventNotFoundException {
        Event selectedEvent = null;

        for (Event e : events) {
            if (e.getEventName().equalsIgnoreCase(eventName)) {
                selectedEvent = e;
                break;
            }
        }

        if (selectedEvent == null) {
            throw new EventNotFoundException();
        }

        if (selectedEvent.getAvailableSeats() < numTickets) {
            throw new EventNotFoundException("Not enough seats available for event '" + eventName + "'.");
        }

        selectedEvent.bookTickets(numTickets);
        double totalCost = calculateBookingCost(numTickets, selectedEvent.getTicketPrice());
        Booking booking = new Booking(customers, selectedEvent, numTickets, totalCost, LocalDateTime.now());
        bookings.add(booking);
        return booking;
    }

    @Override
    public boolean cancelBooking(int bookingId) throws InvalidBookingIDException {
        Booking toCancel = null;

        for (Booking b : bookings) {
            if (b.getBookingId() == bookingId) {
                toCancel = b;
                break;
            }
        }

        if (toCancel == null) {
            throw new InvalidBookingIDException();
        }

        toCancel.getEvent().cancelBooking(toCancel.getNumTickets());
        bookings.remove(toCancel);
        return true;
    }

    @Override
    public Booking getBookingDetails(int bookingId) throws InvalidBookingIDException {
        for (Booking b : bookings) {
            if (b.getBookingId() == bookingId) {
                return b;
            }
        }
        throw new InvalidBookingIDException();
    }

}
