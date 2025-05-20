package com.tickectbooking.client;

import java.util.Scanner;

import com.tickectbooking.entity.Ticket;

public class BookTickets {

	public static void main(String[] args) {
		
		 Scanner sc = new Scanner(System.in);

	        System.out.print("Enter available tickets: ");
	        int availableTickets = sc.nextInt();

	        Ticket ticket = new Ticket(availableTickets);

	        System.out.print("Enter number of tickets to book: ");
	        int noOfBookingTickets = sc.nextInt();

	        String result = ticket.bookTickets(noOfBookingTickets);
	        System.out.println(result);

	        sc.close();

	}

}
