package com.ticketbooking.client;

import java.util.Scanner;

import com.ticketbooking.entity.Ticket;

public class TicketBooking {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);

        System.out.println("Ticket Options:");
        System.out.println("1. Silver (Rs.100.0)");
        System.out.println("2. Gold (Rs.200.0)");
        System.out.println("3. Diamond (Rs.500.0)");

        System.out.print("Enter Ticket Type (Silver/Gold/Diamond): ");
        String ticketType = sc.next();

        System.out.print("Enter Number of Tickets: ");
        int noOfTickets = sc.nextInt();

        Ticket ticket = new Ticket(ticketType, noOfTickets);

        double totalCost = ticket.calculateTotalCost();

        if (totalCost > 0) {
            System.out.println("\nBooking Summary:");
            System.out.println("Ticket Type: " + ticket.getTicketType());
            System.out.println("Number of Tickets: " + ticket.getNoOfTickets());
            System.out.println("Base Price per Ticket: Rs." + ticket.getBasePrice());
            System.out.println("Total Cost: Rs." + totalCost);
        }

        sc.close();
	}

}
