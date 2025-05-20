package com.ticketbooking.app;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ticketbooking.bean.Booking;
import com.ticketbooking.bean.Customer;
import com.ticketbooking.bean.Event;
import com.ticketbooking.bean.Venue;
import com.ticketbooking.exception.EventNotFoundException;
import com.ticketbooking.exception.InsufficientSeatsException;
import com.ticketbooking.service.IBookingSystemRepository;
import com.ticketbooking.service.BookingSystemRepositoryImpl;
import com.ticketbooking.service.BookingSystemServiceProviderImpl;
import com.ticketbooking.service.EventServiceProviderImpl;
import com.ticketbooking.service.IBookingSystemServiceProvider;
import com.ticketbooking.service.IEventServiceProvider;


public class TicketBookingSystem {
	
	private static Scanner sc = new Scanner(System.in);
	
	static IBookingSystemRepository repository = new BookingSystemRepositoryImpl();
    static IBookingSystemServiceProvider bookingService = new BookingSystemServiceProviderImpl(repository);
    static IEventServiceProvider eventService = new EventServiceProviderImpl(repository);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== Ticket Booking System =====");
            System.out.println("1. Create Event");
            System.out.println("2. Book Tickets");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Get Available Seats");
            System.out.println("5. Get Event Details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); 

            try {
                switch (choice) {
                    case 1:
                        createEvent(sc);
                        break;
                    case 2:
                        bookTickets(sc);
                        break;
                    case 3:
                        cancelBooking(sc);
                        break;
                    case 4:
                        getAvailableSeats();
                        break;
                    case 5:
                        getEventDetails();
                        break;
                    case 6:
                        System.out.println("Exiting system. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void createEvent(Scanner sc) {
        System.out.print("Enter event name: ");
        String name = sc.nextLine();
        System.out.print("Enter event date (YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(sc.nextLine());
        System.out.print("Enter event time (HH:MM): ");
        LocalTime time = LocalTime.parse(sc.nextLine());
        System.out.print("Enter total seats: ");
        int totalSeats = sc.nextInt();
        System.out.print("Enter ticket price: ");
        double price = sc.nextDouble();
        sc.nextLine(); 
        System.out.print("Enter event type (movie/sports/concert): ");
        String type = sc.nextLine();

        System.out.print("Enter venue name: ");
        String venueName = sc.nextLine();
        System.out.print("Enter venue address: ");
        String venueAddress = sc.nextLine();

        Venue venue = new Venue(venueName, venueAddress);
        Event event = eventService.createEvent(name, date, time, totalSeats, price, type, venue);

        if (event != null) {
            System.out.println("✅ Event created successfully: " + event.getEventName());
        } else {
            System.out.println("❌ Event creation failed. Please try again.");
        }
    }


      private static void bookTickets(Scanner sc) {
    	    try {
    	        System.out.print("Enter event name: ");
    	        String eventName = sc.nextLine().trim();

    	        Event event = eventService.getEventByName(eventName);

    	        System.out.print("Enter number of tickets: ");
    	        int numTickets = Integer.parseInt(sc.nextLine());

    	        if (event.getAvailableSeats() < numTickets) {
    	            System.out.println("Only " + event.getAvailableSeats() + " seats available. Cannot proceed.");
    	            return;
    	        }

    	        List<Customer> customerList = new ArrayList<>();
    	        for (int i = 1; i <= numTickets; i++) {
    	            System.out.println("\nEnter details for Customer " + i + ":");
    	            System.out.print("Name: ");
    	            String name = sc.nextLine();

    	            System.out.print("Email: ");
    	            String email = sc.nextLine();

    	            System.out.print("Phone Number: ");
    	            String phone = sc.nextLine();

    	            Customer customer = new Customer(name, email, phone);
    	            customerList.add(customer);
    	        }

    	        Booking booking = bookingService.bookTickets(event, numTickets, customerList);

    	        System.out.println("\nTickets booked successfully!");
    	        booking.displayBookingDetails();

    	    } catch (EventNotFoundException e) {
    	        System.out.println("Error: " + e.getMessage());
    	    } catch (InsufficientSeatsException e) {
    	        System.out.println("Insufficient seats: " + e.getMessage());
    	    } catch (Exception e) {
    	        System.out.println("An error occurred while booking tickets: " + e.getMessage());
    	    }
    	}





    private static void cancelBooking(Scanner sc) {
    	try {
            System.out.print("Enter Booking ID to cancel: ");
            int bookingId = sc.nextInt();
            sc.nextLine();
            bookingService.cancelBooking(bookingId);
            System.out.println("Booking cancelled successfully.");
        } catch (Exception e) {
            System.out.println("Error cancelling booking: " + e.getMessage());
        }
    }

    private static void getAvailableSeats() {
    	System.out.print("Enter event name to check available seats: ");
        String eventName = sc.nextLine();
        Event event = null;
		try {
			event = eventService.getEventByName(eventName);
		} catch (EventNotFoundException e) {
			System.out.println("Event not found with name:" + eventName);
		}
        int availableSeats = event.getAvailableSeats();
        System.out.println("Available Seats for " + event.getEventName() + ": " + availableSeats);
    }

    private static void getEventDetails() {
    	List<Event> eventList = eventService.getEventDetails(); 
        if (eventList.isEmpty()) {
            System.out.println("No events available.");
        } else {
            for (Event e : eventList) {
                e.displayEventDetails();
            }
        }
}
}
