package com.ticketbooking.bean;

import java.time.LocalDate;
import java.util.List;

public class Booking {
	
	private int bookingId;
    private List<Customer> customers;  // One-to-many: one booking, many customers
    private Event event;
    private int numTickets;
    private double totalCost;
    private LocalDate bookingDate;

    // Default constructor
    public Booking() {}

    // Main constructor for use in service logic
    public Booking(int bookingId, List<Customer> customers, Event event, int numTickets) {
        this.bookingId = bookingId;
        this.customers = customers;
        this.event = event;
        this.numTickets = numTickets;
        this.bookingDate = LocalDate.now();
        calculateBookingCost(numTickets);
        bookTickets(numTickets);
    }

    // Constructor used when retrieving from DB
    public Booking(int bookingId, List<Customer> customers, Event event, int numTickets, double totalCost, LocalDate bookingDate) {
        this.bookingId = bookingId;
        this.customers = customers;
        this.event = event;
        this.numTickets = numTickets;
        this.totalCost = totalCost;
        this.bookingDate = bookingDate;
    }

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    // Calculate total cost using event ticket price
    public void calculateBookingCost(int numTickets) {
        if (event != null) {
            this.totalCost = event.getTicketPrice() * numTickets;
        }
    }

    // Reduce available seats in event
    public void bookTickets(int numTickets) {
        if (event != null) {
            event.bookTickets(numTickets);
        }
    }

    // Cancel tickets in event
    public void cancelBooking(int numTickets) {
        if (event != null) {
            event.cancelBooking(numTickets);
        }
    }

    public int getAvailableNoOfTickets() {
        return event.getAvailableSeats();
    }

    public void getEventDetails() {
        if (event != null) {
            event.displayEventDetails();
        }
    }

    public void displayBookingDetails() {
        System.out.println("----- Booking Details -----");
        System.out.println("Booking Id: " + bookingId);
        System.out.println("Booking Date: " + bookingDate);
        System.out.println("Tickets Booked: " + numTickets);
        System.out.println("Total Cost: ₹" + totalCost);

        System.out.println("----- Customer Details -----");
        for (Customer customer : customers) {
            customer.displayCustomerDetails();
            System.out.println("---------------------------");
        }

        if (event != null) {
            event.displayEventDetails();
        }

        System.out.println("---------------------------");
    }
}
