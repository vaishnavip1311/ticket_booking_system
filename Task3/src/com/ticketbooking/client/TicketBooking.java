package com.ticketbooking.client;

import java.util.Scanner;

import com.ticketbooking.entity.Ticket;

public class TicketBooking {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
        String choice;

        do {
 
            System.out.println("\nTicket Options:");
            System.out.println("1. Silver (100.0)");
            System.out.println("2. Gold (200.0)");
            System.out.println("3. Diamond (500.0)");

            System.out.print("Enter Ticket Type (Silver/Gold/Diamond) or 'Exit' to stop: ");
            choice = sc.next();

            if (choice.equalsIgnoreCase("Exit")) {
                System.out.println("Exiting the booking system. Thank you!");
                break;
            }

            System.out.print("Enter Number of Tickets: ");
            int noOfTickets = sc.nextInt();

            Ticket ticket = new Ticket(choice, noOfTickets);

            double totalCost = ticket.calculateTotalCost();

            if (totalCost > 0) {
                System.out.println("\nBooking Summary:");
                System.out.println("Ticket Type: " + ticket.getTicketType());
                System.out.println("Number of Tickets: " + ticket.getNoOfTickets());
                System.out.println("Base Price per Ticket: Rs" + ticket.getBasePrice());
                System.out.println("Total Cost: Rs" + totalCost);
            }

        } while (true);

        sc.close();

	}

}
