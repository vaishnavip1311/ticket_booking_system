package com.ticketbooking.client;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import com.ticketbooking.entity.BookingSystem;
import com.ticketbooking.entity.Concert;
import com.ticketbooking.entity.Event;
import com.ticketbooking.entity.Movie;
import com.ticketbooking.entity.Sports;

public class TicketBookingSystem extends BookingSystem{

	private final ArrayList<Event> events = new ArrayList<>();
	static TicketBookingSystem system = new TicketBookingSystem();
    static Scanner scan = new Scanner(System.in);
    
    public static void main(String[] args) {
        String opt;
        do {
            handleMenu();
            System.out.println("Press 'y' to continue:");
            opt = scan.next();
        } while (opt.charAt(0) == 'Y' || opt.charAt(0) == 'y');

        System.out.println("Thank you for using the system!");
    }
    
    public static void handleMenu() {
        System.out.println("\n--- Ticket Booking System Menu ---");
        System.out.println("1. Create Event");
        System.out.println("2. Book Tickets");
        System.out.println("3. Cancel Tickets");
        System.out.println("4. Get Available Seats");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");

        int menuOpt = scan.nextInt();
        scan.nextLine(); 
        switch (menuOpt) {
            case 1:
                system.createEvent();
                break;
            case 2:
                system.bookTickets();
                break;
            case 3:
                system.cancelTickets();
                break;
            case 4:
                system.getAvailableSeats();
            case 5:
                system.exit();
                break;
            default:
                System.out.println("Invalid menu option.");
        }
    }

    @Override
    public void createEvent() {
        System.out.println("Enter event type (Movie, Concert, Sports): ");
        String type = scan.nextLine();

        System.out.println("Event Name: ");
        String name = scan.nextLine();
        System.out.println("Date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(scan.nextLine());
        System.out.println("Time (HH:MM): ");
        LocalTime time = LocalTime.parse(scan.nextLine());
        System.out.println("Venue Name: ");
        String venue = scan.nextLine();
        System.out.println("Total Seats: ");
        int seats = scan.nextInt();
        System.out.println("Ticket Price: ");
        double price = scan.nextDouble();
        scan.nextLine(); 

        boolean created = false;

        switch (type.toLowerCase()) {
            case "movie":
                System.out.println("Genre: ");
                String genre = scan.nextLine();
                System.out.println("Actor Name: ");
                String actor = scan.nextLine();
                System.out.println("Actress Name: ");
                String actress = scan.nextLine();
                events.add(new Movie(name, date, time, venue, seats, price, genre, actor, actress));
                created = true;
                break;
            case "concert":
                System.out.println("Artist: ");
                String artist = scan.nextLine();
                System.out.println("Type: ");
                String concertType = scan.nextLine();
                events.add(new Concert(name, date, time, venue, seats, price, artist, concertType));
                created = true;
                break;
            case "sports":
                System.out.println("Sport Name: ");
                String sport = scan.nextLine();
                System.out.println("Teams (Team1 vs Team2): ");
                String teams = scan.nextLine();
                events.add(new Sports(name, date, time, venue, seats, price, sport, teams));
                created = true;
                break;
            default:
                System.out.println("Invalid event type.");
        }

        if (created) {
            System.out.println("Event created successfully!");
        }
    }

    @Override
    public void bookTickets() {
        if (events.isEmpty()) {
            System.out.println("No events available to book.");
            return;
        }

        displayAllEvents();
        System.out.println("Select event index to book tickets: ");
        int index = scan.nextInt();

        if (index < 0 || index >= events.size()) {
            System.out.println("Invalid index selected.");
            return;
        }

        System.out.println("Enter number of tickets: ");
        int num = scan.nextInt();

        events.get(index).bookTickets(num);
        System.out.println(num + " Tickets booked successfully!");
    }

    @Override
    public void cancelTickets() {
        if (events.isEmpty()) {
            System.out.println("No events available to cancel tickets.");
            return;
        }

        displayAllEvents();
        System.out.println("Select event index to cancel tickets: ");
        int index = scan.nextInt();

        if (index < 0 || index >= events.size()) {
            System.out.println("Invalid index selected.");
            return;
        }

        System.out.println("Enter number of tickets to cancel: ");
        int num = scan.nextInt();

        events.get(index).cancelTickets(num);
        System.out.println(num + " Tickets cancelled successfully!");
    }

    @Override
    public void getAvailableSeats() {
        if (events.isEmpty()) {
            System.out.println("No events to display.");
        } else {
            displayAllEvents();
        }
    }
    
    public void displayAllEvents() {
        for (int i = 0; i < events.size(); i++) {
            System.out.println("[" + i + "]");
            events.get(i).displayEventDetails();
            System.out.println("---------------");
        }
    }
   
    public void exit() {
		System.out.println("Exiting the system.");
	}
}
