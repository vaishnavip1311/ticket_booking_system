package com.ticketbooking.entity;

public class Ticket {
	private String ticketType;
    private int noOfTickets;
    private double basePrice;
    
	public Ticket(String ticketType, int noOfTickets) {
		
		this.ticketType = ticketType;
		this.noOfTickets = noOfTickets;
		setBasePrice();
	}

	public String getTicketType() {
		return ticketType;
	}

	public int getNoOfTickets() {
		return noOfTickets;
	}

	public double getBasePrice() {
		return basePrice;
	}
    
	private void setBasePrice() {
        if (ticketType.equalsIgnoreCase("Silver")) {
            basePrice = 150.0;
        } else if (ticketType.equalsIgnoreCase("Gold")) {
            basePrice = 250.0;
        } else if (ticketType.equalsIgnoreCase("Diamond")) {
            basePrice = 450.0;
        } else {
            basePrice = 0.0;
        }
    }
	
	public double calculateTotalCost() {
        if (basePrice == 0) {
            System.out.println("Invalid ticket type selected.");
            return 0;
        }
        return basePrice * noOfTickets;
    }
    

}
