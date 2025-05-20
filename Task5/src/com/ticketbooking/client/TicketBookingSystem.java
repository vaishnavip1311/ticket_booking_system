package com.ticketbooking.client;

import java.util.Scanner;

import com.ticketbooking.entity.Event;
import java.time.LocalDate;
import java.time.LocalTime;
import com.ticketbooking.entity.Movie;
import com.ticketbooking.entity.Concert;
import com.ticketbooking.entity.Sports;

public class TicketBookingSystem {
	public static Scanner scanner = new Scanner(System.in);
	static Event movie = new Movie("Avengers: Endgame", LocalDate.of(2025, 5, 10), LocalTime.of(18, 30),
            "Cineplex", 100, 15.0, "Action", "Robert Downey Jr.", "Scarlett Johansson");

    static Event concert = new Concert("Coldplay Live", LocalDate.of(2025, 6, 20), LocalTime.of(20, 00),
                "Stadium X", 200, 50.0, "Coldplay", "Rock");

    static Event sports = new Sports("Football Match", LocalDate.of(2025, 7, 5), LocalTime.of(19, 00),
              "National Stadium", 150, 30.0, "Football", "Brazil vs Argentina");


	public static void main(String[] args) {
            while (true) {
                System.out.println("🎟️ TICKET BOOKING SYSTEM 🎟️");
                System.out.println("1️. View Events");
                System.out.println("2️. Book Tickets");
                System.out.println("3️. Cancel Tickets (Movie Only)");
                System.out.println("4️. Exit");
                System.out.print("Enter your choice: ");
                
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        viewEvents();
                        break;
                    case 2:
                        bookTickets();
                        break;
                    case 3:
                        cancelTickets();
                        break;
                    case 4:
                        exitSystem();
                        return;
                    default:
                        System.out.println("❌ Invalid choice. Please try again.");
                }
            }
        }

        public static void viewEvents() {
            System.out.println("\n📋 Event Details:");
            movie.displayEventDetails();
            concert.displayEventDetails();
            sports.displayEventDetails();
        }

        public static void bookTickets() {
            System.out.print("Enter event type (Movie/Concert/Sports): ");
            scanner.nextLine(); 
            String type = scanner.nextLine();
            
            System.out.print("Enter number of tickets: ");
            int numTickets = scanner.nextInt();

            if (type.equalsIgnoreCase("Movie")) {
                movie.bookTickets(numTickets);
            } else if (type.equalsIgnoreCase("Concert")) {
                concert.bookTickets(numTickets);
            } else if (type.equalsIgnoreCase("Sports")) {
                sports.bookTickets(numTickets);
            } else {
                System.out.println("❌ Invalid event type!");
            }
        }

        public static void cancelTickets() {
            System.out.print("Enter number of tickets to cancel (Movie Only): ");
            int cancelTickets = scanner.nextInt();
            movie.cancelBooking(cancelTickets);
        }

        public static void exitSystem() {
            System.out.println("👋 Exiting system... Thank you!");
            scanner.close();
        }
	}

