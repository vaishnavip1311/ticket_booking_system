package com.ticketbooking.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.ticketbooking.bean.Booking;
import com.ticketbooking.bean.Customer;
import com.ticketbooking.bean.Event;
import com.ticketbooking.bean.Venue;
import com.ticketbooking.exception.DbConnectionException;
import com.ticketbooking.exception.EventNotFoundException;
import com.ticketbooking.exception.InsufficientSeatsException;
import com.ticketbooking.exception.InvalidBookingIDException;
import com.ticketbooking.util.DBConnection;

public class BookingSystemRepositoryImpl implements IBookingSystemRepository{
	
	private static Connection conn = null;

    static {
        try {
            conn = DBConnection.getDbConnection();
        } catch (DbConnectionException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Event createEvent(String eventName, LocalDate date, LocalTime time, int totalSeats,
                             double ticketPrice, String eventType, Venue venue) {

        int venueId = 0;

        String venueSQL = "INSERT INTO venu(name, address) VALUES (?, ?)";
        try (PreparedStatement venueStmt = conn.prepareStatement(venueSQL, Statement.RETURN_GENERATED_KEYS)) {
            venueStmt.setString(1, venue.getVenueName());
            venueStmt.setString(2, venue.getAddress());
            venueStmt.executeUpdate();

            ResultSet venueKeys = venueStmt.getGeneratedKeys();
            if (venueKeys.next()) {
                venueId = venueKeys.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        String eventSQL = "INSERT INTO event (event_name, event_date, event_time, venue_id, total_seats, available_seats, ticket_price, event_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(eventSQL, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, eventName);
            pstmt.setString(2, date.toString());
            pstmt.setString(3, time.toString());
            pstmt.setInt(4, venueId);
            pstmt.setInt(5, totalSeats);
            pstmt.setInt(6, totalSeats);
            pstmt.setDouble(7, ticketPrice);
            pstmt.setString(8, eventType);

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                int eventId = rs.getInt(1);
                return new Event(eventId, eventName, date, time, new Venue(venueId, venue.getVenueName(), venue.getAddress()),
                                 totalSeats, totalSeats, ticketPrice, eventType);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public List<Event> getEventDetails() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM Event e JOIN Venu v ON e.venue_id = v.venue_id";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Venue v = new Venue(rs.getInt("venue_id"), rs.getString("venue_name"), rs.getString("address"));
                Event e = new Event(
                	    rs.getInt("event_id"),
                	    rs.getString("event_name"),
                	    LocalDate.parse(rs.getString("event_date")),
                	    LocalTime.parse(rs.getString("event_time")),
                	    v,
                	    rs.getInt("total_seats"),
                	    rs.getInt("available_seats"),
                	    rs.getDouble("ticket_price"),
                	    rs.getString("event_type")
                	);
                events.add(e);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }

    @Override
    public int getAvailableNoOfTickets(String eventName) throws EventNotFoundException {
        String sql = "SELECT available_seats FROM Event WHERE event_name = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, eventName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("available_seats");
            } else {
                throw new EventNotFoundException("Event '" + eventName + "' not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public double calculateBookingCost(int numTickets, double ticketPrice) {
        return numTickets * ticketPrice;
    }

    @Override
    public Booking bookTickets(Event event, int numTickets, List<Customer> customers)
            throws EventNotFoundException, InsufficientSeatsException {

        Connection conn = null;
        Booking booking = null;

        try {
            conn = DBConnection.getDbConnection();
            int availableSeats = event.getAvailableSeats();
            if (availableSeats < numTickets) {
                throw new InsufficientSeatsException("Only " + availableSeats + " seats available for event: " + event.getEventName());
            }

            String insertBookingSql = "INSERT INTO Booking (event_id, num_tickets, total_cost, booking_date) VALUES (?, ?, ?, ?)";
            PreparedStatement bookingStmt = conn.prepareStatement(insertBookingSql, Statement.RETURN_GENERATED_KEYS);
            bookingStmt.setInt(1, event.getEventId());
            bookingStmt.setInt(2, numTickets);
            bookingStmt.setBigDecimal(3, BigDecimal.valueOf(event.getTicketPrice() * numTickets));
            bookingStmt.setDate(4, Date.valueOf(LocalDate.now()));
            bookingStmt.executeUpdate();

            ResultSet bookingKeys = bookingStmt.getGeneratedKeys();
            int bookingId = 0;
            if (bookingKeys.next()) {
                bookingId = bookingKeys.getInt(1);
            } else {
                throw new SQLException("Booking ID not generated.");
            }

            String insertCustomerSql = "INSERT INTO Customer (customer_name, email, phone_number, booking_id) VALUES (?, ?, ?, ?)";
            PreparedStatement custStmt = conn.prepareStatement(insertCustomerSql, Statement.RETURN_GENERATED_KEYS);

            for (Customer customer : customers) {
                custStmt.setString(1, customer.getCustomerName());
                custStmt.setString(2, customer.getEmail());
                custStmt.setString(3, customer.getPhoneNumber());
                custStmt.setInt(4, bookingId);
                custStmt.executeUpdate();

                ResultSet custKeys = custStmt.getGeneratedKeys();
                if (custKeys.next()) {
                    customer.setCustomerId(custKeys.getInt(1));
                    customer.setBookingId(bookingId);
                }
            }

            String updateSeatsSql = "UPDATE Event SET available_seats = available_seats - ? WHERE event_id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSeatsSql);
            updateStmt.setInt(1, numTickets);
            updateStmt.setInt(2, event.getEventId());
            updateStmt.executeUpdate();

            booking = new Booking(bookingId, customers, event, numTickets);

        } catch (SQLException | DbConnectionException e) {
        	System.out.println(e.getMessage());
        }

        return booking;
    }



    @Override
    public void cancelBooking(int bookingId) throws InvalidBookingIDException {
        try {
            conn.setAutoCommit(false);

            String select = "SELECT * FROM Booking WHERE booking_id = ?";
            PreparedStatement sel = conn.prepareStatement(select);
            sel.setInt(1, bookingId);
            ResultSet rs = sel.executeQuery();

            if (!rs.next()) {
                throw new InvalidBookingIDException("Booking ID " + bookingId + " not found.");
            }

            int eventId = rs.getInt("event_id");
            int numTickets = rs.getInt("num_tickets");

            PreparedStatement delCustomers = conn.prepareStatement("DELETE FROM Customer WHERE booking_id = ?");
            delCustomers.setInt(1, bookingId);
            delCustomers.executeUpdate();

            PreparedStatement delBooking = conn.prepareStatement("DELETE FROM Booking WHERE booking_id = ?");
            delBooking.setInt(1, bookingId);
            delBooking.executeUpdate();

            PreparedStatement update = conn.prepareStatement("UPDATE Event SET available_seats = available_seats + ? WHERE event_id = ?");
            update.setInt(1, numTickets);
            update.setInt(2, eventId);
            update.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ignored) {
            }
            e.printStackTrace();
        }
    }

    @Override
    public Booking getBookingDetails(int bookingId) throws InvalidBookingIDException {
        Booking booking = null;

        String bookingSQL = "SELECT * FROM Booking WHERE booking_id = ?";
        String customerSQL = "SELECT * FROM Customer WHERE booking_id = ?";
        String eventSQL = "SELECT * FROM Event e JOIN Venu v ON e.venue_id = v.venue_id WHERE e.event_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(bookingSQL);
            ps.setInt(1, bookingId);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                throw new InvalidBookingIDException("No booking found with ID: " + bookingId);
            }

            int eventId = rs.getInt("event_id");
            int numTickets = rs.getInt("num_tickets");

            PreparedStatement ep = conn.prepareStatement(eventSQL);
            ep.setInt(1, eventId);
            ResultSet ers = ep.executeQuery();
            Event event = null;
            if (ers.next()) {
                Venue venue = new Venue(
                        ers.getInt("venue_id"),
                        ers.getString("venue_name"),
                        ers.getString("address")
                );
                LocalDate eventDate = ers.getDate("event_date").toLocalDate();
                LocalTime eventTime = ers.getTime("event_time").toLocalTime();

                event = new Event(
                    ers.getInt("event_id"),
                    ers.getString("event_name"),
                    eventDate,
                    eventTime,
                    venue,
                    ers.getInt("total_seats"),
                    ers.getInt("available_seats"),
                    ers.getDouble("ticket_price"),
                    ers.getString("event_type")
                );
            }

            PreparedStatement cp = conn.prepareStatement(customerSQL);
            cp.setInt(1, bookingId);
            ResultSet crs = cp.executeQuery();
            List<Customer> customers = new ArrayList<>();

            while (crs.next()) {
                customers.add(new Customer(
                        crs.getInt("customer_id"),
                        crs.getString("customer_name"),
                        crs.getString("email"),
                        crs.getString("phone_number")
                ));
            }

            booking = new Booking(bookingId, customers,event, numTickets);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return booking;
    }
    
    @Override
    public Event getEventByName(String eventName) throws EventNotFoundException {
        Event event = null;
        String query = "SELECT * FROM Event WHERE event_name = ?";

        try (Connection conn = DBConnection.getDbConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, eventName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int eventId = rs.getInt("event_id");
                LocalDate date = rs.getDate("event_date").toLocalDate();
                LocalTime time = rs.getTime("event_time").toLocalTime();
                int totalSeats = rs.getInt("total_seats");
                int availableSeats = rs.getInt("available_seats");
                double ticketPrice = rs.getDouble("ticket_price");
                String eventType = rs.getString("event_type");

                int venueId = rs.getInt("venue_id");
                Venue venue = getVenueById(venueId);

                event = new Event(eventId, eventName, date, time, venue, totalSeats, availableSeats, ticketPrice, eventType);
            } else {
                throw new EventNotFoundException("Event not found with name: " + eventName);
            }

        } catch (SQLException | DbConnectionException e) {
            e.printStackTrace();
        }

        return event;
    }

    public Venue getVenueById(int venueId) {
        Venue venue = null;
        String query = "SELECT * FROM Venu WHERE venue_id = ?";
        try (Connection conn = DBConnection.getDbConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, venueId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("venue_name");
                String address = rs.getString("address");
                venue = new Venue(venueId, name, address);
            }

        } catch (SQLException | DbConnectionException e) {
            e.printStackTrace();
        }

        return venue;
    }


}
