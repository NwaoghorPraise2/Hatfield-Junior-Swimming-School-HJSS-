package database;

import models.Booking;
import java.util.ArrayList;
import java.util.List;

/**
 * Database class responsible for managing bookings.
 */
public class BookingDB {
    private final List<Booking> bookings;
    private static BookingDB instance;

    /**
     * Retrieves the singleton instance of BookingDB.
     * @return The singleton instance of BookingDB.
     */
    public static BookingDB getInstance() {
        if (instance == null) {
            instance = new BookingDB();
        }
        return instance;
    }

    /**
     * Constructs a BookingDB instance.
     */
    public BookingDB() {
        bookings = new ArrayList<>();
    }

    /**
     * Adds a booking to the database.
     * @param booking The booking to be added.
     */
    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    /**
     * Retrieves all bookings stored in the database.
     * @return A list of all bookings.
     */
    public List<Booking> getBookings() {
        return bookings;
    }

    /**
     * Retrieves the number of bookings in the database.
     * @return The count of bookings.
     */
    public int getBookingCount() {
        return bookings.size();
    }

    /**
     * Retrieves bookings associated with a specific learner.
     * @param learnerId The ID of the learner.
     * @return A list of bookings associated with the learner.
     */
    public List<Booking> getBookingsByLearnerId(String learnerId) {
        List<Booking> filteredBookings = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getLearnerId().equals(learnerId)) {
                filteredBookings.add(booking);
            }
        }
        return filteredBookings;
    }

    /**
     * Retrieves bookings with a specific status.
     * @param status The status of the bookings.
     * @return A list of bookings with the specified status.
     */
    public List<Booking> getBookingsByStatus(String status) {
        List<Booking> filteredBookings = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getStatus().equals(status)) {
                filteredBookings.add(booking);
            }
        }
        return filteredBookings;
    }

    /**
     * Retrieves a booking by its ID.
     * @param bookingId The ID of the booking.
     * @return The booking with the specified ID, or null if not found.
     */
    public Booking getBookingByBookingId(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                return booking;
            }
        }
        return null;
    }

    /**
     * Retrieves all bookings stored in the database.
     * @return A list of all bookings.
     */
    public List<Booking> getAllBookings(){
        return bookings;
    }

}
