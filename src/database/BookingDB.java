package database;

import models.Booking;
import models.Lesson;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class BookingDB {
    private final List<Booking> bookings;
    private static BookingDB instance;

    public static BookingDB getInstance() {
        if (instance == null) {
            instance = new BookingDB();
        }
        return instance;
    }

    public BookingDB() {
        bookings = new ArrayList<>();
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public int getBookingCount() {
        return bookings.size();
    }

    public List<Booking> getBookingsByLearnerId(String learnerId) {
        List<Booking> filteredBookings = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getLearnerId().equals(learnerId)) {
                filteredBookings.add(booking);
            }
        }
        return filteredBookings;
    }

    public List<Booking> getBookingsByStatus(String status) {
        List<Booking> filteredBookings = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.getStatus().equals(status)) {
                filteredBookings.add(booking);
            }
        }
        return filteredBookings;
    }

    public Booking getBookingByBookingId(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                return booking;
            }
        }
        return null;
    }

   public List<Booking> getAllBookings(){
        return bookings;
   }

}