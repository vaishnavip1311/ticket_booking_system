package com.ticketbooking.bean;

import java.time.LocalDateTime;
import java.util.Set;

public class Booking {
	
	private static int bookingCounter = 1000;

    private int bookingId;
    private Set<Customer> customers;
    private Event event;
    private int numTickets;
    private double totalCost;
    private LocalDateTime bookingDate;

    public Booking() {
        this.bookingId = bookingCounter++;
        this.bookingDate = LocalDateTime.now();
    }

    public Booking(Set<Customer> customers, Event event, int numTickets, double totalCost) {
        this();
        this.customers = customers;
        this.event = event;
        this.numTickets = numTickets;
        this.totalCost = totalCost;
    }

    public Booking(Set<Customer> customers, Event event, int numTickets, double totalCost, LocalDateTime bookingDate) {
        this.bookingId = bookingCounter++;
        this.customers = customers;
        this.event = event;
        this.numTickets = numTickets;
        this.totalCost = totalCost;
        this.bookingDate = bookingDate;
    }

    public int getBookingId() {
        return bookingId;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public Event getEvent() {
        return event;
    }

    public int getNumTickets() {
        return numTickets;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void displayBookingDetails() {
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Booking Date: " + bookingDate);
        System.out.println("Number of Tickets: " + numTickets);
        System.out.println("Total Cost: ₹" + totalCost);
        System.out.println("Event Booked:");
        event.displayEventDetails();
        System.out.println("Customer Details:");
        for (Customer customer : customers) {
            customer.displayCustomerDetails();
            System.out.println("---");
        }
    }

}
