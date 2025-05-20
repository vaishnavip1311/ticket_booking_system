package com.ticketbooking.bean;

public class Customer {
	
	private int customerId;
    private String customerName;
    private String email;
    private String phoneNumber;
    private int bookingId;  // New field to link with Booking table

    public Customer() {}

    public Customer(int customerId, String customerName, String email, String phoneNumber, int bookingId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bookingId = bookingId;
    }

    public Customer(int customerId, String customerName, String email, String phoneNumber) {
    	this.customerId = customerId;
    	this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    
    public Customer(String customerName, String email, String phoneNumber) {
    	this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

	public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void displayCustomerDetails() {
        System.out.println("Customer Id: " + customerId);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Booking Id: " + bookingId);
    }

}
