package com.ticketbooking.client;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ticketbooking.entity.Booking;
import com.ticketbooking.entity.Concert;
import com.ticketbooking.entity.Customer;
import com.ticketbooking.entity.Event;
import com.ticketbooking.entity.Movie;
import com.ticketbooking.entity.Sport;
import com.ticketbooking.entity.Venue;

public class TicketBookingSystem {

	    private static List<Event> events = new ArrayList<>();
	    private static List<Booking> bookings = new ArrayList<>();
	    private static Scanner sc = new Scanner(System.in);

	    public static void main(String[] args) {
	        while (true) {
	            System.out.println("\n--- Ticket Booking System ---");
	            System.out.println("1. Create Event");
	            System.out.println("2. Book Tickets");
	            System.out.println("3. Cancel Booking");
	            System.out.println("4. Get Available Seats");
	            System.out.println("5. Show Event Details");
	            System.out.println("6. Exit");
	            System.out.print("Choose an option: ");
	            int choice = sc.nextInt();

	            switch (choice) {
	                case 1 -> createEvent();
	                case 2 -> bookTickets();
	                case 3 -> cancelBooking();
	                case 4 -> getAvailableSeats();
	                case 5 -> showEventDetails();
	                case 6 -> {
	                    System.out.println("Thank you for using the system.");
	                    return;
	                }
	                default -> System.out.println("Invalid option.");
	            }
	        }
	    }

	    private static void createEvent() {
	        sc.nextLine(); 
	        System.out.print("Enter Event Type (Movie/Sport/Concert): ");
	        String type = sc.nextLine();

	        System.out.print("Enter Event Name: ");
	        String name = sc.nextLine();
	        System.out.print("Enter Date (yyyy-mm-dd): ");
	        LocalDate date = LocalDate.parse(sc.nextLine());
	        System.out.print("Enter Time (HH:mm): ");
	        LocalTime time = LocalTime.parse(sc.nextLine());
	        System.out.print("Enter Venue Name: ");
	        String vName = sc.nextLine();
	        System.out.print("Enter Venue Address: ");
	        String vAddr = sc.nextLine();

	        Venue venue = new Venue(vName, vAddr);

	        System.out.print("Enter Total Seats: ");
	        int totalSeats = sc.nextInt();
	        System.out.print("Enter Ticket Price: ");
	        double price = sc.nextDouble();
	        sc.nextLine();

	        Event e = null;
	        switch (type.toLowerCase()) {
	            case "movie" -> {
	                System.out.print("Enter Genre: ");
	                String genre = sc.nextLine();
	                System.out.print("Enter Actor Name: ");
	                String actor = sc.nextLine();
	                System.out.print("Enter Actress Name: ");
	                String actress = sc.nextLine();
	                e = new Movie(name, date, time, venue, totalSeats, price, genre, actor, actress);
	            }
	            case "concert" -> {
	                System.out.print("Enter Artist: ");
	                String artist = sc.nextLine();
	                System.out.print("Enter Type (Rock/Classical/Recital): ");
	                String concertType = sc.nextLine();
	                e = new Concert(name, date, time, venue, totalSeats, price, artist, concertType);
	            }
	            case "sport" -> {
	                System.out.print("Enter Sport Name: ");
	                String sport = sc.nextLine();
	                System.out.print("Enter Teams (e.g., India vs Aus): ");
	                String teams = sc.nextLine();
	                e = new Sport(name, date, time, venue, totalSeats, price, sport, teams);
	            }
	        }
	        if (e != null) {
	            events.add(e);
	            System.out.println("Event created successfully!");
	        }
	    }

	    private static void bookTickets() {
	        sc.nextLine();
	        System.out.print("Enter Event Name to Book: ");
	        String eName = sc.nextLine();
	        Event e = events.stream().filter(ev -> ev.getEventName().equalsIgnoreCase(eName)).findFirst().orElse(null);

	        if (e == null) {
	            System.out.println("Event not found.");
	            return;
	        }

	        System.out.print("Enter No of Tickets: ");
	        int n = sc.nextInt();
	        sc.nextLine();

	        if (e.getAvailableSeats() < n) {
	            System.out.println("Not enough tickets available.");
	            return;
	        }

	        Customer[] customers = new Customer[n];
	        for (int i = 0; i < n; i++) {
	            System.out.print("Enter Customer " + (i + 1) + " Name: ");
	            String name = sc.nextLine();
	            System.out.print("Enter Email: ");
	            String email = sc.nextLine();
	            System.out.print("Enter Phone: ");
	            String phone = sc.nextLine();
	            customers[i] = new Customer(name, email, phone);
	        }

	        e.bookTickets(n);
	        Booking b = new Booking(customers, e, n);
	        bookings.add(b);
	        System.out.println("Booking successful!");
	        b.displayBookingDetails();
	    }

	    private static void cancelBooking() {
	        System.out.print("Enter Booking ID to Cancel: ");
	        int id = sc.nextInt();
	        Booking b = bookings.stream().filter(book -> book.getBookingId() == id).findFirst().orElse(null);

	        if (b != null) {
	            b.getEvent().cancelBooking(b.getNumTickets());
	            bookings.remove(b);
	            System.out.println("Booking cancelled.");
	        } else {
	            System.out.println("Booking not found.");
	        }
	    }

	    private static void getAvailableSeats() {
	        for (Event e : events) {
	            System.out.println(e.getEventName() + " => Available Seats: " + e.getAvailableSeats());
	        }
	    }

	    private static void showEventDetails() {
	        for (Event e : events) {
	            e.displayEventDetails();
	            System.out.println("-----------------------");
	        }
	    }

}
