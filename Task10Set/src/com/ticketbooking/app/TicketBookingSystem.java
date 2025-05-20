package com.ticketbooking.app;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.ticketbooking.bean.Booking;
import com.ticketbooking.bean.Customer;
import com.ticketbooking.bean.Event;
import com.ticketbooking.bean.Venue;
import com.ticketbooking.exception.EventNotFoundException;
import com.ticketbooking.exception.InvalidBookingIDException;
import com.ticketbooking.service.BookingSystemServiceProviderImpl;
import com.ticketbooking.util.EventNameLocationComparator;

public class TicketBookingSystem {
	
    public static Scanner sc = new Scanner(System.in);
    public static BookingSystemServiceProviderImpl system = new BookingSystemServiceProviderImpl();

    public static void main(String[] args) {
        while (true) {
            try {
                showMenu();
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        createEvent();
                        break;
                    case 2:
                        bookTickets();
                        break;
                    case 3:
                        cancelTickets();
                        break;
                    case 4:
                        getAvailableSeats();
                        break;
                    case 5:
                        getEventDetails();
                        break;
                    case 6:
                        getSortedEventDetails();
                        break;
                    case 7:
                        getBookingDetails();
                        break;
                    case 8:
                        System.out.println("Exiting Ticket Booking System. Thank you!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NullPointerException e) {
                System.out.println("Error: Null value encountered. Please try again.");
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }

    public static void showMenu() {
        System.out.println("\n----- Ticket Booking System Menu -----");
        System.out.println("1. Create Event");
        System.out.println("2. Book Tickets");
        System.out.println("3. Cancel Tickets");
        System.out.println("4. Get Available Seats");
        System.out.println("5. Get Event Details");
        System.out.println("6. Get Sorted Event Details");
        System.out.println("7. Get Booking Details");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void createEvent() {
        System.out.print("Enter event name: ");
        String eventName = sc.nextLine();

        System.out.print("Enter event date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(sc.nextLine());

        System.out.print("Enter event time (HH:MM): ");
        LocalTime time = LocalTime.parse(sc.nextLine());

        System.out.print("Enter venue name: ");
        String venueName = sc.nextLine();

        System.out.print("Enter venue address: ");
        String address = sc.nextLine();

        System.out.print("Enter total seats: ");
        int totalSeats = sc.nextInt();

        System.out.print("Enter ticket price: ");
        double ticketPrice = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter event type (Movie/Sports/Concert): ");
        String eventType = sc.nextLine();

        Venue venue = new Venue(venueName, address);
        system.createEvent(eventName, date, time, totalSeats, ticketPrice, eventType, venue);
        System.out.println("Event created successfully!");
    }

    public static void bookTickets() {
        try {
            System.out.print("Enter event name to book: ");
            String bookEvent = sc.nextLine();

            System.out.print("Enter number of tickets: ");
            int numTickets = sc.nextInt();
            sc.nextLine();

            Set<Customer> customers = new HashSet<>();
            for (int i = 0; i < numTickets; i++) {
                System.out.println("Enter details for Customer " + (i + 1));
                System.out.print("Name: ");
                String name = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Phone: ");
                String phone = sc.nextLine();
                customers.add(new Customer(name, email, phone));
            }

            Booking booking = system.bookTickets(bookEvent, numTickets, customers);
            System.out.println("Booking successful! Your Booking ID is: " + booking.getBookingId());

        } catch (EventNotFoundException e) {
            System.out.println("Error: Event not found or insufficient seats.");
        }
    }

    public static void cancelTickets() {
        int bookingId = -1;
        try {
            System.out.print("Enter booking ID to cancel: ");
            bookingId = sc.nextInt();
            boolean cancelled = system.cancelBooking(bookingId);
            if (cancelled) {
                System.out.println("Booking cancelled successfully for ID: " + bookingId);
            }
        } catch (InvalidBookingIDException e) {
            System.out.println("Error: Booking with ID " + bookingId + " not found.");
        }
    }

    public static void getAvailableSeats() {
        int availableSeats = system.getAvailableNoOfTickets();
        System.out.println("Total Available Tickets: " + availableSeats);
    }
    
    public static void getEventDetails() {
        Event[] events = system.getEventDetails();
        if (events.length == 0) {
            System.out.println("No events found.");
        } else {
            for (Event e : events) {
                e.displayEventDetails();
            }
        }
    }

    public static void getSortedEventDetails() {
        Event[] events = system.getEventDetails();
        if (events.length == 0) {
            System.out.println("No events found.");
        } else {
            List<Event> eventList = Arrays.asList(events);
            eventList.sort(new EventNameLocationComparator());
            for (Event e : eventList) {
                e.displayEventDetails();
            }
        }
    }

    public static void getBookingDetails() {
        int id = -1;
        try {
            System.out.print("Enter booking ID: ");
            id = sc.nextInt();
            Booking booking = system.getBookingDetails(id);
            booking.displayBookingDetails();
        } catch (InvalidBookingIDException e) {
            System.out.println("Error: Booking with ID " + id + " not found.");
        }
    }
}
