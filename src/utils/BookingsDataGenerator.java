package utils;
import controller.LessonController;
import database.BookingDB;
import models.Booking;
import models.Lesson;

import java.util.List;
import java.util.Random;

public class BookingsDataGenerator {
    private static BookingDB bookingDB;
    private static Random random;
    private LessonController lessonController;

    public BookingsDataGenerator() {
        this.bookingDB = BookingDB.getInstance();
        this.random = new Random();
        this.lessonController = new LessonController();
    }

    public void generateBookings() {
        for (int i = 0; i < 60; i++) {
            Booking booking = createRandomBooking();
            bookingDB.addBooking(booking);
        }
    }


    private Booking createRandomBooking() {
        String[] statuses = {"Booked", "Cancelled", "Attended"};
        int randomStatusIndex = random.nextInt(statuses.length);
        String randomStatus = statuses[randomStatusIndex];

        Booking booking = new Booking();
        booking.setStatus(randomStatus);
        booking.setBookingId(booking.generateBookingID());
        booking.setLesson(lessonController.getLessonRandomly());
        Lesson lesson = booking.getLesson();
        String[] bookings = lesson.getBookings();

        // Randomly pick a LearnerID from bookings[] array
        Random rand = new Random();
        String randomLearnerId = bookings[rand.nextInt(bookings.length)];

        // Set the randomly picked LearnerID to the booking object
        booking.setLearnerId(randomLearnerId);

        return booking;
    }


}
